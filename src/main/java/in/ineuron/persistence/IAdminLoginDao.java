package in.ineuron.persistence;

import in.ineuron.dto.AdminLogin;
import in.ineuron.dto.FeedBack;
import in.ineuron.dto.UserLogin;

public interface IAdminLoginDao
{
	public String addAdminLogin(AdminLogin adminlogin);
}
