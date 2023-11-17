package in.ineuron.daofactory;

import in.ineuron.persistence.IDoctorDao;
import in.ineuron.persistence.IStaffDao;
import in.ineuron.persistence.StaffDaoImpl;
import in.ineuron.persistence.AdminDaoImpl;
import in.ineuron.persistence.DoctorDaoImpl;
import in.ineuron.persistence.IAdminDao;

public class AdminDaoFactory 
{
  private AdminDaoFactory ()
  {}
  
  private static  IAdminDao admindao=null;
  public static IAdminDao getAdminDao()
  {
	  if(admindao==null)
	  {
	  admindao=new AdminDaoImpl();
	  }
	  return admindao;
  }
}
