package in.ineuron.dto;

import java.time.LocalDateTime;

public class Appointment 
{
	   private Integer appointmentId;
	   private  Integer patientId;
	   private String patientName;
	   private Integer patientAge;
	   private String patientGender;
	   private Integer doctorId;
	   private String doctorName;
	   private LocalDateTime DateTime;
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId ) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
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
	public LocalDateTime getDateTime() {
		return DateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		DateTime = dateTime;
	}
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", patientId=" + patientId + ", patientName="
				+ patientName + ", patientAge=" + patientAge + ", patientGender=" + patientGender + ", doctorId="
				+ doctorId + ", doctorName=" + doctorName + ", DateTime=" + DateTime + "]";
	}

	
	
}
	