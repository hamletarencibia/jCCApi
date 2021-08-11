package jcc.api.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	private String server;
	private String port;
	private String databaseName;
	private String user;
	private String password;
	private Connection connection;
	public MySQLConnection(String server, String port, String databaseName,
			String user, String password) throws ClassNotFoundException, SQLException {
		this.server = server;
		this.port = port;
		this.databaseName = databaseName;
		this.user = user;
		this.password = password;
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		connection=DriverManager.getConnection("jdbc:mysql://"+getServer()+":"+getPort()+"/"+getDatabaseName()+"",getUser(),getPassword());
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Connection getConnection(){
		return connection;
	}
}
