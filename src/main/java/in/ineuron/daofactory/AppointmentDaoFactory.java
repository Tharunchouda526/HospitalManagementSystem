package in.ineuron.daofactory;


import in.ineuron.persistence.AppointmentDaoImpl;
import in.ineuron.persistence.IAppointmentDao;

public class AppointmentDaoFactory 
{
  private AppointmentDaoFactory ()
  {}
  
  private static  IAppointmentDao appointmentdao=null;
  public static IAppointmentDao getAppointmentDao()
  {
	  if(appointmentdao==null)
	  {
	  appointmentdao=new AppointmentDaoImpl();
	  }
	  return appointmentdao;
  }
}
