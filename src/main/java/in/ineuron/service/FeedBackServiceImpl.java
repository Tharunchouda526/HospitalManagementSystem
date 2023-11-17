package in.ineuron.service;

import in.ineuron.daofactory.FeedBackDaoFactory;
import in.ineuron.daofactory.PatientDaoFactory;
import in.ineuron.dto.FeedBack;
import in.ineuron.dto.Patient;
import in.ineuron.persistence.IFeedBackDao;
import in.ineuron.persistence.IPatientDao;
import in.ineuron.servicefactory.PatientServiceFactory;

//Service Layer
public class FeedBackServiceImpl implements IFeedBackService {
	private IFeedBackDao feedbackdao;
	@Override
	public String addFeedBack(FeedBack feedback) {
	  feedbackdao= FeedBackDaoFactory.getFeedBackDao();
	  return feedbackdao.addFeedBack(feedback);
	
	  
	}

	@Override
	public FeedBack searchFeedBack(Integer number) {
		 feedbackdao= FeedBackDaoFactory.getFeedBackDao();
		 return feedbackdao.searchFeedBack(number);
		
		
		
		
	}

	


}


