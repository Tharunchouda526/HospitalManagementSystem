package in.ineuron.ControllerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Admin;
import in.ineuron.dto.Doctor;
import in.ineuron.dto.Staff;
import in.ineuron.service.IAdminService;
import in.ineuron.service.IDoctorService;
import in.ineuron.service.IStaffService;
import in.ineuron.servicefactory.AdminServiceFactory;
import in.ineuron.servicefactory.DoctorServiceFactory;
import in.ineuron.servicefactory.StaffServiceFactory;


@WebServlet("/AdminS/*")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        IAdminService adminService = AdminServiceFactory.getAdminService();

        System.out.println("Request URI::" + request.getRequestURI());
        System.out.println("Path info::" + request.getPathInfo());

        if (request.getRequestURI().endsWith("addform_admin")) {
            String adminName = request.getParameter("adminName");
            String adminEmailno = request.getParameter("adminEmailno");
            String adminPassword = request.getParameter("adminPassword");
            String adminGender = request.getParameter("adminGender");
            String adminPhno = request.getParameter("adminPhno");
            String adminAddress = request.getParameter("adminAddress");
            

           Admin admin=new Admin();
           admin.setAdminName(adminName);
           admin.setAdminEmailno(adminEmailno);
           admin.setAdminPassword(adminPassword);
           admin.setAdminGender(adminGender);
           admin.setAdminPhno(Long.parseLong((adminPhno)));
           admin.setAdminAddress(adminAddress);

            String status =adminService.addAdmin(admin);
            PrintWriter out = response.getWriter();

            if (status.equals("success")) {
                out.println("<h1 style='color:green; text-align:center;'>REGISTRATION SUCCESSFUL</h1>");
            } else {
                out.println("<h1 style='color:red; text-align:center;'>REGISTRATION FAILED</h1>");
            }
            out.close();
        }

	}
}