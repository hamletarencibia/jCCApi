package jcc.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import jcc.api.database.mysql.MySQLConnection;

/***/
public class SQLUtils {
	public static String convertDatatypes(String sqlDataTypes){
		String type="";
    	String field=sqlDataTypes.toLowerCase();
		if(field.contains("text")||field.contains("varchar"))//String
			type="String";
		else if(field.equals("char"))//CHAR
			type="Char";
		else if(field.equals("tinyint"))//BYTE
			type="Byte";
		else if(field.equals("smallint"))//SHORT
			type="Short";
		else if(field.contains("integer")||field.equals("int")||field.equals("mediumint"))//INT
			type="Integer";
		else if(field.equals("bigint"))//LONG
			type="Long";
		else if(field.equals("float")||field.equals("decimal"))//FLOAT
			type="Float";
		else if(field.equals("real")||field.equals("double"))//DOUBLE
			type="Double";
		else if (field.equals("bit"))
			type = "Boolean";
		else if (field.equals("date"))
			type = "Date";
		return type;
	}
	public static LinkedList<String> getTablesNameSQLite(String path) throws ClassNotFoundException, SQLException{
		LinkedList<String> tableNames = new LinkedList<String>();
		Connection c = null; 
		Statement stmt = null;
		ResultSet rs=null;
		Class.forName("org.sqlite.JDBC"); 
		c = DriverManager.getConnection("jdbc:sqlite:"+path);
		c.setAutoCommit(false);
		stmt = c.createStatement();
		rs = stmt.executeQuery( "SELECT name FROM sqlite_master WHERE type='table';" );
		while ( rs.next() ) { 
			if(rs.getString(1).equals("sqlite_sequence"))
				continue;
			tableNames.add(rs.getString(1));
		} 
		rs.close(); 
		stmt.close(); 
		c.close();
		return tableNames;
	}
	public static LinkedList<String> getTableNameMySQL(String server,String port,String databseName,String user,String password) throws ClassNotFoundException, SQLException{
		LinkedList<String> tableNames = new LinkedList<String>();
		Connection c = null; 
		ResultSet rs=null;
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		c=DriverManager.getConnection("jdbc:mysql://"+server+":"+port+"/"+databseName+"",user,password);
		rs=c.getMetaData().getTables(c.getCatalog(), null, "%", null);
		while ( rs.next() ) {
			tableNames.add(rs.getString(3));
		} 
		rs.close(); 
		c.close();
		return tableNames;
	}
	public static LinkedList<String> getTableNameMySQL(MySQLConnection connection) throws ClassNotFoundException, SQLException{
		return getTableNameMySQL(connection.getServer(), connection.getPort(), connection.getDatabaseName(), connection.getUser(), connection.getPassword());
	}
}
