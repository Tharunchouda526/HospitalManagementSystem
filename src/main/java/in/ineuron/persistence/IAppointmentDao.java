package in.ineuron.persistence;

import in.ineuron.dto.Appointment;

public interface IAppointmentDao 
{
  //operations to be implemented
	public String addAppointment(Appointment appointment);
	
	public Appointment searchAppointment(Integer appointmentId);
	
	public String updateAppointment(Appointment appointment);
	
	public String deleteAppointment(Integer appointmentId);
}
