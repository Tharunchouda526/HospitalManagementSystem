package in.ineuron.service;

import in.ineuron.dto.Doctor;

public interface IDoctorService 
{
	   //operations to be implemented
		public String addDoctor(Doctor doctor);
		
		public Doctor searchDoctor(Integer doctorId);
		
		public String updateDoctor(Doctor doctor);
	
		public String deleteDoctor(Integer doctorId);

		
}
