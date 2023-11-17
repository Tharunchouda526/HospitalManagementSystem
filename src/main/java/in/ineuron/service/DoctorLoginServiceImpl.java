package in.ineuron.service;

import in.ineuron.daofactory.AdminLoginDaoFactory;
import in.ineuron.daofactory.DoctorLoginDaoFactory;
import in.ineuron.daofactory.UserLoginDaoFactory;
import in.ineuron.dto.AdminLogin;
import in.ineuron.dto.DoctorLogin;
import in.ineuron.dto.UserLogin;
import in.ineuron.persistence.IAdminLoginDao;
import in.ineuron.persistence.IDoctorLoginDao;
import in.ineuron.persistence.IUserLoginDao;



public class DoctorLoginServiceImpl implements IDoctorLoginService {
    private IDoctorLoginDao doctorlogindao;

    @Override
    public String addDoctorLogin(DoctorLogin doctorlogin) {
       
        doctorlogindao = DoctorLoginDaoFactory.getDoctorLoginDao();
        return doctorlogindao.addDoctorLogin(doctorlogin);
    }
}
