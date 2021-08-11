package jcc.api.database;

public class SQLTableKey {
	private String constraintName;
	private String column;
	private String refrencedTable;
	private String refrencedColumn;
	public SQLTableKey(String constraintName, String column, String refrencedTable, String refrencedColumn) {
		super();
		this.constraintName = constraintName;
		this.column = column;
		this.refrencedTable = refrencedTable;
		this.refrencedColumn = refrencedColumn;
	}
	public String getConstraintName() {
		return constraintName;
	}
	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getRefrencedTable() {
		return refrencedTable;
	}
	public void setRefrencedTable(String refrencedTable) {
		this.refrencedTable = refrencedTable;
	}
	public String getRefrencedColumn() {
		return refrencedColumn;
	}
	public void setRefrencedColumn(String refrencedColumn) {
		this.refrencedColumn = refrencedColumn;
	}
}
