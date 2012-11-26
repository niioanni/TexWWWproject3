import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;


//total uploads of the webapps
public class TotalUploadedFotos implements ServletContextAttributeListener {

  private static int total_uploaded_fotos = -1;	
	
  public static int getAccessions(){
	
		return total_uploaded_fotos-1;
  
  }
 
  public void attributeAdded(ServletContextAttributeEvent scab) {
  
		total_uploaded_fotos++;
  }
 
  public void attributeRemoved(ServletContextAttributeEvent scab) {}
  public void attributeReplaced(ServletContextAttributeEvent scab) {}
 
}
