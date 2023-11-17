package in.ineuron.servicefactory;

import in.ineuron.service.AdminLoginServiceImpl;
import in.ineuron.service.IAdminLoginService;
import in.ineuron.service.IPatientService;
import in.ineuron.service.IUserLoginService;
import in.ineuron.service.PatientServiceImpl;
import in.ineuron.service.UserLoginServiceImpl;
//Abstraction
public class AdminLoginServiceFactory 
{
	//make the constructor private to avoid object creation
	private  AdminLoginServiceFactory ()
	{
		
	}
	private static IAdminLoginService adminloginservice=null;
	public static IAdminLoginService getAdminLoginService()
	{
		if(adminloginservice==null)
		{
		adminloginservice=new AdminLoginServiceImpl();
		}
		return adminloginservice;
		
	}

}
