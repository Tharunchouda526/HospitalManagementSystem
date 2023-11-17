package in.ineuron.ControllerServlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.FeedBack;
import in.ineuron.dto.Patient;
import in.ineuron.service.IFeedBackService;
import in.ineuron.service.IPatientService;
import in.ineuron.servicefactory.FeedBackServiceFactory;
import in.ineuron.servicefactory.PatientServiceFactory;


@WebServlet("/FeedBack/*")
public class FeedBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 doProcess(request, response);
	}
	 private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	        IFeedBackService feedbackService = FeedBackServiceFactory.getFeedBackService();

	        System.out.println("Request URI::" + request.getRequestURI());
	        System.out.println("Path info::" + request.getPathInfo());
	        FeedBack feedback =new FeedBack();
	        if (request.getRequestURI().endsWith("addform_feedback")) {
	            String name = request.getParameter("name");
	            String email = request.getParameter("email");
	            String message = request.getParameter("message");
	          

	          
	            feedback.setName(name);
	            feedback.setEmail(email);
	            feedback.setMessage(message);
	           
	            String status = feedbackService.addFeedBack(feedback);
	            PrintWriter out = response.getWriter();

	            if (status.equals("success")) {
	                out.println("<h1 style='color:green; text-align:center;'>FEEDBACK SUCCESSFUL</h1>");
	            } else {
	                out.println("<h1 style='color:red; text-align:center;'>FEEDBACK FAILED</h1>");
	            }
	            out.close();
	        }

	        if (request.getRequestURI().endsWith("searchform_feedback")) {
	        	String number = request.getParameter("number");
	        	
	        	feedback = feedbackService.searchFeedBack(Integer.parseInt(number));
	        	PrintWriter out = response.getWriter();

	            if (feedback != null) {
	                out.println("<body>");
	                out.println("<br/><br/><br/>");
	                out.println("<center>");
	                out.println("<table border='1'>");
	                out.println("<tr><th>ID</th><td>" + feedback.getNumber() + "</td></tr>");
	                out.println("<tr><th>NAME</th><td>" + feedback.getName() + "</td></tr>");
	                out.println("<tr><th>EMAIL</th><td>" + feedback.getEmail() + "</td></tr>");
	                out.println("<tr><th>MESSAGE</th><td>" + feedback.getMessage() + "</td></tr>");
	               
	                out.println("</table>");
	                out.println("</center>");
	                out.println("</body>");
	            } else {
	                out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID " + number + "</h1>");
	            }
	            out.close();
	        }
	        if (request.getRequestURI().endsWith("searchform_feedbackform")) {
	        	String number = request.getParameter("number");
	        	
	        	
	        	feedback = feedbackService.searchFeedBack(Integer.parseInt(number));
	        	PrintWriter out = response.getWriter();

	            if (feedback != null) {
	            	out.println("<html>");
	            	out.println("<head>");
	            	out.println("<style>");
	            	out.println("table {");
	            	out.println("    font-family: Arial, sans-serif;");
	            	out.println("    border-collapse: collapse;");
	            	out.println("    width: 100%;");
	            	out.println("    background-color: #f2f2f2;");
	            	out.println("}");
	            	out.println("th, td {");
	            	out.println("    border: 1px solid #dddddd;");
	            	out.println("    text-align: left;");
	            	out.println("    padding: 8px;");
	            	out.println("}");
	            	out.println("tr:nth-child(even) {");
	            	out.println("    background-color: #ffffff;"); // Background color for even rows
	            	out.println("}");
	            	out.println("tr:nth-child(odd) {");
	            	out.println("    background-color: #f2f2f2;"); // Background color for odd rows
	            	out.println("}");
	            	out.println("th {");
	            	out.println("    background-color: #4CAF50;"); // Header background color
	            	out.println("    color: white;");
	            	out.println("}");
	            	out.println("</style>");
	            	out.println("</head>");
	            	out.println("<body>");
	            	out.println("<br/><br/><br/>");
	            	out.println("<center>");
	            	out.println("<table>");
	            	out.println("<tr>");
	            	out.println("<th>ID</th><td style='background-color: #4CAF50; color: white;'>" + feedback.getNumber() + "</td>");
	            	out.println("<th>NAME</th><td>" + feedback.getName() + "</td>");
	            	out.println("<th>EMAIL</th><td>" + feedback.getEmail() + "</td>");
	            	out.println("<th>MESSAGE</th><td>" + feedback.getMessage() + "</td>");
	            		            	out.println("</tr>");
	            	out.println("</table>");
	            	out.println("</center>");
	            	out.println("</body>");
	            	out.println("</html>");



	            } else {
	                out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID " + number + "</h1>");
	            }
	            out.close();
	        }
	       
	        
	 }
}