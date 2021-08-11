package jcc.api.database;

import java.util.LinkedList;

public class SQLTable {
	private String tableName;
	private LinkedList<SQLTableColumn> columns;
	
	public SQLTable(){
		
	}
	public SQLTable(String tableName) {
		this.tableName = tableName;
	}
	public SQLTable(String tableName, LinkedList<SQLTableColumn> columns) {
		this.tableName = tableName;
		this.columns = columns;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public LinkedList<SQLTableColumn> getColumns() {
		if(columns==null)
			columns=new LinkedList<SQLTableColumn>();
		return columns;
	}
	public void setColumns(LinkedList<SQLTableColumn> columns) {
		this.columns = columns;
	}
}
