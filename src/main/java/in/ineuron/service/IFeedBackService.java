package in.ineuron.service;

import in.ineuron.dto.FeedBack;
import in.ineuron.dto.Patient;

public interface IFeedBackService 
{
	   //operations to be implemented
		public String addFeedBack(FeedBack feedback);
		
		public FeedBack searchFeedBack(Integer number);
		
		
		
}
