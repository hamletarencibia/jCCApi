package jcc.api.utils;

import java.util.Calendar;

public class CalendarConverter{
	public static String weekDays(Calendar calendar){
		switch(calendar.get(Calendar.DAY_OF_WEEK)){
			case Calendar.SUNDAY:
				return "Sunday";
			case Calendar.MONDAY:
				return "Monday";
			case Calendar.TUESDAY:
				return "Tuesday";
			case Calendar.WEDNESDAY:
				return "Wednesday";
			case Calendar.THURSDAY:
				return "Thursday";
			case Calendar.FRIDAY:
				return "Friday";
			case Calendar.SATURDAY:
				return "Saturday";
			default:
				return "";
		}
	}
	public static String weekDays(Calendar calendar, Language language){
		if(language==Language.SPANISH){
			switch(calendar.get(Calendar.DAY_OF_WEEK)){
				case Calendar.SUNDAY:
					return "Domingo";
				case Calendar.MONDAY:
					return "Lunes";
				case Calendar.TUESDAY:
					return "Martes";
				case Calendar.WEDNESDAY:
					return "Miércoles";
				case Calendar.THURSDAY:
					return "Jueves";
				case Calendar.FRIDAY:
					return "Viernes";
				case Calendar.SATURDAY:
					return "Sábado";
				default:
					return "";
			}
		}
		else {
			switch(calendar.get(Calendar.DAY_OF_WEEK)){
				case Calendar.SUNDAY:
					return "Sunday";
				case Calendar.MONDAY:
					return "Monday";
				case Calendar.TUESDAY:
					return "Tuesday";
				case Calendar.WEDNESDAY:
					return "Wednesday";
				case Calendar.THURSDAY:
					return "Thursday";
				case Calendar.FRIDAY:
					return "Friday";
				case Calendar.SATURDAY:
					return "Saturday";
				default:
					return "";
			}
		}
	}
	public static String month(Calendar calendar){
		switch(calendar.get(Calendar.MONTH)){
			case Calendar.JANUARY:
				return "January";
			case Calendar.FEBRUARY:
				return "February";
			case Calendar.MARCH:
				return "March";
			case Calendar.APRIL:
				return "April";
			case Calendar.MAY:
				return "May";
			case Calendar.JUNE:
				return "June";
			case Calendar.JULY:
				return "July";
			case Calendar.AUGUST:
				return "August";
			case Calendar.SEPTEMBER:
				return "September";
			case Calendar.OCTOBER:
				return "October";
			case Calendar.NOVEMBER:
				return "November";
			case Calendar.DECEMBER:
				return "December";
			default:
				return "";
		}
	}
	public static String month(Calendar calendar, Language language){
		if(language==Language.SPANISH){
			switch(calendar.get(Calendar.MONTH)){
				case Calendar.JANUARY:
					return "Enero";
				case Calendar.FEBRUARY:
					return "Febrero";
				case Calendar.MARCH:
					return "Marzo";
				case Calendar.APRIL:
					return "Abril";
				case Calendar.MAY:
					return "Mayo";
				case Calendar.JUNE:
					return "Junio";
				case Calendar.JULY:
					return "Julio";
				case Calendar.AUGUST:
					return "Agosto";
				case Calendar.SEPTEMBER:
					return "Septiembre";
				case Calendar.OCTOBER:
					return "Octubre";
				case Calendar.NOVEMBER:
					return "Noviembre";
				case Calendar.DECEMBER:
					return "Diciembre";
				default:
					return "";
			}
		}
		else{
			switch(calendar.get(Calendar.MONTH)){
				case Calendar.JANUARY:
					return "January";
				case Calendar.FEBRUARY:
					return "February";
				case Calendar.MARCH:
					return "March";
				case Calendar.APRIL:
					return "April";
				case Calendar.MAY:
					return "May";
				case Calendar.JUNE:
					return "June";
				case Calendar.JULY:
					return "July";
				case Calendar.AUGUST:
					return "August";
				case Calendar.SEPTEMBER:
					return "September";
				case Calendar.OCTOBER:
					return "October";
				case Calendar.NOVEMBER:
					return "November";
				case Calendar.DECEMBER:
					return "December";
				default:
					return "";
			}
		}
	}
}