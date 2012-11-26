<%@ page import="javax.xml.parsers.*,org.w3c.dom.*,javax.xml.transform.*,java.io.*,javax.xml.transform.dom.DOMSource,javax.xml.transform.stream.StreamResult,javax.xml.parsers.ParserConfigurationException" %>

<link rel="stylesheet" href="./css/page.css" type="text/css"></link>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
 <link rel="icon"  type="image/PNG" href="./css/camera_icon.png">

<html>
<head>
</head>
<body>
<%
String img = request.getParameter("img");
%>

<%
try{
String img2 = img.substring(0,img.length()-4);
String XmlPath = "./xmls/"+img2+".xml";
String path = "/usr/share/tomcat6/webapps/uploadfoto/xmls/"+ img2 + ".xml";
%>

<%
String DescrVar = getXMLValue("Description",path);
String HVar = getXMLValue("Height",path);
String WVar = getXMLValue("Width",path);
String RotVar = getXMLValue("Rotation",path);

if(request.getParameter	("description")!=null){ DescrVar = request.getParameter("description");}
if(request.getParameter	("width")!=null){ WVar = request.getParameter("width");}
if(request.getParameter	("height")!=null){ HVar = request.getParameter("height");}
if(request.getParameter("rotation")!=null){RotVar = request.getParameter("rotation");}
%>

<%!
Document doc;

String getXMLValue(String name, String path) {
		
		try{	File fXmlFile = new File(path);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();
				NodeList nlist=doc.getElementsByTagName(name);
				String value = nlist.item(0).getFirstChild().getNodeValue();
				return value;
		}catch(Exception e){ e.printStackTrace();}
		return null;
}


void setXMLValue(String s,String name, String path){

		try{	File fXmlFile = new File(path);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();
				NodeList nlist=doc.getElementsByTagName(name);
				nlist.item(0).getFirstChild().setNodeValue(s);
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(path);
				transformer.transform(source, result);
				return;
		}catch(Exception e){ e.printStackTrace();}
}
%>

<%
String appPath = application.getRealPath("/");
DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
DocumentBuilder db=dbf.newDocumentBuilder();
doc=db.parse(appPath + XmlPath);
%>

<div id="home2"><a href="./loading">HoME</a></div>
<form action="showimage.jsp" method="GET">
<div id="hd11"><div>Description</div></div>
<div id="hd22"><input name="description" type="text" value="<%=DescrVar%>" size="25"/></div><br>
<div id="hd11"><div>Height</div></div>
<div id="hd22"><input name="height" type="text" value="<%=HVar%>" size="25"/></div><br>
<div id="hd11"><div>Width</div></div>
<div id="hd22"><input name="width" type="text" value="<%=WVar%>" size="25"/></div><br>
<div id="hd11"><div>Rotation</div></div>
<div id="hd22"><input name="rotation" type="text" value="<%=RotVar%>"/></div><br>
</div>

<div><input name="img" type="hidden" value="<%=img%>"/></div><br>
<div id="but21">
<input type="submit" value="appLy chanGes"/>
</div>

</form>

<center>
<img src='./image/<%= img %>' height="<%=HVar%>px" width="<%=WVar%>px" style="transform: rotate(<%=RotVar%>deg);
   -moz-transform: rotate(<%=RotVar%>deg);
   -webkit-transform: rotate(<%=RotVar%>deg);" />
</center>


<%
setXMLValue(DescrVar,"Description",path);
setXMLValue(RotVar,"Rotation",path);
setXMLValue(HVar,"Height",path);
setXMLValue(WVar,"Width",path);
%>

<%
}catch(Exception e){ }
%>

</body>
</html>