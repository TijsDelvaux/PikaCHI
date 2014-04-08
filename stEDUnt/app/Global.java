import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        
    	if (Student.find.findRowCount() == 0) {
	    	Student serge = new Student("serge@gmail.com", "Serge",	"pass");
	    	serge.save();
	    	StudentAdvertisement a = new StudentAdvertisement(serge, "aa", "bb");
	    	a.save();
	    	
	    	
	    	Student ine = new Student("ine@gmail.com", "Ine",	"pass");
	    	ine.save();
	    	TutorAdvertisement t = new TutorAdvertisement(ine, "aa", "bb", 20);
	    	t.save();
	    	
	    	
	    	Conversation c = new Conversation(serge, ine, new Message("Hey hey", serge));
	    	c.save();
	    	
	    	Student tijs = new Student("tijs@gmail.com", "Tijs",	"pass");
	    	tijs.save();
	    	
	    	c = new Conversation(serge, tijs, new Message("Hey tijs", serge));
	    	c.save();
	    	
	    	
    	}
    }
}