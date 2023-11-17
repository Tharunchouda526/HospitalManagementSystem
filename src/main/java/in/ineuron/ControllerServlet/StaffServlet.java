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
import in.ineuron.dto.Staff;
import in.ineuron.service.IDoctorService;
import in.ineuron.service.IStaffService;
import in.ineuron.servicefactory.DoctorServiceFactory;
import in.ineuron.servicefactory.StaffServiceFactory;


@WebServlet("/staff/*")
public class StaffServlet extends HttpServlet {
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
        IStaffService staffService = StaffServiceFactory.getStaffService();
        Staff staff =new Staff();
        Connection connection=null;
        PreparedStatement pstmt=null;
        ResultSet res=null;
        System.out.println("Request URI::" + request.getRequestURI());
        System.out.println("Path info::" + request.getPathInfo());

        if (request.getRequestURI().endsWith("addform_staff")) {
            String staffName = request.getParameter("staffName");
            String staffEmailno = request.getParameter("staffEmailno");
            String staffPassword = request.getParameter("staffPassword");
            String staffGender = request.getParameter("staffGender");
            String staffPhno = request.getParameter("staffPhno");
            String staffAddress = request.getParameter("staffAddress");
            

            staff =new Staff();
            staff.setStaffName(staffName);
            staff.setStaffEmailno(staffEmailno);
            staff.setStaffPassword(staffPassword);
            staff.setStaffGender(staffGender);
            staff.setStaffPhno(Long.parseLong((staffPhno)));
            staff.setStaffAddress(staffAddress);

            String status =staffService.addStaff(staff);
            PrintWriter out = response.getWriter();

            if (status.equals("success")) {
                out.println("<h1 style='color:green; text-align:center;'>REGISTRATION SUCCESSFUL</h1>");
            } else {
                out.println("<h1 style='color:red; text-align:center;'>REGISTRATION FAILED</h1>");
            }
            out.close();
        }

        if (request.getRequestURI().endsWith("searchform_staff")) {
        	String staffId = request.getParameter("staffId");
        	
        	staff = staffService.searchStaff(Integer.parseInt(staffId));
        	PrintWriter out = response.getWriter();

        	 if (staff.getStaffId()!= null) {
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
            	out.println("<th>ID</th><td>" + staff.getStaffId() + "</td>");
            	out.println("<th>NAME</th><td>" + staff.getStaffName() + "</td>");
            	out.println("<th>EMAILNO</th><td>" + staff.getStaffEmailno() + "</td>");
            	out.println("<th>PASSWORD</th><td>" + staff.getStaffPassword() + "</td>");
            	out.println("<th>GENDER</th><td>" + staff.getStaffGender() + "</td>");
            	out.println("<th>PHNO</th><td>" + staff.getStaffPhno() + "</td>");
            	out.println("<th>ADDRESS</th><td>" + staff.getStaffAddress() + "</td>");
            	out.println("</tr>");
            	out.println("</table>");
            	out.println("</center>");
            	out.println("</body>");
            	out.println("</html>");



            } 
        	 else {
                out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID " + staffId + "</h1>");
            }
            out.close();
        }
        if (request.getRequestURI().endsWith("viewform_staff")) {
            String url = "jdbc:mysql://localhost:3306/accounts";
            String user = "root";
            String password = "root123";

            String sqlQuery = "SELECT staffId, staffName, staffEmailno, staffPassword,  staffGender, staffPhno, staffAddress FROM Staff";

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
                out.println("<th>ID</th><th>NAME</th><th>EMAILNO</th><th>PASSWORD</th</th><th>GENDER</th><th>PHNO</th><th>ADDRESS</th><th>");
                out.println("</tr>");

                // Iterate through the result set
                while (res.next()) {
                    out.println("<tr>");
                    out.println("<td>" + res.getInt("staffId") + "</td>");
                    out.println("<td>" + res.getString("staffName") + "</td>");
                    out.println("<td>" + res.getString("staffEmailno") + "</td>");
                    out.println("<td>" + res.getString("staffPassword") + "</td>");
                    out.println("<td>" + res.getString("staffGender") + "</td>");
                    out.println("<td>" + res.getLong("staffPhno") + "</td>");
                    out.println("<td>" + res.getString("staffAddress") + "</td>");
                   
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

        if (request.getRequestURI().endsWith("deleteform_staff")) {
			String staffId = request.getParameter("staffId");
			
			String status = staffService.deleteStaff(Integer.parseInt(staffId));
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
        
        if (request.getRequestURI().endsWith("editform_staff")) {
			String staffId = request.getParameter("staffId");

			staff = staffService.searchStaff(Integer.parseInt(staffId));
        	PrintWriter out = response.getWriter();
			if (staff != null) {
				// display student records as a form data so it is editable
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./staff/updateRecord'>");
				out.println("<table>");
				out.println("<tr><th>ID</th><td>" + staff.getStaffId() + "</td></tr>");
				out.println("<input type='hidden' name='staffId' value='" + staff.getStaffId() + "'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='staffName' value='" + staff.getStaffName()
						+ "'/></td></tr>");
				out.println("<tr><th>EMAILNO</th><td><input type='text' name='staffEmailno' value='" + staff.getStaffEmailno()
						+ "'/></td></tr>");
				out.println("<tr><th>PASSWORD</th><td><input type='text' name='staffPassword' value='" + staff.getStaffPassword()
				+ "'/></td></tr>");
				out.println("<tr><th>GENDER</th><td><input type='text' name='staffGender' value='" + staff.getStaffGender()
				+ "'/></td></tr>");
				out.println("<tr><th>PHNO</th><td><input type='text' name='staffPhno' value='" + staff.getStaffPhno()
				+ "'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='staffAddress' value='" + staff.getStaffAddress()
				+ "'/></td></tr>");
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
			} else {
				// display not found message
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>Record not avaialable for the give id :: " + staffId
						+ "</h1>");
				out.println("</body>");
			}
			out.close();
		}
        if (request.getRequestURI().endsWith("updateRecord")) {
        	String staffId = request.getParameter("staffId");
            String staffName = request.getParameter("staffName");
            String staffEmailno = request.getParameter("staffEmailno");
            String staffPassword = request.getParameter("staffPassword");
            String staffGender = request.getParameter("staffGender");
            String staffPhno = request.getParameter("staffPhno");
            String staffAddress = request.getParameter("staffAddress");
            

            staff =new Staff();
            staff.setStaffId(Integer.parseInt(staffId));
            staff.setStaffName(staffName);
            staff.setStaffEmailno(staffEmailno);
            staff.setStaffPassword(staffPassword);
            staff.setStaffGender(staffGender);
            staff.setStaffPhno(Long.parseLong(staffPhno));
            staff.setStaffAddress(staffAddress);

            String status =staffService.updateStaff(staff);
            PrintWriter out = response.getWriter();

            if (status.equals("success")) {
                out.println("<h1 style='color:green; text-align:center;'>STAFF RECORD UPDATED SUCCESSFULLY</h1>");
            } else {
                out.println("<h1 style='color:red; text-align:center;'>STAFF UPDATED RECORD FAILED</h1>");
            }
            out.close();
        }

       



	}




}
