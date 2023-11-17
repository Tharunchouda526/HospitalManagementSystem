package in.ineuron.ControllerServlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Patient;
import in.ineuron.service.IPatientService;
import in.ineuron.servicefactory.PatientServiceFactory;


@WebServlet("/Patient/*")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ,NullPointerException {
		
		 try {
			doProcess(request, response);
		} catch (IOException | ServletException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,NullPointerException {
		
		 try {
			doProcess(request, response);
		} catch (IOException | ServletException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException,NullPointerException {
	        IPatientService patientService = PatientServiceFactory.getPatientService();
	        Connection connection=null;
	        PreparedStatement pstmt=null;
	        ResultSet res=null;
	        
	        System.out.println("Request URI::" + request.getRequestURI());
	        System.out.println("Path info::" + request.getPathInfo());
	        Patient patient=new Patient();
	        if (request.getRequestURI().endsWith("addform_patient")) {
	            String patientName = request.getParameter("patientName");
	            String patientEmailno = request.getParameter("patientEmailno");
	            String patientPassword = request.getParameter("patientPassword");
	            String patientAge = request.getParameter("patientAge");
	            String patientGender = request.getParameter("patientGender");
	            String patientPhno = request.getParameter("patientPhno");
	            String patientAddress = request.getParameter("patientAddress");
	            String patientBloodGroup = request.getParameter("patientBloodGroup");
	            

	            patient = new Patient();
	            patient.setPatientName(patientName);
	            patient.setPatientEmailno(patientEmailno);
	            patient.setPatientPassword(patientPassword);
	            patient.setPatientAge(Integer.parseInt(patientAge));
	            patient.setPatientGender(patientGender);
	            patient.setPatientPhno(Long.parseLong(patientPhno));
	            patient.setPatientAddress(patientAddress);
	            patient.setPatientBloodGroup(patientBloodGroup);

	            String status = patientService.addPatient(patient);
	            PrintWriter out = response.getWriter();

	            if (status.equals("success")) {
	                out.println("<h1 style='color:green; text-align:center;'>PATIENT REGISTRATION SUCCESSFUL</h1>");
	            } else {
	                out.println("<h1 style='color:red; text-align:center;'>PATIENT REGISTRATION FAILED</h1>");
	            }
	            out.close();
	        }

	        if (request.getRequestURI().endsWith("searchform_patient")) {
	            String patientId = request.getParameter("patientId");

	            patient = patientService.searchPatient(Integer.parseInt(patientId));
	            PrintWriter out = response.getWriter();

	             if (patient.getPatientId()!= null) { 
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

	                // First row: Labels
	                out.println("<tr>");
	                out.println("<th>ID</th>");
	                out.println("<th>NAME</th>");
	                out.println("<th>EMAILNO</th>");
	                out.println("<th>PASSWORD</th>");
	                out.println("<th>AGE</th>");
	                out.println("<th>GENDER</th>");
	                out.println("<th>PHNO</th>");
	                out.println("<th>ADDRESS</th>");
	                out.println("<th>BLOODGROUP</th>");
	                out.println("</tr>");

	                // Second row: Dynamic values
	                out.println("<tr>");
	                out.println("<td style='background-color: #4CAF50; color: white;'>" + patient.getPatientId() + "</td>");
	                out.println("<td>" + patient.getPatientName() + "</td>");
	                out.println("<td>" + patient.getPatientEmailno() + "</td>");
	                out.println("<td>" + patient.getPatientPassword() + "</td>");
	                out.println("<td>" + patient.getPatientAge() + "</td>");
	                out.println("<td>" + patient.getPatientGender() + "</td>");
	                out.println("<td>" + patient.getPatientPhno() + "</td>");
	                out.println("<td>" + patient.getPatientAddress() + "</td>");
	                out.println("<td>" + patient.getPatientBloodGroup() + "</td>");
	                out.println("</tr>");

	                out.println("</table>");
	                out.println("</center>");
	                out.println("</body>");
	                out.println("</html>");
	            } 
	             else {
	                out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID " + patientId + "</h1>");
	            }
	            out.close();
	        }


	        

	        if (request.getRequestURI().endsWith("viewform_patient")) {
	            String url = "jdbc:mysql://localhost:3306/accounts";
	            String user = "root";
	            String password = "root123";

	            String sqlQuery = "SELECT patientId, patientName, patientEmailno, patientPassword, patientAge, patientGender, patientPhno, patientAddress, patientBloodGroup FROM patient";

	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	                connection = DriverManager.getConnection(url, user, password);
	                pstmt = connection.prepareStatement(sqlQuery);

	                res = pstmt.executeQuery();

	                PrintWriter out = response.getWriter();

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
	                out.println("<br/><br/><br>");
	                out.println("<center>");
	                out.println("<table>");

	                out.println("<tr>");
	                out.println("<th>ID</th><th>NAME</th><th>EMAILNO</th><th>PASSWORD</th><th>AGE</th><th>GENDER</th><th>PHNO</th><th>ADDRESS</th><th>BLOODGROUP</th>");
	                out.println("</tr>");

	                // Iterate through the result set
	                while (res.next()) {
	                    out.println("<tr>");
	                    out.println("<td>" + res.getInt("patientId") + "</td>");
	                    out.println("<td>" + res.getString("patientName") + "</td>");
	                    out.println("<td>" + res.getString("patientEmailno") + "</td>");
	                    out.println("<td>" + res.getString("patientPassword") + "</td>");
	                    out.println("<td>" + res.getInt("patientAge") + "</td>");
	                    out.println("<td>" + res.getString("patientGender") + "</td>");
	                    out.println("<td>" + res.getLong("patientPhno") + "</td>");
	                    out.println("<td>" + res.getString("patientAddress") + "</td>");
	                    out.println("<td>" + res.getString("patientBloodGroup") + "</td>");
	                    out.println("</tr>");
	                }

	                out.println("</table>");
	                out.println("</center>");
	                out.println("</body>");
	                out.println("</html>");
	             
	        


	                
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            }
	            res.close();
	            pstmt.close();
	            connection.close();
	        }
	        
	        if (request.getRequestURI().endsWith("deleteform_patient")) {
				String patientId = request.getParameter("patientId");
				String status = patientService.deletePatient(Integer.parseInt(patientId));

				PrintWriter out = response.getWriter();
				if (status.equals("success")) {
					out.println("<body>");
					out.println("<h1 style='color:green;text-align:center;'>PATIENT RECORD DELETED SUCCESFULLY</h1>");
					out.println("</body>");
				} else if (status.equals("failure")) {
					out.println("<body>");
					out.println("<h1 style='color:red;text-align:center;'>PATIENT RECORD DELETION FAILED</h1>");
					out.println("</body>");

				} else {
					out.println("<body>");
					out.println("<h1 style='color:green;text-align:center;'>PATIENT RECORD NOT FOUND FOR DELETION</h1>");
					out.println("</body>");

				}
				out.close();
				
			}
	       


	        
	        if (request.getRequestURI().endsWith("editform_patient")) {
				String patientId = request.getParameter("patientId");

				patient = patientService.searchPatient(Integer.parseInt(patientId));
				PrintWriter out = response.getWriter();
				if (patient != null) {
					// display student records as a form data so it is editable
					out.println("<body>");
					out.println("<center>");
					out.println("<form method='post' action='./Patient/updateRecord'>");
					out.println("<table>");
					out.println("<tr><th>ID</th><td>" + patient.getPatientId() + "</td></tr>");
					out.println("<input type='hidden' name='patientId' value='" + patient.getPatientId() + "'/>");
					out.println("<tr><th>NAME</th><td><input type='text' name='patientName' value='" + patient.getPatientName()
							+ "'/></td></tr>");
					out.println("<tr><th>EMAILNO</th><td><input type='text' name='patientEmailno' value='" + patient.getPatientEmailno()
							+ "'/></td></tr>");
					out.println("<tr><th>PASSWORD</th><td><input type='text' name='patientPassword' value='" + patient.getPatientPassword()
					+ "'/></td></tr>");
					out.println("<tr><th>AGE</th><td><input type='text' name='patientAge' value='" + patient.getPatientAge()
							+ "'/></td></tr>");
					out.println("<tr><th>GENDER</th><td><input type='text' name='patientGender' value='" + patient.getPatientGender()
					+ "'/></td></tr>");
					out.println("<tr><th>PHNO</th><td><input type='text' name='patientPhno' value='" + patient.getPatientPhno()
					+ "'/></td></tr>");
					out.println("<tr><th>ADDRESS</th><td><input type='text' name='patientAddress' value='" + patient.getPatientAddress()
					+ "'/></td></tr>");
					out.println("<tr><th>BLOODGROUP</th><td><input type='text' name='patientBloodGroup' value='" + patient.getPatientBloodGroup()
					+ "'/></td></tr>");
					out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
					out.println("</table>");
					out.println("</form>");
					out.println("</center>");
					out.println("</body>");
				} else {
					// display not found message
					out.println("<body>");
					out.println("<h1 style='color:red;text-align:center;'>Record not avaialable for the give id :: " + patientId
							+ "</h1>");
					out.println("</body>");
				}
				out.close();
			}
			if (request.getRequestURI().endsWith("updateRecord")) {
				String patientId = request.getParameter("patientId");
				String patientName = request.getParameter("patientName");
				String patientEmailno = request.getParameter("patientEmailno");
				String patientPassword = request.getParameter("patientPassword");
				String patientAge = request.getParameter("patientAge");
				String patientGender =request.getParameter("patientGender");
				String patientPhno = request.getParameter("patientPhno");
				String patientAddress = request.getParameter("patientAddress");
				String patientBloodGroup = request.getParameter("patientBloodGroup");
				
				

				
				patient.setPatientId(Integer.parseInt(patientId));
				patient.setPatientName(patientName);
				patient.setPatientEmailno(patientEmailno);
				patient.setPatientPassword(patientPassword);
				patient.setPatientAge(Integer.parseInt(patientAge));
				patient.setPatientGender(patientGender);
				patient.setPatientPhno(Long.parseLong(patientPhno));
				patient.setPatientAddress(patientAddress);
				patient.setPatientBloodGroup(patientBloodGroup);
				

				String status = patientService.updatePatient(patient);
				PrintWriter out=response.getWriter();

				if (status.equals("success")) {
					out.println("<h1 style='color:green; text-align:center;'>PATIENT RECORD UPDATED SUCCESSFULLY</h1>");
				} else {
					out.println("<h1 style='color:green; text-align:center;'>PATIENT RECORD UPDATION FAILED</h1>");
				}
				out.close();

			}
		



	 }
	 
}

