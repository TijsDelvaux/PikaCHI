package models;

import play.db.ebean.Model;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;


@Entity
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
	
	public double getPrice(){
		return price;
	}
	
	
	//static methods
	
	public static TutorAdvertisement create(Student student, String studies, String description, double price) {
		
		TutorAdvertisement ad = new TutorAdvertisement(student, studies, description, price);
		ad.save();
        return ad;
    }
	
    public static Model.Finder<Long,TutorAdvertisement> find = new Model.Finder(Long.class, TutorAdvertisement.class);
	
    public static TutorAdvertisement findFromUser(String user) {
        try{
        	
        	System.out.println(user);
        	
        	return find.where()
            .eq("student_email", user)
            .findList().get(0);
        }
        catch(IndexOutOfBoundsException e){
        	return null;
        }
    }
	
	
	
}
