package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.AdminLogin;
import in.ineuron.dto.FeedBack;
import in.ineuron.dto.UserLogin;
import in.ineuron.util.JdbcUtil;

public class AdminLoginDaoImpl implements IAdminLoginDao 
{
   Connection connection=null;
   PreparedStatement pstmt =null;
   ResultSet res=null;
   
   
	@Override
	public String addAdminLogin(AdminLogin adminlogin)
	{
		String sqlInsertQuery = "insert into adminlogin(email,password) values(?,?)";

		try {
			connection =JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				pstmt=connection.prepareStatement(sqlInsertQuery);
				if(pstmt!=null)
				{
					pstmt.setString(1,adminlogin.getEmail() );
					pstmt.setString(2,adminlogin.getPassword() );
					
					
					int rowAffected=pstmt.executeUpdate();
					if(rowAffected==1)
					{
						return "success";
					}
					else
					{
						return "failure";
					}
					
					
					
				}
			}
		} catch (SQLException | IOException e) {
			
			e.printStackTrace();
		}
		return "failure";
	}
}