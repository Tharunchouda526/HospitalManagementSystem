package in.ineuron.ControllerServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Doctor;
import in.ineuron.dto.Patient;
import in.ineuron.service.IDoctorService;
import in.ineuron.service.IPatientService;
import in.ineuron.servicefactory.DoctorServiceFactory;
import in.ineuron.servicefactory.PatientServiceFactory;


@WebServlet("/Doctor/*")
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 try {
			doProcess(request, response);
		} catch (IOException | ServletException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 try {
			doProcess(request, response);
		} catch (IOException | ServletException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
	        IDoctorService doctorService = DoctorServiceFactory.getDoctorService();
	        
	        Connection connection=null;
	        PreparedStatement pstmt=null;
	        ResultSet res=null;
	        
	        Doctor doctor =new Doctor();
	        System.out.println("Request URI::" + request.getRequestURI());
	        System.out.println("Path info::" + request.getPathInfo());

	        if (request.getRequestURI().endsWith("addform_doctor")) {
	            String doctorName = request.getParameter("doctorName");
	            String doctorEmail = request.getParameter("doctorEmail");
	            String doctorPassword = request.getParameter("doctorPassword");
	            String doctorAge = request.getParameter("doctorAge");
	            String doctorGender = request.getParameter("doctorGender");
	            String doctorPhno = request.getParameter("doctorPhno");
	            String doctorSpecialization = request.getParameter("doctorSpecialization");
	            String doctorAddress = request.getParameter("doctorAddress");
	            

	            doctor =new Doctor();
	            doctor.setDoctorName (doctorName );
	            doctor.setDoctorEmail(doctorEmail);
	            doctor.setDoctorPassword(doctorPassword);
	            doctor.setDoctorAge(Integer.parseInt(doctorAge));
	            doctor.setDoctorGender(doctorGender);
	            doctor.setDoctorPhno(Long.parseLong(doctorPhno));
	            doctor.setDoctorSpecialization(doctorSpecialization);
	            doctor.setDoctorAddress(doctorAddress);

	            String status = doctorService.addDoctor(doctor);
	            PrintWriter out = response.getWriter();

	            if (status.equals("success")) {
	                out.println("<h1 style='color:green; text-align:center;'> DOCTOR REGISTRATION SUCCESSFUL</h1>");
	            } else {
	                out.println("<h1 style='color:red; text-align:center;'>DOCTOR REGISTRATION FAILED</h1>");
	            }
	            out.close();
	        }

	        if (request.getRequestURI().endsWith("searchform_doctor")) {
	            String doctorId = request.getParameter("doctorId");

	            doctor = doctorService.searchDoctor(Integer.parseInt(doctorId));
	            PrintWriter out = response.getWriter();

	            if (doctor.getDoctorId()!= null) {
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
	                out.println("<th>EMAIL</th>");
	                out.println("<th>PASSWORD</th>");
	                out.println("<th>AGE</th>");
	                out.println("<th>GENDER</th>");
	                out.println("<th>PHNO</th>");
	                out.println("<th>SPECIALIZATION</th>");
	                out.println("<th>ADDRESS</th>");
	               
	                out.println("</tr>");

	                // Second row: Dynamic values
	                out.println("<tr>");
	                out.println("<td style='background-color: #4CAF50; color: white;'>" + doctor.getDoctorId() + "</td>");
	                out.println("<td>" + doctor.getDoctorName() + "</td>");
	                out.println("<td>" + doctor.getDoctorEmail() + "</td>");
	                out.println("<td>" + doctor.getDoctorPassword() + "</td>");
	                out.println("<td>" + doctor.getDoctorAge() + "</td>");
	                out.println("<td>" + doctor.getDoctorGender() + "</td>");
	                out.println("<td>" + doctor.getDoctorPhno() + "</td>");
	                out.println("<td>" + doctor.getDoctorSpecialization() + "</td>");
	                out.println("<td>" + doctor.getDoctorAddress() + "</td>");
	              
	                out.println("</tr>");

	                out.println("</table>");
	                out.println("</center>");
	                out.println("</body>");
	                out.println("</html>");
	            } else {
	                out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID " + doctorId + "</h1>");
	            }
	            out.close();
	        }

	        
	        if (request.getRequestURI().endsWith("viewform_doctors")) {
	            String url = "jdbc:mysql://localhost:3306/accounts";
	            String user = "root";
	            String password = "root123";

	            String sqlQuery = "SELECT doctorId, doctorName, doctorEmail, doctorPassword, doctorAge, doctorGender, doctorPhno, doctorSpecialization, doctorAddress FROM doctor";

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
	                out.println("<th>ID</th><th>NAME</th><th>EMAIL</th><th>PASSWORD</th><th>AGE</th><th>GENDER</th><th>PHNO</th><th>SPECIALIZATION</th><th>ADDRESS</th>");
	                out.println("</tr>");

	                // Iterate through the result set
	                while (res.next()) {
	                    out.println("<tr>");
	                    out.println("<td>" + res.getInt("doctorId") + "</td>");
	                    out.println("<td>" + res.getString("doctorName") + "</td>");
	                    out.println("<td>" + res.getString("doctorEmail") + "</td>");
	                    out.println("<td>" + res.getString("doctorPassword") + "</td>");
	                    out.println("<td>" + res.getInt("doctorAge") + "</td>");
	                    out.println("<td>" + res.getString("doctorGender") + "</td>");
	                    out.println("<td>" + res.getLong("doctorPhno") + "</td>");
	                    out.println("<td>" + res.getString("doctorSpecialization") + "</td>");
	                    out.println("<td>" + res.getString("doctorAddress") + "</td>");
	                    out.println("</tr>");
	                }

	                out.println("</table>");
	                out.println("</center>");
	                out.println("</body>");
	                out.println("</html");
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    if (res != null) {
	                        res.close();
	                    }
	                    if (pstmt != null) {
	                        pstmt.close();
	                    }
	                    if (connection != null) {
	                        connection.close();
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        if (request.getRequestURI().endsWith("deleteform_doctor")) {
				String doctorId = request.getParameter("doctorId");
				String status = doctorService.deleteDoctor(Integer.parseInt(doctorId));

				PrintWriter out = response.getWriter();
				if (status.equals("success")) {
					out.println("<body>");
					out.println("<h1 style='color:green;text-align:center;'>RECORD DELETED SUCCESFULLY</h1>");
					out.println("</body>");
				} else if (status.equals("failure")) {
					out.println("<body>");
					out.println("<h1 style='color:red;text-align:center;'>RECORD DELETION FAILED</h1>");
					out.println("</body>");

				} else {
					out.println("<body>");
					out.println("<h1 style='color:green;text-align:center;'>RECORD NOT FOUND FOR DELETION</h1>");
					out.println("</body>");

				}
				out.close();
				
			}
	       
	        
	        
	        if (request.getRequestURI().endsWith("editform_doctor")) {
				String doctorId = request.getParameter("doctorId");

				doctor = doctorService.searchDoctor(Integer.parseInt(doctorId));
				PrintWriter out = response.getWriter();
				if (doctor != null) {
					// display student records as a form data so it is editable
					out.println("<body>");
					out.println("<center>");
					out.println("<form method='post' action='./Doctor/updateRecord'>");
					out.println("<table>");
					out.println("<tr><th>ID</th><td>" + doctor.getDoctorId() + "</td></tr>");
					out.println("<input type='hidden' name='doctorId' value='" + doctor.getDoctorId() + "'/>");
					out.println("<tr><th>NAME</th><td><input type='text' name='doctorName' value='" + doctor.getDoctorName()
							+ "'/></td></tr>");
					out.println("<tr><th>EMAIL</th><td><input type='text' name='doctorEmail' value='" + doctor.getDoctorEmail()
							+ "'/></td></tr>");
					out.println("<tr><th>PASSWORD</th><td><input type='text' name='doctorPassword' value='" + doctor.getDoctorPassword()
					+ "'/></td></tr>");
					out.println("<tr><th>AGE</th><td><input type='text' name='doctorAge' value='" + doctor.getDoctorAge()
							+ "'/></td></tr>");
					out.println("<tr><th>GENDER</th><td><input type='text' name='doctorGender' value='" + doctor.getDoctorGender()
					+ "'/></td></tr>");
					out.println("<tr><th>PHNO</th><td><input type='text' name='doctorPhno' value='" + doctor.getDoctorPhno()
					+ "'/></td></tr>");
					out.println("<tr><th>SPECIALIZATION</th><td><input type='text' name='doctorSpecialization' value='" + doctor.getDoctorSpecialization()
					+ "'/></td></tr>");
					out.println("<tr><th>ADDRESS</th><td><input type='text' name='doctorAddress' value='" + doctor.getDoctorAddress()
					+ "'/></td></tr>");
					out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
					out.println("</table>");
					out.println("</form>");
					out.println("</center>");
					out.println("</body>");
				} else {
					// display not found message
					out.println("<body>");
					out.println("<h1 style='color:red;text-align:center;'>Record not avaialable for the give id :: " + doctorId
							+ "</h1>");
					out.println("</body>");
				}
				out.close();
			}
			if (request.getRequestURI().endsWith("updateRecord")) {
				String doctorId = request.getParameter("doctorId");
				String doctorName = request.getParameter("doctorName");
				String doctorEmail = request.getParameter("doctorEmail");
				String doctorPassword = request.getParameter("doctorPassword");
				String doctorAge = request.getParameter("doctorAge");
				String doctorGender =request.getParameter("doctorGender");
				String doctorPhno = request.getParameter("doctorPhno");
				String doctorSpecialization= request.getParameter("doctorSpecialization");
				String doctorAddress = request.getParameter("doctorAddress");
				
				
				    doctor =new Doctor();
				    doctor.setDoctorId(Integer.parseInt(doctorId));
				    doctor.setDoctorName (doctorName );
		            doctor.setDoctorEmail(doctorEmail);
		            doctor.setDoctorPassword(doctorPassword);
		            doctor.setDoctorAge(Integer.parseInt(doctorAge));
		            doctor.setDoctorGender(doctorGender);
		            doctor.setDoctorPhno(Long.parseLong(doctorPhno));
		            doctor.setDoctorSpecialization(doctorSpecialization);
		            doctor.setDoctorAddress(doctorAddress);
				
		            String status = doctorService.updateDoctor(doctor);
		            PrintWriter out = response.getWriter();
				
				if (status.equals("success")) {
					out.println("<h1 style='color:green; text-align:center;'>DOCTOR RECORD UPDATED SUCCESSFULLY</h1>");
				} else {
					out.println("<h1 style='color:green; text-align:center;'>DOCTOR RECORD UPDATION FAILED</h1>");
				}
				out.close();

			}
		}


}



