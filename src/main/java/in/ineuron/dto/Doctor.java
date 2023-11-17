package in.ineuron.dto;

public class Doctor 
{
	   private Integer doctorId;
	   private  String doctorName;
	   private String doctorEmail;
	   private String doctorPassword;
	   private Integer doctorAge;
	   private String doctorGender;
	   private Long doctorPhno;
	   private String doctorSpecialization;
	   private String doctorAddress;
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorEmail() {
		return doctorEmail;
	}
	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}
	public String getDoctorPassword() {
		return doctorPassword;
	}
	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
	}
	public Integer getDoctorAge() {
		return doctorAge;
	}
	public void setDoctorAge(Integer doctorAge) {
		this.doctorAge = doctorAge;
	}
	public String getDoctorGender() {
		return doctorGender;
	}
	public void setDoctorGender(String doctorGender) {
		this.doctorGender = doctorGender;
	}
	public Long getDoctorPhno() {
		return doctorPhno;
	}
	public void setDoctorPhno(Long doctorPhno) {
		this.doctorPhno = doctorPhno;
	}
	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}
	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}
	public String getDoctorAddress() {
		return doctorAddress;
	}
	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", doctorEmail=" + doctorEmail
				+ ", doctorPassword=" + doctorPassword + ", doctorAge=" + doctorAge + ", doctorGender=" + doctorGender
				+ ", doctorPhno=" + doctorPhno + ", doctorSpecialization=" + doctorSpecialization + ", doctorAddress="
				+ doctorAddress + "]";
	}
	   
}