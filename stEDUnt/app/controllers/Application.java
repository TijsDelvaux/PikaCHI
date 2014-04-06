package controllers;

import models.Student;
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
            
            System.out.println("added new user");
            
        	Student s = new Student(email, name, password);
        	s.save();
            
            return null;
        }

    }

}
