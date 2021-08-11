package jcc.api.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**This class contains only static method. 
 * The methods contained in this class works with dates in string and calendar objects.
 * @author Hamlet Arencibia Casanova*/
public class DateUtils {
	/**Returns a calendar from a specified string.
	 * @param date The string containing a date. Example: "10/07/1993"
	 * @return Returns a GregorianCalendar with the specified date. It will return 'null' if the date specified wasn't valid.*/
	public static Calendar stringToCalendar(String date){
		Calendar calendar=Calendar.getInstance();
		String[] splitDate=date.split(" ");
		if(splitDate.length==2){
			String stringDate=splitDate[0];
			String stringTime=splitDate[1];
			String[] splittedDate=stringDate.split("-");
			String[] splittedTime=stringTime.split(":");
			if(splittedTime.length==3&&splittedDate.length==3){
				calendar.set(Integer.parseInt(splittedDate[0]), Integer.parseInt(splittedDate[1])-1, Integer.parseInt(splittedDate[2]));
				calendar.set(Calendar.HOUR, Integer.parseInt(splittedTime[0]));
				calendar.set(Calendar.MINUTE, Integer.parseInt(splittedTime[1]));
				calendar.set(Calendar.SECOND, Integer.parseInt(splittedTime[2]));
			}
		}
		else if(splitDate.length==1){
			String stringDate=splitDate[0];
			String[] splittedDate=stringDate.split("-");
			if(splittedDate.length==3){
				calendar.set(Integer.parseInt(splittedDate[0]), Integer.parseInt(splittedDate[1])-1, Integer.parseInt(splittedDate[2]));
			}
			else{
				String[] splittedTime=stringDate.split(":");
				if(splittedTime.length==3){
					calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splittedTime[0]));
					calendar.set(Calendar.MINUTE, Integer.parseInt(splittedTime[1]));
					calendar.set(Calendar.SECOND, Integer.parseInt(splittedTime[2]));
				}
			}
			return calendar;
		}
		else{
			
		}
		return null;
	}
	/**Returns a string with the date from a specified calendar.
	 * @param calendar The {@link Calendar} containing a date.
	 * @param separator The string to separate the values.
	 * @return Returns a string containing the day, month and year of the specified calendar, separated by the specified string.*/
	public static String dateToString(Calendar calendar, String separator){
		String date;
		date=calendar.get(Calendar.YEAR)+separator+(calendar.get(Calendar.MONTH)+1)+separator+calendar.get(Calendar.DAY_OF_MONTH);
		return date;
	}
	/**Returns a string with the date from a specified calendar.
	 * @param calendar The {@link Calendar} containing a date.
	 * @return Returns a string containing the day, month and year of the specified calendar, separated by a '-'.*/
	public static String dateToString(Calendar calendar){
		String date;
		date=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
		return date;
	}
	/**Returns a string with the time from a specified calendar.
	 * @param calendar The {@link Calendar} containing a time.
	 * @param separator The string to separate the values.
	 * @return Returns a string containing the hour, minutes and seconds of the specified calendar, separated by the specified string.*/
	public static String timeToString(Calendar calendar, String separator){
		String hours = calendar.get(Calendar.HOUR_OF_DAY)+"";
		String minutes = calendar.get(Calendar.MINUTE)+"";
		String seconds = calendar.get(Calendar.SECOND)+"";
		if(calendar.get(Calendar.HOUR_OF_DAY)<10) {
			hours = "0"+hours;
		}
		if(calendar.get(Calendar.MINUTE)<10) {
			minutes = "0"+minutes;
		}
		if(calendar.get(Calendar.SECOND)<10) {
			seconds = "0"+seconds;
		}
		return hours+separator+minutes+separator+seconds;
	}
	/**Returns a string with the time from a specified calendar.
	 * @param calendar The {@link Calendar} containing a time.
	 * @return Returns a string containing the hour, minutes and seconds of the specified calendar, separated by a ':'.*/
	public static String timeToString(Calendar calendar){
		return timeToString(calendar, ":");
	}
	/**Returns a string with the time from a specified calendar.
	 * @param calendar The {@link Calendar} containing a time.
	 * @return Returns a string containing the date and time of the specified calendar.*/
	public static String dateTimeToString(Calendar calendar){
		String datetime;
		datetime=dateToString(calendar)+" "+timeToString(calendar);
		return datetime;
	}
	/**Determines if the date is correct.
	 * @param date A string containing the date.
	 * @return Returns 'true' if the date is correct and 'false' if it is not.*/
	public static boolean validateDate(String date){
		String[] separator={"/","-",":"," ","_"};
		for(String x:separator){
			if(date.contains(x)){
				String[] splittedDate=date.split(x);
				if(splittedDate.length==3){
					if(Integer.parseInt(splittedDate[1])<13&&Integer.parseInt(splittedDate[1])>0){
						switch(Integer.parseInt(splittedDate[1])){
						case 1:
						case 3:
						case 5:
						case 7:
						case 8:
						case 10:
						case 12:
							if(Integer.parseInt(splittedDate[0])>0&&Integer.parseInt(splittedDate[0])<32)
								return true;
							else
								return false;
						case 2:
							GregorianCalendar calendar=new GregorianCalendar();
							boolean isLeapYear=calendar.isLeapYear(Integer.parseInt(splittedDate[2]));
							if(Integer.parseInt(splittedDate[0])>0&&((Integer.parseInt(splittedDate[0])<29&&!isLeapYear)||(Integer.parseInt(splittedDate[0])<30&&isLeapYear)))
								return true;
							else
								return false;
						case 4:
						case 6:
						case 9:
						case 11:
							if(Integer.parseInt(splittedDate[0])>0&&Integer.parseInt(splittedDate[0])<31)
								return true;
							else
								return false;
						}
					}
					else{
						return false;
					}
				}
				else
					return false;
			}
		}
		return false;
	}
}