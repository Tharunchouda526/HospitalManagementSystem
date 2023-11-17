package in.ineuron.servicefactory;

import in.ineuron.service.IPatientService;
import in.ineuron.service.PatientServiceImpl;
//Abstraction
public class PatientServiceFactory 
{
	//make the constructor private to avoid object creation
	private  PatientServiceFactory ()
	{
		
	}
	private static IPatientService patientservice=null;
	public static IPatientService getPatientService()
	{
		if(patientservice==null)
		{
		patientservice=new PatientServiceImpl();
		}
		return patientservice;
		
	}

}
