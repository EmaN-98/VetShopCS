package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import connection.ConnectionMake;
import model.Consultation;

public class DefaultDoctor implements ConsultationDoctorStrategy{

	private static Connection conn;
	private final static String insertConsultation = "INSERT INTO consultations (animalName,doctorName,date,diagnosis,treatment,state)" + " VALUES (?,?,?,?,?,?)";
	private final static String getDefaultDoctor ="select doctorName, count(*) as nr FROM vetshop.consultations GROUP BY doctorName ORDER BY nr ASC";
	private Logger logger;
	
	@Override
	public void insertConsultation(Consultation c) {

		PreparedStatement stmt = null;
		ResultSet res=null;

		
		try {

			//userId=u.getUserId();
			String animalName=c.getAnimalName();
			String doctorName=c.getDoctorName();
			String date=c.getDate();
			String diagnosis=c.getDiagnosis();
			String treatment=c.getTreatment();

			boolean ok1=false;
			boolean ok2=false;
			boolean ok3=false;
			boolean ok4=false;
			boolean ok5=false;
			
			if(Pattern.matches("[a-z A-Z]+" ,animalName)&&!animalName.isEmpty()) {
				ok1=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again animalName!");
			}

			if(Pattern.matches("-" ,doctorName)&&!doctorName.isEmpty()) {
				ok2=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again doctorName!");
			}

			if(Pattern.matches("^\\d?\\d.\\d{2}.\\d{4}$" ,date)&&!date.isEmpty()) {
				ok3=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Check again date!");
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


			if(ok3&&ok1&&ok2&&ok4&&ok5) {
				conn = connection.ConnectionMake.getConnection();
				String doc="";
				ResultSet rs= conn.createStatement().executeQuery(getDefaultDoctor);
				if (rs.next()) {
					doc=rs.getString("doctorName");
				    System.out.println(doc);
				}
				//stmt = conn.prepareStatement(getDefaultDoctor, Statement.RETURN_GENERATED_KEYS);
				
				stmt = conn.prepareStatement(insertConsultation, Statement.RETURN_GENERATED_KEYS);

				stmt.setString(1, animalName);
				stmt.setString(2, doc);
				stmt.setString(3, date);
				stmt.setString(4, diagnosis);
				stmt.setString(5, treatment);
				stmt.setString(6, "scheduled");
				
				stmt.executeUpdate();

				res = stmt.getGeneratedKeys();
				JOptionPane.showMessageDialog(null,"Consultation successfully added.");
			}

		} catch (Exception exc) {

			logger.severe(Arrays.toString(exc.getStackTrace()));
			
			JOptionPane.showMessageDialog(null, "Sorry, cannot insert consultation. ");

		} finally {
			ConnectionMake.close(stmt);
			ConnectionMake.close(res);
			ConnectionMake.close(conn);
		}
		

	}


}
