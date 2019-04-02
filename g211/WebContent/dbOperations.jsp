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
	<f:view>
		<center>
			<h1>Data Analysis Tool</h1>
			<h1>G211</h1>
			<p>
				Users can view the tables and the data present in the tables
			</p>
			<hr />
			<div class="operationsMain">
				<table width="100%">
			<tr>
			<td width="50%"><a href="dbOptions.jsp">Main Menu</a></td>
			<td width="50%" align="right"><a href="confirm.jsp">Logout</a></td>
			</tr>
			</table><br />
				
				<h:form>
					<br />
					<br />
					<div class="left-div">
						<h:panelGrid columns="5">
							<h:commandButton value="Table list"
								action="#{databaseOperations.fetchTables}" styleClass="button" />
							<h:commandButton value="View table"
								action="#{databaseOperations.fetchTableData}" disabled="#{databaseOperations.disableDisplayTable})" styleClass="button" />
							<h:commandButton value="Show Colummn's list"
								action="#{databaseOperations.fetchColumnNames}" disabled="#{databaseOperations.disableColumnList}" styleClass="button" />
							<h:commandButton value="View selected columns"
								action="#{databaseOperations.fetchColumnData}" disabled="#{databaseOperations.disableColumnData}" styleClass="button" />
							<h:commandButton value="Process SQL query"
								action="#{databaseOperations.queryProcessing}" styleClass="button" />
							 
						</h:panelGrid>
					</div>
					<div class="right-div">
						<pre>
						<h:outputText value="#{databaseOperations.message}"
								rendered="#{databaseOperations.renderMessage}" style="color:red" />
					</pre>
						<panelGrid columns="4">
						 <h:selectOneListbox
							id="selectOneCb" style="width:150px; height:100px"
							value="#{databaseOperations.tableSelected}"
							rendered="#{databaseOperations.renderTablename}" size="5">
							<f:selectItems value="#{databaseOperations.tableList}" />
						</h:selectOneListbox> 
						<h:outputText value=" " /> 
						<h:outputText value=" " /> 
						<h:outputText value=" " />
						<h:selectManyListbox id="selectcolumns"
							style="width:150px; height:100px"
							value="#{databaseOperations.columnSelected}"
							rendered="#{databaseOperations.columnRender}" size="5">
							<f:selectItems value="#{databaseOperations.columnsList}" />
						</h:selectManyListbox>
						 <h:outputText value=" " />
						  <h:outputText value=" " /> 
						  <h:outputText value=" " />
						<h:inputTextarea rows="6" cols="40"
							style="height:100px" value="#{databaseOperations.userQuery}" />  
							</panelGrid>
						<br /> <br />
						<h:outputText value=" " />
						<h:outputText value=" " />
						&#160;&#160;
						<h:outputText value=" " />
						<h:outputText value=" " />
						<hr />
					</div>
					<div class="bottom">
						<div
							style="background-attachment: scroll; overflow: auto; height: 200px; background-repeat: repeat"
							align="center">
							<t:dataTable value="#{databaseOperations.result}" var="row"
								rendered="#{databaseOperations.renderTabledata}" border="1"
								cellspacing="0" cellpadding="1"
								columnClasses="columnClass border" headerClass="headerClass"
								footerClass="footerClass" rowClasses="rowClass2"
								styleClass="dataTableEx" width="900px">
								<t:columns var="col" value="#{databaseOperations.columnSelected}">
									<f:facet name="header">
										<t:outputText styleClass="outputHeader" value="#{col}" />
									</f:facet>
									<t:outputText styleClass="outputText" value="#{row[col]}" />
								</t:columns>
							</t:dataTable>
						</div>
					</div>
					<hr />
				</h:form>
				<div class="footer">
					
				</div>
			</div>
		</center>
	</f:view>
</body>
	</html>
</jsp:root>