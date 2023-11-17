package in.ineuron.dto;

public class Admin 
{
	   private Integer adminId;
	   private  String adminName;
	   private String adminEmailno;
	   private String adminPassword;
	   private String adminGender;
	   private Long adminPhno;
	   private String adminAddress;
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminEmailno() {
		return adminEmailno;
	}
	public void setAdminEmailno(String adminEmailno) {
		this.adminEmailno = adminEmailno;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getAdminGender() {
		return adminGender;
	}
	public void setAdminGender(String adminGender) {
		this.adminGender = adminGender;
	}
	public Long getAdminPhno() {
		return adminPhno;
	}
	public void setAdminPhno(Long adminPhno) {
		this.adminPhno = adminPhno;
	}
	public String getAdminAddress() {
		return adminAddress;
	}
	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminEmailno=" + adminEmailno
				+ ", adminPassword=" + adminPassword + ", adminGender=" + adminGender + ", adminPhno=" + adminPhno
				+ ", adminAddress=" + adminAddress + "]";
	}
	   
	   
	
	   
}