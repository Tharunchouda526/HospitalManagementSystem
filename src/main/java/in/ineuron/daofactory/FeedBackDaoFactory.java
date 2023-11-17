package in.ineuron.daofactory;

import in.ineuron.persistence.FeedBackDaoImpl;
import in.ineuron.persistence.IFeedBackDao;

import in.ineuron.persistence.PatientDaoImpl;

public class FeedBackDaoFactory 
{
  private FeedBackDaoFactory ()
  {}
  
  private static  IFeedBackDao feedbackdao=null;
  public static IFeedBackDao getFeedBackDao()
  {
	  if(feedbackdao==null)
	  {
	  feedbackdao=new FeedBackDaoImpl();
	  }
	  return feedbackdao;
  }
}
