package in.ineuron.persistence;

import in.ineuron.dto.Patient;

public interface IPatientDao 
{
  //operations to be implemented
	public String addPatient(Patient patient);
	
	public Patient searchPatient(Integer pid);
	
	public String updatePatient(Patient patient);
	
	public String deletePatient(Integer pid);
	
	
}
