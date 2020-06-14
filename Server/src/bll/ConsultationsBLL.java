package bll;

import java.text.ParseException;
import java.util.List;


import model.Consultation;
import operations.ConsultationDoctorContext;
import operations.ConsultationsOp;
import operations.DefaultDoctor;
import operations.SpecificDoctor;

public class ConsultationsBLL {

	public static List<Object> viewAllConsultations() {

		return ConsultationsOp.findAll();
	}

	public static Consultation viewConsultation(int id) {

		return ConsultationsOp.findById(id);
	}

	public static void insertConsultation(Consultation consultation) {

		if(consultation.getDoctorName().equals("-"))
		{
			ConsultationDoctorContext ctx=new ConsultationDoctorContext(new DefaultDoctor());
			ctx.insertConsultation(consultation);
		}
		else {
			ConsultationDoctorContext ctx=new ConsultationDoctorContext(new SpecificDoctor());
			ctx.insertConsultation(consultation);
		}
			
	}

	public static Consultation editConsultation(Consultation consultation) {

		return ConsultationsOp.editConsultation(consultation);
	}

	public static void deleteConsultation(int id) {

		ConsultationsOp.deleteConsultation(id);
	}

	public static List<Object> findScheduledConsultations(String doctorName) throws ParseException{
		return ConsultationsOp.findScheduledConsultations(doctorName);
	}

	public static void generateReport(String type, int id, String location) {
	 	ConsultationsOp.generateReport(type, id, location);
	}

}
