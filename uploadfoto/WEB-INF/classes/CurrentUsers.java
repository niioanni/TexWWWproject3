import javax.servlet.*;
import javax.servlet.http.*;

public class CurrentUsers implements HttpSessionListener {

  static int website_users = 0;
  
  public static int getCurrentUsers() {
    return website_users;
  }
  
  public void sessionCreated(HttpSessionEvent e) {
    website_users++;
  }
  public void sessionDestroyed(HttpSessionEvent e) {
    website_users--;
  }

}
