<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:t="http://myfaces.apache.org.tomahawk" version="2.0">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml"
		xmlns:f="http://java.sun.com/jsf/core"
		xmlns:h="http://java.sun.com/jsf/html"
		xmlns:t="http://myfaces.apache.org/tomahawk">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Data Analysis Tool</title>
<link rel="stylesheet" href="CSS/style.css" />
</head>
<body>
<center>
	<f:view>
		<h1 align="center">Data Analysis Tool</h1>
			<h1  align="center">
				G211
			</h1>
			<p align="center">
			User can perform various data analysis function on the data from the given options 
			</p>
			<hr />
			
			<table width="100%">
			<tr>
			<td width="50%"><a href="dbOptions.jsp">Main Menu</a></td>
			<td width="50%" align="right"><a href="confirm.jsp">Logout</a></td>
			</tr>
			</table>
				
				 <br />
				<hr />
	<h1>Please click the button to export the commands to a new Text file</h1>
	<h:form>
	<h:commandButton action="#{export.exportTxt}" styleClass="button" />
	</h:form>
	</f:view>
</center>	
</body>
	</html>
</jsp:root>