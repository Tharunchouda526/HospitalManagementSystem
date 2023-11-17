package in.ineuron.servicefactory;

import in.ineuron.service.IStaffService;
import in.ineuron.service.StaffServiceImpl;
//Abstraction
public class StaffServiceFactory 
{
	//make the constructor private to avoid object creation
	private  StaffServiceFactory ()
	{
		
	}
	private static IStaffService staffservice=null;
	public static IStaffService getStaffService()
	{
		if(staffservice==null)
		{
			staffservice=new StaffServiceImpl();
		}
		return staffservice;
		
	}

}
