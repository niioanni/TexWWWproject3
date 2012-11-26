import java.io.*;
import java.util.*;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

public class ImageUploader extends HttpServlet {
   
   private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 7000 * 1024;
   private int maxMemSize = 4 * 1024;
   private File file ;
   private String tempname;


   public void init( ){
      // Get the file location where it would be stored.
      filePath = getServletContext().getInitParameter("file-upload");
	}
   
	public void doPost(HttpServletRequest take, HttpServletResponse give) throws ServletException, java.io.IOException {
      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(take);
      give.setContentType("text/html");
	  String about = "";
	  String rotation= "0";
	  String height= "300";
	  String width = "250";
	  
      String[] features = new String[2];
	  
      java.io.PrintWriter out = give.getWriter( );
      if( !isMultipart ){
         
         return;
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("/usr/share/tomcat6/webapps/uploadfoto/image/"));
      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 
      // Parse the request to get file items.
      List fileItems = upload.parseRequest(take);
      // Process the uploaded file items
      Iterator i = fileItems.iterator();
	  
      while ( i.hasNext () ) 
      {
         FileItem fi = (FileItem)i.next();
         if ( !fi.isFormField () )	
         {
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
            String contentType = fi.getContentType();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize();
			//out.println("before write file " + "<br>");
            // Write the file
            if( fileName.lastIndexOf("/") >= 0 ){
               file = new File( filePath + 
               fileName.substring( fileName.lastIndexOf("/"))) ;

            }else{
               file = new File( filePath + 
               fileName.substring(fileName.lastIndexOf("/")+1)) ;
			   
            }
			
			if(fileName.substring(fileName.lastIndexOf(".") + 1).equalsIgnoreCase("bmp") || 
				fileName.substring(fileName.lastIndexOf(".") + 1).equalsIgnoreCase("jpg") || 
				fileName.substring(fileName.lastIndexOf(".") + 1).equalsIgnoreCase("jpeg") || 
				fileName.substring(fileName.lastIndexOf(".") + 1).equalsIgnoreCase("png") || 
				fileName.substring(fileName.lastIndexOf(".") + 1).equalsIgnoreCase("tif") || 
				fileName.substring(fileName.lastIndexOf(".") + 1).equalsIgnoreCase("gif") || 
				fileName.substring(fileName.lastIndexOf(".") + 1).equalsIgnoreCase("tgif")) {
                
																							features[0] = tempname= fileName; 
																							fi.write( file ) ;   
            }
            else {
				features[0] = "Please Upload An Image";
                break;
            }
	    	              
         }
		 
		 else {
             int temp = 0;
             if(fi.getFieldName().equals("about")) {
                 about = fi.getString();
             }
			 
            
			PrintWriter out_xml = new PrintWriter("/usr/share/tomcat6/webapps/uploadfoto/xmls/" + 
												  tempname.replaceFirst("[.][^.]+$", "") + ".xml");
			out_xml.println("<Image>\n    <Description>" 
							+ about + "</Description>\n <Rotation>" 
							+ rotation + "</Rotation>\n <Height>" 
							+ height + "</Height>\n <Width>" 
							+ width + "</Width>\n</Image>");
			out_xml.close();
            
			features[1] = about;
			
		 }
		 
      }
	  
		take.getSession().setAttribute("uploads","uploads");

	    take.setAttribute("attribute", features);
        RequestDispatcher view = take.getRequestDispatcher("myfoto.jsp");
		
		
		 try {    
            view.forward(take, give);
        } 
        catch (ServletException ex) {} 
        catch (IOException ex) {} 
	  
		}catch(Exception ex) {
       System.out.println(ex);
   }
   
   }
   public void doGet(HttpServletRequest take, 
                       HttpServletResponse give)
        throws ServletException, java.io.IOException {
		
		// Use "take" to read incoming HTTP headers (e.g. cookies)
		// and HTML form data (e.g. data the user entered and submitted)
    
		// Use "give" to specify the HTTP response line and headers
		// (e.g. specifying the content type)
		give.setContentType("text/html");
        String[] features;
        File images = new File("/usr/share/tomcat6/webapps/uploadfoto/image/");
        File[] allImages = images.listFiles(); 
        features = new String[allImages.length];
        for(int i = 0; i < features.length; i++) { features[i] = allImages[i].getName();}
        take.setAttribute("attribute", features);
        RequestDispatcher view = take.getRequestDispatcher("UploadFile.jsp");
        try {    
            view.forward(take, give);
        } 
        catch (ServletException ex) {} 
        catch (IOException ex) {} 
   } 
}

