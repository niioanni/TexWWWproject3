import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Info extends HttpServlet {

  public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

    request.getSession();
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><center>");
    out.print("Users that are currently on the site:");
	out.print("</br>");
    out.print(CurrentUsers.getCurrentUsers());
    out.print("</br>");
    out.print("Uploads that have been done in the current session:");
	out.print("</br>");
    out.print(UploadCounter.getNewAttributes());
    out.print("</br>");
    out.print("Total Uploads:");
	out.print("</br>");
    out.print(TotalUploadedFotos.getAccessions());
    out.println("</center></html>");
  }
}
