package in.ineuron.servicefactory;

import in.ineuron.service.IPatientService;
import in.ineuron.service.IUserLoginService;
import in.ineuron.service.PatientServiceImpl;
import in.ineuron.service.UserLoginServiceImpl;
//Abstraction
public class UserLoginServiceFactory 
{
	//make the constructor private to avoid object creation
	private  UserLoginServiceFactory ()
	{
		
	}
	private static IUserLoginService userloginservice=null;
	public static IUserLoginService getUserLoginService()
	{
		if(userloginservice==null)
		{
		userloginservice=new UserLoginServiceImpl();
		}
		return userloginservice;
		
	}

}
