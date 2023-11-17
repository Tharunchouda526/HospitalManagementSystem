package in.ineuron.service;

import in.ineuron.daofactory.AdminDaoFactory;
import in.ineuron.daofactory.DoctorDaoFactory;
import in.ineuron.daofactory.StaffDaoFactory;
import in.ineuron.dto.Admin;
import in.ineuron.dto.Doctor;
import in.ineuron.dto.Staff;
import in.ineuron.persistence.IAdminDao;
import in.ineuron.persistence.IDoctorDao;
import in.ineuron.persistence.IStaffDao;
import in.ineuron.servicefactory.DoctorServiceFactory;

//Service Layer
public class AdminServiceImpl implements IAdminService {
	private IAdminDao admindao;
	@Override
	public String addAdmin(Admin admin) {
		admindao= AdminDaoFactory.getAdminDao();
	  return admindao.addAdmin(admin);
	
	  
	

	}


}


