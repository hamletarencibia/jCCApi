package jcc.api.database.sqloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is based on PHP Doctrine used in Symfony Framework. Its work is simple, it provides an easy way to execute most commons sql statements
 * such as the CRUDs (create, read, update and delete).
 * To use it you just has to create a {@link SQLoader} object with a {@link Connection}, with this object you can call
 * all the CRUDs methods and work with the database.
 * @author Hamlet Arencibia Casanova
 */
public class SQLoader {
	private Connection connection;
	/**
	 * 
	 * @param c A {@link Connection} object specifying the database connection (Works with MySQL and SQLite).
	 */
	public SQLoader(Connection c){
		connection=c;
	}
	/**
	 * Returns the connection to the database.
	 * @return The {@link Connection} object representing the connection to the database
	 */
	public Connection getConnection() {
		return connection;
	}
	/**
	 * Sets the connection to the database.
	 * @param connection A {@link Connection} object specifying the connection to the database
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	/**
	 * Returns a {@link Repository} object from which can can be obtain values from the table.
	 * @param <E> the type of element held in the {@link Repository} class.
	 * @param entity A {@link Object} object specifying the table and kind of object(class) that the values retrieved will be.
	 * @return A {@link Repository} object to work with the table values.
	 * @throws SQLoaderException 
	 */
	public <E> Repository<E> getRepository(Class<E> entity) throws SQLoaderException{
		return new Repository<E>(entity,connection);
		
	}
	/**
	 * A class nested inside {@link SQLoader}. It can only be instantiated by the method getRepository in {@link SQLoader}.
	 * It provides a direct connection with a database table by the class with the annotation {@link Entity} given in the getRepository method.
	 * @author Hamlet Arencibia Casanova
	 */
	public static class Repository<E>{
		private Class<E> entity;
		private EntityValues<E> table;
		private String query;
		private Connection connection;
		
		private Repository(Class<E> entity, Connection connection) throws SQLoaderException{
			this.entity=entity;
			this.table=SQLoaderUtils.generateDescription(this.entity);
			this.connection=connection;
		}
		private Connection getConnection() {
			return connection;
		}
		
