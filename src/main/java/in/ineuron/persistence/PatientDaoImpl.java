package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Patient;
import in.ineuron.util.JdbcUtil;

//peristence Logic using JDBC API
public class PatientDaoImpl implements IPatientDao 
{
   Connection connection=null;
   PreparedStatement pstmt =null;
   ResultSet res=null;
   
   
	@Override
	public String addPatient(Patient patient)
	{
		String sqlInsertQuery = "insert into Patient (patientName,patientEmailno,patientPassword,patientAge,patientGender,patientPhno,patientAddress,patientBloodGroup) values(?,?,?,?,?,?,?,?)";

		try {
			connection =JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				pstmt=connection.prepareStatement(sqlInsertQuery);
				if(pstmt!=null)
				{
					pstmt.setString(1,patient.getPatientName() );
					pstmt.setString(2,patient.getPatientEmailno() );
					pstmt.setString(3, patient.getPatientPassword());
					pstmt.setInt(4,patient.getPatientAge() );
					pstmt.setString(5,patient.getPatientGender() );
					pstmt.setLong(6,patient.getPatientPhno() );
					pstmt.setString(7,patient.getPatientAddress() );
					pstmt.setString(8,patient.getPatientBloodGroup() );
					
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
	public Patient searchPatient(Integer patientId) {
	    String sqlSelectQuery = "SELECT patientId,patientName,patientEmailno,patientPassword,patientAge,patientGender,patientPhno,patientAddress,patientBloodGroup from patient WHERE patientId = ?";

	    Patient patient = null;
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet res = null;
	    patient = new Patient();
	    try {
	        connection = JdbcUtil.getJdbcConnection();
	        pstmt = connection.prepareStatement(sqlSelectQuery);
	        pstmt.setInt(1, patientId);

	        res = pstmt.executeQuery();
	       
	        if (res.next()) {
	          
	            patient.setPatientId(res.getInt(1));
	            patient.setPatientName(res.getString(2));
	            patient.setPatientEmailno(res.getString(3));
	            patient.setPatientPassword(res.getString(4));
	            patient.setPatientAge(res.getInt(5));
	            patient.setPatientGender(res.getString(6));
	            patient.setPatientPhno(res.getLong(7));
	            patient.setPatientAddress(res.getString(8));
	            patient.setPatientBloodGroup(res.getString(9));
	            
	            
	            
	           
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

	    return patient;
	}

	@Override
	public String updatePatient(Patient patient) 
	{
		 String updateQuery = "UPDATE Patient SET patientName=?,patientEmailno=?,patientPassword=?,patientAge=?,patientGender=?,patientPhno=?,patientAddress=?,patientBloodGroup=? WHERE patientId=?";
        
		 try {
		        connection = JdbcUtil.getJdbcConnection();
		        if (connection != null) 
		        {
		        	 pstmt = connection.prepareStatement(updateQuery);
		        	if(pstmt!=null) 
		        	{
		            pstmt.setString(1, patient.getPatientName());
		            pstmt.setString(2,patient.getPatientEmailno());
		            pstmt.setString(3,patient.getPatientPassword());
		            pstmt.setInt(4, patient.getPatientAge());
		            pstmt.setString(5, patient.getPatientGender());
		            pstmt.setLong(6, patient.getPatientPhno());
		            pstmt.setString(7, patient.getPatientAddress());
		            pstmt.setString(8, patient.getPatientBloodGroup());
		            pstmt.setInt(9, patient.getPatientId());

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
		   
		    return "failure";
	}

	@Override
	public String deletePatient(Integer patientId) {
		String sqlDeleteQuery = "delete from patient where patientId=? ";

		
		try {
			connection =JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				pstmt=connection.prepareStatement(sqlDeleteQuery);
				if(pstmt!=null)
				{
					pstmt.setInt(1, patientId);
				
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
