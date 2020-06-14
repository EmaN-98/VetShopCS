package report;

public class ReportFactory {

	public static Report generateReport(String type) {
		if ( "pdf".equals(type) )
			{return new PdfReport();}
		else if ( "txt".equals(type) )
			{return new TxtReport();}

		return null;
	}


}
