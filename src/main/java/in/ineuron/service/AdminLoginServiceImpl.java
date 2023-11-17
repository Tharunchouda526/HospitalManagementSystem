package in.ineuron.service;

import in.ineuron.daofactory.AdminLoginDaoFactory;
import in.ineuron.daofactory.UserLoginDaoFactory;
import in.ineuron.dto.AdminLogin;
import in.ineuron.dto.UserLogin;
import in.ineuron.persistence.IAdminLoginDao;
import in.ineuron.persistence.IUserLoginDao;



public class AdminLoginServiceImpl implements IAdminLoginService {
    private IAdminLoginDao adminlogindao;

    @Override
    public String addAdminLogin(AdminLogin adminlogin) {
       
        adminlogindao = AdminLoginDaoFactory.getAdminLoginDao();
        return adminlogindao.addAdminLogin(adminlogin);
    }
}
