package jcc.api.database.sqloader;

import java.lang.reflect.Method;

class EntityValuesColumn{
	private String fieldName;
	private String type;
	private String column;
	private Method getMethod;
	private Method setMethod;
	private EntityValuesManyToOne key;
	public EntityValuesColumn(String fieldName, String type, String column) {
		this.fieldName = fieldName;
		this.type = type;
		this.column = column;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public EntityValuesManyToOne getKey() {
		return key;
	}
	public void setKey(EntityValuesManyToOne key) {
		this.key = key;
	}
	public Method getGetMethod() {
		return getMethod;
	}
	public void setGetMethod(Method getMethod) {
		this.getMethod = getMethod;
	}
	public Method getSetMethod() {
		return setMethod;
	}
	public void setSetMethod(Method setMethod) {
		this.setMethod = setMethod;
	}
}