package in.ineuron.service;

import in.ineuron.daofactory.AppointmentDaoFactory;
import in.ineuron.daofactory.PatientDaoFactory;
import in.ineuron.dto.Appointment;
import in.ineuron.dto.Patient;
import in.ineuron.persistence.IAppointmentDao;
import in.ineuron.persistence.IPatientDao;
import in.ineuron.servicefactory.PatientServiceFactory;


public class AppointmentServiceImpl implements IAppointmentService {
	private IAppointmentDao appointmentdao;
	@Override
	public String addAppointment(Appointment appointment) {
	  appointmentdao= AppointmentDaoFactory.getAppointmentDao();
	  return  appointmentdao.addAppointment(appointment);
	
	  
	}

	@Override
	public Appointment searchAppointment(Integer appointmentId) {
		 appointmentdao= AppointmentDaoFactory.getAppointmentDao();
		 return appointmentdao.searchAppointment(appointmentId);
		
		
		
	}

	@Override
	public String updateAppointment(Appointment appointment) {
		 appointmentdao= AppointmentDaoFactory.getAppointmentDao();
		return appointmentdao.updateAppointment(appointment);
		
		
	}

	@Override
	public String deleteAppointment(Integer appointmentId) {
		 appointmentdao= AppointmentDaoFactory.getAppointmentDao();
		return appointmentdao.deleteAppointment(appointmentId);
		
	}


}


