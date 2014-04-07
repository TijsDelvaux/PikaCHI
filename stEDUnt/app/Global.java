import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        
    	if (Student.find.findRowCount() == 0) {
	    	Student s = new Student("a@a.com", "name",	"pass");
	    	s.save();
	    	StudentAdvertisement a = new StudentAdvertisement(s, "aa", "bb");
	    	a.save();
    	}
    }
}