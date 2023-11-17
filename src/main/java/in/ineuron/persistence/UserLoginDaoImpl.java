package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.FeedBack;
import in.ineuron.dto.UserLogin;
import in.ineuron.util.JdbcUtil;

public class UserLoginDaoImpl implements IUserLoginDao 
{
   Connection connection=null;
   PreparedStatement pstmt =null;
   ResultSet res=null;
   
   
	@Override
	public String addUserLogin(UserLogin userlogin)
	{
		String sqlInsertQuery = "insert into userlogin(userName,userPassword) values(?,?)";

		try {
			connection =JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				pstmt=connection.prepareStatement(sqlInsertQuery);
				if(pstmt!=null)
				{
					pstmt.setString(1,userlogin.getUserName() );
					pstmt.setString(2,userlogin.getUserPassword() );
					
					
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