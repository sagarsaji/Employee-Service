package com.ust.newemployee.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Branchauth {

	@JsonProperty("authCodeArea")
	private List<String> authCodeArea;
	@JsonProperty("authCodeLevel")
	private List<String> authCodeLevel;
	@JsonProperty("branchName")
	private List<String> branchName;
	@JsonProperty("defaultWhse")
	private List<String> defaultWhse;
	@JsonProperty("defaultQueue")
	private List<String> defaultQueue;

	public Branchauth(List<String> authCodeArea, List<String> authCodeLevel, List<String> branchName,
			List<String> defaultWhse, List<String> defaultQueue) {
		super();
		this.authCodeArea = authCodeArea;
		this.authCodeLevel = authCodeLevel;
		this.branchName = branchName;
		this.defaultWhse = defaultWhse;
		this.defaultQueue = defaultQueue;
	}

	public List<String> getAuthCodeArea() {
		return authCodeArea;
	}

	public void setAuthCodeArea(List<String> authCodeArea) {
		this.authCodeArea = authCodeArea;
	}

	public List<String> getAuthCodeLevel() {
		return authCodeLevel;
	}

	public void setAuthCodeLevel(List<String> authCodeLevel) {
		this.authCodeLevel = authCodeLevel;
	}

	public List<String> getBranchName() {
		return branchName;
	}

	public void setBranchName(List<String> branchName) {
		this.branchName = branchName;
	}

	public List<String> getDefaultWhse() {
		return defaultWhse;
	}

	public void setDefaultWhse(List<String> defaultWhse) {
		this.defaultWhse = defaultWhse;
	}

	public List<String> getDefaultQueue() {
		return defaultQueue;
	}

	public void setDefaultQueue(List<String> defaultQueue) {
		this.defaultQueue = defaultQueue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authCodeArea, authCodeLevel, branchName, defaultQueue, defaultWhse);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branchauth other = (Branchauth) obj;
		return Objects.equals(authCodeArea, other.authCodeArea) && Objects.equals(authCodeLevel, other.authCodeLevel)
				&& Objects.equals(branchName, other.branchName) && Objects.equals(defaultQueue, other.defaultQueue)
				&& Objects.equals(defaultWhse, other.defaultWhse);
	}

	public Branchauth() {
		super();
	}

}
