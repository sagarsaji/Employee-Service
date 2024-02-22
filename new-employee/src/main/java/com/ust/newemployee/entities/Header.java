package com.ust.newemployee.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Header {

	@JsonProperty("_id")
	private String id;

	@JsonProperty("forwardAddr")
	private String forwardAddr;

	@JsonProperty("empName")
	private String empName;

	@JsonProperty("homeBranch")
	private String homeBranch;

	@JsonProperty("homeMachine")
	private String homeMachine;

	@JsonProperty("psoftEmpId")
	private String psoftEmpId;

	@JsonProperty("empInitials")
	private String empInitials;

	@JsonProperty("titleCode")
	private String titleCode;

	@JsonProperty("titleSuffix")
	private String titleSuffix;

	@JsonProperty("emailListFlag")
	private String emailListFlag;

	@JsonProperty("payrollBranchId")
	private String payrollBranchId;

	@JsonProperty("payrollWhseId")
	private String payrollWhseId;

	@JsonProperty("mailAcctName")
	private String mailAcctName;

	@JsonProperty("slaveType")
	private String slaveType;

	@JsonProperty("emailAttchFlag")
	private String emailAttchFlag;

	@JsonProperty("psoftDeptCode")
	private String psoftDeptCode;

	@JsonProperty("trilLogonId")
	private String trilLogonId;

	@JsonProperty("vpnAccessFlag")
	private String vpnAccessFlag;

	@JsonProperty("trilLog2Flag")
	private String trilLog2Flag;

	@JsonProperty("clientlessVpnFlag")
	private String clientlessVpnFlag;

	@JsonProperty("imAccessFlag")
	private String imAccessFlag;

	@JsonProperty("rightFaxId")
	private String rightFaxId;

	@JsonProperty("lastChgTd")
	private Date lastChgTd;

	@JsonProperty("name1")
	private String name1;

	@JsonProperty("name2")
	private String name2;

	@JsonProperty("name3")
	private String name3;

	@JsonProperty("name4")
	private String name4;

	@JsonProperty("trilAuthCode")
	private List<String> trilAuthCode;

	@JsonProperty("trilAuthLevel")
	private List<String> trilAuthLevel;

	@JsonProperty("empAcct")
	private List<String> empAcct;

	@JsonProperty("smtpAuth")
	private List<String> smtpAuth;

	@JsonProperty("smtpDomain")
	private List<String> smtpDomain;

	@JsonProperty("smtpUserId")
	private List<String> smtpUserId;

	@JsonProperty("smtpGateway")
	private List<String> smtpGateway;

	public Header(String id, String forwardAddr, String empName, String homeBranch, String homeMachine,
			String psoftEmpId, String empInitials, String titleCode, String titleSuffix, String emailListFlag,
			String payrollBranchId, String payrollWhseId, String mailAcctName, String slaveType, String emailAttchFlag,
			String psoftDeptCode, String trilLogonId, String vpnAccessFlag, String trilLog2Flag,
			String clientlessVpnFlag, String imAccessFlag, String rightFaxId, Date lastChgTd, String name1,
			String name2, String name3, String name4, List<String> trilAuthCode, List<String> trilAuthLevel,
			List<String> empAcct, List<String> smtpAuth, List<String> smtpDomain, List<String> smtpUserId,
			List<String> smtpGateway) {
		super();
		this.id = id;
		this.forwardAddr = forwardAddr;
		this.empName = empName;
		this.homeBranch = homeBranch;
		this.homeMachine = homeMachine;
		this.psoftEmpId = psoftEmpId;
		this.empInitials = empInitials;
		this.titleCode = titleCode;
		this.titleSuffix = titleSuffix;
		this.emailListFlag = emailListFlag;
		this.payrollBranchId = payrollBranchId;
		this.payrollWhseId = payrollWhseId;
		this.mailAcctName = mailAcctName;
		this.slaveType = slaveType;
		this.emailAttchFlag = emailAttchFlag;
		this.psoftDeptCode = psoftDeptCode;
		this.trilLogonId = trilLogonId;
		this.vpnAccessFlag = vpnAccessFlag;
		this.trilLog2Flag = trilLog2Flag;
		this.clientlessVpnFlag = clientlessVpnFlag;
		this.imAccessFlag = imAccessFlag;
		this.rightFaxId = rightFaxId;
		this.lastChgTd = lastChgTd;
		this.name1 = name1;
		this.name2 = name2;
		this.name3 = name3;
		this.name4 = name4;
		this.trilAuthCode = trilAuthCode;
		this.trilAuthLevel = trilAuthLevel;
		this.empAcct = empAcct;
		this.smtpAuth = smtpAuth;
		this.smtpDomain = smtpDomain;
		this.smtpUserId = smtpUserId;
		this.smtpGateway = smtpGateway;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getForwardAddr() {
		return forwardAddr;
	}

	public void setForwardAddr(String forwardAddr) {
		this.forwardAddr = forwardAddr;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getHomeBranch() {
		return homeBranch;
	}

	public void setHomeBranch(String homeBranch) {
		this.homeBranch = homeBranch;
	}

	public String getHomeMachine() {
		return homeMachine;
	}

	public void setHomeMachine(String homeMachine) {
		this.homeMachine = homeMachine;
	}

	public String getPsoftEmpId() {
		return psoftEmpId;
	}

	public void setPsoftEmpId(String psoftEmpId) {
		this.psoftEmpId = psoftEmpId;
	}

	public String getEmpInitials() {
		return empInitials;
	}

	public void setEmpInitials(String empInitials) {
		this.empInitials = empInitials;
	}

	public String getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	public String getTitleSuffix() {
		return titleSuffix;
	}

	public void setTitleSuffix(String titleSuffix) {
		this.titleSuffix = titleSuffix;
	}

	public String getEmailListFlag() {
		return emailListFlag;
	}

	public void setEmailListFlag(String emailListFlag) {
		this.emailListFlag = emailListFlag;
	}

	public String getPayrollBranchId() {
		return payrollBranchId;
	}

	public void setPayrollBranchId(String payrollBranchId) {
		this.payrollBranchId = payrollBranchId;
	}

	public String getPayrollWhseId() {
		return payrollWhseId;
	}

	public void setPayrollWhseId(String payrollWhseId) {
		this.payrollWhseId = payrollWhseId;
	}

	public String getMailAcctName() {
		return mailAcctName;
	}

	public void setMailAcctName(String mailAcctName) {
		this.mailAcctName = mailAcctName;
	}

	public String getSlaveType() {
		return slaveType;
	}

	public void setSlaveType(String slaveType) {
		this.slaveType = slaveType;
	}

	public String getEmailAttchFlag() {
		return emailAttchFlag;
	}

	public void setEmailAttchFlag(String emailAttchFlag) {
		this.emailAttchFlag = emailAttchFlag;
	}

	public String getPsoftDeptCode() {
		return psoftDeptCode;
	}

	public void setPsoftDeptCode(String psoftDeptCode) {
		this.psoftDeptCode = psoftDeptCode;
	}

	public String getTrilLogonId() {
		return trilLogonId;
	}

	public void setTrilLogonId(String trilLogonId) {
		this.trilLogonId = trilLogonId;
	}

	public String getVpnAccessFlag() {
		return vpnAccessFlag;
	}

	public void setVpnAccessFlag(String vpnAccessFlag) {
		this.vpnAccessFlag = vpnAccessFlag;
	}

	public String getTrilLog2Flag() {
		return trilLog2Flag;
	}

	public void setTrilLog2Flag(String trilLog2Flag) {
		this.trilLog2Flag = trilLog2Flag;
	}

	public String getClientlessVpnFlag() {
		return clientlessVpnFlag;
	}

	public void setClientlessVpnFlag(String clientlessVpnFlag) {
		this.clientlessVpnFlag = clientlessVpnFlag;
	}

	public String getImAccessFlag() {
		return imAccessFlag;
	}

	public void setImAccessFlag(String imAccessFlag) {
		this.imAccessFlag = imAccessFlag;
	}

	public String getRightFaxId() {
		return rightFaxId;
	}

	public void setRightFaxId(String rightFaxId) {
		this.rightFaxId = rightFaxId;
	}

	public Date getLastChgTd() {
		return lastChgTd;
	}

	public void setLastChgTd(Date lastChgTd) {
		this.lastChgTd = lastChgTd;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getName4() {
		return name4;
	}

	public void setName4(String name4) {
		this.name4 = name4;
	}

	public List<String> getTrilAuthCode() {
		return trilAuthCode;
	}

	public void setTrilAuthCode(List<String> trilAuthCode) {
		this.trilAuthCode = trilAuthCode;
	}

	public List<String> getTrilAuthLevel() {
		return trilAuthLevel;
	}

	public void setTrilAuthLevel(List<String> trilAuthLevel) {
		this.trilAuthLevel = trilAuthLevel;
	}

	public List<String> getEmpAcct() {
		return empAcct;
	}

	public void setEmpAcct(List<String> empAcct) {
		this.empAcct = empAcct;
	}

	public List<String> getSmtpAuth() {
		return smtpAuth;
	}

	public void setSmtpAuth(List<String> smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	public List<String> getSmtpDomain() {
		return smtpDomain;
	}

	public void setSmtpDomain(List<String> smtpDomain) {
		this.smtpDomain = smtpDomain;
	}

	public List<String> getSmtpUserId() {
		return smtpUserId;
	}

	public void setSmtpUserId(List<String> smtpUserId) {
		this.smtpUserId = smtpUserId;
	}

	public List<String> getSmtpGateway() {
		return smtpGateway;
	}

	public void setSmtpGateway(List<String> smtpGateway) {
		this.smtpGateway = smtpGateway;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientlessVpnFlag, emailAttchFlag, emailListFlag, empAcct, empInitials, empName,
				forwardAddr, homeBranch, homeMachine, id, imAccessFlag, lastChgTd, mailAcctName, name1, name2, name3,
				name4, payrollBranchId, payrollWhseId, psoftDeptCode, psoftEmpId, rightFaxId, slaveType, smtpAuth,
				smtpDomain, smtpGateway, smtpUserId, titleCode, titleSuffix, trilAuthCode, trilAuthLevel, trilLog2Flag,
				trilLogonId, vpnAccessFlag);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Header other = (Header) obj;
		return Objects.equals(clientlessVpnFlag, other.clientlessVpnFlag)
				&& Objects.equals(emailAttchFlag, other.emailAttchFlag)
				&& Objects.equals(emailListFlag, other.emailListFlag) && Objects.equals(empAcct, other.empAcct)
				&& Objects.equals(empInitials, other.empInitials) && Objects.equals(empName, other.empName)
				&& Objects.equals(forwardAddr, other.forwardAddr) && Objects.equals(homeBranch, other.homeBranch)
				&& Objects.equals(homeMachine, other.homeMachine) && Objects.equals(id, other.id)
				&& Objects.equals(imAccessFlag, other.imAccessFlag) && Objects.equals(lastChgTd, other.lastChgTd)
				&& Objects.equals(mailAcctName, other.mailAcctName) && Objects.equals(name1, other.name1)
				&& Objects.equals(name2, other.name2) && Objects.equals(name3, other.name3)
				&& Objects.equals(name4, other.name4) && Objects.equals(payrollBranchId, other.payrollBranchId)
				&& Objects.equals(payrollWhseId, other.payrollWhseId)
				&& Objects.equals(psoftDeptCode, other.psoftDeptCode) && Objects.equals(psoftEmpId, other.psoftEmpId)
				&& Objects.equals(rightFaxId, other.rightFaxId) && Objects.equals(slaveType, other.slaveType)
				&& Objects.equals(smtpAuth, other.smtpAuth) && Objects.equals(smtpDomain, other.smtpDomain)
				&& Objects.equals(smtpGateway, other.smtpGateway) && Objects.equals(smtpUserId, other.smtpUserId)
				&& Objects.equals(titleCode, other.titleCode) && Objects.equals(titleSuffix, other.titleSuffix)
				&& Objects.equals(trilAuthCode, other.trilAuthCode)
				&& Objects.equals(trilAuthLevel, other.trilAuthLevel)
				&& Objects.equals(trilLog2Flag, other.trilLog2Flag) && Objects.equals(trilLogonId, other.trilLogonId)
				&& Objects.equals(vpnAccessFlag, other.vpnAccessFlag);
	}

	public Header() {
		super();
	}

}
