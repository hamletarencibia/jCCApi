package jcc.api.database.sqloader;
/**
 * The class provides the condition ORDER BY to the SQLoader's selection methods like findAll() and findBy().
 * @author Hamlet Arencibia Casanova
 *
 */
public class OrderBy {
	private String column;
	private int orderDirection;
	/**
	 * Field number indicating that the result must be in ascending order.
	 */
	public final static int ASC = 1;
	/**
	 * Field number indicating that the result must be in descending order.
	 */
	public final static int DESC = 0;
	/**
	 * Construct a {@link OrderBy} with a column and the order direction.
	 * @param column name of the column by which the result will be sorted
	 * @param orderDirection the direction of the sorting. Can be {@link OrderBy}.DESC or {@link OrderBy}.DESC
	 */
	public OrderBy(String column, int orderDirection) {
		super();
		this.column = column;
		this.orderDirection = orderDirection;
	}
	/**
	 * Returns the column by which the result will be sorted.
	 * @return name of the column
	 */
	public String getColumn() {
		return column;
	}
	/**
	 * Sets the column by which the table will be ordered.
	 * @param column name of the column
	 */
	public void setColumn(String column) {
		this.column = column;
	}
	/**
	 * Returns the direction of the sorting. 
	 * @return 1 for ascending and 0 for descending
	 */
	public int getOrderDirection() {
		return orderDirection;
	}
	/**
	 * Sets the direction of the sorting. Can be {@link OrderBy}.DESC or {@link OrderBy}.ASC
	 * @param orderDirection it must be 1 for <i>ascending</i> or 0 for <i>descending</i>. Otherwise it will be set to 1.
	 */
	public void setOrderDirection(int orderDirection) {
		if(orderDirection == 0 || orderDirection == 1)
			this.orderDirection = orderDirection;
		else
			this.orderDirection = 1;
	}
}
