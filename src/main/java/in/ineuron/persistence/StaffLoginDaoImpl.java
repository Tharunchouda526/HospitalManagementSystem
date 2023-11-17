package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.FeedBack;
import in.ineuron.dto.Patient;
import in.ineuron.dto.StaffLogin;
import in.ineuron.util.JdbcUtil;

//peristence Logic using JDBC API
public class StaffLoginDaoImpl implements IStaffLoginDao 
{
   Connection connection=null;
   PreparedStatement pstmt =null;
   ResultSet res=null;
   
   
	@Override
	public String addStaffLogin(StaffLogin stafflogin)
	{
		String sqlInsertQuery = "insert into stafflogin (email,password) values(?,?)";

		try {
			connection =JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				pstmt=connection.prepareStatement(sqlInsertQuery);
				if(pstmt!=null)
				{
					pstmt.setString(1,stafflogin.getEmail() );
					pstmt.setString(2,stafflogin.getPassword() );
					
					
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

