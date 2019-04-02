package edu.uic.ids.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="dbBean")
@SessionScoped
public class DatabaseAccessInfoBean {
	private String userName;
	private String password;
	private String database;
	private String dbms="mysql";
	private String host;
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getDbms() {
		return dbms;
	}
	public String getHost() {
		return host;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public void setDbms(String dbms) {
		this.dbms = dbms;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	
}
