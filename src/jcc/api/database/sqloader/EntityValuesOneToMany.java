package jcc.api.database.sqloader;

class EntityValuesOneToMany {
	private String targetClass;
	private String referencedField;
	private String fieldName;
	public EntityValuesOneToMany(String targetClass, String fieldMatch, String fieldName) {
		super();
		this.targetClass = targetClass;
		this.referencedField = fieldMatch;
		this.fieldName = fieldName;
	}
	public String getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}
	public String getReferencedField() {
		return referencedField;
	}
	public void setReferencedField(String fieldMatch) {
		this.referencedField = fieldMatch;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}