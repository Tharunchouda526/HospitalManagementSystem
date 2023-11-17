package in.ineuron.servicefactory;

import in.ineuron.service.AdminLoginServiceImpl;
import in.ineuron.service.DoctorLoginServiceImpl;
import in.ineuron.service.IAdminLoginService;
import in.ineuron.service.IDoctorLoginService;
import in.ineuron.service.IPatientService;
import in.ineuron.service.IUserLoginService;
import in.ineuron.service.PatientServiceImpl;
import in.ineuron.service.UserLoginServiceImpl;
//Abstraction
public class DoctorLoginServiceFactory 
{
	//make the constructor private to avoid object creation
	private  DoctorLoginServiceFactory ()
	{
		
	}
	private static IDoctorLoginService doctorloginservice=null;
	public static IDoctorLoginService getDoctorLoginService()
	{
		if(doctorloginservice==null)
		{
		doctorloginservice=new DoctorLoginServiceImpl();
		}
		return doctorloginservice;
		
	}

}
