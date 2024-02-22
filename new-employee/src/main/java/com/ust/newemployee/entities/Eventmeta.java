package com.ust.newemployee.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Eventmeta {

	@JsonProperty("_id")
	private String iD;
	@JsonProperty("collecionVersion")
	private String collecionVersion;
	@JsonProperty("account")
	@NotBlank(message = "Account field should not be blank")
	@NotNull(message = "Account field should not be null")
	private String account;
	@JsonProperty("id")
	@NotBlank(message = "Id field should not be blank")
	@NotNull(message = "Id field should not be null")
	private String id;
	@JsonProperty("type")
	private String type;
	@JsonProperty("machineName")
	private String machineName;
	@JsonProperty("syncid")
	private long syncid;
	@JsonProperty("expireDate")
	private String expireDate;

	public Eventmeta(String iD, String collecionVersion, String account, String id, String type, String machineName,
			long syncid, String expireDate) {
		super();
		this.iD = iD;
		this.collecionVersion = collecionVersion;
		this.account = account;
		this.id = id;
		this.type = type;
		this.machineName = machineName;
		this.syncid = syncid;
		this.expireDate = expireDate;
	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	public String getCollecionVersion() {
		return collecionVersion;
	}

	public void setCollecionVersion(String collecionVersion) {
		this.collecionVersion = collecionVersion;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public long getSyncid() {
		return syncid;
	}

	public void setSyncid(long syncid) {
		this.syncid = syncid;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, collecionVersion, expireDate, iD, id, machineName, syncid, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eventmeta other = (Eventmeta) obj;
		return Objects.equals(account, other.account) && Objects.equals(collecionVersion, other.collecionVersion)
				&& Objects.equals(expireDate, other.expireDate) && Objects.equals(iD, other.iD)
				&& Objects.equals(id, other.id) && Objects.equals(machineName, other.machineName)
				&& syncid == other.syncid && Objects.equals(type, other.type);
	}

	public Eventmeta() {
		super();
	}

}
