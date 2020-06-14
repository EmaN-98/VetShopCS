package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import connection.ConnectionMake;
import model.Consultation;
import report.Report;
import report.ReportFactory;



public class ConsultationsOp {

	private static Connection conn;
	private final static String deleteConsultation = "DELETE FROM consultations WHERE consultationId = ? ";
	private final static String editConsultation= "UPDATE consultations SET animalName=?, doctorName=?, date=?, diagnosis=?, treatment=?, state=? WHERE consultationId= ? ";
	private final static String findStatementString = "SELECT * FROM consultations where consultationId = ?";
	private static Logger logger;
	
	public static void deleteConsultation(int id) {
		PreparedStatement stmt = null;
		ResultSet res=null;
		try {
			if (Pattern.matches("[0-9]+",String.valueOf(id)) && !String.valueOf(id).isEmpty() ) {

				conn = ConnectionMake.getConnection();
				stmt = conn.prepareStatement(deleteConsultation, Statement.RETURN_GENERATED_KEYS);
				stmt.setLong(1, id);
				stmt.executeUpdate();
				res = stmt.getGeneratedKeys();

				JOptionPane.showMessageDialog(null,"Consultation successfully deleted.");}

		} catch (SQLException exc) {
			exc.printStackTrace();
			JOptionPane.showMessageDialog(null, "Sorry, cannot delete consultation.");

		} finally {
			ConnectionMake.close(stmt);
			ConnectionMake.close(res);
			ConnectionMake.close(conn);
		}
	}

	public static Consultation editConsultation(Consultation c) {

		PreparedStatement stmt = null;
		ResultSet res=null;
		try {
			String animalName=c.getAnimalName();
 			String doctorName=c.getDoctorName();
			String date=c.getDate();
			String diagnosis=c.getDiagnosis();
			String treatment=c.getTreatment();
			String state=c.getState();
			int id=c.getConsultationId();

			boolean ok1=false;
			boolean ok2=false;
			boolean ok3=false;
			boolean ok4=false;
			boolean ok5=false;
			boolean ok6=false;
			boolean ok7=false;

			if(Pattern.matches("[a-z A-Z]+" ,animalName)&&!animalName.isEmpty()) {
				ok1=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Check again animalName!");
			}

			if(Pattern.matches("[a-z A-Z]+" ,doctorName)&&!doctorName.isEmpty()) {
				ok2=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Check again doctorName!");
			}

			if(Pattern.matches("^\\d?\\d.\\d{2}.\\d{4}$" ,date)&&!date.isEmpty()) {
				ok3=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again date!");
			}

			if (Pattern.matches("[a-z A-Z]+",diagnosis) && !diagnosis.isEmpty() ) {

				ok4=true;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Check again diagnosis!");
			}

			if(Pattern.matches("[a-z A-Z]+" ,treatment)&&!treatment.isEmpty()) {
				ok5=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again treatment!");
			}

			if(Pattern.matches("[0-9]+" ,String.valueOf(id))&&!String.valueOf(id).isEmpty()) {
				ok6=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again ID!");
			}
			if(Pattern.matches("[a-z A-Z]+" ,state)&&!state.isEmpty()) {
				ok7=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again state!");
			}

			if(ok3&&ok1&&ok2&&ok4&&ok5&&ok6&&ok7) {

				conn = ConnectionMake.getConnection();
				stmt = conn.prepareStatement(editConsultation, Statement.RETURN_GENERATED_KEYS);

				stmt.setString(1, c.getAnimalName());
				stmt.setString(2, c.getDoctorName());
				stmt.setString(3, c.getDate());
				stmt.setString(4, c.getDiagnosis());
				//if(c.getState().equals("in progress"))
				stmt.setString(5, c.getTreatment());
				stmt.setString(6, c.getState());
				stmt.setInt(7, c.getConsultationId());

				int changes =stmt.executeUpdate();
				res = stmt.getGeneratedKeys();

				if(changes > 0) { 
					JOptionPane.showMessageDialog(null,"Consultation successfully edited.");
					return c;
				} else {
					return null;
				}
			}
		} catch (Exception exc) {
			logger.severe(Arrays.toString(exc.getStackTrace()));
			
			JOptionPane.showMessageDialog(null,
					"Sorry, cannot edit consultation. Check again the data desired");

		} finally {
			ConnectionMake.close(stmt);
			ConnectionMake.close(res);
			ConnectionMake.close(conn);
		}
		return null;
	}


	public static List<Object> findAll() {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Object> consultations = new ArrayList<>();
		try {
			conn = ConnectionMake.getConnection();
			 preparedStatement = conn.prepareStatement("SELECT * FROM consultations");
			 resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Consultation c = new Consultation(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
				consultations.add(c);
			}
		} catch (SQLException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	 finally {
		ConnectionMake.close(preparedStatement);
		ConnectionMake.close(resultSet);
		ConnectionMake.close(conn);
	}
		return consultations;
	}

	public static Consultation findById(int consultationId) {

		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			if (Pattern.matches("[0-9]+",String.valueOf(consultationId)) && !String.valueOf(consultationId).isEmpty() ) {
				conn = ConnectionMake.getConnection();
				 preparedStatement = conn.prepareStatement(findStatementString);
				preparedStatement.setInt(1, consultationId);

				 resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					return new Consultation(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
					
				}}
		} catch (SQLException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
			JOptionPane.showMessageDialog(null,"Sorry, cannot show consultation information. Check ID");
		}finally {
			ConnectionMake.close(preparedStatement);
			ConnectionMake.close(resultSet);
			ConnectionMake.close(conn);
		}

		return null;
	}

	public static List<Object> findScheduledConsultations(String doctorName) throws ParseException {

		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		List<Object> sch = new ArrayList<Object>();
		try {
			conn = ConnectionMake.getConnection();
			 preparedStatement = conn.prepareStatement("SELECT * FROM consultations WHERE doctorName=?");
			preparedStatement.setString(1, doctorName);
			 resultSet = preparedStatement.executeQuery();


			while (resultSet.next()) {
				Consultation c = new Consultation(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));

				String pattern = "dd.MM.yyyy";
				String current =new SimpleDateFormat(pattern).format(new Date());
				
				String date=resultSet.getString(4);

				Date dateCurrent=new SimpleDateFormat("dd.MM.yyyy").parse(current);  
				Date dateSch=new SimpleDateFormat("dd.MM.yyyy").parse(date);  

				String state=resultSet.getString(7);
				if (dateSch.compareTo(dateCurrent) >= 0 && "scheduled".equals(state)) {
					sch.add(c);
					System.out.println("scheduled");}
				else {System.out.println("in progress/done");}

			}
		} catch (SQLException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}finally {
			ConnectionMake.close(preparedStatement);
			ConnectionMake.close(resultSet);
			ConnectionMake.close(conn);
		}
		return (List<Object>) sch;
	}

	public static void generateReport(String type, int id, String location) {
		try {
			
			if("txt".equals(type)) {
				
				Report r=ReportFactory.generateReport("txt");
				r.generateReport(location, id); //double '\' -> '\\' in location
			}
			else if("pdf".equals(type)) {
				
				Report r=ReportFactory.generateReport("pdf");
				r.generateReport(location, id);
			}
			JOptionPane.showMessageDialog(null,"Report successfully generated!");
		}catch(Exception ex) {
			logger.severe(Arrays.toString(ex.getStackTrace()));
			
			JOptionPane.showMessageDialog(null,"Sorry, cannot generate report. Check ID&type&location");
		}

	}
}
