package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;


@Entity
public class StudentAdvertisement extends Advertisement {
	
	public StudentAdvertisement(Student student, String studies, String description){
		super(student,studies,description);
	}

	public StudentAdvertisement(Student student){
		super(student);
	}
	
	//static methods
	
	public static StudentAdvertisement create(Student student, String studies, String description) {
		
		StudentAdvertisement ad = new StudentAdvertisement(student, studies, description);
		ad.save();
        return ad;
    }
	
    public static Model.Finder<Long,StudentAdvertisement> find = new Model.Finder(Long.class, StudentAdvertisement.class);
	
//    public static List<StudentAdvertisement> findStudentAdvInvolving(String user) {
//        return find.where()
//            .eq("members.email", user)
//            .findList();
//    }
	
}
