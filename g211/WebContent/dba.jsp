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
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
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
	<div class="main">
		<h1 align="Center">Data Analysis Tool</h1>
		<h1 align ="center">
			G211
		</h1>
		<hr />
		<!-- <a href="index.html">Home</a> <br /> <br /> -->
		<div class="box" align="center">
			<h2>
				Login to Database
			</h2>
			 <h3>
				<!-- <i style="color: blue; font-size: 90%;">* denotes a required
					field</i> --><!--  <br /> -->
			</h3>
			<f:view>
				<h:form>
					<h:panelGrid columns="3">
						<h:outputText value="Username:" />
						<h:inputText id="username" value="#{dbBean.userName}"
							style="width:100%" required="true"
							requiredMessage="&#160;&#160;UserName is required" />
						<h:message for="username"
							style="color:red; font-size:90% ;text-decoration:blink;" />
						<h:outputText value="Password:" />
						<h:inputSecret id="password" value="#{dbBean.password}"
							style="width:100%" required="true"
							requiredMessage="&#160;&#160;Password is required" />
						<h:message for="password"
							style="color:red; font-size:90% ;text-decoration:blink;" />
							
							
							<h:outputText value="Database:" />
						<h:inputText id="database" value="#{dbBean.database}"
							style="width:100%" required="true"
							requiredMessage="&#160;&#160;Database is required" />
						<h:message for="database"
							style="color:red; font-size:90% ;text-decoration:blink;" />
							
							
						<h:outputText value="Host:" />
						<h:selectOneRadio id="host" value="#{dbBean.host}"
							style="width:100%" required="true"
							requiredMessage="&#160;&#160;Please click on a host" layout="pageDirection">
							<f:selectItem itemValue="131.193.209.69"
								itemLabel="131.193.209.69" />
							<f:selectItem itemValue="131.193.209.68"
								itemLabel="131.193.209.68" />
							<f:selectItem itemValue="localhost"
								itemLabel="localhost" />
						</h:selectOneRadio>
						<h:message for="host"
							style="color:red; font-size:90%; text-decoration:blink;" />
							
						   
						
						<h:outputText value="" />
						<h:commandButton value="Login" action="#{methods.login}"
							styleClass="button" />
						<h:outputText value="" />
					</h:panelGrid>
					<br />
					<pre>
						<h:outputText value="#{dbAccess.message}"
							rendered="#{dbAccess.renderMessage}"
							style="color:red; font-weight:bold; text-decoration:blink;" />
						</pre>
				</h:form>
			</f:view>
		</div>
		<div class="footer">
			
		</div>
	</div>

</body>
	</html>
</jsp:root>