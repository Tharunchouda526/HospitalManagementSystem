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

import in.ineuron.service.IDoctorLoginService;
import in.ineuron.servicefactory.DoctorLoginServiceFactory;

@WebServlet("/doc/*")
public class DoctorLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doProcess(request, response);
        } catch (IOException | ServletException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doProcess(request, response);
        } catch (IOException | ServletException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        IDoctorLoginService doctorloginService = DoctorLoginServiceFactory.getDoctorLoginService();
        Connection connection=null;
        PreparedStatement pstmt=null;
        ResultSet res=null;
        
        if (request.getRequestURI().endsWith("doclogin")) {
            String Email = request.getParameter("email");
            String Password = request.getParameter("password");

            String url = "jdbc:mysql://localhost:3306/accounts";
            String user = "root";
            String password = "root123";

            String sqlQuery = "SELECT DoctorPassword FROM doctor WHERE DoctorEmail = ?";

            

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
                pstmt = connection.prepareStatement(sqlQuery);
                pstmt.setString(1, Email);

                res = pstmt.executeQuery();

                if (res.next()) {
                    String storedPassword = res.getString("DoctorPassword");
                    if (Password.equals(storedPassword)) {
                        response.sendRedirect(request.getContextPath() + "/DoctorLogin2.html");
                    } else {
                        PrintWriter out = response.getWriter();
                        out.println("<h1 style='color:red; text-align:center;'>INVALID LOGIN</h1>");
                    }
                } else {
                    PrintWriter out = response.getWriter();
                    out.println("<h1 style='color:red; text-align:center;'>INVALID LOGIN</h1>");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                // Close resources in a finally block to ensure they are always closed
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
    }
}
