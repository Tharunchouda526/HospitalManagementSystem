package in.ineuron.service;

import in.ineuron.daofactory.DoctorDaoFactory;
import in.ineuron.daofactory.StaffDaoFactory;
import in.ineuron.dto.Doctor;
import in.ineuron.dto.Staff;
import in.ineuron.persistence.IDoctorDao;
import in.ineuron.persistence.IStaffDao;
import in.ineuron.servicefactory.DoctorServiceFactory;

//Service Layer
public class StaffServiceImpl implements IStaffService {
	private IStaffDao staffdao;
	@Override
	public String addStaff(Staff staff) {
		staffdao= StaffDaoFactory.getStaffDao();
	  return staffdao.addStaff(staff);
	
	  
	}

	@Override
	public Staff searchStaff(Integer  staffId) {
		staffdao=StaffDaoFactory.getStaffDao();
		return staffdao.searchStaff( staffId);
		
		
		
	}

	@Override
	public String updateStaff(Staff staff) {
		staffdao=StaffDaoFactory.getStaffDao();
		return staffdao.updateStaff(staff);
		
		
	}

	@Override
	public String deleteStaff(Integer  staffId) {
		staffdao=StaffDaoFactory.getStaffDao();
		return staffdao.deleteStaff( staffId);
		
	}


}


