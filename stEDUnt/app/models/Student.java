package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

@Entity
public class Student extends Model{
	
	private StudentAdvertisement studentAdv;
	private TutorAdvertisement tutorAdv;
	private List<Message> messages;
	
    @Id
    public String email;
    public String name;
    public String password;
    public Language language;
    
	public Student(String email, String name, String password){
		studentAdv = null;
		tutorAdv = null;
		messages = new ArrayList<Message>();
	}
	
	public static Finder<String,Student> find = new Finder<String,Student>(
	        String.class, Student.class
	    ); 
	
	public static Student authenticate(String email, String password) {
        return find.where().eq("email", email)
            .eq("password", password).findUnique();
    }
	
	public static Language getLanguage(){
		return language;
	}
	


	
	
}
