package in.ineuron.dto;

public class Patient 
{
   private Integer patientId;
   private  String patientName;
   private String patientEmailno;
   private String patientPassword;
   private Integer patientAge;
   private String patientGender;
   private Long patientPhno;
   private String patientAddress;
   private String patientBloodGroup;
public Integer getPatientId() {
	return patientId;
}
public void setPatientId(Integer patientId) {
	this.patientId = patientId;
}
public String getPatientName() {
	return patientName;
}
public void setPatientName(String patientName) {
	this.patientName = patientName;
}
public String getPatientEmailno() {
	return patientEmailno;
}
public void setPatientEmailno(String patientEmailno) {
	this.patientEmailno = patientEmailno;
}
public String getPatientPassword() {
	return patientPassword;
}
public void setPatientPassword(String patientPassword) {
	this.patientPassword = patientPassword;
}
public Integer getPatientAge() {
	return patientAge;
}
public void setPatientAge(Integer patientAge) {
	this.patientAge = patientAge;
}
public String getPatientGender() {
	return patientGender;
}
public void setPatientGender(String patientGender) {
	this.patientGender = patientGender;
}
public Long getPatientPhno() {
	return patientPhno;
}
public void setPatientPhno(Long patientPhno) {
	this.patientPhno = patientPhno;
}
public String getPatientAddress() {
	return patientAddress;
}
public void setPatientAddress(String patientAddress) {
	this.patientAddress = patientAddress;
}
public String getPatientBloodGroup() {
	return patientBloodGroup;
}
public void setPatientBloodGroup(String patientBloodGroup) {
	this.patientBloodGroup = patientBloodGroup;
}
@Override
public String toString() {
	return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", patientEmailno=" + patientEmailno
			+ ", patientPassword=" + patientPassword + ", patientAge=" + patientAge + ", patientGender=" + patientGender
			+ ", patientPhno=" + patientPhno + ", patientAddress=" + patientAddress + ", patientBloodGroup="
			+ patientBloodGroup + "]";
}
   
}