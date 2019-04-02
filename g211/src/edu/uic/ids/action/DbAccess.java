package edu.uic.ids.action;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.jsp.jstl.sql.Result;

import edu.uic.ids.model.DatabaseAccessInfoBean;
@ManagedBean(name="dbAccess")
@SessionScoped

public class DbAccess {
	private static DatabaseAccessInfoBean dbBean;
	private Connection connection;
	private Connection worldConnection;
	private DatabaseMetaData databaseMetaData;
	private Statement statement;
	private Statement worldStatement;
	private ResultSet resultSet, rs;
	private ResultSetMetaData resultSetMetaData;
	private Result result;

	private ResultSet worldResultSet, worldRs;
	private ResultSetMetaData worldResultSetMetaData;
	private Result worldResult;
	private DatabaseMetaData worldDatabaseMetaData;

	
	private String jdbcDriver;
	private String url;
	private String urlWorld;

	private String message;
	private Boolean renderMessage = false;

	private static final String ACCESS_DENIED = "28000";
	private static final String TIMEOUT = "08S01";
	private static final String INVALID_DB_SCHEMA = "42000";

	public DbAccess(){
		dbClose();
	}
	
	//establishing a connection to a database
	public String dbConnect() {

			jdbcDriver = "com.mysql.jdbc.Driver";
			url = "jdbc:mysql://" + dbBean.getHost() + ":3306/"+dbBean.getDatabase();
			urlWorld = "jdbc:mysql://" + dbBean.getHost() + ":3306/world";
		
		try {
			Class.forName(jdbcDriver); // registers driver
			connection = DriverManager.getConnection(url, dbBean.getUserName(), dbBean.getPassword());
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			databaseMetaData = connection.getMetaData();
			
			worldConnection = DriverManager.getConnection(urlWorld, dbBean.getUserName(), dbBean.getPassword());
			worldStatement = worldConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			worldDatabaseMetaData = worldConnection.getMetaData();
			return "SUCCESS";
		} catch (ClassNotFoundException e) {
			message ="MySql is not found or supported. Please make a selection again";
			renderMessage = true;
			return "FAIL";
		} // end catch
		
		//catching SQL exceptions
		catch (SQLException se) {
			if (se.getSQLState().equals(ACCESS_DENIED)) {
			
				message = "SQL State: " + se.getSQLState() + "\n" +  "SQL Error Code: " + se.getErrorCode() + "\n" +
						 "Message :" + se.getMessage() + "\n" + "Access has been denied. The entered credentials didn't get authenticated. Please try again";
			} else if (se.getSQLState().equals(INVALID_DB_SCHEMA)) {
				
				message = "SQL State: " + se.getSQLState() + "\n" +  "SQL Error Code: " + se.getErrorCode() + "\n" 
						+ "Message :" + se.getMessage() + "\n" + "Invalid schema enterted. Please enter a schema which is available in the database";
			} else if (se.getSQLState().equals(TIMEOUT)) {
				
				message = "SQL State: " + se.getSQLState() + "\n" +  "SQL Error Code: " + se.getErrorCode() + "\n" 
						+ "Message :" + se.getMessage() + "\n" + "The session timed out. Verify whether you have entered the correct host and port";
			}  else {
				
				message = "SQL State: " + se.getSQLState() + "\n" +  "SQL Error Code: " + se.getErrorCode() + "\n" 
						+ "Message :" + se.getMessage() + "\n" + "Unknown SQL Exception has occurred, please try after sometime!";
			}
			renderMessage = true;
			return "FAIL";
		} // end catch
		
		//catching other exceptions
		catch (Exception e) {
			message = "The application encountered an exception: " + e.getMessage();
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.close();
				} 
				
				catch (SQLException se) 
				{
					message = "Sorry, application encountered an SQL Exception at this moment while closing the connection. Please find the error details below "+ "\n" + " SQL State: " + se.getSQLState() + "\n" + "SQL Error Code: " + se.getErrorCode() + "\n"  + "Message :" + 
		            se.getMessage();
				}
			}
			renderMessage = true;
			return "FAIL";
		} // end catch
	}
	
	public void dbClose()
	{
		try {
			if(resultSet!=null) resultSet.close();
			if(statement!=null) statement.close();
			if(connection!=null) connection.close();
		} catch (SQLException se) {
			message = "Encountered an SQL Exception while closing the connection."+ "\n" + "SQL State: " + se.getSQLState() + "\n" +  "SQL Error Code: " + se.getErrorCode() + "\n" +
					"Message :" + se.getMessage();
		}
		catch (Exception e)
		{
			message = "The application encountered an exception: " + e.getMessage() + "occured";
		}
	}

	public DatabaseAccessInfoBean getDbBean() {
		return dbBean;
	}

	public void setDbBean(DatabaseAccessInfoBean dbBean) {
		this.dbBean = dbBean;
	}

	public Connection getConnection() {
		return connection;
	}

	public DatabaseMetaData getDatabaseMetaData() {
		return databaseMetaData;
	}

	public Statement getStatement() {
		return statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public ResultSet getRs() {
		return rs;
	}

	public ResultSetMetaData getResultSetMetaData() {
		return resultSetMetaData;
	}

	public Result getResult() {
		return result;
	}

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public String getUrl() {
		return url;
	}

	public String getMessage() {
		return message;
	}

	public Boolean getRenderMessage() {
		return renderMessage;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void setDatabaseMetaData(DatabaseMetaData databaseMetaData) {
		this.databaseMetaData = databaseMetaData;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public void setResultSetMetaData(ResultSetMetaData resultSetMetaData) {
		this.resultSetMetaData = resultSetMetaData;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setRenderMessage(Boolean renderMessage) {
		this.renderMessage = renderMessage;
	}

	public ResultSet fetchColumnNames(String sqlQuery)
	{
		try
		{
			ResultSet resultSet;
			/*if(sqlQuery.contains("world"))
			resultSet= worldStatement.executeQuery(sqlQuery);
			else*/ 			resultSet= statement.executeQuery(sqlQuery);

			return resultSet;
		} catch (SQLException se) {
			message = "Application encountered an SQL Exception while fetching the column names" + "\n" + "SQL State: " + se.getSQLState() + "\n" + "SQL Error Code: " + se.getErrorCode() + "\n" +
					"Message :" + se.getMessage() + "\n";
			return resultSet = null;
		} 
		catch (Exception e) {
			e.printStackTrace();
			message = "Exception: " + e.getMessage();
			return resultSet = null;
		}
	}
	
	public ResultSet fetchColumnData(String query)
	{
		try {
			ResultSet resultSet ;
			/*if(query.contains("world"))
				 resultSet = worldStatement.executeQuery(query);
			else*/
			 resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException se) {
			message = "Application encountered an SQL Exception while fetching the column data requested." + "\n" + "SQL Error Code: " + se.getErrorCode() + "\n"+ "SQL State: " + se.getSQLState() + "\n" +
					"Message :" + se.getMessage() + "\n";
			return resultSet = null;
		} catch (Exception e) {e.printStackTrace();
			message = "Exception:  " + e.getMessage();
			return resultSet = null;
		}
	}
	
	public ResultSet selectQueryProcessing(String query)
	{
		try {
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException se) {
			try {
				
				resultSet = worldStatement.executeQuery(query);
			} catch (SQLException e) {
				message = "Sorry, application encountered an SQL Exception at this moment" +  "\n" + "SQL Error Code: " + se.getErrorCode() + "\n"+  "SQL State: " + se.getSQLState() + "\n" +
						"Message :" + se.getMessage() + "\n";
				se.printStackTrace();
				return resultSet;
			}
			return resultSet;

			
		} 
		catch (Exception e) {
			e.printStackTrace();
			message = "Ooops, the application encountered an exception: " + e.getMessage();
			e.printStackTrace();
			return resultSet;
		}
	}
		
	public ResultSet[] fetchTables()
	{
		ResultSet[] rs = new ResultSet[2];
		try {
			DatabaseMetaData meta = (DatabaseMetaData) connection.getMetaData();
			ResultSet rSet = meta.getTables(dbBean.getDatabase(), null, null, new String[] {"TABLE"});
			rs[0]=rSet;
			/*DatabaseMetaData meta1 = (DatabaseMetaData) worldConnection.getMetaData();
			ResultSet rSet1 = meta1.getTables("world", null, null, new String[] {"TABLE"});
			rs[1]=rSet1;*/
			return rs;
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
			message = "SQL Exception has occured while fetching the tables, find the error details below" + "\n" + "SQL State: " + se.getSQLState() + "\n" +  "SQL Error Code: " + se.getErrorCode() + "\n" +
					"Message :" + se.getMessage() + "\n";
			
			return rs;
		} 
		catch (Exception e) {
			e.printStackTrace();
			message = "Ooops, the application encountered an exception: " + e.getMessage();
			return rs;
		}
	}
	
	/* Method used to fetch the table data */
	public ResultSet fetchTableData(String sqlQuery)
	{
		try {
			resultSet = statement.executeQuery(sqlQuery);
			return resultSet;
		} catch (SQLException se) {
			message = "SQL Exception has occured while fetching data from the tables, find the error details below"+ "\n" + "Error Code: " + se.getErrorCode() + "\n" +
					"SQL State: " + se.getSQLState() + "\n" +
					"Message :" + se.getMessage();
			return resultSet = null;
		} catch (Exception e) {e.printStackTrace();
			message = "Ooops, the application encountered an exception: " + e.getMessage();
			return resultSet = null;
		}
	}
	
	/* Method used to create a specified table in the Database */
	
}