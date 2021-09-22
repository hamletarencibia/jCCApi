package jcc.api.objects;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

/**<p>This class represent the identity number in Cuba. All cuban identity numbers should be implemented as an instance of this class. 
 * The class have many methods to work with the cuban identity number such as getSex() which returns the sex corresponding to the CI number or getAge(), 
 * which returns the age.</p>
 * <pre>Example:
 * 		{@code CI ci=new CI("93071007341");}
 * 
 * This will create a CI object with the value "93071007341". If we call the method getBirthDate(separator) it will return the next:
 * 		{@code ci.getBirthDate(CI.SLASH_SEPARATOR);}
 * 		Returns: 10/07/1993</pre>
 * @author Hamlet Arencibia Casanova*/
public class CI {
	private String value;
	
	/**Construct a CI with the specified String.
	 * @param value The String to be set for the CI number.
	 * @throws WrongLengthException Throws an exception if the value doesn't have 11 digits.
	 * @throws InvalidDateException Throws an exception if the date given by the 6 first digits ins't valid.
	 * @throws InvalidCharactersException Throws an exception if the value doesn't have numbers only.*/
	public CI(String value) throws InvalidCharactersException, InvalidDateException, WrongLengthException {
		this.value=value;
		isValid();
	}
	/**Returns the CI number.
	 * @return Returns a String with the CI number.*/
	public String getValue(){
		return value;
	}
	/**Set the value of the CI with the specified String.
	 * @param value The String to be set for the CI number.*/
	public void setValue(String value) throws InvalidCharactersException, InvalidDateException, WrongLengthException {
		this.value=value;
	}
	/**Returns a {@link Calendar} object with the birth date corresponding to the CI.
	 * @return Returns a {@link Calendar} whit the birth date corresponding to the CI number.*/
	public LocalDate getBirthDate(){
		String yearSubstring = value.substring(0, 2);
		String century = String.valueOf(LocalDate.now().getYear() - Integer.parseInt(yearSubstring)).substring(0, 2);
		int year = Integer.parseInt(century + yearSubstring);
		return LocalDate.of(year, Integer.parseInt(value.substring(2, 4)), Integer.parseInt(value.substring(4, 6)));
	}
	/**Returns the age corresponding to the CI number.
	 * @return Returns an integer value representing the age corresponding to the CI number. It will return '-1' if the CI number is not valid.*/
	public int getAge(){
		return Period.between(getBirthDate(), LocalDate.now()).getYears();
	}
	/**Return the sex corresponding to the CI number
	 * @return Return 'true' if the sex is male and 'false' if the sex is female.*/
	public boolean getSex(){
		return (value.charAt(9) - 48) % 2 == 0;
	}
	/**Determines if the given CI number is correct.
	*  @param value the CI number
	*  @return Returns 'true' if the CI number is correct and 'false' if it is not.
	* */
	public static boolean validate(String value) {
		if(value.length()==11){
			try{
				Long.parseLong(value);
			}
			catch(Exception e){
				return false;
			}
			try {
				String yearSubstring = value.substring(0, 2);
				String century = String.valueOf(LocalDate.now().getYear() - Integer.parseInt(yearSubstring)).substring(0, 2);
				int year = Integer.parseInt(century + yearSubstring);
				LocalDate.of(year, Integer.parseInt(value.substring(2, 4)), Integer.parseInt(value.substring(4, 6)));
				return true;
			}
			catch(Exception e) {
				return false;
			}
		}
		else{
			return false;
		}
	}
	private void isValid() throws InvalidCharactersException, InvalidDateException, WrongLengthException {
		if(value.length()==11){
			try{
				Long.parseLong(value);
			}
			catch(Exception e){
				throw new InvalidCharactersException();
			}
			try {
				getBirthDate();
			}
			catch(Exception e) {
				throw new InvalidDateException();
			}
		}
		else{
			throw new WrongLengthException();
		}
	}
	/**Determines if the CI number is equal to the specified CI number.
	 * @param ci The CI object to be compared.
	 * @return Returns 'true' if they are equals and 'false' if they are not. It also returns 'false' if the CI number isn't correct.*/
	public boolean equals(Object ci){
		return (ci instanceof CI id) && equals(id.getValue());
	}
	/**Determines if the CI number is equal to the specified String.
	 * @param ci The String object to be compared.
	 * @return Returns 'true' if they are equals and 'false' if they are not. It also returns 'false' if the CI number isn't correct.*/
	public boolean equals(String ci){
		return ci.equals(value);
	}
	/**Overrides the toString() method from Object superclass.
	 * @return Returns the CI number(The same returns of the getValue() method)*/
	@Override
	public String toString(){
		return value;
	}
	
	public class WrongLengthException extends Exception{
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getMessage() {
			return "The CI value must have only 11 digits.";
		}
	}
	
	public class InvalidDateException extends Exception{
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getMessage() {
			return "The CI first 6 digits doesn't match any valid date.";
		}
	}
	
	public class InvalidCharactersException extends Exception{
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getMessage() {
			return "The CI value can only contain digits.";
		}
	}
}