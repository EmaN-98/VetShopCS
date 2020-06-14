package report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import model.Consultation;
import operations.ConsultationsOp;

public class TxtReport extends Report{

	private static Logger logger;
	public void generateReport(String path, int consultationId) throws IOException {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter =null;
		try {										

			File file = new File(path, "TxtReport.txt");
			 fileWriter = 
					new FileWriter(file);
			 bufferedWriter =
					new BufferedWriter(fileWriter);

			Consultation c=new Consultation();
			c=ConsultationsOp.findById(consultationId);
			bufferedWriter.write("Report for consultation no."+c.getConsultationId()+"\nAnimal's name: "+c.getAnimalName()+"\nDoctor's name: "+c.getDoctorName()+"\ndate: "+c.getDate()+"\ndiagnosis: "+c.getDiagnosis()+"\ntreatment: "+c.getTreatment());

			
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file 'TxtReport.txt'");
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}finally {
			bufferedWriter.close();
			fileWriter.close();
		}

	}


}
