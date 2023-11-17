package in.ineuron.persistence;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Admin;
import in.ineuron.dto.Doctor;
import in.ineuron.dto.Staff;
import in.ineuron.util.JdbcUtil;

public class AdminDaoImpl implements IAdminDao {
	   Connection connection=null;
	   PreparedStatement pstmt =null;
	   ResultSet res=null;
	   

	@Override
	public String addAdmin(Admin admin) {
		String sqlInsertQuery = "insert into admin (adminName,adminEmailno,adminPassword,adminGender,adminPhno,adminAddress) values(?,?,?,?,?,?)";

		try {
			connection =JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				pstmt=connection.prepareStatement(sqlInsertQuery);
				if(pstmt!=null)
				{
					pstmt.setString(1,admin.getAdminName() );
					pstmt.setString(2,admin.getAdminEmailno() );
					pstmt.setString(3,admin.getAdminPassword() );
					pstmt.setString(4,admin.getAdminGender() );
					pstmt.setLong(5,admin.getAdminPhno() );
					pstmt.setString(6,admin.getAdminAddress() );
				
					
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