package in.ineuron.service;

import in.ineuron.daofactory.PatientDaoFactory;
import in.ineuron.dto.Patient;
import in.ineuron.persistence.IPatientDao;
import in.ineuron.servicefactory.PatientServiceFactory;

//Service Layer
public class PatientServiceImpl implements IPatientService {
	private IPatientDao patientdao;
	@Override
	public String addPatient(Patient patient) {
	  patientdao= PatientDaoFactory.getPatientDao();
	  return patientdao.addPatient(patient);
	
	  
	}

	@Override
	public Patient searchPatient(Integer patientId) {
		patientdao=PatientDaoFactory.getPatientDao();
		return patientdao.searchPatient(patientId);
		
		
		
	}

	@Override
	public String updatePatient(Patient patient) {
		patientdao=PatientDaoFactory.getPatientDao();
		return patientdao.updatePatient(patient);
		
		
	}

	@Override
	public String deletePatient(Integer patientId) {
		patientdao=PatientDaoFactory.getPatientDao();
		return patientdao.deletePatient(patientId);
		
	}


}


