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
		return ok(start.render());
//        return ok(index.render("Your new application is ready."));
    }
    
    public static Result start(){
    	return ok(start.render());
    	//return ok(views.html.start.render(new Student("test@test.test","test","test")));
    }
    
    public static Result login() {
        return ok(
            login.render(form(Login.class))
        );
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

}
