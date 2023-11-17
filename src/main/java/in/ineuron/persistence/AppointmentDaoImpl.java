package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import in.ineuron.dto.Appointment;
import in.ineuron.dto.Doctor;
import in.ineuron.dto.Patient;
import in.ineuron.util.JdbcUtil;

public class AppointmentDaoImpl implements IAppointmentDao {
    Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet res = null;

    @Override
    public String addAppointment(Appointment appointment) {
        PatientDaoImpl patientImpl = new PatientDaoImpl();
        Patient patient = patientImpl.searchPatient(appointment.getPatientId());
        DoctorDaoImpl doctorImpl = new DoctorDaoImpl();
        Doctor doctor = doctorImpl.searchDoctor(appointment.getDoctorId());

        String sqlInsertQuery = "INSERT INTO Appointment (patientId, patientName, patientAge, patientGender, doctorId, doctorName, DateTime) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = JdbcUtil.getJdbcConnection();
            if (connection != null) {
                pstmt = connection.prepareStatement(sqlInsertQuery);
                if (pstmt != null) {
                    pstmt.setInt(1, appointment.getPatientId());
                    pstmt.setString(2, patient.getPatientName());
                    pstmt.setInt(3, patient.getPatientAge());
                    pstmt.setString(4, patient.getPatientGender());
                    pstmt.setInt(5, appointment.getDoctorId());
                    pstmt.setString(6, doctor.getDoctorName());
                
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = appointment.getDateTime().format(formatter);

                    pstmt.setString(7, formattedDateTime);

                    int rowAffected = pstmt.executeUpdate();
                    if (rowAffected == 1) {
                        return "success";
                    } else {
                        return "failure";
                    }
                }
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
        return "failure";
    }

    @Override
    public Appointment searchAppointment(Integer appointmentId) {
        String sqlSelectQuery = "SELECT appointmentId, patientId, patientName, patientAge, patientGender, doctorId, doctorName, DateTime FROM Appointment WHERE appointmentId = ?";
        Appointment appointment = null;

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        appointment = new Appointment();
        try {
            connection = JdbcUtil.getJdbcConnection();
            pstmt = connection.prepareStatement(sqlSelectQuery);
            pstmt.setInt(1, appointmentId);

            res = pstmt.executeQuery();
            if (res.next()) {
                appointment.setAppointmentId(res.getInt(1));
                appointment.setPatientId(res.getInt(2));
                appointment.setPatientName(res.getString(3));
                appointment.setPatientAge(res.getInt(4));
                appointment.setPatientGender(res.getString(5));
                appointment.setDoctorId(res.getInt(6));
                appointment.setDoctorName(res.getString(7));

               
                String dateTimeString = res.getString(8).replace('T', ' ');

                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                appointment.setDateTime(dateTime);


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
        return appointment;
    }

    @Override
    public String updateAppointment(Appointment appointment) {
        String updateQuery = "UPDATE Appointment SET patientId=?, patientName=?, patientAge=?, patientGender=?, doctorId=?, doctorName=?, DateTime=? WHERE appointmentId = ?";

        try {
            connection = JdbcUtil.getJdbcConnection();
            if (connection != null) {
                pstmt = connection.prepareStatement(updateQuery);
                if (pstmt != null) {
                    pstmt.setInt(1, appointment.getPatientId());
                    pstmt.setString(2, appointment.getPatientName());
                    pstmt.setInt(3, appointment.getPatientAge());
                    pstmt.setString(4, appointment.getPatientGender());
                    pstmt.setInt(5, appointment.getDoctorId());
                    pstmt.setString(6, appointment.getDoctorName());

                    // Convert LocalDateTime to a formatted string for database update
                    

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = appointment.getDateTime().format(formatter);

                    pstmt.setString(7, formattedDateTime);

                    pstmt.setInt(8, appointment.getAppointmentId());

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
    public String deleteAppointment(Integer appointmentId) {
        String sqlDeleteQuery = "DELETE FROM Appointment WHERE appointmentId = ?";

        try {
            connection = JdbcUtil.getJdbcConnection();
            if (connection != null) {
                pstmt = connection.prepareStatement(sqlDeleteQuery);
                if (pstmt != null) {
                    pstmt.setInt(1, appointmentId);
                    int rowAffected = pstmt.executeUpdate();
                    if (rowAffected == 1) {
                        return "success";
                    } else {
                        return "not found";
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return "failure";
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
}
