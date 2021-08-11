package jcc.api.database.sqloader;

class EntityValuesManyToOne{
	private String column;
	private String matchColumn;
	private String targetClass;
	private String fieldName;
	
	public EntityValuesManyToOne(String column, String matchColumn, String fieldName) {
		super();
		this.column = column;
		this.matchColumn = matchColumn;
		this.fieldName = fieldName;
	}

	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}


	public String getMatchColumn() {
		return matchColumn;
	}
	public void setMatchColumn(String matchColumn) {
		this.matchColumn = matchColumn;
	}

	public String getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(String className) {
		this.targetClass = className;
	}

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}