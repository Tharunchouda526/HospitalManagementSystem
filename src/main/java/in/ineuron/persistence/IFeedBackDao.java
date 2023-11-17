package in.ineuron.persistence;

import in.ineuron.dto.Doctor;
import in.ineuron.dto.FeedBack;
import in.ineuron.dto.Staff;

public interface IFeedBackDao 
{
  //operations to be implemented
	public String addFeedBack(FeedBack feedback);
	
	public FeedBack searchFeedBack(Integer number);
	
	
}
