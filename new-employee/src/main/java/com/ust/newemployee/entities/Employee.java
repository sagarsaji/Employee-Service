package com.ust.newemployee.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

public class Employee {

	@JsonProperty("_id")
	private String id;
	@JsonProperty("status")
	private Status status;
	@Valid
	@JsonProperty("eventMeta")
	private Eventmeta eventMeta;
	@JsonProperty("header")
	private Header header;
	@JsonProperty("branchAuth")
	private Branchauth branchAuth;

	public Employee(String id, Status status, Eventmeta eventMeta, Header header, Branchauth branchAuth) {
		super();
		this.id = id;
		this.status = status;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
		return Objects.hash(branchAuth, eventMeta, header, id, status);
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
				&& Objects.equals(header, other.header) && Objects.equals(id, other.id)
				&& Objects.equals(status, other.status);
	}

	public Employee() {
		super();
	}

}
