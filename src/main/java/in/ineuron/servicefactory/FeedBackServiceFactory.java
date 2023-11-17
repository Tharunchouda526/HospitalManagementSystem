package in.ineuron.servicefactory;

import in.ineuron.service.FeedBackServiceImpl;
import in.ineuron.service.IFeedBackService;

//Abstraction
public class FeedBackServiceFactory 
{
	//make the constructor private to avoid object creation
	private  FeedBackServiceFactory ()
	{
		
	}
	private static IFeedBackService feedbackservice=null;
	public static IFeedBackService getFeedBackService()
	{
		if(feedbackservice==null)
		{
		feedbackservice=new FeedBackServiceImpl();
		}
		return feedbackservice;
		
	}

}
