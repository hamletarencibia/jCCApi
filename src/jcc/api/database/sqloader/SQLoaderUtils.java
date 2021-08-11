package jcc.api.database.sqloader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

final class SQLoaderUtils {
	/*
	 *########################################################################
	 * Generates the query statement, putting the name of the fields to search 
	 * #######################################################################
	 */
	public static <E>String generateSelectQueryFieldsWithMinMax(EntityValues<E> tableDescription,String columnName, boolean max){
		String query="";
		boolean first=true;
		String minMax = "MAX";
		if(!max)
			minMax="MIN";
		for(EntityValuesColumn column:tableDescription.getColumns()){
			if(first) {
				first=false;
			}
			else {
				query+=", ";
			}
			if(column.getFieldName().equals(columnName)) {
				query += minMax+"("+tableDescription.getTableName()+"."+column.getColumn()+")"+" as "+tableDescription.getTableName()+column.getColumn();
			}
			else {
				query+=tableDescription.getTableName()+"."+column.getColumn()+" as "+tableDescription.getTableName()+column.getColumn();
			}
		}
		return query;
	}
	/*
	 *########################################################################
	 * Generates the query statement, putting the WHERE clause parameters for the search criteria
	 * #######################################################################
	 * 
	 * 
	 * agregar un throw si el campo a buscar no se encuentra
	 */
	public static <E>String generateSelectQueryCriteria(EntityValues<E> tableDescription, SearchCriteria[] searchCriteria) throws SQLoaderException{
		String query="";
		int loopCounter = 0;
		for(SearchCriteria criteria:searchCriteria){
			String columnName="";
			boolean hasKey=false;
			for(EntityValuesColumn column:tableDescription.getColumns()){
				if(criteria.getParameter().equals(column.getFieldName())){
					columnName=column.getColumn();
					if(column.getKey()!=null){
						hasKey=true;
					}
					break;
				}
			}
			Object value=criteria.getValue();				
			if(value.getClass().getName().equals("java.lang.Boolean")){
				if((boolean) value){
					value = Integer.parseInt("1");
				}
				else{
					value = Integer.parseInt("0");
				}
			}
			if(hasKey){
				Method getIdMethod = getGetIdMethod(tableDescription);
				try {
					value=getIdMethod.invoke(value, new Object[] {});
				} catch (IllegalAccessException e) {
					throw getIllegalAccessException(value.getClass().getName(), getIdMethod.getName(), getIdMethod.getModifiers());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String symbol="=";
			switch(criteria.getComparativeMethod()) {
				case SearchCriteria.SMALLER_THAN:
					symbol="<";
					break;
				case SearchCriteria.BIGGER_THAN:
					symbol=">";
					break;
				case SearchCriteria.BIGGER_OR_EQUAL_TO:
					symbol=">=";
					break;
				case SearchCriteria.SMALLER_OR_EQUAL_TO:
					symbol="<=";
					break;
				case SearchCriteria.CONTAINS:
					symbol="LIKE";
					break;
				case SearchCriteria.NOT_EQUAL:
					symbol="!=";
					break;
			}
			if(loopCounter!=0){
				query+=" AND ";
			}
			if(criteria.getComparativeMethod()==SearchCriteria.CONTAINS)
				query+=tableDescription.getTableName()+"."+columnName+" "+symbol+" \"%"+value+"%\"";
			else
				query+=tableDescription.getTableName()+"."+columnName+" "+symbol+" \""+value+"\"";
			loopCounter++;
		}
		return query;
	}
	public static void assign(Object object,ResultSet resultSet,EntityValues<?> tableDescription, Connection connection) throws SQLoaderException, SQLException {
		HashMap<String,Object> map = new HashMap<>();
		assignSelectedValues(map, object, resultSet, tableDescription, connection);
		assignManyToOneValues(map, object, resultSet, tableDescription, connection);
		assignOneToManyValues(map, object, tableDescription, connection);
		assignManyToManyValues(map, object, tableDescription, connection);
	}
	/*
	 *########################################################################
	 * Assigns the result of the statement to the objects 
	 * #######################################################################
	 */
	public static <E>void assignSelectedValues(HashMap<String, Object> map, Object object,ResultSet resultSet,EntityValues<E> tableDescription, Connection connection) throws SQLoaderException, SQLException {
		for(EntityValuesColumn column:tableDescription.getColumns()){
			if(column.getType().equals("ManyToOne"))
				continue;
			Method setToObject = column.getSetMethod();
			Object value = null;
			Object result = resultSet.getObject(column.getColumn());
			String nameTemp=Misc.Strings.capitalize(column.getFieldName());
			//Assigns date object
			if(column.getType().equals("Date")||column.getType().equals("Time")||column.getType().equals("DateTime")){
				if(result!=null){
					value=DateUtils.stringToCalendar(result.toString());
				}
			}
			//Assigns any other object from java.lang
			else{
				if(result!=null){
					try {
						value=Class.forName("java.lang."+column.getType()).getConstructor(Class.forName("java.lang.String")).newInstance(result.toString());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			try {
				setToObject.invoke(object, value);
			} catch (IllegalAccessException e) {
				throw getIllegalAccessException(object.getClass().getName(), "set"+nameTemp+"("+column.getType()+")", setToObject.getModifiers());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Method getIdMethod = getGetIdMethod(tableDescription);
		Object idValue = null;
		try {
			idValue = getIdMethod.invoke(object, new Object[] {});
		} catch (IllegalAccessException e) {
			throw getIllegalAccessException(object.getClass().getName(), getIdMethod.getName(), getIdMethod.getModifiers());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put(object.getClass().getName()+"-"+idValue, object);
	}
	/*
	 *########################################################################
	 * Assigns the values on a Many-To-One relationship
	 * #######################################################################
	 */
	public static <E>void assignManyToOneValues(HashMap<String, Object> map, Object object, ResultSet resultSet, EntityValues<E> tableDescription, Connection connection) throws SQLoaderException, SQLException {
		for(EntityValuesManyToOne manyToOne:tableDescription.getManyToOnes()) {
			Class<?> entity = null;
			try {
				entity = object.getClass().getClassLoader().loadClass(manyToOne.getTargetClass());
			} catch (ClassNotFoundException e) {
				throw getClassNotFoundException(manyToOne.getTargetClass());
			}
			Method setToObject;
			try {
				setToObject = object.getClass().getMethod("set"+Misc.Strings.capitalize(manyToOne.getFieldName()), entity);
			} catch (NoSuchMethodException e) {
				throw getNoSuchMethodException(object.getClass().getName(), "set"+Misc.Strings.capitalize(manyToOne.getFieldName())+"("+entity.getName()+")");
			}
			Object keyObject = null;
			keyObject = resultSet.getObject(manyToOne.getColumn());
			if(map.containsKey(entity.getName()+"-"+keyObject)) {
				try {
					setToObject.invoke(object,map.get(entity.getName()+"-"+keyObject));
				} catch (IllegalAccessException e) {
					throw getIllegalAccessException(object.getClass().getName(), setToObject.getName(), setToObject.getModifiers());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			EntityValues<?> newTableDescription = generateDescription(entity);
			String query = "SELECT * FROM "+newTableDescription.getTableName();
			query += " WHERE " + newTableDescription.getTableName() + "." + newTableDescription.getId().getColumn() + " = \"" + keyObject + "\"";
			Statement statement = null;
			statement = connection.createStatement();
			ResultSet newResultSet = null;
			Object newObject = instantiateConstructor(newTableDescription);
			boolean count=false;
			newResultSet = statement.executeQuery(query);
			while(newResultSet.next()){
				//Assigns the values to the object
				SQLoaderUtils.assignSelectedValues(map, newObject, newResultSet, newTableDescription, connection);
				//Assigns the Many-To-One
				SQLoaderUtils.assignManyToOneValues(map, newObject, newResultSet, newTableDescription, connection);
				//Assigns the One-To-Many
				SQLoaderUtils.assignOneToManyValues(map, newObject, newTableDescription, connection);
				//Assigns the Many-To-Many
				SQLoaderUtils.assignManyToManyValues(map, newObject, newTableDescription, connection);
				count = true;
			}
			statement.close();
			connection.commit();
			if(count) {
				try {
					setToObject.invoke(object,newObject);
				} catch (IllegalAccessException e) {
					throw getIllegalAccessException(object.getClass().getName(), setToObject.getName(), setToObject.getModifiers());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/*
	 *########################################################################
	 * Assigns the values on a One-To-Many relationship
	 * #######################################################################
	 */
	public static <E>void assignOneToManyValues(HashMap<String, Object> map, Object object, EntityValues<E> tableDescription, Connection connection) throws SQLoaderException, SQLException {
		for(EntityValuesOneToMany oneToMany:tableDescription.getOneToManys()) {
			Class<?> entity;
			try {
				entity = object.getClass().getClassLoader().loadClass(oneToMany.getTargetClass());
			} catch (ClassNotFoundException e) {
				throw getClassNotFoundException(oneToMany.getTargetClass());
			}
			
			EntityValues<?> newTableDescription = generateDescription(entity);
			LinkedList<Object> result = new LinkedList<Object>();
			Statement statement = null;
			statement = connection.createStatement();
			Object referencedValue = null;
			Method idMethod = getGetIdMethod(tableDescription);
			try {
				referencedValue = idMethod.invoke(object, new Object[] {});
			} catch (IllegalAccessException e1) {
				throw getIllegalAccessException(object.getClass().getName(), idMethod.getName(), idMethod.getModifiers());
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String query = "SELECT * FROM "+newTableDescription.getTableName();
			query += " WHERE "+newTableDescription.getTableName()+"."+oneToMany.getReferencedField()+" = "+referencedValue;
			ResultSet resultSet = null;
			boolean count=false;
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				Object keyObject = null;
				try {
					keyObject = resultSet.getObject(newTableDescription.getId().getColumn());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(map.containsKey(entity.getName()+"-"+keyObject)) {
					result.add(map.get(entity.getName()+"-"+keyObject));
					count = true;
					continue;
				}
				Object newObject = instantiateConstructor(newTableDescription);
				//Assigns the values to the object
				SQLoaderUtils.assignSelectedValues(map, newObject, resultSet, newTableDescription, connection);
				//Assigns the Many-To-One
				SQLoaderUtils.assignManyToOneValues(map, newObject, resultSet, newTableDescription, connection);
				//Assigns the One-To-Many
				SQLoaderUtils.assignOneToManyValues(map, newObject, newTableDescription, connection);
				//Assigns the Many-To-Many
				SQLoaderUtils.assignManyToManyValues(map, newObject, newTableDescription, connection);
				result.add(newObject);
				count=true;
			}
			statement.close();
			connection.commit();
			if(count) {
				Method setToObject;
				try {
					setToObject = object.getClass().getMethod("set"+Misc.Strings.capitalize(oneToMany.getFieldName()), LinkedList.class);
				} catch (NoSuchMethodException e) {
					throw getNoSuchMethodException(object.getClass().getName(), "set"+Misc.Strings.capitalize(oneToMany.getFieldName())+"(java.util.LinkedList)");
				}
				try {
					setToObject.invoke(object, result);
				} catch (IllegalAccessException e) {
					throw getIllegalAccessException(object.getClass().getName(), setToObject.getName(), setToObject.getModifiers());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/*
	 *########################################################################
	 * Assigns the values on a Many-To-Many relationship
	 * #######################################################################
	 */
	public static <E>void assignManyToManyValues(HashMap<String, Object> map, Object object, EntityValues<E> tableDescription, Connection connection) throws SQLoaderException, SQLException {
		for(EntityValuesManyToMany manyToMany:tableDescription.getManyToManys()) {
			Class<?> entity;
			try {
				entity = object.getClass().getClassLoader().loadClass(manyToMany.getTargetClass());
			} catch (ClassNotFoundException e) {
				throw getClassNotFoundException(manyToMany.getTargetClass());
			}
			EntityValues<?> targetDescription = generateDescription(entity);
			Statement statement = null;
			statement = connection.createStatement();
			String query = "SELECT * FROM "+manyToMany.getTableName()+", "+targetDescription.getTableName();
			query += " WHERE "+manyToMany.getTableName()+"."+manyToMany.getReferencedColumnName()+" = "+targetDescription.getTableName()+"."+manyToMany.getTargetReferencedColumnName();
			Object value = null;
			Method getIdMethod = getGetIdMethod(tableDescription);
			try {
				value=getIdMethod.invoke(object, new Object[] {});
			} catch (IllegalAccessException e2) {
				throw getIllegalAccessException(object.getClass().getName(), getIdMethod.getName(), getIdMethod.getModifiers());
			} catch (IllegalArgumentException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (InvocationTargetException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			query += " AND "+manyToMany.getTableName()+"."+manyToMany.getColumnName()+" = \""+value+"\"";
			ResultSet resultSet = null;
			boolean count=false;
			LinkedList<Object> result=new LinkedList<Object>();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				Object keyObject = null;
				try {
					keyObject = resultSet.getObject(targetDescription.getId().getColumn());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(map.containsKey(entity.getName()+"-"+keyObject)) {
					result.add(map.get(entity.getName()+"-"+keyObject));
					count = true;
					continue;
				}
				Object tempObject = instantiateConstructor(targetDescription);
				//Assigns the values to the object
				SQLoaderUtils.assignSelectedValues(map, tempObject, resultSet, targetDescription, connection);
				//Assigns the Many-To-One
				SQLoaderUtils.assignManyToOneValues(map, tempObject, resultSet, targetDescription, connection);
				//Assigns the One-To-Many
				SQLoaderUtils.assignOneToManyValues(map, tempObject,targetDescription, connection);
				//Assigns the Many-To-Many
				SQLoaderUtils.assignManyToManyValues(map, tempObject, targetDescription, connection);
				result.add(tempObject);
				count = true;
			}
			statement.close();
			connection.commit();
			if(count) {
				Method setToObject;
				try {
					setToObject = object.getClass().getMethod("set"+Misc.Strings.capitalize(manyToMany.getFieldName()), LinkedList.class);
				} catch (NoSuchMethodException e) {
					throw getNoSuchMethodException(object.getClass().getName(), "set"+Misc.Strings.capitalize(manyToMany.getFieldName()));
				}
				try {
					setToObject.invoke(object, result);
				} catch (IllegalAccessException e) {
					throw getIllegalAccessException(object.getClass().getName(), setToObject.getName(), setToObject.getModifiers());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/*#####################################################################
	################	Generates the description of the table  ############
	#####################################################################*/
	@SuppressWarnings("unchecked")
	public static <E>EntityValues<E> generateDescription(Class<E> entity) throws SQLoaderException {
		if(EntityLoader.getEntitiesMap().get(entity.getName())!=null) 
			return (EntityValues<E>) EntityLoader.getEntitiesMap().get(entity.getName());
		EntityValues<E> entityValues = null;
		if(checkClass(entity)) {
			String tableName=entity.getAnnotation(Table.class).name();
			if(tableName.equals(""))
				tableName = entity.getSimpleName();
			Field[] fields = addFields(entity.getSuperclass(),entity.getDeclaredFields());
			entityValues=new EntityValues<E>(tableName);
			Constructor<E> constructor = null;
			try {
				constructor = entity.getConstructor(new Class<?> [] {});
			} catch (NoSuchMethodException e1) {
				throw getNoSuchConstructorException(entity.getName(), entity.getName()+"()");
			}
			entityValues.setConstructor(constructor);
			LinkedList<EntityValuesColumn> columns=new LinkedList<EntityValuesColumn>();
			LinkedList<EntityValuesManyToOne> manyToOnes=new LinkedList<EntityValuesManyToOne>();
			LinkedList<EntityValuesOneToMany> oneToManys = new LinkedList<EntityValuesOneToMany>();
			LinkedList<EntityValuesManyToMany> manyToManys = new LinkedList<EntityValuesManyToMany>();
			for(Field field:fields){
				boolean isId=false;
				for(Annotation annotation:field.getDeclaredAnnotations()) {
					if(annotation.annotationType().getName().equals("jcc.api.database.sqloader.Id")) {
						isId=true;
						continue;
					}
					else if(annotation.annotationType().getName().equals("jcc.api.database.sqloader.Column")){
						String type = ((Column) annotation).type();
						String column = ((Column) annotation).column();
						if(type.equals(""))
							type = field.getType().getSimpleName();
						if(column.equals(""))
							column = field.getName();
						EntityValuesColumn entityColumn = new EntityValuesColumn(field.getName(), type, column);
						String capitalizedName = Misc.Strings.capitalize(field.getName());
						String argName = "java.lang."+type;
						if(type.equals("Date")||type.equals("Time")||type.equals("DateTime")){
							argName = "java.util.Calendar";
						}
						try {
							entityColumn.setGetMethod(findMethod(entity, "get"+capitalizedName, new Class<?>[] {}));
							entityColumn.setSetMethod(findMethod(entity, "set"+capitalizedName, Class.forName(argName)));
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						columns.add(entityColumn);
						if(isId) {
							isId=false;
							entityValues.setId(entityColumn);
						}
					}
					else if(annotation.annotationType().getName().equals("jcc.api.database.sqloader.ManyToOne")){
						String columnName = ((ManyToOne) annotation).columnName();
						String referencedColumnName = ((ManyToOne) annotation).referencedColumnName();
						String targetClass = field.getType().getName();
						if(columnName.equals(""))
							columnName = field.getName();
						if(referencedColumnName.equals(""))
							referencedColumnName = "id";
						EntityValuesColumn entityColumn= new EntityValuesColumn(field.getName(), "ManyToOne", columnName);
						columns.add(entityColumn);
						EntityValuesManyToOne key= new EntityValuesManyToOne(columnName, referencedColumnName,field.getName());
						key.setTargetClass(targetClass);
						manyToOnes.add(key);
						columns.getLast().setKey(key);
					}
					else if(annotation.annotationType().getName().equals("jcc.api.database.sqloader.OneToMany")){
						String referencedField = ((OneToMany) annotation).mappedBy();
						String targetClass = field.getGenericType().getTypeName().split("<")[1].split(">")[0];
						if(referencedField.equals(""))
							referencedField = entity.getSimpleName();
						EntityValuesOneToMany oneToMany = new EntityValuesOneToMany(targetClass, referencedField, field.getName());
						oneToManys.add(oneToMany);
					}
					else if(annotation.annotationType().getName().equals("jcc.api.database.sqloader.ManyToMany")){
						String targetClass = field.getGenericType().getTypeName().split("<")[1].split(">")[0];
						String columnName = ((ManyToMany) annotation).columnName();
						String referencedColumnName = ((ManyToMany) annotation).referencedColumnName();
						String targetReferencedColumnName = ((ManyToMany) annotation).targetReferencedColumnName();
						if(columnName.equals(""))
							columnName = entity.getSimpleName();
						if(referencedColumnName.equals("")) {
							String[] targetClassNameArray = targetClass.split("\\.");
							referencedColumnName = targetClassNameArray[targetClassNameArray.length-1];
						}
						if(targetReferencedColumnName.equals(""))
							targetReferencedColumnName = "id";
						EntityValuesManyToMany manyToMany = new EntityValuesManyToMany(field.getName(), 
								targetClass, 
								((ManyToMany) annotation).joinTable(), 
								columnName, 
								referencedColumnName, 
								targetReferencedColumnName);
						manyToManys.add(manyToMany);
					}
				}
			}
			entityValues.setColumns(columns);
			entityValues.setManyToOnes(manyToOnes);
			entityValues.setOneToManys(oneToManys);
			entityValues.setManyToManys(manyToManys);
		}
		else {
			throw getInvalidClassStructureException();
		}
		EntityLoader.getEntitiesMap().put(entity.getName(), entityValues);
		return entityValues;
	}
	/*###################################################################
	################	Checks if the given class is correct ############
	###################################################################*/
	public static boolean checkClass(Class<?> entity) {
		Annotation[] annotations=entity.getAnnotations();
		boolean isEntity=false;
		for(Annotation annotation:annotations) {
			if(annotation.annotationType().getName().equals("jcc.api.database.sqloader.Entity")) {
				isEntity=true;
			}
		}
		return isEntity;
	}
	/*#############################################################################
	################	Adds the field of the class and its superclass ############
	#############################################################################*/
	public static Field[] addFields(Class<?> entity,Field[] fields) {
		if(checkClass(entity)) {
			return addFields(entity.getSuperclass(), mergeFields(fields, entity.getDeclaredFields()));
		}
		return fields;
	}
	/*#############################################################################
	################	Finds the method in the class or in its superclass     ############
	#############################################################################*/
	public static Method findMethod(Class<?> entity, String methodName, Class<?> ... arg) {
		Method method = null;
		try {
			method = entity.getDeclaredMethod(methodName, arg);
		} catch (NoSuchMethodException | SecurityException e) {
			method = findMethod(entity.getSuperclass(), methodName, arg);
		}
		return method;
	}
	/*##########################################################
	################	Merge the arrays of fields  ############
	##########################################################*/
	public static Field[] mergeFields(Field[] array1, Field[] array2){
		Field[] array3=new Field[array1.length+array2.length];
		for(int i=0;i<array1.length;i++) {
			array3[i]=array1[i];
		}
		for(int i=array1.length;i<array3.length;i++) {
			array3[i]=array2[i-array1.length];
		}
		return array3;
	}
	/*######################################################################
	################	Return the get method of the id field   ############
	######################################################################*/
	public static <E>Method getGetIdMethod(EntityValues<E> tableDescription) throws SQLoaderException{
		return tableDescription.getId().getGetMethod();
	}
	/*######################################################################
	################	Returns a new object of the given class   ############
	######################################################################*/
	public static <E>E instantiateConstructor(EntityValues<E> tableDescription) throws SQLoaderException {
		E object = null;
		try {
			object = tableDescription.getConstructor().newInstance(new Object[] {});
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	public static SQLoaderException getInvalidClassStructureException() {
		return new SQLoaderException(SQLoaderException.INVALID_CLASS_STRUCTURE);
	}
	public static SQLoaderException getNoSuchMethodException(String className, String methodName) {
		return new SQLoaderException(SQLoaderException.NO_SUCH_METHOD, className, methodName);
	}
	public static SQLoaderException getClassNotFoundException(String className) {
		return new SQLoaderException(SQLoaderException.CLASS_NOT_FOUND, className);
	}
	public static SQLoaderException getIllegalAccessException(String className, String methodName, int modifier) {
		String modifierName = "private";
		if(modifier == Modifier.PROTECTED)
			modifierName = "protected";
		return new SQLoaderException(SQLoaderException.ILLEGAL_ACCESS, className, methodName, modifierName);
	}
	public static SQLoaderException getNoSuchConstructorException(String className, String constructor) {
		return new SQLoaderException(SQLoaderException.NO_SUCH_CONSTRUCTOR, className, constructor);
	}
	public static SQLoaderException getIllegalAccessConstructorException(String className, String constructor, int modifier) {
		String modifierName = "private";
		if(modifier == Modifier.PROTECTED)
			modifierName = "protected";
		return new SQLoaderException(SQLoaderException.ILLEGAL_ACCESS_CONSTRUCTOR, className, constructor, modifierName);
	}
	
}