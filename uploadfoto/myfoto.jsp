<%@ page import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" href="./css/page.css" type="text/css"></link>
<title>Selected</title>
</head>

<html>
    <body>
            <%
            String[] features = (String [])request.getAttribute("attribute");
            if(features[0].equals("Please Upload An Image")) {out.println("Please Upload An Image.");}
            else { out.println("<img src=\"./image/" + features[0] + "\" width=\"300\" height=\"250\"><br>");}
            out.println(features[1]);
            out.println("<li><a href=\"./loading\">HoME</a></li>");        
            %>
    </body>
</html>

