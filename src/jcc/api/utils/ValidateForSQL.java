package jcc.api.utils;
/**This class contains only static method. 
 * The methods contained in this class have the only purpose of validate values to insert in a SQL database.
 * @author Hamlet Arencibia Casanova*/
public class ValidateForSQL {
	/**Changes a string for SQL to recognize simple quotes.
	 * @param text The string to be changed.
	 * @return Returns a string with the quotes modified that SQL could recognize it.*/
	public static String validateQuotes(String text){
		String validatedText="";
		String[] textArray=text.split("\'");
		for(int i=0;i<textArray.length;i++){
			if(i==0)
				validatedText=validatedText+textArray[i];
			else
				validatedText=validatedText+"''"+textArray[i];
		}
		return validatedText;
	}
}
