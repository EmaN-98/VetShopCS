package model;

import java.io.Serializable;
import java.util.List;

public class Consultation implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int consultationId;
	private String animalName;
	private String doctorName;
	private String date;
	private String diagnosis;
	private String treatment;
	private String state;

	public String getState() {
 		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getConsultationId() {
		return consultationId;
	}
	public void setConsultationId(int consultationId) {
		this.consultationId = consultationId;
	}
	public String getAnimalName() {
		return animalName;
	}
	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public Consultation(int consultationId, String animalName, String doctorName, String date, String diagnosis,
			String treatment,String state) {
		super();
		this.consultationId = consultationId;
		this.animalName = animalName;
		this.doctorName = doctorName;
		this.date = date;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.state=state;
	}
	public Consultation(String animalName, String doctorName, String date, String diagnosis, String treatment,String state) {
		super();
		this.animalName = animalName;
		this.doctorName = doctorName;
		this.date = date;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.state=state;
	}
	public Consultation() {
		super();
	}


	public static String[] getHeader(){
		String []head=new String[7];
		head[0]="consultationId";
		head[1]="animalName";
		head[2]="doctorName";
		head[3]="date";
		head[4]="diagnosis";
		head[5]="treatment";
		head[6]="state";
		return head;
	}

	public static Object[][] getDate(List<Consultation> list){
		Object [][]data=new Object[list.size()][7];
		int i=0;
		for(Object o:list){
			Consultation c=(Consultation)o;
			data[i][0]=c.getConsultationId();
			data[i][1]=c.getAnimalName();
			data[i][2]=c.getDoctorName();
			data[i][3]=c.getDate();
			data[i][4]=c.getDiagnosis();
			data[i][5]=c.getTreatment();
			data[i][6]=c.getState();
			i++;
		}
		return data;
	}
	@Override
	public String toString() {
		return "Consultation [consultationId=" + consultationId + ", animalName=" + animalName + ", doctorName="
				+ doctorName + ", date=" + date + ", diagnosis=" + diagnosis + ", treatment=" + treatment + ", state="
				+ state + "\n]";
	}







}
