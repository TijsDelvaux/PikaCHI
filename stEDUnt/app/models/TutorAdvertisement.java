package models;

public class TutorAdvertisement extends Advertisement{
	
	private double price;
	
	public TutorAdvertisement(Student student, String studies, String description, double price){
		super(student,studies,description);
		this.price = price;
	}
	
	public TutorAdvertisement(Student student){
		super(student);
		this.price = 0.0;
	}
}
