package com.ust.employee.route;

import java.util.Arrays;

import java.util.List;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mongodb.MongoDbConstants;
import org.apache.camel.model.dataformat.JsonLibrary;

import org.springframework.stereotype.Component;

import com.mongodb.client.model.Projections;
import com.ust.employee.entities.Employee;
import com.ust.employee.entities.Eventmeta;
import com.ust.employee.entities.Hashcode;
import com.ust.employee.entities.Header;
import com.ust.employee.exception.UserNotFoundException;

@Component
public class EmployeeRoute extends RouteBuilder {

	final List<String> includedFields = Arrays.asList("_id", "header.empName","header.trilLogonId", "header.psoftEmpId",
			"header.trilLog2Flag", "header.trilAuthCode", "header.trilAuthLevel",
			"header.empAcct", "branchAuth.authCodeArea", "branchAuth.authCodeLevel", "branchAuth.branchName");

	@Override
	public void configure() throws Exception {

		onException(UserNotFoundException.class).handled(true).log("User not found").setBody(constant("User Not Found"))
				.marshal().json().setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404));

		rest()

				.get("/workday/{id}").to("direct:findByWorkdayId")

				.get("/associate/{id}").to("direct:findByAssociateId")
				
				.post("/newEmployee").to("direct:newEmployee");
		
		
		
		
		from("direct:newEmployee")
			.unmarshal().json(JsonLibrary.Jackson,Employee.class)
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					
					Employee emp = exchange.getIn().getBody(Employee.class);
					String id = emp.getEventMeta().getAccount()+"*"+emp.getEventMeta().getId();
					emp.setId(id);
					exchange.setProperty("_id", emp.getId());
					emp.getEventMeta().setiD(emp.getId());
					emp.getHeader().setId(emp.getId());
					exchange.setProperty("eventMeta", emp.getEventMeta().hashCode());
					exchange.setProperty("header",emp.getHeader().hashCode());
					
				}
			})
			.setProperty("empdetails",body())
			.setBody(exchangeProperty("_id"))
			.to("mongodb:employeedb?database=employeedb&collection=employee&operation=findById")
			.log("${body}")
			.choice()
				.when(body().isNull())
					.log("employee not present. add employee")
					.setBody(exchangeProperty("empdetails"))
					.process(new Processor() {
						
						@Override
						public void process(Exchange exchange) throws Exception {
							
							Employee emp = exchange.getIn().getBody(Employee.class);
							Eventmeta eventMeta = emp.getEventMeta();
							Header header = emp.getHeader();
							eventMeta.setiD(emp.getId());
							header.setId(emp.getId());
							exchange.setProperty("employeeEventMeta", eventMeta);
							exchange.setProperty("employeeHeader", header);
						}
					})
					.to("mongodb:employeedb?database=employeedb&collection=employee&operation=insert")
					.log("employee added successfully...")
					.setBody(exchangeProperty("employeeEventMeta"))
					.to("mongodb:employeedb?database=employeedb&collection=eventMeta&operation=insert")
					.log("eventMeta added successfully...")
					.setBody(exchangeProperty("employeeHeader"))
					.to("mongodb:employeedb?database=employeedb&collection=header&operation=insert")
					.log("header added successfully...")
					.process(new Processor() {
						
						@Override
						public void process(Exchange exchange) throws Exception {
							
							Hashcode hashcode = new Hashcode();
							Employee emp = exchange.getProperty("empdetails",Employee.class);
							hashcode.setId(emp.getId());
							hashcode.setEventMeta(emp.getEventMeta().hashCode());
							hashcode.setHeader(emp.getHeader().hashCode());
							exchange.getIn().setBody(hashcode);
							
						}
					})
					.to("mongodb:employeedb?database=employeedb&collection=hashcode&operation=insert")
					.log("hashcode updated successfully...")
				.otherwise()
					.log("employee present. check for any changes in hashcode")
					.setBody(exchangeProperty("empdetails"))
					.to("mongodb:employeedb?database=employeedb&collection=employee&operation=save")
					.log("employee saved")
					.setHeader(MongoDbConstants.CRITERIA, simple("{ \"_id\": '${exchangeProperty._id}' }"))
					.to("mongodb:employeedb?database=employeedb&collection=hashcode&operation=findOneByQuery")
					.marshal().json()
					.unmarshal().json(JsonLibrary.Jackson,Hashcode.class)
					.choice()
						.when(body().isNotNull())
							.log("id present. check for hashcode")
							.setProperty("hashcodebody",body())
							.setProperty("empeventMeta",simple("${body.eventMeta}"))
							.setProperty("empHeader",simple("${body.header}"))
							.choice()
								.when(exchangeProperty("eventMeta").isEqualTo(exchangeProperty("empeventMeta")))
									.log("eventMeta is same. no need to change")
								.otherwise()
									.log("change in eventMeta. so change")
									.process(new Processor() {
										
										@Override
										public void process(Exchange exchange) throws Exception {
											
											Employee emp = exchange.getProperty("empdetails",Employee.class);
											Eventmeta eventMeta = emp.getEventMeta();
											eventMeta.setiD(emp.getId());
											exchange.getIn().setBody(eventMeta);
											
											
										}
									})
									.to("mongodb:employeedb?database=employeedb&collection=eventMeta&operation=save")
									.log("eventMeta updated successfully....")
									.process(new Processor() {
										
										@Override
										public void process(Exchange exchange) throws Exception {
											
											Hashcode hashcode = exchange.getProperty("hashcodebody",Hashcode.class);
											Employee emp = exchange.getProperty("empdetails",Employee.class);
											hashcode.setEventMeta(emp.getEventMeta().hashCode());
											exchange.getIn().setBody(hashcode);
										}
									})
									.to("mongodb:employeedb?database=employeedb&collection=hashcode&operation=save")
									.log("hashcode updated...")
							.endChoice()
							.choice()
								.when(exchangeProperty("header").isEqualTo(exchangeProperty("empHeader")))
									.log("header is same. no need to change")
								.otherwise()
									.log("change is header. so change")
									.process(new Processor() {
										
										@Override
										public void process(Exchange exchange) throws Exception {
											
											Employee emp = exchange.getProperty("empdetails",Employee.class);
											Header header = emp.getHeader();
											header.setId(emp.getId());
											exchange.getIn().setBody(header);
											
											
										}
									})
									.to("mongodb:employeedb?database=employeedb&collection=header&operation=save")
									.log("Header updated successfully....")
									.process(new Processor() {
										
										@Override
										public void process(Exchange exchange) throws Exception {
											
											Hashcode hashcode = exchange.getProperty("hashcodebody",Hashcode.class);
											Employee emp = exchange.getProperty("empdetails",Employee.class);
											hashcode.setHeader(emp.getHeader().hashCode());
											exchange.getIn().setBody(hashcode);
										}
									})
									.to("mongodb:employeedb?database=employeedb&collection=hashcode&operation=save")
									.log("hashcode updated...")
							.endChoice()
						.otherwise()
							.log("incorrect id")	
					.endChoice()
			.end();
			
			

			
		
		
		
		
		
		
		
		
		
		

		from("direct:findByWorkdayId").setHeader(MongoDbConstants.CRITERIA)
				.simple("{ \"header.psoftEmpId\": { \"$in\": ['${header.id}'] } }").to("direct:projectionAndException");

		from("direct:findByAssociateId")
				.setHeader(MongoDbConstants.CRITERIA,
						simple("{ \"header.trilLogonId\": { \"$in\": ['${header.id}'] } }"))
				.to("direct:projectionAndException");

		from("direct:projectionAndException")
				.setHeader(MongoDbConstants.FIELDS_PROJECTION, constant(Projections.include(includedFields)))
				.to("mongodb:employee?database=employeedb&collection=employee&operation=findOneByQuery").choice()
				.when(body().isNull()).throwException(new UserNotFoundException("User not found")).otherwise()
				.log("User found").marshal().json().end();

	}

}
