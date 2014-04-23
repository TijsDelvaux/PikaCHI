package controllers;

import models.Advertisement;
import models.Conversation;
import models.Message;
import models.Student;
import models.StudentAdvertisement;
import models.TutorAdvertisement;
import play.*;
import play.mvc.*;
import play.data.*; 
import static play.data.Form.*;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {


	
	@Security.Authenticated(Secured.class)
	public static Result index() {
	    return ok(start.render(
	        Student.find.byId(request().username())
	    )); 
	}
	
	
	@Security.Authenticated(Secured.class)
    public static Result start(){
    	return ok(start.render(
    	        Student.find.byId(request().username())
    	    )); 
    }
    
    public static Result login() {
        return ok(
            login.render(form(Login.class))
        );
    }
    
    public static Result register() {
        return ok(
            register.render(form(Register.class))
        );
    }
    
    public static Result registerNewUser() {
    	Form<Register> regForm = Form.form(Register.class).bindFromRequest();
        if (regForm.hasErrors()) {
            return badRequest(register.render(regForm));
        } else {
	    	return redirect(
	                routes.Application.login()
	            );
        }
    }
    
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
            routes.Application.login()
        );
    }
    
      public static Result authenticate() {
            Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
            if (loginForm.hasErrors()) {
                return badRequest(login.render(loginForm));
            } else {
                session().clear();
                session("email", loginForm.get().email);
                return redirect(
                    routes.Application.start()
                );
            }
        }
      
  	@Security.Authenticated(Secured.class)
      public static Result viewAllAdvertisements() {  	  
    	  return ok(
    	            viewAdvertisements.render(
    	            		Student.find.byId(request().username()), 
    	            		StudentAdvertisement.find.all(),
    	            		TutorAdvertisement.find.all()
    	            		)
    	        );
      }
  	
  	
  	@Security.Authenticated(Secured.class)
    public static Result viewMyOwnAdvertisements() {  	  
  	  return ok(
//  	            viewOwnAdvertisements.render(
//  	            		Student.find.byId(request().username()), 
//  	            		StudentAdvertisement.findStudentAdvInvolving(request().username()),
//  	            		TutorAdvertisement.findTutorAdvInvolving(request().username())
//  	            		)
  	            		
  	            		    	            viewOwnAdvertisements.render(
    	            		Student.find.byId(request().username()), 
    	            		StudentAdvertisement.find.all(),
    	            		TutorAdvertisement.find.all()
    	            		)
  	        );
    }

    @Security.Authenticated(Secured.class)
    public static Result viewMyAdvertisements() {
	     return ok(
	                myAdvertisements.render(Student.find.byId(request().username()))
	            );
    }
    
    public static Boolean hasStudAdv(String email) {
    	List<StudentAdvertisement> sal = StudentAdvertisement.find.all();
    	for(StudentAdvertisement sa: sal){
    		if(sa.student.email.equals(email)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public static Boolean hasTutAdv(String email) {
    	List<TutorAdvertisement> sal = TutorAdvertisement.find.all();
    	for(TutorAdvertisement sa: sal){
    		if(sa.student.email.equals(email)){
    			return true;
    		}
    	}
    	return false;
    }
  	
  	@Security.Authenticated(Secured.class)
    public static Result addNewStudAdvertisementForm() {  
        	Form<StudentAdvertisementForm> adForm = Form.form(StudentAdvertisementForm.class).bindFromRequest();
            if (adForm.hasErrors()) {
                return badRequest(postNewStudentAdvertisement.render(Student.find.byId(request().username()),adForm));
            } else {
                return redirect(
                    routes.Application.start()
                );
            }
  		}
  		
  		
  	@Security.Authenticated(Secured.class)
  	    public static Result addNewTutAdvertisementForm() {  
  		Form<TutorAdvertisementForm> adForm = Form.form(TutorAdvertisementForm.class).bindFromRequest();
        if (adForm.hasErrors()) {
            return badRequest(postNewTutorAdvertisement.render(Student.find.byId(request().username()),adForm, null));
        } else {
            return redirect(
                routes.Application.start()
            );
        }
        }
  	
  	@Security.Authenticated(Secured.class)
	    public static Result addNewMessageForm(Long id) {  
		Form<MessageForm> adForm = Form.form(MessageForm.class).bindFromRequest();
	    if (adForm.hasErrors()) {
	        return badRequest(postNewMessage.render(adForm, id));
	    } else {
	    	
	    	
            
            Message m = new Message(adForm.field("text").value().toString(), Student.find.byId(request().username()));
      		Conversation c = Conversation.find.byId(id.toString());    		
      		c.messages.add(m);
      		c.save();
	    	
	    	
	    	
	    	
        return redirect(
            routes.Application.viewMyConversation(id)
        );
    }
    }

  	@Security.Authenticated(Secured.class)
    public static Result addNewMessage(Long id) {
    	return ok(
                postNewMessage.render(form(MessageForm.class), id)
            );
    }
    
   
  	@Security.Authenticated(Secured.class)
    public static Result addNewStudAdvertisement() {
    	return ok(
                postNewStudentAdvertisement.render(Student.find.byId(request().username()),
                		form(StudentAdvertisementForm.class))
            );
    }
  	
    @Security.Authenticated(Secured.class)
    public static Result addNewTutAdvertisement() {
    	return ok(
                postNewTutorAdvertisement.render(Student.find.byId(request().username()),form(TutorAdvertisementForm.class), TutorAdvertisement.findFromUser(request().username()))
            );
    }
    
    
    
    
  	@Security.Authenticated(Secured.class)
    public static Result viewMyConversations() {

    	return ok(
                viewConversations.render(Student.find.byId(request().username()),  Conversation.findConversationsOfStudent(Student.find.byId(request().username())))
            );
    }
  	
  	@Security.Authenticated(Secured.class)
    public static Result viewMyConversation(Long id) {
  		Student s = Student.find.byId(request().username());
  		Conversation c = Conversation.find.byId(id.toString());
  		
  		if(!c.getParticipants().contains(s)){ //unauthorized access!
  			return redirect(
  	                routes.Application.viewMyConversations()
  	            );
  		}
  		else{
  		return ok(
                viewConversation.render(s,c)
            );
  		}
    }
  	
  	@Security.Authenticated(Secured.class)
  	public static Result createNewConversation(String receive) {
  		//Make a conversation
  		Student sender = Student.find.byId(request().username());
  		Student receiver = Student.find.byId(receive);
  		Conversation c = new Conversation(sender, sender, new Message("Test", sender));
  		//Show all the conversations
  		//Better: immediately go to the new conversation TODO!!
  		return viewMyConversations();
  	}
  	
  	
  	//src: 1 = viewAdvertisements
  	//src: 2 = viewMyOwnAdvertisements
  	@Security.Authenticated(Secured.class)
    public static Result deleteTutAdvertisement(Long id, Long src) {
  		Student s = Student.find.byId(request().username());
  		TutorAdvertisement ta = TutorAdvertisement.find.byId(id);
  		
  		try{
  		if(ta.student.email.equals(s.email)){
  			ta.delete();
  		}
  		}
  		catch(NullPointerException e){
  			
  		}
  		if(src==1){
  		return ok(
	            viewAdvertisements.render(
	            		Student.find.byId(request().username()), 
	            		StudentAdvertisement.find.all(),
	            		TutorAdvertisement.find.all()
	            		)
	        );
  		}
  		
  		else{
  		return ok(
  				viewOwnAdvertisements.render(
	            		Student.find.byId(request().username()), 
	            		StudentAdvertisement.find.all(),
	            		TutorAdvertisement.find.all()
	            		)
	        );
  		}
  		
  	}
  	
  	@Security.Authenticated(Secured.class)
    public static Result deleteStudAdvertisement(Long id, Long src) {
  		Student s = Student.find.byId(request().username());
  		StudentAdvertisement sa = StudentAdvertisement.find.byId(id);

  		try{  		
  		if(sa.student.email.equals(s.email)){
  			sa.delete();
  		}
  		}
  		catch(NullPointerException e){
  			
  		}
  		if(src==1){
  		return ok(
	            viewAdvertisements.render(
	            		Student.find.byId(request().username()), 
	            		StudentAdvertisement.find.all(),
	            		TutorAdvertisement.find.all()
	            		)
	        );
  		}
  		
  		else{
  		return ok(
  				viewOwnAdvertisements.render(
	            		Student.find.byId(request().username()), 
	            		StudentAdvertisement.find.all(),
	            		TutorAdvertisement.find.all()
	            		)
	        );
  		}
  		
  	}
    
            
            
    public static class Login {

        public String email;
        public String password;
        
        public String validate() {
            if (Student.authenticate(email, password) == null) {
              return "Invalid user or password";
            }
            return null;
        }

    }
    
    public static class Register {

        public String email;
        public String password;
        public String name;
        
        public String validate() {
        	
            if (email.length()==0 | password.length()==0 | name.length()==0) {
              return "Please fill in all required forms";
            }
            
        	Student s = new Student(email, name, password);
        	s.save();
        	
            return null;
        }
    }
    
    
    
    public static class StudentAdvertisementForm {

        public String studies;
    	public String description;
        
        public String validate() {        	
        	
            if (studies.length()==0 | description.length()==0) {
              return "Please fill in all required forms";
            }
            
            if(request().username() == null){
            	return "username is null, make sure you are logged in";
            }
            
            models.StudentAdvertisement.create(Student.find.byId(request().username()), studies, description);
            return null;
            
        }

    }
    
    public static class TutorAdvertisementForm {

        public String studies;
    	public String description;
    	public double price;
        
        public String validate() {        	
        	
            if (studies.length()==0 | description.length()==0) {
              return "Please fill in all required forms";
            }
            
            if(request().username() == null){
            	return "username is null, make sure you are logged in";
            }
            
            models.TutorAdvertisement.create(Student.find.byId(request().username()), studies, description, price);
            return null;
            
        }

    }
    
    
    public static class MessageForm {

        public String text;
        
        public String validate() {     
        	
        	
            if (text.length()==0) {
              return "Please fill in all required forms";
            }
            
            if(request().username() == null){
            	return "username is null, make sure you are logged in";
            }

            
            return null;
            
        }

    }
    

}
