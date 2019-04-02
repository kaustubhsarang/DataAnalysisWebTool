<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html" version="2.0">
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
	<f:view>
		<div class="main">
			<h1 align="center">Data Analysis Tool</h1>
			<h1  align="center">
				G211
			</h1>
			<hr />
			<p align="right"><a href="confirm.jsp">Logout</a></p>
			
			<br /><p align="center"> <a href="dbOperations.jsp">Table Data</a>&#160; &#160; 
			<a href="upload.jsp">Upload Command File</a>&#160; &#160;
			<a href="stats.jsp">Data Analysis</a> &#160; &#160; 
			<a href="export.jsp">Export</a> &#160; &#160;
		 </p>
			<div class="footer">
				
			</div>
		</div>
	</f:view>
</body>
	</html>
</jsp:root>