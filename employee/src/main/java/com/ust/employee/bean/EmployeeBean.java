package com.ust.employee.bean;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.ust.employee.entities.Employee;
import com.ust.employee.entities.Eventmeta;
import com.ust.employee.entities.Hashcode;
import com.ust.employee.entities.Header;

@Component
public class EmployeeBean {
	
	public void generateIdAndSettingProperty(Exchange exchange) {
		
		Employee emp = exchange.getIn().getBody(Employee.class);
		String id = emp.getEventMeta().getAccount()+"*"+emp.getEventMeta().getId();
		emp.setId(id);
		exchange.setProperty("_id", emp.getId());
		Eventmeta eventMeta = emp.getEventMeta();
		Header header = emp.getHeader();
		eventMeta.setiD(emp.getId());
		header.setId(emp.getId());
		exchange.setProperty("eventMeta", eventMeta);
		exchange.setProperty("header",header);
		
	}
	
	public void settingHashCode(Exchange exchange) {
		
		Hashcode hashcode = new Hashcode();
		Employee emp = exchange.getProperty("empdetails",Employee.class);
		hashcode.setId(emp.getId());
		hashcode.setEventMeta(emp.getEventMeta().hashCode());
		hashcode.setHeader(emp.getHeader().hashCode());
		exchange.getIn().setBody(hashcode);
		
	}

}
