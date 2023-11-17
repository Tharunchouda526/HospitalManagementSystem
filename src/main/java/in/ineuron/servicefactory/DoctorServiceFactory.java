package in.ineuron.servicefactory;

import in.ineuron.service.IDoctorService;
import in.ineuron.service.DoctorServiceImpl;
//Abstraction
public class DoctorServiceFactory 
{
	//make the constructor private to avoid object creation
	private  DoctorServiceFactory ()
	{
		
	}
	private static IDoctorService doctorservice=null;
	public static IDoctorService getDoctorService()
	{
		if(doctorservice==null)
		{
			doctorservice=new DoctorServiceImpl();
		}
		return doctorservice;
		
	}

}
