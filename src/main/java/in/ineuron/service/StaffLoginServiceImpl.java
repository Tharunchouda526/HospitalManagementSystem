package in.ineuron.service;

import in.ineuron.daofactory.StaffLoginDaoFactory;
import in.ineuron.daofactory.UserLoginDaoFactory;
import in.ineuron.dto.StaffLogin;
import in.ineuron.dto.UserLogin;
import in.ineuron.persistence.IStaffLoginDao;
import in.ineuron.persistence.IUserLoginDao;



public class StaffLoginServiceImpl implements IStaffLoginService {
    private IStaffLoginDao stafflogindao;

    @Override
    public String addStaffLogin(StaffLogin stafflogin) {
       
        stafflogindao = StaffLoginDaoFactory.getStaffLoginDao();
        return stafflogindao.addStaffLogin(stafflogin);
    }
}
