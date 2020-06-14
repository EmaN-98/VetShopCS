package operations;

import model.Consultation;

public class ConsultationDoctorContext {

	private ConsultationDoctorStrategy strategy;

	public ConsultationDoctorContext(ConsultationDoctorStrategy strategy) {
		
		this.strategy = strategy;
	}
	
	public void insertConsultation(Consultation c) {
		
	 	strategy.insertConsultation(c);
	}
	
	
}
