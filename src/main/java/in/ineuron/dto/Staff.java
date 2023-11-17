package in.ineuron.dto;

public class Staff 
{
	   private Integer staffId;
	   private  String staffName;
	   private String staffEmailno;
	   private String staffPassword;
	   private String staffGender;
	   private Long staffPhno;
	   private String staffAddress;
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffEmailno() {
		return staffEmailno;
	}
	public void setStaffEmailno(String staffEmailno) {
		this.staffEmailno = staffEmailno;
	}
	public String getStaffPassword() {
		return staffPassword;
	}
	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}
	public String getStaffGender() {
		return staffGender;
	}
	public void setStaffGender(String staffGender) {
		this.staffGender = staffGender;
	}
	public Long getStaffPhno() {
		return staffPhno;
	}
	public void setStaffPhno(Long staffPhno) {
		this.staffPhno = staffPhno;
	}
	public String getStaffAddress() {
		return staffAddress;
	}
	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}
	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffName=" + staffName + ", staffEmailno=" + staffEmailno
				+ ", staffPassword=" + staffPassword + ", staffGender=" + staffGender + ", staffPhno=" + staffPhno
				+ ", staffAddress=" + staffAddress + "]";
	}
	   
	   
}