package in.ineuron.daofactory;

import in.ineuron.persistence.IDoctorDao;
import in.ineuron.persistence.DoctorDaoImpl;

public class DoctorDaoFactory 
{
  private DoctorDaoFactory ()
  {}
  
  private static  IDoctorDao doctordao=null;
  public static IDoctorDao getDoctorDao()
  {
	  if(doctordao==null)
	  {
	  doctordao=new DoctorDaoImpl();
	  }
	  return doctordao;
  }
}
