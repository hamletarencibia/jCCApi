package jcc.api.objects;

import java.util.Calendar;

import jcc.api.utils.DateUtils;

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
	private char [] ciArray;
	/**Field number used in method getBirthDate for separate the numbers with a "/" in the birth date returned.*/
	public static final int SLASH_SEPARATOR=0;
	/**Field number used in method getBirthDate for separate the numbers with a "," in the birth date returned.*/
	public static final int COMMA_SEPARATOR=1;
	/**Field number used in method getBirthDate for separate the numbers with a "-" in the birth date returned.*/
	public static final int MINUS_SEPARATOR=2;
	/**Field number used in method getBirthDate for separate the numbers with a " " in the birth date returned.*/
	public static final int SPACE_SEPARATOR=3;
	/**Field number used in method getBirthDate for separate the numbers with a "." in the birth date returned.*/
	public static final int DOT_SEPARATOR=4;
	
	/**Construct a CI with the specified String.
	 * @param value The String to be set for the CI number.
	 * @throws WrongLengthException Throws an exception if the value doesn't have 11 digits.
	 * @throws InvalidDateException Throws an exception if the date given by the 6 first digits ins't valid.
	 * @throws InvalidCharactersException Throws an exception if the value doesn't have numbers only.*/
	public CI(String value) throws InvalidCharactersException, InvalidDateException, WrongLengthException {
		this.value=value;
		this.ciArray=value.toCharArray();
		isValid();
	}
	/**Returns the CI number.
	 * @return Returns a String with the CI number.*/
	public String getValue(){
		return value;
	}
	/**Set the value of the CI with the specified String.
	 * @param value The String to be set for the CI number.*/
	public void setValue(String value){
		this.value=value;
		this.ciArray=value.toCharArray();
	}
	/**Returns a {@link Calendar} object with the birth date corresponding to the CI.
	 * @return Returns a {@link Calendar} whit the birth date corresponding to the CI number.*/
	public Calendar getBirthDate(){
		int monthDigit1=ciArray[2]-48;
		int monthDigit2=ciArray[3]-48;
		if(monthDigit1==1&&monthDigit2==0) {
			monthDigit1=0;
			monthDigit2=9;
		}
		else {
			monthDigit2=monthDigit2-1;
		}
		Calendar calendar=Calendar.getInstance();
		char [] actualYear=new String(calendar.get(Calendar.YEAR)+"").toCharArray();
		byte century=Byte.parseByte(actualYear[0]+""+actualYear[1]);
		String year="";
		if(actualYear[2]<ciArray[0]||(actualYear[2]==ciArray[0]&&actualYear[3]<ciArray[1])) 
			year=(century-1)+""+ciArray[0]+""+ciArray[1];
		else 
			year=century+""+ciArray[0]+""+ciArray[1];
		calendar.set(Integer.parseInt(year), Integer.parseInt(monthDigit1+""+monthDigit2), Integer.parseInt(ciArray[4]+""+ciArray[5]));
		return calendar;
	}
	/**Returns the age corresponding to the CI number.
	 * @return Returns an integer value representing the age corresponding to the CI number. It will return '-1' if the CI number is not valid.*/
	public int getAge(){
		int age=0;
		Calendar now=Calendar.getInstance();
		Calendar birth=getBirthDate();
		boolean ageUp=false;
		age=now.get(Calendar.YEAR)-birth.get(Calendar.YEAR);
		if(now.get(Calendar.MONTH)<birth.get(Calendar.MONTH))
			ageUp=true;
		else if(now.get(Calendar.MONTH)==birth.get(Calendar.MONTH)&&now.get(Calendar.DAY_OF_MONTH)<birth.get(Calendar.DAY_OF_MONTH))
			ageUp=true;
		if(ageUp)
			age--;
		return age;
	}
	/**Return the sex corresponding to the CI number
	 * @return Return 'true' if the sex is male and 'false' if the sex is female.*/
	public boolean getSex(){
		if(ciArray[ciArray.length-2]%2==0)
			return true;
		else
			return false;
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
			String year="";
			Calendar now=Calendar.getInstance();
			char [] actualYear=new String(now.get(Calendar.YEAR)+"").toCharArray();
			byte century=Byte.parseByte(actualYear[0]+""+actualYear[1]);
			char[] ciArray = value.toCharArray();
			if(actualYear[2]<ciArray[0]||(actualYear[2]==ciArray[0]&&actualYear[3]<ciArray[1])) 
				year=(century-1)+""+ciArray[0]+""+ciArray[1];
			else 
				year=century+""+ciArray[0]+""+ciArray[1];
			if(!DateUtils.validateDate(ciArray[4]+""+ciArray[5]+"/"+ciArray[2]+""+ciArray[3]+"/"+year)) {
				return false;
			}
			return true;
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
			String year="";
			Calendar now=Calendar.getInstance();
			char [] actualYear=new String(now.get(Calendar.YEAR)+"").toCharArray();
			byte century=Byte.parseByte(actualYear[0]+""+actualYear[1]);
			if(actualYear[2]<ciArray[0]||(actualYear[2]==ciArray[0]&&actualYear[3]<ciArray[1])) 
				year=(century-1)+""+ciArray[0]+""+ciArray[1];
			else 
				year=century+""+ciArray[0]+""+ciArray[1];
			if(!DateUtils.validateDate(ciArray[4]+""+ciArray[5]+"/"+ciArray[2]+""+ciArray[3]+"/"+year)) {
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
	public boolean equals(CI ci){
		if(((CI) ci).getValue().equals(this.value)){
			return true;
		}
		else{
			return false;
		}
	}
	/**Determines if the CI number is equal to the specified String.
	 * @param ci The String object to be compared.
	 * @return Returns 'true' if they are equals and 'false' if they are not. It also returns 'false' if the CI number isn't correct.*/
	public boolean equals(String ci){
		if(ci.equals(this.value)){
			return true;
		}
		else{
			return false;
		}
	}
	/**Determines if the birth date of the CI number is near, a week or less, to the actual date.
	 * @return Returns 'true' if the birth date is 7 days or less near from the actual date and 'false' if it is not. It also returns 'false' if the CI number isn't correct.*/
	public boolean isNearBirthDay(){
		
		return false;
	}
	/**Overrides the toString() method from Object superclass.
	 * @return Returns the CI number(The same returns of the getValue() method)*/
	@Override
	public String toString(){
		return this.value;
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
