import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        
    	Student s = new Student("a@a.com", "name",	"pass");
    	s.save();
    	
    }
}