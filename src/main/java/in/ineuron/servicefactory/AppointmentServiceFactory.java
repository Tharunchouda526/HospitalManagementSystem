package in.ineuron.servicefactory;

import in.ineuron.service.IDoctorService;
import in.ineuron.service.AppointmentServiceImpl;
import in.ineuron.service.DoctorServiceImpl;
import in.ineuron.service.IAppointmentService;
//Abstraction
public class AppointmentServiceFactory 
{
	//make the constructor private to avoid object creation
	private  AppointmentServiceFactory ()
	{
		
	}
	private static IAppointmentService appointmentservice=null;
	public static IAppointmentService getAppointmentService()
	{
		if(appointmentservice==null)
		{
			appointmentservice=new AppointmentServiceImpl();
		}
		return appointmentservice;
		
	}

}
