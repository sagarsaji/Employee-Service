package com.ust.newemployee.bean;

import org.apache.camel.Exchange;
import org.apache.camel.component.bean.validator.BeanValidationException;
import org.bson.Document;
import org.springframework.stereotype.Component;

import com.ust.newemployee.entities.Employee;
import com.ust.newemployee.entities.Eventmeta;
import com.ust.newemployee.entities.Hashcode;
import com.ust.newemployee.entities.Header;
import com.ust.newemployee.entities.Message;
import com.ust.newemployee.entities.Status;

@Component
public class EmployeeBean {

	public void generateIdAndSettingProperty(Exchange exchange) {

		Employee emp = exchange.getIn().getBody(Employee.class);
		String id = emp.getEventMeta().getAccount() + "*" + emp.getEventMeta().getId();
		emp.setId(id);
		exchange.setProperty("_id", emp.getId());
		emp.getEventMeta().setiD(emp.getId());
		emp.getHeader().setId(emp.getId());
		exchange.setProperty("eventMeta", emp.getEventMeta());
		exchange.setProperty("header", emp.getHeader());
		exchange.setProperty("eventMetaHash", emp.getEventMeta().hashCode());
		exchange.setProperty("headerHash", emp.getHeader().hashCode());

	}

	public void settingStatusForFirstEntry(Exchange exchange) {

		Employee emp = exchange.getIn().getBody(Employee.class);
		Status status = new Status();

		status.setEventMeta("eventMeta added to collection and hashcode updated");
		status.setHeader("header added to collection and hashcode updated");

		emp.setStatus(status);
		exchange.getIn().setBody(emp);

	}

	public void removeIdFromEventmetaAndHeader(Exchange exchange) {

		Document doc = exchange.getIn().getBody(Document.class);
		Document eventMeta = exchange.getProperty("eventMeta", Document.class);
		eventMeta.remove("_id");
		doc.put("eventMeta", eventMeta);

		Document header = exchange.getProperty("header", Document.class);
		header.remove("_id");
		doc.put("header", header);

		exchange.getIn().setBody(doc);

	}

	public void updateHashCode(Exchange exchange) {

		Hashcode hashcode = new Hashcode();
		Employee emp = exchange.getProperty("empdetails", Employee.class);
		hashcode.setId(emp.getId());
		hashcode.setEventMeta(emp.getEventMeta().hashCode());
		hashcode.setHeader(emp.getHeader().hashCode());
		exchange.getIn().setBody(hashcode);

	}

	public void settingStatusIfNoChange(Exchange exchange) {

		Employee emp = exchange.getIn().getBody(Employee.class);
		Status status = new Status();
		status.setEventMeta("No change in data");
		status.setHeader("No change in data");
		emp.setStatus(status);
		exchange.getIn().setBody(emp);

	}

	public void settingStatusIfBothChange(Exchange exchange) {

		Employee emp = exchange.getIn().getBody(Employee.class);
		emp.getStatus().setEventMeta("Eventmeta collection and hashcode updated");
		emp.getStatus().setHeader("Header collection and hashcode updated");
		exchange.getIn().setBody(emp);

	}

	public void settingStatusIfEventmetaChange(Exchange exchange) {

		Employee emp = exchange.getProperty("employeeFetchedToObj", Employee.class);
		emp.getStatus().setEventMeta("Hashcode updated. Eventmeta collection updated");
		emp.getStatus().setHeader("No change in data");
		exchange.getIn().setBody(emp);

	}

	public void updateEventmeta(Exchange exchange) {

		Employee emp = exchange.getProperty("empdetails", Employee.class);
		Eventmeta eventMeta = emp.getEventMeta();
		eventMeta.setiD(emp.getId());
		exchange.getIn().setBody(eventMeta);

	}

	public void updateEventmetaHashCode(Exchange exchange) {

		Hashcode hashcode = exchange.getProperty("hashcodebody", Hashcode.class);
		Employee emp = exchange.getProperty("empdetails", Employee.class);
		hashcode.setEventMeta(emp.getEventMeta().hashCode());
		exchange.getIn().setBody(hashcode);

	}

	public void settingStatusIfHeaderChange(Exchange exchange) {

		Employee emp = exchange.getProperty("employeeFetchedToObj", Employee.class);
		emp.getStatus().setHeader("Hashcode updated. Header collection updated");
		emp.getStatus().setEventMeta("No change in data");
		exchange.getIn().setBody(emp);

	}

	public void updateHeader(Exchange exchange) {

		Employee emp = exchange.getProperty("empdetails", Employee.class);
		Header header = emp.getHeader();
		header.setId(emp.getId());
		exchange.getIn().setBody(header);

	}

	public void updateHeaderHashCode(Exchange exchange) {

		Hashcode hashcode = exchange.getProperty("hashcodebody", Hashcode.class);
		Employee emp = exchange.getProperty("empdetails", Employee.class);
		hashcode.setHeader(emp.getHeader().hashCode());
		exchange.getIn().setBody(hashcode);

	}

	public void updateEventmetaAndHeaderHashcode(Exchange exchange) {

		Hashcode hashcode = exchange.getProperty("hashcodebody", Hashcode.class);
		Employee emp = exchange.getProperty("empdetails", Employee.class);
		hashcode.setEventMeta(emp.getEventMeta().hashCode());
		hashcode.setHeader(emp.getHeader().hashCode());
		exchange.getIn().setBody(hashcode);

	}

	public void updateEventmetaAndHeader(Exchange exchange) {

		Employee emp = exchange.getProperty("empdetails", Employee.class);
		Eventmeta eventMeta = emp.getEventMeta();
		Header header = emp.getHeader();
		eventMeta.setiD(emp.getId());
		header.setId(emp.getId());
		exchange.setProperty("headerUpdate", header);
		exchange.getIn().setBody(eventMeta);

	}

	public void validationMessage(Exchange exchange) {

		Message message = new Message();
		String exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, BeanValidationException.class).getMessage();
		message.setMessage(exception);
		exchange.getIn().setBody(message);

	}

	public void insertionMessage(Exchange exchange) {

		Message message = new Message();
		message.setMessage("Details saved into collections successfully and hashcode saved.");
		exchange.getIn().setBody(message);

	}

	public void sameDataMessage(Exchange exchange) {

		Message message = new Message();
		message.setMessage("No change in data");
		exchange.getIn().setBody(message);

	}

	public void differentDataMessage(Exchange exchange) {

		Message message = new Message();
		message.setMessage("Eventmeta and header collections updated and hashcode updated.");
		exchange.getIn().setBody(message);

	}

	public void differentEventmetaMessage(Exchange exchange) {

		Message message = new Message();
		message.setMessage("Eventmeta collection updated and hashcode updated.");
		exchange.getIn().setBody(message);

	}

	public void differentHeaderMessage(Exchange exchange) {

		Message message = new Message();
		message.setMessage("Header collection updated and hashcode updated.");
		exchange.getIn().setBody(message);

	}

}
