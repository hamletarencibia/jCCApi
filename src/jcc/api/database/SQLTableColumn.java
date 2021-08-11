package jcc.api.database;

public class SQLTableColumn {
	private String type;
	private String name;
	private boolean nullable;
	private boolean autoIncrement;
	private SQLTableKey key;
	
	public SQLTableColumn(String type, String name, boolean nullable, boolean autoIncrement, SQLTableKey key) {
		this.type = type;
		this.name = name;
		this.nullable = nullable;
		this.autoIncrement = autoIncrement;
		this.key = key;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public boolean isAutoIncrement() {
		return autoIncrement;
	}
	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}
	public SQLTableKey getKey() {
		return key;
	}
	public void setKey(SQLTableKey key) {
		this.key = key;
	}
}