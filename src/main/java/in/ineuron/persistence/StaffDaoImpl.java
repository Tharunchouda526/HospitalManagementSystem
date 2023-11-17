package in.ineuron.persistence;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Doctor;
import in.ineuron.dto.Staff;
import in.ineuron.util.JdbcUtil;

public class StaffDaoImpl implements IStaffDao {
	   Connection connection=null;
	   PreparedStatement pstmt =null;
	   ResultSet res=null;
	   

	@Override
	public String addStaff(Staff staff) {
		String sqlInsertQuery = "insert into Staff (staffName,staffEmailno,staffPassword,staffGender,staffPhno,staffAddress) values(?,?,?,?,?,?)";

		try {
			connection =JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				pstmt=connection.prepareStatement(sqlInsertQuery);
				if(pstmt!=null)
				{
					pstmt.setString(1,staff.getStaffName() );
					pstmt.setString(2,staff.getStaffEmailno() );
					pstmt.setString(3,staff.getStaffPassword() );
					pstmt.setString(4,staff.getStaffGender() );
					pstmt.setLong(5,staff.getStaffPhno() );
					pstmt.setString(6,staff.getStaffAddress() );
				
					
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

	@Override
	public Staff searchStaff(Integer staffId) {
	    String sqlSelectQuery = "SELECT staffId, staffName,staffEmailno,staffPassword,staffGender,staffPhno,staffAddress FROM staff WHERE staffId = ?";

	    Staff staff =null;
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet res = null;
	    staff = new Staff();

	    try {
	        connection = JdbcUtil.getJdbcConnection();
	        pstmt = connection.prepareStatement(sqlSelectQuery);
	        pstmt.setInt(1, staffId);

	        res = pstmt.executeQuery();

	        if (res.next()) {
	        	
	        	staff.setStaffId(res.getInt(1));
	        	staff.setStaffName(res.getString(2));
	        	staff.setStaffEmailno(res.getString(3));
	        	staff.setStaffPassword(res.getString(4));
	        	staff.setStaffGender(res.getString(5));
	        	staff.setStaffPhno(res.getLong(6));
	        	staff.setStaffAddress(res.getString(7));
	        	
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (res != null) {
	                res.close();
	            }
	            if (pstmt != null) {
	                pstmt.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return staff;
	}

	@Override
	public String updateStaff(Staff staff) 
	{
		 String updateQuery = "UPDATE Staff SET staffName=?, staffEmailno=?, staffPassword=?, staffGender=?, staffPhno=?, staffAddress=? WHERE staffId = ?";
;
        
		 try {
		        connection = JdbcUtil.getJdbcConnection();
		        if (connection != null) 
		        {
		        	 pstmt = connection.prepareStatement(updateQuery);
		        	if(pstmt!=null) 
		        	{
		            pstmt.setString(1, staff.getStaffName());
		            pstmt.setString(2, staff.getStaffEmailno());
		            pstmt.setString(3, staff.getStaffPassword());
		            pstmt.setString(4, staff.getStaffGender());
		            pstmt.setLong(5, staff.getStaffPhno());
		            pstmt.setString(6, staff.getStaffAddress());
		            pstmt.setInt(7, staff.getStaffId());

		            int rowsUpdated = pstmt.executeUpdate();
		            if (rowsUpdated == 1)
		            {
		                return "success";
		            }
		            
		        	}
		        }
		 }
		        catch (SQLException | IOException e) 
		        {
		        e.printStackTrace();
		        }
		 finally {
		        try {
		            if (pstmt != null) {
		                pstmt.close();
		            }
		            if (connection != null) {
		                connection.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    
		    return "failure";
		}
		    
	

	@Override
	public String deleteStaff(Integer staffId) {
		String sqlDeleteQuery = "delete from staff where staffId=? ";

		
		try {
			connection =JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				pstmt=connection.prepareStatement(sqlDeleteQuery);
				if(pstmt!=null)
				{
					pstmt.setInt(1, staffId);
				
				int rowAffected=pstmt.executeUpdate();
				if(rowAffected==1)
				{
					return "success";
				}
				else
				{
					return "not found";
				}
			    }	

			}
		}catch (SQLException | IOException e) 
		{
			
			e.printStackTrace();
			return "failure";
			
		}
		return "failure";
		
		
		
	

	}


	}
		
		
	