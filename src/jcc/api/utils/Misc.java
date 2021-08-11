package jcc.api.utils;

/**This class contains only static method. 
 * The methods contained in this class don't have any relation between them, this is a miscellaneous class with all sort of methods, doing all sort of operations.
 * @author Hamlet Arencibia Casanova*/
public class Misc {
	public static class Strings{
		/**The method changes a string first letter to lower case.
		 * @param text The string to be changed.
		 * @return A string with the first letter in lower case.*/
		public static String decapitalize(String text){
			String[] words = text.split(" ");
			for(int i=0;i<words.length;i++) {
				String[] letters=words[i].split("");
				letters[0]=letters[0].toLowerCase();
				words[i]=ArrayUtils.arrayToString(letters, "");
			}
			return ArrayUtils.arrayToString(words, " ");
		}
	}
	public static class TimeUtils{
		public static long convertToMilliseconds(int hour,int minutes,int seconds){
			long x=((hour*60+minutes)*60+seconds)*1000;
			return x;
		}
	}
}
