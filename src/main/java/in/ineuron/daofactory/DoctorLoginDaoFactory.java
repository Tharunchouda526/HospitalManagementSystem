package in.ineuron.daofactory;

import in.ineuron.persistence.DoctorLoginDaoImpl;
import in.ineuron.persistence.IDoctorLoginDao;

public class DoctorLoginDaoFactory 
{
  private DoctorLoginDaoFactory ()
  {}
  
  private static  IDoctorLoginDao doctorlogindao=null;
  public static IDoctorLoginDao getDoctorLoginDao()
  {
	  if(doctorlogindao==null)
	  {
	  doctorlogindao=new DoctorLoginDaoImpl();
	  }
	  return doctorlogindao;
  }

}
