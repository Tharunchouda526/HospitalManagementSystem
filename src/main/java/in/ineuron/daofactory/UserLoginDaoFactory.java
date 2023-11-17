package in.ineuron.daofactory;

import in.ineuron.persistence.IUserLoginDao;
import in.ineuron.persistence.UserLoginDaoImpl;

public class UserLoginDaoFactory 
{
  private UserLoginDaoFactory ()
  {}
  
  private static  IUserLoginDao userlogindao=null;
  public static IUserLoginDao getUserLoginDao()
  {
	  if(userlogindao==null)
	  {
	  userlogindao=new UserLoginDaoImpl();
	  }
	  return userlogindao;
  }

}
