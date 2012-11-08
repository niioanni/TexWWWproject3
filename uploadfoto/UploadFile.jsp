<%@ page import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="./css/page.css" type="text/css"></link>
<title>Upload Your Image</title>
</head>
<body>

<div id="hd"> <h1> Upload Your Image</h1> </div>
<div class="container">
<form action="ImageUploader" method="post" enctype="multipart/form-data" >
<div id="hd0"> <input type="file" name="file" size="25" class="input" /> </div><br><br>
<div id="hd1"><b>About:</b></div>
<div id="hd2"><textarea name="about" id="about" class="input" size="25"></textarea> </div><br>
<br>
<div id="but">
<input type="submit" value="upLoad it!" class="button"/>
</div>
</form>
        <%
            String[] features = (String [])request.getAttribute("attribute");
			 out.println("<ul>");
             for(int i = 0; i < features.length; i++) {
			   out.println("<li><img src=\"./image/" + features[i] + "\" width=\"300\" height=\"250\" rotation=\"0\"> <br>");
               out.println("<center><a href=\"showimage.jsp?img=" + features[i] + "\">View</a></center></li>");
             }
			 out.println("</ul>");         
            %>


</body>
</form>

</html>
