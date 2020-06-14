package model;

import java.io.Serializable;
import java.util.List;

public class Animal implements Serializable{

	private static final long serialVersionUID = 1L;
	private int animalId;
	private String name;
	private String species;
	private String owner;
	private int age;
	private String diseases;
	private String treatments;
	public int getAnimalId() {
		return animalId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDiseases() {
		return diseases;
	}
	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}
	public String getTreatments() {
		return treatments;
	}
	public void setTreatments(String treatments) {
		this.treatments = treatments;
	}
	public Animal(int animalId, String name, String species, String owner, int age, String diseases,
			String treatments) {
		super();
		this.animalId = animalId;
		this.name = name;
		this.species = species;
		this.owner = owner;
		this.age = age;
		this.diseases = diseases;
		this.treatments = treatments;
	}
	public Animal(String name, String species, String owner, int age, String diseases, String treatments) {
		super();
		this.name = name;
		this.species = species;
		this.owner = owner;
		this.age = age;
		this.diseases = diseases;
		this.treatments = treatments;
	}
	public Animal() {
		super();
	}

	public static String[] getHeader(){
		String []head=new String[7];
		head[0]="animalId";
		head[1]="name";
		head[2]="species";
		head[3]="owner";
		head[4]="age";
		head[5]="diseases";
		head[6]="treatments";
		return head;
	}

	public static Object[][] getData(List<Object> list){
		Object [][]data=new Object[list.size()][7];
		int i=0;
		for(Object o:list){
		 	Animal a=(Animal)o;
			data[i][0]=a.getAnimalId();
			data[i][1]=a.getName();
			data[i][2]=a.getSpecies();
			data[i][3]=a.getOwner();
			data[i][4]=a.getAge();
			data[i][5]=a.getDiseases();
			data[i][6]=a.getTreatments();
			i++;
		}
		return data;
	}

	@Override
	public String toString() {
		return "Animal [animalId=" + animalId + ", name=" + name + ", species=" + species + ", owner=" + owner
				+ ", age=" + age + ", diseases=" + diseases + ", treatments=" + treatments + "]\n";
	}



}
