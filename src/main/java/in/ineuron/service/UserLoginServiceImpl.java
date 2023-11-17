package in.ineuron.service;

import in.ineuron.daofactory.UserLoginDaoFactory;
import in.ineuron.dto.UserLogin;
import in.ineuron.persistence.IUserLoginDao;



public class UserLoginServiceImpl implements IUserLoginService {
    private IUserLoginDao userlogindao;

    @Override
    public String addUserLogin(UserLogin userlogin) {
       
        userlogindao = UserLoginDaoFactory.getUserLoginDao();
        return userlogindao.addUserLogin(userlogin);
    }
}
