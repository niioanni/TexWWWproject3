import javax.servlet.*;
import javax.servlet.http.*;

//uploads per session
public class UploadCounter implements HttpSessionAttributeListener{

private static int uploads = 0;
	
	public static int getNewAttributes(){
	
			return uploads;
	
	}
		
	public void attributeAdded(HttpSessionBindingEvent event){
	
		 uploads++;
	}
	
	public void attributeRemoved(HttpSessionBindingEvent event){}
	
	public void attributeReplaced(HttpSessionBindingEvent event){}
}
