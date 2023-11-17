package in.ineuron.servicefactory;

import in.ineuron.service.IPatientService;
import in.ineuron.service.IStaffLoginService;
import in.ineuron.service.IUserLoginService;
import in.ineuron.service.PatientServiceImpl;
import in.ineuron.service.StaffLoginServiceImpl;
import in.ineuron.service.UserLoginServiceImpl;
//Abstraction
public class StaffLoginServiceFactory 
{
	//make the constructor private to avoid object creation
	private  StaffLoginServiceFactory ()
	{
		
	}
	private static IStaffLoginService staffloginservice=null;
	public static IStaffLoginService getStaffLoginService()
	{
		if(staffloginservice==null)
		{
		staffloginservice=new StaffLoginServiceImpl();
		}
		return staffloginservice;
		
	}

}
