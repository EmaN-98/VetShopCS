package bll;

import java.util.List;


import model.Animal;
import operations.AnimalsOp;


public class AnimalsBLL {

	public static List<Object> viewAllAnimals() {

		return AnimalsOp.findAll();
	}

	public static Animal viewAnimal(int id) {

		return AnimalsOp.findById(id);
	}

	public static int insertAnimal(Animal animal) {

		return AnimalsOp.insertAnimal(animal);
	}

	public static Animal editAnimal(Animal animal) {

		return AnimalsOp.editAnimal(animal);
	}

	public static void deleteAnimal(int id) {

		AnimalsOp.deleteAnimal(id);
	}



}
