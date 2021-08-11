package jcc.api.database.sqloader;

import jcc.api.database.sqloader.SQLoader.Repository;

/**
 * The class is used to give the search criteria in the findBy() and findOneBy() method in the {@link Repository} class.
 * It has two fields: parameter and value. Parameter correspond to the field name of the class to search and the value field is the value it must have to match
 * @author Hamlet Arencibia Casanova
 *
 */
public class SearchCriteria{
	private String parameter;
	private Object value;
	private int comparativeMethod;
	/**
	 * Field number indicating that the value to compare should be equal to the given.
	 */
	public static final int EQUAL_TO=0;
	/**
	 * Field number indicating that the value to compare should be bigger than the given.
	 */
	public static final int BIGGER_THAN=1;
	/**
	 * Field number indicating that the value to compare should be smaller than the given.
	 */
	public static final int SMALLER_THAN=-1;
	/**
	 * Field number indicating that the value to compare should be bigger than or equal to the given.
	 */
	public static final int BIGGER_OR_EQUAL_TO=2;
	/**
	 * Field number indicating that the value to compare should be smaller than or equal to the given.
	 */
	public static final int SMALLER_OR_EQUAL_TO=-2;
	/**
	 * Field number indicating that the value to compare should contain the given value.
	 */
	public static final int CONTAINS=3;
	/**
	 * Field number indicating that the value to compare is unlike the given value.
	 */
	public static final int NOT_EQUAL=-3;
	/**
	 * Construct a {@link SearchCriteria} with a column and a value to search in the database.
	 * @param parameter the name of the field
	 * @param value the value to be matched
	 */
	public SearchCriteria(String parameter, Object value) {
		this.parameter = parameter;
		this.value = value;
		this.comparativeMethod=EQUAL_TO;
	}
	/**
	 * Construct a {@link SearchCriteria} with a column, a value to search in the database and the way to compare it.
	 * @param parameter the name of the field
	 * @param value the value to be matched
	 * @param comparativeMethod the method to compare the value
	 */
	public SearchCriteria(String parameter, Object value, int comparativeMethod) {
		this.parameter = parameter;
		this.value = value;
		setComparativeMethod(comparativeMethod);
	}
	/**
	 * Returns the name of the column in the database where the value must be found.
	 * @return the name of the column
	 */
	public String getParameter() {
		return parameter;
	}
	/**
	 * Sets the name of the column to search.
	 * @param parameter the name of the column
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	/**
	 * Returns the value to be matched in the search.
	 * @return the value to be matched
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * Sets the value to be matched in the search.
	 * @param value the value to be matched
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	/**
	 * Returns the comparative method. 
	 * @return Returns -1 for <i>smaller than</i>, 1 for <i>bigger than</i>, -2 for <i>smaller than or equal to</i>, 2 for <i>bigger than or equal to</i>, -3 for <i>not equal to</i>, 3 for <i>LIKE </i> and 0 for <i>equal to</i> the value.
	 */
	public int getComparativeMethod() {
		return comparativeMethod;
	}
	/**
	 * Sets the comparative method. It most be -1 for <i>smaller than</i>, 1 for <i>bigger than</i>, -2 for <i>smaller than or equal to</i>, 2 for <i>bigger than or equal to</i>, -3 for <i>not equal to</i>, 3 for <i>LIKE </i> and 0 for <i>equal to</i> the value. Otherwise it will be set to equal by default.
	 * @param comparativeMethod An integer representing the comparative method
	 */
	public void setComparativeMethod(int comparativeMethod) {
		if(comparativeMethod>=-3&&comparativeMethod<=3)
			this.comparativeMethod = comparativeMethod;
		else
			this.comparativeMethod = EQUAL_TO;
	}
	
}