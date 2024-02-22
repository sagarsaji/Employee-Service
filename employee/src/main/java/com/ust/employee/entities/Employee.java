package com.ust.employee.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

	@JsonProperty("_id")
	private String id;
	@JsonProperty("eventMeta")
	private Eventmeta eventMeta;
	@JsonProperty("header")
	private Header header;
	@JsonProperty("branchAuth")
	private Branchauth branchAuth;

	public Employee(String id, Eventmeta eventMeta, Header header, Branchauth branchAuth) {
		super();
		this.id = id;
		this.eventMeta = eventMeta;
		this.header = header;
		this.branchAuth = branchAuth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Eventmeta getEventMeta() {
		return eventMeta;
	}

	public void setEventMeta(Eventmeta eventMeta) {
		this.eventMeta = eventMeta;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Branchauth getBranchAuth() {
		return branchAuth;
	}

	public void setBranchAuth(Branchauth branchAuth) {
		this.branchAuth = branchAuth;
	}

	@Override
	public int hashCode() {
		return Objects.hash(branchAuth, eventMeta, header, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(branchAuth, other.branchAuth) && Objects.equals(eventMeta, other.eventMeta)
				&& Objects.equals(header, other.header) && Objects.equals(id, other.id);
	}

	public Employee() {
		super();
	}

}
