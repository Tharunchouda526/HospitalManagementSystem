package in.ineuron.persistence;

import in.ineuron.dto.Doctor;
import in.ineuron.dto.Staff;

public interface IStaffDao 
{
  //operations to be implemented
	public String addStaff(Staff staff);
	
	public Staff searchStaff(Integer staffId);
	
	public String updateStaff(Staff staff);
	
	public String deleteStaff(Integer staffId);
}
