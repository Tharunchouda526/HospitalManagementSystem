package in.ineuron.ControllerServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.ineuron.dto.Appointment;
import in.ineuron.dto.Patient;
import in.ineuron.service.IAppointmentService;
import in.ineuron.servicefactory.AppointmentServiceFactory;

@WebServlet("/Appointment/*")
public class AppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			doProcess(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
        IAppointmentService appointmentService = AppointmentServiceFactory.getAppointmentService();
        Appointment appointment=new Appointment();
        Connection connection=null;
        PreparedStatement pstmt=null;
        ResultSet res=null;

            if (request.getRequestURI().endsWith("addform_appointment")) {
                String patientId = request.getParameter("patientId");
                String doctorId = request.getParameter("doctorId");
                String DateTime = request.getParameter("DateTime");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(DateTime, formatter);

                appointment = new Appointment();
                appointment.setPatientId(Integer.parseInt(patientId));
                appointment.setDoctorId(Integer.parseInt(doctorId));
                appointment.setDateTime(dateTime);

                String status = appointmentService.addAppointment(appointment);
                PrintWriter out = response.getWriter();

                if (status.equals("success")) {
                    out.println("<h1 style='color:green; text-align:center;'>APPOINTMENT SCHEDULED</h1>");
                } else {
                    out.println("<h1 style='color:red; text-align:center;'>APPOINTMENT FAILED</h1>");
                }
                out.close();
            }
        

            if (request.getRequestURI().endsWith("searchform_appointment")) {
                String appointmentId = request.getParameter("appointmentId");

                appointment = appointmentService.searchAppointment(Integer.parseInt(appointmentId));
                PrintWriter out = response.getWriter();
                if (appointment.getAppointmentId()!= null) {
                    // Display the appointment details
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<style>");
                   
                    out.println("</style>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<br/><br/><br/>");
                    out.println("<center>");
                    out.println("<table>");

                    // First row: Labels
                    out.println("<tr>");
                    out.println("<th>APPOINTMENTID</th>");
                    out.println("<th>PATIENTID</th>");
                    out.println("<th>PATIENTNAME</th>");
                    out.println("<th>PATIENTAGE</th>");
                    out.println("<th>PATIENTGENDER</th>");
                    out.println("<th>DOCTORID</th>");
                    out.println("<th>DOCTORNAME</th>");
                    out.println("<th>DATETIME</th>");
                    out.println("</tr>");

                    // Second row: Dynamic values
                    out.println("<tr>");
                    out.println("<td style='background-color: #4CAF50; color: white;'>" + appointment.getAppointmentId() + "</td>");
                    out.println("<td>" + appointment.getPatientId() + "</td>");
                    out.println("<td>" + appointment.getPatientName() + "</td>");
                    out.println("<td>" + appointment.getPatientAge() + "</td>");
                    out.println("<td>" + appointment.getPatientGender() + "</td>");
                    out.println("<td>" + appointment.getDoctorId() + "</td>");
                    out.println("<td>" + appointment.getDoctorName() + "</td>");
                    DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    out.println("<td>" + appointment.getDateTime().format(displayFormatter) + "</td>");
                    out.println("</tr>");

                    out.println("</table>");
                    out.println("</center>");
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    // Display the "RECORD NOT AVAILABLE" message
                    out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID " + appointmentId + "</h1>");
                }
                out.close();
            }
    
        
        if (request.getRequestURI().endsWith("viewform_appointment")) {
            String url = "jdbc:mysql://localhost:3306/accounts";
            String user = "root";
            String password = "root123";

            String sqlQuery = "SELECT appointmentId,patientId, patientName, patientAge, patientGender, doctorId, doctorName, DateTime FROM appointment";

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
                out.println("<th>APPOINTMENTID</th><th>PATIENTID</th><th>PATIENTNAME</th><th>PATIENTAGE</th><th>PATIENTGENDER</th><th>DOCTORID</th><th>DOCTORNAME</th><th>DATETIME</th>");
                out.println("</tr>");

                // Iterate through the result set
                while (res.next()) {
                    out.println("<tr>");
                    out.println("<td>" + res.getInt("appointmentId") + "</td>");
                    out.println("<td>" + res.getInt("patientId") + "</td>");
                    out.println("<td>" + res.getString("patientName") + "</td>");
                    out.println("<td>" + res.getString("patientAge") + "</td>");
                    out.println("<td>" + res.getString("patientGender") + "</td>");
                    
                    out.println("<td>" + res.getLong("doctorId") + "</td>");
                    out.println("<td>" + res.getString("doctorName") + "</td>");
                    out.println("<td>" + res.getString("DateTime") + "</td>");
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
        if (request.getRequestURI().endsWith("deleteform_appointment")) {
            String appointmentId = request.getParameter("appointmentId");
            String status = appointmentService.deleteAppointment(Integer.parseInt(appointmentId));
            PrintWriter out = response.getWriter();

            if (status.equals("success")) {
                out.println("<h1 style='color:green;text-align:center;'APPOINTMENT  DELETED SUCCESSFULLY .</h1>");
            } else if (status.equals("failure")) {
                out.println("<h1 style='color:red;text-lign:center;'>APPOINTMENT  DELETED FAILED.</h1>");
            } else {
                out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR DELETION.</h1>");
            }
            out.close();
        }
     

       
        if (request.getRequestURI().endsWith("editform_appointment")) {
			String appointmentId = request.getParameter("appointmentId");

			appointment = appointmentService.searchAppointment(Integer.parseInt(appointmentId));
			PrintWriter out = response.getWriter();
			if (appointment != null) {
				
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./Appointment/updateRecord'>");
				out.println("<table>");
				out.println("<tr><th>ID</th><td>" + appointment.getAppointmentId() + "</td></tr>");
				out.println("<input type='hidden' name='appointmentId' value='" +  appointment.getAppointmentId() + "'/>");
				out.println("<tr><th>PATIENTID</th><td><input type='text' name='patientId' value='" +  appointment.getPatientId()
						+ "'/></td></tr>");
				out.println("<tr><th>PATIENTNAME</th><td><input type='text' name='patientName' value='" +  appointment.getPatientName()
						+ "'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='patientAge' value='" +  appointment.getPatientAge()
				+ "'/></td></tr>");
				out.println("<tr><th>GENDER</th><td><input type='text' name='patientGender' value='" + appointment.getPatientGender()
						+ "'/></td></tr>");
				out.println("<tr><th>DOCTORID</th><td><input type='text' name='doctorId' value='" +  appointment.getDoctorId()
				+ "'/></td></tr>");
				out.println("<tr><th>DOCTORNAME</th><td><input type='text' name='doctorName' value='" +  appointment.getDoctorName()
				+ "'/></td></tr>");
				DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
               

				out.println("<tr><th>DATETIME</th><td><input type='text' name='DateTime' value='" +  appointment.getDateTime().format(displayFormatter)
				+ "'/></td></tr>");
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
			} else {
				// display not found message
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>Record not avaialable for the give id :: " +  appointmentId
						+ "</h1>");
				out.println("</body>");
			}
			out.close();
		}
        if (request.getRequestURI().endsWith("updateRecord")) {
            String appointmentId = request.getParameter("appointmentId");
            String patientId = request.getParameter("patientId");
            String patientName = request.getParameter("patientName");
            String patientAge = request.getParameter("patientAge");
            String patientGender = request.getParameter("patientGender");
            String doctorId = request.getParameter("doctorId");
            String doctorName = request.getParameter("doctorName");
            String DateTime = request.getParameter("DateTime");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(DateTime, formatter);

            appointment.setAppointmentId(Integer.parseInt(appointmentId));
            appointment.setPatientId(Integer.parseInt(patientId));
            appointment.setPatientName(patientName);
            appointment.setPatientAge(Integer.parseInt(patientAge));
            appointment.setPatientGender(patientGender);
            appointment.setDoctorId(Integer.parseInt(doctorId));
            appointment.setDoctorName(doctorName);
            appointment.setDateTime(dateTime);

            String status = appointmentService.updateAppointment(appointment);
            PrintWriter out = response.getWriter();

            if (status.equals("success")) {
                out.println("<h1 style='color:green; text-align:center;'>APPOINTMENT RECORD UPDATED SUCCESSFULLY</h1>");
            } else {
                out.println("<h1 style='color:red; text-align:center;'>APPOINTMENT RECORD UPDATION FAILED</h1>");
            }
            out.close();
        }
}
}