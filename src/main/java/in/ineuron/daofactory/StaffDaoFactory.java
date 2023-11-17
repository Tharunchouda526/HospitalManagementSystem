package in.ineuron.daofactory;

import in.ineuron.persistence.IDoctorDao;
import in.ineuron.persistence.IStaffDao;
import in.ineuron.persistence.StaffDaoImpl;
import in.ineuron.persistence.DoctorDaoImpl;

public class StaffDaoFactory 
{
  private StaffDaoFactory ()
  {}
  
  private static  IStaffDao staffdao=null;
  public static IStaffDao getStaffDao()
  {
	  if(staffdao==null)
	  {
	  staffdao=new StaffDaoImpl();
	  }
	  return staffdao;
  }
}
