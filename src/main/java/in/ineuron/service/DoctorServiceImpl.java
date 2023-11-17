package in.ineuron.service;

import in.ineuron.daofactory.DoctorDaoFactory;
import in.ineuron.dto.Doctor;
import in.ineuron.persistence.IDoctorDao;
import in.ineuron.servicefactory.DoctorServiceFactory;

//Service Layer
public class DoctorServiceImpl implements IDoctorService {
	private IDoctorDao doctordao;
	@Override
	public String addDoctor(Doctor doctor) {
		doctordao= DoctorDaoFactory.getDoctorDao();
	  return doctordao.addDoctor(doctor);
	
	  
	}

	@Override
	public Doctor searchDoctor(Integer  doctorId) {
		doctordao=DoctorDaoFactory.getDoctorDao();
		return doctordao.searchDoctor( doctorId);
		
		
		
	}

	@Override
	public String updateDoctor(Doctor doctor) {
		doctordao=DoctorDaoFactory.getDoctorDao();
		return doctordao.updateDoctor(doctor);
		
		
	}

	@Override
	public String deleteDoctor(Integer  doctorId) {
		doctordao=DoctorDaoFactory.getDoctorDao();
		return doctordao.deleteDoctor( doctorId);
		
	}


}


