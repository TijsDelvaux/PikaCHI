package models;

public class StudentAdvertisement extends Advertisement {
	
	public StudentAdvertisement(Student student, String studies, String description){
		super(student,studies,description);
	}

	public StudentAdvertisement(Student student){
		super(student);
	}
}
