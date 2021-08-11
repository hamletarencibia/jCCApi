package jcc.api.database.sqloader;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
/**
 * 
 * @author Hamlet Arencibia Casanova
 */
class EntityValues<E> {
	private String tableName;
	private EntityValuesColumn id;
	private Constructor<E> constructor;
	private LinkedList<EntityValuesColumn> columns;
	private LinkedList<EntityValuesManyToOne> manyToOnes;
	private LinkedList<EntityValuesOneToMany> oneToManys;
	private LinkedList<EntityValuesManyToMany> manyToManys;
	/**
	 * 
	 * @param tableName
	 */
	public EntityValues(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * 
	 * @param tableName
	 * @param columns
	 */
	public EntityValues(String tableName, LinkedList<EntityValuesColumn> columns) {
		this.tableName = tableName;
		this.columns = columns;
	}
	/**
	 * 
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * 
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public LinkedList<EntityValuesColumn> getColumns() {
		return columns;
	}
	public void setColumns(LinkedList<EntityValuesColumn> columns) {
		this.columns = columns;
	}
	
	public LinkedList<EntityValuesManyToOne> getManyToOnes() {
		return manyToOnes;
	}
	public void setManyToOnes(LinkedList<EntityValuesManyToOne> keys) {
		this.manyToOnes = keys;
	}

	public EntityValuesColumn getId() {
		return id;
	}
	public void setId(EntityValuesColumn id) {
		this.id = id;
	}

	public Constructor<E> getConstructor() {
		return constructor;
	}
	public void setConstructor(Constructor<E> constructor) {
		this.constructor = constructor;
	}
	public LinkedList<EntityValuesOneToMany> getOneToManys() {
		return oneToManys;
	}
	public void setOneToManys(LinkedList<EntityValuesOneToMany> oneToManys) {
		this.oneToManys = oneToManys;
	}
	
	public LinkedList<EntityValuesManyToMany> getManyToManys() {
		return manyToManys;
	}
	public void setManyToManys(LinkedList<EntityValuesManyToMany> manyToManys) {
		this.manyToManys = manyToManys;
	}
}
