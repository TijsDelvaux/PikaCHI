package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;


@MappedSuperclass 
public class Advertisement extends Model {
	
	@Id
    public Long id;
	@ManyToOne
	public Student student;
	public String studies;
	public String description;
	public boolean testAd;
	
	//static methods
	
	public static Advertisement create(Student student, String studies, String description, boolean testAd) {
		Advertisement ad = new Advertisement(student, studies, description, testAd);
		ad.save();
        return ad;
    }
	
    public static Model.Finder<Long,Advertisement> find = new Model.Finder(Long.class, Advertisement.class);
	
    public static List<Advertisement> findInvolving(String user) {
        return find.where()
            .eq("members.email", user)
            .findList();
    }
    
    //actual implementation
	
	public Advertisement(Student student, String studies, String description, boolean testAd){
		this.studies = studies;
		this.description = description;
		this.student = student;
		this.testAd = testAd;
	}

	public Advertisement(Student student){
		this.student = student;
		this.studies = "";
		this.description = "";
	}
	
	public Student getStudent() {
		return student;
	}
	
	public String getStudies() {
		return studies;
	}

	public void setStudies(String studies) {
		this.studies = studies;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
	
	public boolean isTest(){
		return testAd;
	}
	
	
}
