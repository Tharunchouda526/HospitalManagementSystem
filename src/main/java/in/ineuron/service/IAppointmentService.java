package in.ineuron.service;

import in.ineuron.dto.Appointment;
import in.ineuron.dto.Doctor;

public interface IAppointmentService 
{
	   
		public String addAppointment(Appointment appointment);
		
		public Appointment searchAppointment(Integer appointmentId);
		
		public String updateAppointment(Appointment appointment);
		
		public String deleteAppointment(Integer appointmentId);
	

		
}
