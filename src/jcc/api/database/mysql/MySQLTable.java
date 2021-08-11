package jcc.api.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import jcc.api.database.SQLTable;
import jcc.api.database.SQLTableColumn;
import jcc.api.database.SQLTableKey;

public class MySQLTable extends SQLTable{
	private MySQLConnection connection;
	
	public MySQLTable(MySQLConnection connection, String tableName) throws ClassNotFoundException, SQLException{
		this.connection=connection;
		setTableName(tableName);
		setTableDescription(connection, tableName);
	}
	public MySQLTable(String server,String port,String database,String user,String password, String tableName) throws ClassNotFoundException, SQLException{
		this.connection=new MySQLConnection(server, port, database, user, password);
		setTableName(tableName);
		setTableDescription(this.connection, tableName);
	}
	
	public MySQLConnection getConnection() {
		return connection;
	}
	public void setConnection(MySQLConnection connection) {
		this.connection = connection;
	}
	
	private void setTableDescription(MySQLConnection connection,String tableName) throws ClassNotFoundException, SQLException{
		Connection c = null; 
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		c=DriverManager.getConnection("jdbc:mysql://"+connection.getServer()+":"+connection.getPort()+"/"+connection.getDatabaseName()+"",connection.getUser(),connection.getPassword());
		Statement statement = c.createStatement();
	    ResultSet resultSet = statement.executeQuery("SELECT * FROM "+tableName);
	    ResultSetMetaData rsmd = resultSet.getMetaData();
	    for(int i=1;i<=rsmd.getColumnCount();i++){
	    	boolean nullable=true;
	    	if(rsmd.isNullable(i)==0){
	    		nullable=false;
	    	}
	    	ResultSet keySet = statement.executeQuery("SELECT CONSTRAINT_NAME,COLUMN_NAME, REFERENCED_TABLE_NAME, REFERENCED_COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME = '" + tableName + "' AND COLUMN_NAME = '" + rsmd.getColumnName(i) + "'");
	    	SQLTableKey key = null;
	    	while (keySet.next()) {
	    		key = new SQLTableKey(keySet.getString("CONSTRAINT_NAME"), keySet.getString("COLUMN_NAME"), keySet.getString("REFERENCED_TABLE_NAME"), keySet.getString("REFERENCED_COLUMN_NAME"));
	    	}
	    	SQLTableColumn column=new SQLTableColumn(rsmd.getColumnTypeName(i), rsmd.getColumnName(i), nullable, rsmd.isAutoIncrement(i), key);
	    	getColumns().add(column);
	    }
	}
}