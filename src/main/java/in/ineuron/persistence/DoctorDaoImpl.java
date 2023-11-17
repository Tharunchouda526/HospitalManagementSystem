package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Doctor;
import in.ineuron.util.JdbcUtil;

//peristence Logic using JDBC API
public class DoctorDaoImpl implements IDoctorDao 
{
   Connection connection=null;
   PreparedStatement pstmt =null;
   ResultSet res=null;
   
   
	@Override
	public String addDoctor(Doctor doctor)
	{
		String sqlInsertQuery = "insert into Doctor (doctorName,doctorEmail,doctorPassword,doctorAge,doctorGender,doctorPhno,doctorAddress,doctorSpecialization) values(?,?,?,?,?,?,?,?)";

		try {
			connection =JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				pstmt=connection.prepareStatement(sqlInsertQuery);
				if(pstmt!=null)
				{
					pstmt.setString(1,doctor.getDoctorName() );
					pstmt.setString(2,doctor.getDoctorEmail() );
					pstmt.setString(3,doctor.getDoctorPassword() );
					pstmt.setInt(4,doctor.getDoctorAge() );
					pstmt.setString(5,doctor.getDoctorGender() );
					pstmt.setLong(6,doctor.getDoctorPhno() );
					pstmt.setString(7,doctor.getDoctorAddress() );
					pstmt.setString(8,doctor.getDoctorSpecialization() );
					
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
	public Doctor searchDoctor(Integer doctorId) {
	    String sqlSelectQuery = "SELECT doctorId, doctorName, doctorEmail,doctorPassword, doctorAge, doctorGender, doctorPhno,doctorSpecialization, doctorAddress FROM Doctor WHERE doctorId = ?";

	    Doctor doctor = null;
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet res = null;
	    doctor = new Doctor();
	    try {
	        connection = JdbcUtil.getJdbcConnection();
	        pstmt = connection.prepareStatement(sqlSelectQuery);
	        pstmt.setInt(1, doctorId);

	        res = pstmt.executeQuery();

	        if (res.next()) {
	            doctor.setDoctorId(res.getInt(1));
	            doctor.setDoctorName(res.getString(2));
	            doctor.setDoctorEmail(res.getString(3));
	            doctor.setDoctorPassword(res.getString(4));
	            doctor.setDoctorAge(res.getInt(5));
	            doctor.setDoctorGender(res.getString(6));
	            doctor.setDoctorPhno(res.getLong(7));
	            doctor.setDoctorSpecialization(res.getString(8));
	            doctor.setDoctorAddress(res.getString(9));
	            
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

	    return doctor;
	}


	@Override
	public String updateDoctor(Doctor doctor) {
	    String updateQuery = "UPDATE Doctor SET doctorName=?, doctorEmail=?, doctorPassword=?, doctorAge=?, doctorGender=?, doctorPhno=?, doctorSpecialization=?, doctorAddress=? WHERE doctorId = ?";
	    
	    try {
	        connection = JdbcUtil.getJdbcConnection();
	        if (connection != null) {
	            pstmt = connection.prepareStatement(updateQuery);
	            if (pstmt!= null) {
	                pstmt.setString(1, doctor.getDoctorName());
	                pstmt.setString(2, doctor.getDoctorEmail());
	                pstmt.setString(3, doctor.getDoctorPassword());
	                pstmt.setInt(4, doctor.getDoctorAge());
	                pstmt.setString(5, doctor.getDoctorGender());
	                pstmt.setLong(6, doctor.getDoctorPhno());
	                pstmt.setString(7, doctor.getDoctorSpecialization());
	                pstmt.setString(8, doctor.getDoctorAddress());
	                pstmt.setInt(9, doctor.getDoctorId());

	                int rowsUpdated = pstmt.executeUpdate();
	                if (rowsUpdated == 1) {
	                    return "success";
	                } 
	            }
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    } finally {
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
	public String deleteDoctor(Integer doctorId) {
		String sqlDeleteQuery = "delete from doctor where doctorId=? ";

		
		try {
			connection =JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				pstmt=connection.prepareStatement(sqlDeleteQuery);
				if(pstmt!=null)
				{
					pstmt.setInt(1, doctorId);
				
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
