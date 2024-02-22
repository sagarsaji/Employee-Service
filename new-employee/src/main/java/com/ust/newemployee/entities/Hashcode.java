package com.ust.newemployee.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hashcode {

	@JsonProperty("_id")
	private String id;
	@JsonProperty("eventMeta")
	private int eventMeta;
	@JsonProperty("header")
	private int header;

	public Hashcode(String id, int eventMeta, int header) {
		super();
		this.id = id;
		this.eventMeta = eventMeta;
		this.header = header;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getEventMeta() {
		return eventMeta;
	}

	public void setEventMeta(int eventMeta) {
		this.eventMeta = eventMeta;
	}

	public int getHeader() {
		return header;
	}

	public void setHeader(int header) {
		this.header = header;
	}

	public Hashcode() {
		super();
	}

}
