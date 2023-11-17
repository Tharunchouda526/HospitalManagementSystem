package in.ineuron.daofactory;

import in.ineuron.persistence.AdminLoginDaoImpl;
import in.ineuron.persistence.IAdminLoginDao;
import in.ineuron.persistence.IUserLoginDao;
import in.ineuron.persistence.UserLoginDaoImpl;

public class AdminLoginDaoFactory 
{
  private AdminLoginDaoFactory ()
  {}
  
  private static  IAdminLoginDao adminlogindao=null;
  public static IAdminLoginDao getAdminLoginDao()
  {
	  if(adminlogindao==null)
	  {
	  adminlogindao=new AdminLoginDaoImpl();
	  }
	  return adminlogindao;
  }

}
