package jcc.api.database.sqloader;

import java.awt.Font;

import javax.swing.JLabel;

/**This class contains only static method. 
 * The methods contained in this class don't have any relation between them, this is a miscellaneous class with all sort of methods, doing all sort of operations.
 * @author Hamlet Arencibia Casanova*/
class Misc {
	public static class Labels{
		/**The method adjust the size of a label to its icon.
		 * @param label The JLabel to be adjusted.*/
		public static void adjustLabelToIcon(JLabel label){
			label.setSize(label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
		}
		/**Adjust the font size to the label size.
		 * @param label The JLabel to be adjusted.*/
		public static void adjustFontToSize(JLabel label){
			Font labelFont = label.getFont();
			String labelText = label.getText();
			int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
			int componentWidth = label.getWidth();
			double widthRatio = (double)componentWidth / (double)stringWidth;
			int newFontSize = (int)(labelFont.getSize() * widthRatio);
			int componentHeight = label.getHeight();
			int fontSizeToUse = Math.min(newFontSize, componentHeight);
			label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		}
	}
	public static class Strings{
		/**The method changes a string first letter to upper case.
		 * @param text The string to be changed.
		 * @return A string with the first letter in upper case.*/
		public static String capitalize(String text){
			String[] words = text.split(" ");
			for(int i=0;i<words.length;i++) {
				String[] letters=words[i].split("");
				letters[0]=letters[0].toUpperCase();
				words[i]=ArrayWorks.arrayToString(letters, "");
			}
			return ArrayWorks.arrayToString(words, " ");
		}
		/**The method changes a string first letter to lower case.
		 * @param text The string to be changed.
		 * @return A string with the first letter in lower case.*/
		public static String decapitalize(String text){
			String[] words = text.split(" ");
			for(int i=0;i<words.length;i++) {
				String[] letters=words[i].split("");
				letters[0]=letters[0].toLowerCase();
				words[i]=ArrayWorks.arrayToString(letters, "");
			}
			return ArrayWorks.arrayToString(words, " ");
		}
	}
	public static class TimeUtils{
		public static long convertToMilliseconds(int hour,int minutes,int seconds){
			long x=((hour*60+minutes)*60+seconds)*1000;
			return x;
		}
	}
}
