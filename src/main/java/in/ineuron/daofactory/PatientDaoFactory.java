package in.ineuron.daofactory;

import in.ineuron.persistence.IPatientDao;
import in.ineuron.persistence.PatientDaoImpl;

public class PatientDaoFactory 
{
  private PatientDaoFactory ()
  {}
  
  private static  IPatientDao patientdao=null;
  public static IPatientDao getPatientDao()
  {
	  if(patientdao==null)
	  {
	  patientdao=new PatientDaoImpl();
	  }
	  return patientdao;
  }
}
