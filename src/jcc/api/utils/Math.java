package jcc.api.utils;
/**This class contains only static method. 
 * @author Hamlet Arencibia Casanova*/
public class Math {		
	
	public static class Percentage {
		/**Calculate the percentage that represents a part from a total.
		 * @param part The part of the total to calculate the percentage.
		 * @param total The total amount.
		 * @return Returns a double with the percentage that represent the part given from the total.*/
		public static double percentage(double part, double total){
			return part*100/total;
		}
		/**Calculates the part that represents a percentage from a total.
		 * @param percentage The percentage of the total.
		 * @param total The total amount.
		 * @return Return the part of the total that represent the given percentage.*/
		public static double part(double percentage, double total){
			return total*percentage/100;
		}
		/**Calculates the part that represents a percentage from a total.
		 * @param percentage The percentage of the total.
		 * @param part The part that represent the given percentage.
		 * @return Return the total amount.*/
		public static double total(double percentage, double part){
			return part*100/percentage;
		}
	}
}