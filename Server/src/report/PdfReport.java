package report;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import model.Consultation;
import operations.ConsultationsOp;

public class PdfReport extends Report{

	private Logger logger;
	public void generateReport(String path, int consultationId) {

		try {										
			Consultation c=new Consultation();
			c=ConsultationsOp.findById(consultationId);//////

			PDDocument document = new PDDocument();
			PDPage pg=new PDPage();
			document.addPage(pg);

			PDPageContentStream cont = new PDPageContentStream(document, pg);

			cont.beginText();

			cont.setFont(PDType1Font.TIMES_ROMAN, 12);
			cont.setLeading(14.5f);

			cont.newLineAtOffset(25, 700);

			cont.showText("Report for consultation no."+c.getConsultationId());
			cont.newLine();		                cont.newLine();

			cont.showText("Animal's name: "+c.getAnimalName());
			cont.newLine();

			cont.showText("Doctor's name: "+c.getDoctorName());
			cont.newLine();

			cont.showText("Date: "+c.getDate());
			cont.newLine();

			cont.showText("Diagnosis: "+c.getDiagnosis());
			cont.newLine();

			cont.showText("Treatment: "+c.getTreatment());
			cont.newLine();

			cont.endText();
			cont.close();

			document.save(path+"\\PdfReport.pdf");
			document.close();

		}
		catch(FileNotFoundException ex) {
	 		System.out.println(
					"Unable to open file 'PdfReport.pdf'");
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}

	}




}
