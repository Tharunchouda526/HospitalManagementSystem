package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.FeedBack;
import in.ineuron.dto.Patient;
import in.ineuron.util.JdbcUtil;

//peristence Logic using JDBC API
public class FeedBackDaoImpl implements IFeedBackDao 
{
   Connection connection=null;
   PreparedStatement pstmt =null;
   ResultSet res=null;
   
   
	@Override
	public String addFeedBack(FeedBack feedback)
	{
		String sqlInsertQuery = "insert into feedback (name,email,message) values(?,?,?)";

		try {
			connection =JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				pstmt=connection.prepareStatement(sqlInsertQuery);
				if(pstmt!=null)
				{
					pstmt.setString(1,feedback.getName() );
					pstmt.setString(2,feedback.getEmail() );
					pstmt.setString(3, feedback.getMessage());
					
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
	public FeedBack searchFeedBack(Integer number) {
	    String sqlSelectQuery = "SELECT number,name,email,message from feedback WHERE number = ?";

	     FeedBack feedback =null;
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet res = null;
	    feedback = new FeedBack();

	    try {
	        connection = JdbcUtil.getJdbcConnection();
	        pstmt = connection.prepareStatement(sqlSelectQuery);
	        pstmt.setInt(1, number);

	        res = pstmt.executeQuery();

	        if (res.next()) {
	        	
	            feedback.setNumber(res.getInt(1));
	            feedback.setName(res.getString(2));
	            feedback.setEmail(res.getString(3));
	            feedback.setMessage(res.getString(4));
	          
	            
	            
	            
	           
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

	    return feedback;
	}

}