		private EntityValues<E> getTableDescription() {
			return table;
		}
		/**
		 * This method returns only one object, since the id is an unique entity
		 * @param id An integer specifying the id of the row to search in database
		 * @return A {@link Object} object with the result of the sql statement or null if not found in database.
		 * @throws SQLoaderException 
		 * @throws SQLException 
		 */
		public E find(Object id) throws SQLoaderException, SQLException{
			Statement statement = null;
			this.query = "SELECT * FROM "+getTableDescription().getTableName();
			this.query += " WHERE " + getTableDescription().getTableName() + "." + getTableDescription().getId().getColumn() + " = \"" + id + "\"";
			ResultSet resultSet = null;
			E object = null;
			statement = getConnection().createStatement();
			resultSet = statement.executeQuery(this.query);
			while(resultSet.next()){
				object = SQLoaderUtils.instantiateConstructor(getTableDescription());
				SQLoaderUtils.assign(object, resultSet, getTableDescription(), getConnection());
			}
			statement.close();
			return object;
		}
		/**
		 * This method returns the object in the table with the minimum value 
		 * @param fieldName the name of the column to find the minimum value
		 * @return A {@link Object} object with the result of the sql statement or null if not found in database.
		 * @throws SQLoaderException if an error occurred while using the SQLoader object.
		 * @throws SQLException 
		 */
		public E findMin(String fieldName) throws SQLoaderException, SQLException{
			Statement statement = null;
			this.query="SELECT ";
			this.query+=SQLoaderUtils.generateSelectQueryFieldsWithMinMax(getTableDescription(),fieldName,false);
			this.query += " FROM "+getTableDescription().getTableName();
			E object=null;
			ResultSet resultSet = null;
			statement = getConnection().createStatement();
			resultSet = statement.executeQuery(this.query);
			while(resultSet.next()){
				object = SQLoaderUtils.instantiateConstructor(getTableDescription());
				SQLoaderUtils.assign(object, resultSet, getTableDescription(), getConnection());
			}
			statement.close();
			return object;
		}
		/**
		 * This method returns the object in the table with the maximum value
		 * @param fieldName the name of the column to find the maximum value
		 * @return A {@link Object} object with the result of the sql statement or null if not found in database.
		 * @throws SQLoaderException 
		 * @throws SQLException 
		 */
		public E findMax(String fieldName) throws SQLoaderException, SQLException{
			Statement statement = null;
			this.query="SELECT ";
			this.query+=SQLoaderUtils.generateSelectQueryFieldsWithMinMax(getTableDescription(),fieldName,true);
			this.query += " FROM "+getTableDescription().getTableName();
			E object=null;
			ResultSet resultSet = null;
			statement = getConnection().createStatement();
			resultSet = statement.executeQuery(this.query);
			while(resultSet.next()){
				object = SQLoaderUtils.instantiateConstructor(getTableDescription());
				SQLoaderUtils.assign(object, resultSet, getTableDescription(), getConnection());
			}
			statement.close();
			return object;
		}
		/**
		 * This method returns all values from the table
		 * @return A {@link LinkedList} of {@link Object} with all the values in the table or null if the table is empty.
		 * @throws SQLoaderException 
		 * @throws SQLException 
		 */
		public List<E> findAll() throws SQLoaderException, SQLException{
			List<E> result=new LinkedList<E>();
			Statement statement = null;
			query="SELECT * FROM "+getTableDescription().getTableName();
			ResultSet resultSet = null;
			statement = getConnection().createStatement();
			resultSet = statement.executeQuery(query);
			E object = null;
			while(resultSet.next()){
				object=SQLoaderUtils.instantiateConstructor(getTableDescription());
				SQLoaderUtils.assign(object, resultSet, getTableDescription(), getConnection());
				result.add(object);
			}
			statement.close();
			if(result.size() > 0)
				return result;
			else
				return null;
		}
		/**
		 * This method returns all values from the table that matches the criteria given.
		 * @param criteria A {@link SearchCriteria} array with the parameters and values to match in the search. If null it will get all the data in the table
		 * @return A {@link LinkedList} of {@link Object} with all that matches in the table or null if there were none.
		 * @throws SQLoaderException 
		 * @throws SQLException 
		 */
		public LinkedList<E> findBy(SearchCriteria[] criteria) throws SQLoaderException, SQLException{
			LinkedList<E> result = new LinkedList<E>();
			Statement statement = null;
			this.query = "SELECT * FROM "+getTableDescription().getTableName();
			if(criteria != null) {
				this.query += " WHERE ";
				this.query+=SQLoaderUtils.generateSelectQueryCriteria(getTableDescription(), criteria);
			}
			ResultSet resultSet;
			statement = getConnection().createStatement();
			resultSet = statement.executeQuery(this.query);
			while(resultSet.next()){
				E object = SQLoaderUtils.instantiateConstructor(getTableDescription());
				SQLoaderUtils.assign(object, resultSet, getTableDescription(), getConnection());
				result.add(object);
			}
			statement.close();
			if(result.size() > 0)
				return result;
			else
				return null;
		}
		/**
		 * This method returns only one object that matches the criteria given.
		 * @param criteria A {@link SearchCriteria} array with the parameters and values to match in the search
		 * @return A {@link Object} object with the result of the SQL statement or null if not found in database.
		 * @throws SQLoaderException 
		 * @throws SQLException 
		 */
		public E findOneBy(SearchCriteria[] criteria) throws SQLoaderException, SQLException{
			Statement statement = null;
			this.query="SELECT * FROM "+getTableDescription().getTableName();
			this.query+=" WHERE ";
			this.query+=SQLoaderUtils.generateSelectQueryCriteria(getTableDescription(), criteria);
			this.query+=" LIMIT 1";
			E object=null;
			ResultSet resultSet;
			statement = getConnection().createStatement();
			resultSet = statement.executeQuery(this.query);
			while(resultSet.next()){
				object=SQLoaderUtils.instantiateConstructor(getTableDescription());
				SQLoaderUtils.assign(object, resultSet, getTableDescription(), getConnection());
			}
			statement.close();
			return object;
		}
		/**
		 * Removes all the rows in the table. Erases all values in table but doesn't initialize the autoincrement counters
		 * @throws SQLException if an SQL syntax error occurs
		 */
		public void deleteAll() throws SQLException {
			Statement statement = getConnection().createStatement();
			statement.executeUpdate("DELETE FROM "+getTableDescription().getTableName());
			statement.close();
			getConnection().commit();
		}
		/**
		 * Truncates the table. Erases all values in table and initialize the autoincrement counters
		 * @throws SQLException if an SQL syntax error occurs
		 */
		public void empty() throws SQLException {
			Statement statement = getConnection().createStatement();
			statement.executeUpdate("TRUNCATE "+getTableDescription().getTableName());
			statement.close();
			getConnection().commit();
		}
	}
	/**
	 * Updates or insert a row in the table. If the object has a null id, a new row will be inserted, otherwise an existing row will be updated.
	 * @param object A {@link Object} object with the values to be inserted or updated.
	 * @throws SQLoaderException 
	 * @throws SQLException 
	 */
	public void persist(Object object) throws SQLoaderException, SQLException{
		EntityValues<?> tableDescription=SQLoaderUtils.generateDescription(object.getClass());
		Method getIdMethod=SQLoaderUtils.getGetIdMethod(tableDescription);
		Object idValue = null;
		try {
			idValue = getIdMethod.invoke(object, new Object[] {});
		} catch (IllegalAccessException e) {
			throw SQLoaderUtils.getIllegalAccessException(object.getClass().getName(), getIdMethod.getName(), getIdMethod.getModifiers());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(idValue==null){
			create(object);
		}
		else{
			update(object);
		}
	}
	/**
	 * Inserts a new row in the table with the values of the given object 
	 * @param object A {@link Object} object with the values to be inserted in a new row
	 * @throws SQLoaderException 
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public int create(Object object) throws SQLoaderException, SQLException{
		EntityValues<?> tableDescription=SQLoaderUtils.generateDescription(object.getClass());
		int id = 0;
		if(tableDescription!=null) {
			String query="INSERT INTO "+tableDescription.getTableName()+" (";
			LinkedList<EntityValuesColumn> columns=tableDescription.getColumns();
			for(int i=1;i<columns.size();i++){
				if(i==1){
					query+=columns.get(i).getColumn();
				}
				else{
					query+=","+columns.get(i).getColumn();
				}
			}
			query+=") VALUES (";
			for(int i=1;i<columns.size();i++){
				EntityValuesColumn column=columns.get(i);
				String name=Misc.Strings.capitalize(column.getFieldName());
				Method method=null;
				try {
					method=object.getClass().getDeclaredMethod("get"+name, new Class<?>[]{});
				} catch (NoSuchMethodException e) {
					throw SQLoaderUtils.getNoSuchMethodException(object.getClass().getName(), "get"+name+"()");
				}
				Object value = null;
				try {
					value = method.invoke(object, new Object[]{});
				} catch (IllegalAccessException e) {
					throw SQLoaderUtils.getIllegalAccessException(object.getClass().getName(), method.getName(), method.getModifiers());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(i!=1){
					query+=",";
				}
				if(value == null) {
					query+="null";
				}
				else {
					if(column.getType().equals("ManyToOne")){
						Method getIdMethod = SQLoaderUtils.getGetIdMethod(SQLoaderUtils.generateDescription(value.getClass()));
						try {
							System.out.println(getIdMethod);
							value=getIdMethod.invoke(value, new Object[] {});
						} catch (IllegalAccessException e) {
							throw SQLoaderUtils.getIllegalAccessException(object.getClass().getName(), getIdMethod.getName(), getIdMethod.getModifiers());
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(column.getType().equals("Boolean")){
						if((boolean) value){
							value = Integer.parseInt("1");
						}
						else{
							value = Integer.parseInt("0");
						}
					}
					if(column.getType().equals("Date")){
						Calendar calendar=(Calendar) value;
						value=DateUtils.dateToString(calendar);
					}
					query+="\""+value+"\"";
				}
			}
			query+=");";
			PreparedStatement statement = null;
			ResultSet rs = null;
			statement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			if(rs.next())
				id=rs.getInt(1);
			for(EntityValuesManyToMany manyToMany:tableDescription.getManyToManys()) {
				Method valuesMethod = null;
				try {
					valuesMethod = object.getClass().getDeclaredMethod("get"+Misc.Strings.capitalize(manyToMany.getFieldName()), new Class<?>[]{});
				} catch (NoSuchMethodException e) {
					throw SQLoaderUtils.getNoSuchMethodException(object.getClass().getName(), "get"+Misc.Strings.capitalize(manyToMany.getFieldName())+"()");
				}
				LinkedList<Object> values = null;
				try {
					values = (LinkedList<Object>) valuesMethod.invoke(object, new Object[]{});
				} catch (IllegalAccessException e) {
					throw SQLoaderUtils.getIllegalAccessException(object.getClass().getName(), valuesMethod.getName(), valuesMethod.getModifiers());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(values == null)
					continue;
				EntityValues<?> td = null;
				try {
					td = SQLoaderUtils.generateDescription(object.getClass().getClassLoader().loadClass(manyToMany.getTargetClass()));
				} catch (ClassNotFoundException e2) {
					throw SQLoaderUtils.getClassNotFoundException(manyToMany.getTargetClass());
				}
				for(Object v:values) {
					Method vId = SQLoaderUtils.getGetIdMethod(td);
					Object vIdValue = null;
					try {
						vIdValue = vId.invoke(v, new Object[]{});
					} catch (IllegalAccessException e1) {
						throw SQLoaderUtils.getIllegalAccessException(v.getClass().getName(), vId.getName(), vId.getModifiers());
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Statement s = getConnection().createStatement();
					String q = "INSERT INTO "+manyToMany.getTableName()+" ("+manyToMany.getColumnName()+","+manyToMany.getReferencedColumnName()+") VALUES('"+id+"','"+vIdValue+"')";
					s.execute(q);
					s.close();
					getConnection().commit();
				}
			}
			statement.close();
			getConnection().commit();
		}
		return id;
	}
	/**
	 * Updates an existing row in the table with the values of the given object
	 * @param object A {@link Object} object with the values for the row to be updated. It updates the row that matches the object id.
	 * @throws SQLoaderException 
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public void update(Object object) throws SQLoaderException, SQLException{
		EntityValues<?> tableDescription=SQLoaderUtils.generateDescription(object.getClass());
		if(tableDescription!=null) {
			Statement statement = null;
			String query="UPDATE "+tableDescription.getTableName()+" SET ";
			LinkedList<EntityValuesColumn> columns=tableDescription.getColumns();
			for(int i=1;i<columns.size();i++){
				EntityValuesColumn column=columns.get(i);
				String name=Misc.Strings.capitalize(column.getFieldName());
				Method method=null;
				try {
					method=object.getClass().getDeclaredMethod("get"+name, new Class<?>[]{});
				} catch (NoSuchMethodException e) {
					throw SQLoaderUtils.getNoSuchMethodException(object.getClass().getName(), "get"+name+"()");
				}
				Object value = null;
				try {
					value = method.invoke(object, new Object[]{});
				} catch (IllegalAccessException e) {
					throw SQLoaderUtils.getIllegalAccessException(object.getClass().getName(), method.getName(), method.getModifiers());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(column.getType().equals("ManyToOne") && value != null){
					Method getIdMethod = SQLoaderUtils.getGetIdMethod(SQLoaderUtils.generateDescription(value.getClass()));
					try {
						value=getIdMethod.invoke(value, new Object[] {});
					} catch (IllegalAccessException e) {
						throw SQLoaderUtils.getIllegalAccessException(value.getClass().getName(), getIdMethod.getName(), getIdMethod.getModifiers());
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(column.getType().equals("Boolean") && value != null){
					if((boolean) value){
						value = Integer.parseInt("1");
					}
					else{
						value = Integer.parseInt("0");
					}
				}
				if(column.getType().equals("Date") && value != null){
					Calendar calendar=(Calendar) value;
					value=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
				}
				if(i==1){
					query+=column.getColumn()+" = \""+value+"\"";
				}
				else{
					query+=" ,"+column.getColumn()+" = \""+value+"\"";
				}
			}
			Method getIdMethod=SQLoaderUtils.getGetIdMethod(tableDescription);
			Object methodIdValue = null;
			try {
				methodIdValue = getIdMethod.invoke(object, new Object[] {});
			} catch (IllegalAccessException e) {
				throw SQLoaderUtils.getIllegalAccessException(object.getClass().getName(), getIdMethod.getName(), getIdMethod.getModifiers());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			query+=" WHERE "+tableDescription.getTableName()+".id = "+methodIdValue;
			statement = getConnection().createStatement();
			statement.executeUpdate(query);
			for(EntityValuesManyToMany manyToMany:tableDescription.getManyToManys()) {
				Statement st = getConnection().createStatement();
				String qr = "DELETE FROM "+manyToMany.getTableName()+" WHERE "+manyToMany.getColumnName()+" = '"+methodIdValue+"'";
				st.execute(qr);
				Method valuesMethod;
				try {
					valuesMethod = object.getClass().getDeclaredMethod("get"+Misc.Strings.capitalize(manyToMany.getFieldName()), new Class<?>[]{});
				} catch (NoSuchMethodException e) {
					throw SQLoaderUtils.getNoSuchMethodException(object.getClass().getName(), "get"+Misc.Strings.capitalize(manyToMany.getFieldName())+"()");
				}
				LinkedList<Object> values = null;
				try {
					values = (LinkedList<Object>) valuesMethod.invoke(object, new Object[]{});
				} catch (IllegalAccessException e) {
					throw SQLoaderUtils.getIllegalAccessException(object.getClass().getName(), valuesMethod.getName(), valuesMethod.getModifiers());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				EntityValues<?> td = null;
				try {
					td = SQLoaderUtils.generateDescription(object.getClass().getClassLoader().loadClass(manyToMany.getTargetClass()));
				} catch (ClassNotFoundException e) {
					throw SQLoaderUtils.getClassNotFoundException(manyToMany.getTargetClass());
				}
				for(Object v:values) {
					Method vId = SQLoaderUtils.getGetIdMethod(td);
					Object vIdValue = null;
					try {
						vIdValue = vId.invoke(v, new Object[]{});
					} catch (IllegalAccessException e) {
						throw SQLoaderUtils.getIllegalAccessException(v.getClass().getName(), vId.getName(), vId.getModifiers());
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Statement s = getConnection().createStatement();
					String q = "INSERT INTO "+manyToMany.getTableName()+" ("+manyToMany.getColumnName()+","+manyToMany.getReferencedColumnName()+") VALUES('"+methodIdValue+"','"+vIdValue+"')";
					s.execute(q);
				}
			}
			statement.close();
			getConnection().commit();
		}
	}
	/**
	 * Deletes a row in the table.
	 * @param object A {@link Object} object representing the table row to be deleted.
	 * @throws SQLoaderException 
	 * @throws SQLException 
	 */
	public void delete(Object object) throws SQLoaderException, SQLException{
		EntityValues<?> tableDescription=SQLoaderUtils.generateDescription(object.getClass());
		Method getIdMethod=SQLoaderUtils.getGetIdMethod(tableDescription);
		if(getIdMethod!=null) {
			Statement statement = null;
			Object idValue = null;
			try {
				idValue = getIdMethod.invoke(object, new Object[] {});
			} catch (IllegalAccessException e1) {
				throw SQLoaderUtils.getIllegalAccessException(object.getClass().getName(), getIdMethod.getName(), getIdMethod.getModifiers());
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(EntityValuesManyToMany manyToMany:tableDescription.getManyToManys()) {
				Statement st = null;
				String qr = "DELETE FROM "+manyToMany.getTableName()+" WHERE "+manyToMany.getColumnName()+" = '"+idValue+"'";
				st = getConnection().createStatement();
				st.execute(qr);
			}
			statement = getConnection().createStatement();
			statement.execute("DELETE FROM "+tableDescription.getTableName()+" WHERE "+tableDescription.getTableName()+"."+tableDescription.getId().getColumn()+" = "+idValue);
			statement.close();
			getConnection().commit();
		}
	}
}