package jcc.api.database.sqloader;

class EntityValuesManyToMany {
	private String fieldName;
	private String targetClass;
	private String tableName;
	private String columnName;
	private String referencedColumnName;
	private String targetReferencedColumnName;
	public EntityValuesManyToMany(String fieldName, String targetClass, String tableName, String columnName,
			String referencedColumnName, String targetReferencedColumnName) {
		super();
		this.fieldName = fieldName;
		this.targetClass = targetClass;
		this.tableName = tableName;
		this.columnName = columnName;
		this.referencedColumnName = referencedColumnName;
		this.targetReferencedColumnName = targetReferencedColumnName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(String targertClass) {
		this.targetClass = targertClass;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getReferencedColumnName() {
		return referencedColumnName;
	}
	public void setReferencedColumnName(String referencedColumnName) {
		this.referencedColumnName = referencedColumnName;
	}
	public String getTargetReferencedColumnName() {
		return targetReferencedColumnName;
	}
	public void setTargetReferencedColumnName(String targetReferencedColumnName) {
		this.targetReferencedColumnName = targetReferencedColumnName;
	}
}