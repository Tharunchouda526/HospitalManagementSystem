package in.ineuron.servicefactory;

import in.ineuron.service.AdminServiceImpl;
import in.ineuron.service.IAdminService;
import in.ineuron.service.IStaffService;
import in.ineuron.service.StaffServiceImpl;
//Abstraction
public class AdminServiceFactory 
{
	//make the constructor private to avoid object creation
	private  AdminServiceFactory ()
	{
		
	}
	private static IAdminService adminservice=null;
	public static IAdminService getAdminService()
	{
		if(adminservice==null)
		{
			adminservice=new AdminServiceImpl();
		}
		return adminservice;
		
	}

}
