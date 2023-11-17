package in.ineuron.ControllerServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.UserLogin;
import in.ineuron.service.IAdminLoginService;
import in.ineuron.service.IUserLoginService;
import in.ineuron.servicefactory.AdminLoginServiceFactory;
import in.ineuron.servicefactory.UserLoginServiceFactory;

@WebServlet("/Admin/*")
public class AdminLoginServlet extends HttpServlet {
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
        IAdminLoginService adminloginService = AdminLoginServiceFactory.getAdminLoginService();
        
        Connection connection=null;
        PreparedStatement pstmt=null;
        ResultSet res=null;

        if (request.getRequestURI().endsWith("admin")) {
        	String Email = request.getParameter("email");
        	String Password = request.getParameter("password");
        	
            String url = "jdbc:mysql://localhost:3306/accounts";
            String user = "root";
            String password = "root123";

   
            String sqlQuery = "SELECT AdminPassword FROM admin WHERE AdminEmailno = ?";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
                pstmt = connection.prepareStatement(sqlQuery);

                pstmt.setString(1, Email); 

               
                res = pstmt.executeQuery();

                if (res.next()) {
                    String storedPassword = res.getString("adminPassword");
                    if (Password.equals(storedPassword)) {
                    	 
                        response.sendRedirect( request.getContextPath()+"/AdminLogin2.html");
                        } 
                    
                    else 
                    {
                         
                            PrintWriter out = response.getWriter();
                            out.println("<h1 style='color:red; text-align:center;'>INVALID LOGIN</h1>");
                        

                   
                    }
                } else {
                   
                    PrintWriter out = response.getWriter();
                    out.println("<h1 style='color:red; text-align:center;'>INVALID LOGIN</h1>");
                }

                
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            res.close();
            pstmt.close();
            connection.close();
        }
    }
}
