<%@ page import="java.util.*" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <link rel="icon"  type="image/PNG" href="./css/camera_icon.png">
<link rel="stylesheet" href="./css/page.css" type="text/css"></link>
<title>Selected</title>
</head>

<html>
    <body>
            <%
            String[] features = (String [])request.getAttribute("attribute");
            if(features[0].equals("Please Upload An Image")) {out.println("<h3>Please Upload An Image.</h2>");}
            else { out.println("<center><div class="+"stamp"+"><img src=\"./image/" + features[0] + "\" width=\"300\" height=\"250\"></div></center>");
            }
            out.println("<div id="+"home"+"><center><li><a href=\"./loading\">HoME</a></li></div>");        
            %>
    </body>
</html>

