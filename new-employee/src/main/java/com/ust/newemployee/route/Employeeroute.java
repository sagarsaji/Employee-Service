package com.ust.newemployee.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.bean.validator.BeanValidationException;
import org.apache.camel.component.mongodb.MongoDbConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ust.newemployee.bean.EmployeeBean;
import com.ust.newemployee.constants.BeanClassConstants;
import com.ust.newemployee.constants.EmployeeConstants;
import com.ust.newemployee.constants.PropertyConstant;
import com.ust.newemployee.constants.RouteIdConstants;
import com.ust.newemployee.entities.Employee;
import com.ust.newemployee.entities.Hashcode;

@Component
public class Employeeroute extends RouteBuilder {

	@Autowired
	private EmployeeBean employeeBean;

	@Value("${camel.mongodb.database}")
	private String employeedb;

	@Value("${camel.mongodb.collection1}")
	private String employee;

	@Value("${camel.mongodb.collection2}")
	private String eventMeta;

	@Value("${camel.mongodb.collection3}")
	private String header;

	@Value("${camel.mongodb.collection4}")
	private String hashcode;

	@Override
	public void configure() throws Exception {

		onException(BeanValidationException.class).handled(true).setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
				.bean(employeeBean, BeanClassConstants.VALIDATION_MESSAGE).marshal().json().log("${body}");

		onException(Throwable.class).handled(true).setBody(constant("{{error.unknownServerError}}")).log("${body}");

		rest()

				.post("/newEmployee").to(EmployeeConstants.ADD_NEW_EMPLOYEE);

		from(EmployeeConstants.ADD_NEW_EMPLOYEE).routeId(RouteIdConstants.ADD_NEW_EMPLOYEE).unmarshal()
				.json(JsonLibrary.Jackson, Employee.class).to(EmployeeConstants.BEAN_VALIDATOR)
				.bean(employeeBean, BeanClassConstants.GENERATE_ID_AND_SETTING_PROPERTIES)
				.setProperty(PropertyConstant.EMPLOYEE_DETAILS, body()).to(EmployeeConstants.EMPLOYEE_FIND_BY_ID)
				.choice().when(body().isNull()).to(EmployeeConstants.INSERT_INTO_COLLECTIONS)
				.bean(employeeBean, BeanClassConstants.INSERTION_MESSAGE).marshal().json().log("${body}").otherwise()
				.log("employee present. so check for changes")
				.setProperty(PropertyConstant.EMPLOYEE_FETCHED_FROM_DB, body())
				.to(EmployeeConstants.CHECK_HASHCODE_IN_COLLECTION).to(EmployeeConstants.CONVERT_TO_HASHCODE_OBJECT)
				.log("id found. check for hashcode")
				.to(EmployeeConstants.SETTING_PROPERTIES_FOR_HASHCODE_EVENTMETA_HEADER)
				.setBody(exchangeProperty(PropertyConstant.EMPLOYEE_FETCHED_FROM_DB))
				.to(EmployeeConstants.CONVERT_TO_EMPLOYEE_OBJECT)
				.setProperty(PropertyConstant.FETCHED_EMP_TO_EMPLOYEE_OBJECT, body())
				.to(EmployeeConstants.CHECK_EVENTMETA_HEADER_SAME).to(EmployeeConstants.COMPARING_HASHCODES).end();

		from(EmployeeConstants.INSERT_INTO_COLLECTIONS).routeId(RouteIdConstants.INSERT_INTO_COLLECTION)
				.setBody(exchangeProperty(PropertyConstant.EMPLOYEE_DETAILS))
				.bean(employeeBean, BeanClassConstants.SETTING_STATUS_FOR_FIRST_ENTRY)
				.bean(employeeBean, BeanClassConstants.REMOVE_ID_FROM_EVENTMETA_HEADER)
				.to(EmployeeConstants.INSERT_INTO_EMPLOYEE_DB).to(EmployeeConstants.INSERT_INTO_EVENTMETA_DB)
				.to(EmployeeConstants.INSERT_INTO_HEADER_DB).bean(employeeBean, BeanClassConstants.UPDATE_HASHCODE)
				.to(EmployeeConstants.INSERT_INTO_HASHCODE_DB);

		from(EmployeeConstants.CHECK_EVENTMETA_HEADER_SAME).routeId(RouteIdConstants.CHECKING_EVENTMETA_HEADER_SAME)
				.choice()
				.when(simple(
						"${exchangeProperty.eventMetaHash} == ${exchangeProperty.empeventMeta} && ${exchangeProperty.headerHash} == ${exchangeProperty.empHeader}"))
				.log("no change in data").setBody(exchangeProperty(PropertyConstant.FETCHED_EMP_TO_EMPLOYEE_OBJECT))
				.bean(employeeBean, BeanClassConstants.SETTING_STATUS_IF_NO_CHANGE)
				.bean(employeeBean, BeanClassConstants.REMOVE_ID_FROM_EVENTMETA_HEADER)
				.to(EmployeeConstants.UPDATE_EMPLOYEE_COLLECTION)
				.bean(employeeBean, BeanClassConstants.NOCHANGE_MESSAGE).marshal().json().log("${body}").end();

		from(EmployeeConstants.COMPARING_HASHCODES).routeId(RouteIdConstants.COMPARING_HASHCODES).choice().when(simple(
				"${exchangeProperty.eventMetaHash} != ${exchangeProperty.empeventMeta} && ${exchangeProperty.headerHash} != ${exchangeProperty.empHeader}"))
				.to(EmployeeConstants.DIFFERENT_EVENTMETA_HEADER)
				.when(exchangeProperty(PropertyConstant.EVENTMETA_HASHCODE_FROM_PAYLOAD)
						.isNotEqualTo(exchangeProperty(PropertyConstant.EVENTMETA_HASHCODE_FROM_HASHCODE_COLLECTION)))
				.to(EmployeeConstants.DIFFERENT_EVENTMETA)
				.when(exchangeProperty(PropertyConstant.HEADER_HASHCODE_FROM_PAYLOAD)
						.isNotEqualTo(exchangeProperty(PropertyConstant.HEADER_HASHCODE_FROM_HASHCODE_COLLECTION)))
				.to(EmployeeConstants.DIFFERENT_HEADER).end();

		from(EmployeeConstants.DIFFERENT_EVENTMETA_HEADER).routeId(RouteIdConstants.DIFFERENT_EVENTMETA_HEADER)
				.log("both data has changed").setBody(exchangeProperty(PropertyConstant.FETCHED_EMP_TO_EMPLOYEE_OBJECT))
				.bean(employeeBean, BeanClassConstants.SETTING_STATUS_IF_BOTH_CHANGE)
				.bean(employeeBean, BeanClassConstants.REMOVE_ID_FROM_EVENTMETA_HEADER)
				.to(EmployeeConstants.UPDATE_EMPLOYEE_COLLECTION)
				.bean(employeeBean, BeanClassConstants.UPDATE_EVENTMETA_HEADER)
				.to(EmployeeConstants.UPDATE_EVENTMETA_COLLECTION)
				.setBody(exchangeProperty(PropertyConstant.UPDATE_HEADER))
				.to(EmployeeConstants.UPDATE_HEADER_COLLECTION)
				.bean(employeeBean, BeanClassConstants.UPDATE_EVENTMETA_HEADER_HASHCODE)
				.to(EmployeeConstants.UPDATE_HASHCODE_COLLECTION)
				.bean(employeeBean, BeanClassConstants.DIFFERENT_MESSAGE).marshal().json().log("${body}");

		from(EmployeeConstants.INSERT_INTO_EMPLOYEE_DB).routeId(RouteIdConstants.INSERT_INTO_EMPLOYEE_DB)
				.to("mongodb:employeedb?database=" + employeedb + "&collection=" + employee + "&operation=insert")
				.log("employee added successfully...");

		from(EmployeeConstants.INSERT_INTO_EVENTMETA_DB).routeId(RouteIdConstants.INSERT_INTO_EVENTMETA_DB)
				.setBody(exchangeProperty(PropertyConstant.EVENTMETA_BODY))
				.to("mongodb:employeedb?database=" + employeedb + "&collection=" + eventMeta + "&operation=insert")
				.log("eventMeta added successfully...");

		from(EmployeeConstants.INSERT_INTO_HEADER_DB).routeId(RouteIdConstants.INSERT_INTO_HEADER_DB)
				.setBody(exchangeProperty(PropertyConstant.HEADER_BODY))
				.to("mongodb:employeedb?database=" + employeedb + "&collection=" + header + "&operation=insert")
				.log("header added successfully...");

		from(EmployeeConstants.INSERT_INTO_HASHCODE_DB).routeId(RouteIdConstants.INSERT_INTO_HASHCODE_DB)
				.to("mongodb:employeedb?database=" + employeedb + "&collection=" + hashcode + "&operation=insert")
				.log("hashcode updated successfully...");

		from(EmployeeConstants.UPDATE_EVENTMETA_COLLECTION).routeId(RouteIdConstants.UPDATE_EVENTMETA_COLLECTION)
				.to("mongodb:employeedb?database=" + employeedb + "&collection=" + eventMeta + "&operation=save")
				.log("eventMeta collection updated...");

		from(EmployeeConstants.UPDATE_HEADER_COLLECTION).routeId(RouteIdConstants.UPDATE_HEADER_COLLECTION)
				.to("mongodb:employeedb?database=" + employeedb + "&collection=" + header + "&operation=save")
				.log("header collection updated...");

		from(EmployeeConstants.UPDATE_HASHCODE_COLLECTION).routeId(RouteIdConstants.UPDATE_HASHCODE_COLLECTION)
				.to("mongodb:employeedb?database=" + employeedb + "&collection=" + hashcode + "&operation=save")
				.log("hashcode collection updated...");

		from(EmployeeConstants.UPDATE_EMPLOYEE_COLLECTION).routeId(RouteIdConstants.UPDATE_EMPLOYEE_COLLECTION)
				.to("mongodb:employeedb?database=" + employeedb + "&collection=" + employee + "&operation=save")
				.log("employee collection updated...");

		from(EmployeeConstants.DIFFERENT_EVENTMETA).routeId(RouteIdConstants.CHANGE_IN_EVENTMETA)
				.log("change in eventMeta hashcode. so change data")
				.bean(employeeBean, BeanClassConstants.SETTING_STATUS_IF_EVENTMETA_CHANGE)
				.bean(employeeBean, BeanClassConstants.REMOVE_ID_FROM_EVENTMETA_HEADER)
				.to(EmployeeConstants.UPDATE_EMPLOYEE_COLLECTION)
				.bean(employeeBean, BeanClassConstants.UPDATE_EVENTMETA)
				.to(EmployeeConstants.UPDATE_EVENTMETA_COLLECTION)
				.bean(employeeBean, BeanClassConstants.UPDATE_EVENTMETA_HASHCODE)
				.to(EmployeeConstants.UPDATE_HASHCODE_COLLECTION)
				.bean(employeeBean, BeanClassConstants.DIFFERENT_EVENTMETA_MESSAGE).marshal().json().log("${body}");

		from(EmployeeConstants.DIFFERENT_HEADER).routeId(RouteIdConstants.CHANGE_IN_HEADER)
				.log("header hashcode has changed. so update data")
				.bean(employeeBean, BeanClassConstants.SETTING_STATUS_IF_HEADER_CHANGE)
				.bean(employeeBean, BeanClassConstants.REMOVE_ID_FROM_EVENTMETA_HEADER)
				.to(EmployeeConstants.UPDATE_EMPLOYEE_COLLECTION).bean(employeeBean, BeanClassConstants.UPDATE_HEADER)
				.to(EmployeeConstants.UPDATE_HEADER_COLLECTION)
				.bean(employeeBean, BeanClassConstants.UPDATE_HEADER_HASHCODE)
				.to(EmployeeConstants.UPDATE_HASHCODE_COLLECTION)
				.bean(employeeBean, BeanClassConstants.DIFFERENT_HEADER_MESSAGE).marshal().json().log("${body}");

		from(EmployeeConstants.CHECK_HASHCODE_IN_COLLECTION).routeId(RouteIdConstants.CHECK_HASHCODE_IN_COLLECTION)
				.setHeader(MongoDbConstants.CRITERIA, simple("{ \"_id\": '${exchangeProperty._id}' }"))
				.to("mongodb:employeedb?database=" + employeedb + "&collection=" + hashcode
						+ "&operation=findOneByQuery");

		from(EmployeeConstants.EMPLOYEE_FIND_BY_ID).routeId(RouteIdConstants.EMPLOYEE_FIND_BY_ID)
				.setBody(exchangeProperty(PropertyConstant.EMPLOYEE_ID))
				.to("mongodb:employeedb?database=" + employeedb + "&collection=" + employee + "&operation=findById");

		from(EmployeeConstants.CONVERT_TO_HASHCODE_OBJECT).routeId(RouteIdConstants.CONVERT_TO_HASHCODE_OBJECT)
				.marshal().json().unmarshal().json(JsonLibrary.Jackson, Hashcode.class);

		from(EmployeeConstants.CONVERT_TO_EMPLOYEE_OBJECT).routeId(RouteIdConstants.CONVERT_TO_EMPLOYEE_OBJECT)
				.marshal().json().unmarshal().json(JsonLibrary.Jackson, Employee.class);

		from(EmployeeConstants.SETTING_PROPERTIES_FOR_HASHCODE_EVENTMETA_HEADER)
				.routeId(RouteIdConstants.SETTING_PROPERTIES_FOR_HASHCODE_EVENTMETA_HEADER)
				.setProperty(PropertyConstant.HASHCODE_FETCHED_FROM_DB, body())
				.setProperty(PropertyConstant.EVENTMETA_HASHCODE_FROM_HASHCODE_COLLECTION, simple("${body.eventMeta}"))
				.setProperty(PropertyConstant.HEADER_HASHCODE_FROM_HASHCODE_COLLECTION, simple("${body.header}"));

	}

}
