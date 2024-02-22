package com.ust.newemployee.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {

	@JsonProperty("eventMeta")
	private String eventMeta;
	@JsonProperty("header")
	private String header;

	public Status(String eventMeta, String header) {
		super();
		this.eventMeta = eventMeta;
		this.header = header;
	}

	public String getEventMeta() {
		return eventMeta;
	}

	public void setEventMeta(String eventMeta) {
		this.eventMeta = eventMeta;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Status() {
		super();
	}

}
