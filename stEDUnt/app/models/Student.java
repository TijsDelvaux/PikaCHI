package models;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	private StudentAdvertisement studentAdv;
	private TutorAdvertisement tutorAdv;
	private List<Message> messages;
	
	public Student(){
		studentAdv = null;
		tutorAdv = null;
		messages = new ArrayList<Message>();
	}
}
