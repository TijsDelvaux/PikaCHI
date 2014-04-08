package controllers;

import models.Advertisement;
import models.Conversation;
import models.Student;
import models.StudentAdvertisement;
import models.TutorAdvertisement;
import play.*;
import play.mvc.*;
import play.data.*; 
import static play.data.Form.*;
import views.html.*;

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
    public static Result addNewStudAdvertisementForm() {  
        	Form<StudentAdvertisementForm> adForm = Form.form(StudentAdvertisementForm.class).bindFromRequest();
            if (adForm.hasErrors()) {
                return badRequest(postNewStudentAdvertisement.render(adForm));
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
            return badRequest(postNewTutorAdvertisement.render(adForm, null));
        } else {
            return redirect(
                routes.Application.start()
            );
        }
        }

        
    
   
  	@Security.Authenticated(Secured.class)
    public static Result addNewStudAdvertisement() {
    	return ok(
                postNewStudentAdvertisement.render(form(StudentAdvertisementForm.class))
            );
    }
  	
    @Security.Authenticated(Secured.class)
    public static Result addNewTutAdvertisement() {
    	return ok(
                postNewTutorAdvertisement.render(form(TutorAdvertisementForm.class), TutorAdvertisement.findFromUser(request().username()))
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

}
