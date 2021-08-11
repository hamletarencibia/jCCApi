package jcc.api.database.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jcc.api.database.SQLTable;
import jcc.api.database.SQLTableColumn;

public class SQLiteTable extends SQLTable{
	private String databasePath;
	private String databaseName;
	
	public SQLiteTable(String path,String tableName) throws ClassNotFoundException, SQLException{
		this.databasePath=path;
		setTableName(tableName);
		this.databaseName=path.split("/")[path.split("/").length-1];
		setTableDescription(path,tableName);
	}
	public String getDatabasePath() {
		return databasePath;
	}
	public void setDatabasePath(String databasePath) {
		this.databasePath = databasePath;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databseName) {
		this.databaseName = databseName;
	}
	private void setTableDescription(String path,String tableName) throws ClassNotFoundException, SQLException{
		Connection c = null; 
		Statement stmt = null;
		Class.forName("org.sqlite.JDBC"); 
		c = DriverManager.getConnection("jdbc:sqlite:"+path);
		c.setAutoCommit(false);
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery( "PRAGMA table_info( "+tableName+" );" );
		while(rs.next()){
			SQLTableColumn column=new SQLTableColumn(rs.getString(3), rs.getString(2), rs.getBoolean(4), rs.getBoolean(6), null);
			getColumns().add(column);
		}
	}
}