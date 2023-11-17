package in.ineuron.daofactory;

import in.ineuron.persistence.IStaffLoginDao;
import in.ineuron.persistence.IUserLoginDao;
import in.ineuron.persistence.StaffLoginDaoImpl;
import in.ineuron.persistence.UserLoginDaoImpl;

public class StaffLoginDaoFactory 
{
  private StaffLoginDaoFactory ()
  {}
  
  private static  IStaffLoginDao stafflogindao=null;
  public static IStaffLoginDao getStaffLoginDao()
  {
	  if(stafflogindao==null)
	  {
	  stafflogindao=new StaffLoginDaoImpl();
	  }
	  return stafflogindao;
  }

}
