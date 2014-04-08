package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

import com.avaje.ebean.*;

@Entity
public class Message extends Model{
	
    @Id
    public long id;
    public String text;
    public Date date;
    
	public Message(String text){
		this.text = text;
		this.date = new Date();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	
	
}