package jcc.api.database.sqloader;
/**This class contains only static method. 
 * The methods contained in this class have the only purpose of work with arrays.
 * <pre>Example:
 * 			{@code char[] x={'J','u','a','n'};
 			 String name=arrayToString(x,"");}</pre>
 <p>After this, the value of <b>name</b> will be "Juan".</p>
 <p>Lets see another example:</p>
 <pre>Example:
 * 			{@code int[] x={1,2,3};
 			 long amount=arrayAmount(x)}</pre>
 <p>After this, the value of <b>amount</b> will be 6.</p>
 <p>Now we try with the reverse() method:</p>
 <pre>Example:
 * 			{@code int[] x={1,2,3};
 			 int[] y=reverse(x)}</pre>
 <p>After this, the values of <b>y</b> will be {3,2,1}.</p>
 * @author Hamlet Arencibia Casanova*/
class ArrayWorks {
	/**
	 * Adds a value to the next free position on the array
	 * @param value The value to be added
	 * @param stringArray The array to add the value
	 */
	public static void add(String value,String[] stringArray) {
		for(int i=0;i<stringArray.length;i++) {
			if(stringArray[i]==null) {
				stringArray[i]=value;
				break;
			}
		}
	}
	/**Converts an array to a string, separating each value of the array with the specified separator.
	 * @param charArray The array to convert.
	 * @param separator The separator between each array value.
	 * @return Returns a string with all the values of the array and separated by the specified separator.*/
	public static String arrayToString(char[] charArray,String separator){
		String x="";
		for(int i=0;i<charArray.length;i++){
			if(i<charArray.length-1)
				x=x+charArray[i]+separator;
			else
				x=x+charArray[i];
		}
		return x;
	}
	/**Converts an array to a string, separating each value of the array with the specified separator.
	 * @param stringArray The array to convert.
	 * @param separator The separator between each array value.
	 * @return Returns a string with all the values of the array and separated by the specified separator.*/
	public static String arrayToString(String[] stringArray,String separator){
		String x="";
		for(int i=0;i<stringArray.length;i++){
			if(i<stringArray.length-1)
				x=x+stringArray[i]+separator;
			else
				x=x+stringArray[i];
		}
		return x;
	}
	/**Converts an array to a string, separating each value of the array with the specified separator.
	 * @param intArray The array to convert.
	 * @param separator The separator between each array value.
	 * @return Returns a string with all the values of the array and separated by the specified separator.*/
	public static String arrayToString(int[] intArray,String separator){
		String x="";
		for(int i=0;i<intArray.length;i++){
			if(i<intArray.length-1)
				x=x+intArray[i]+separator;
			else
				x=x+intArray[i];
		}
		return x;
	}
	/**Calculate the total amount of all the values in an array of numbers.
	 * @param byteArray The array of numbers.
	 * @return Returns a long value representing the sum of all values of the array.*/
	public static long arrayAmount(byte[] byteArray){
		long x=0;
		for(int i=0;i<byteArray.length;i++){
			x=x+byteArray[i];
		}
		return x;
	}
	/**Calculate the total amount of all the values in a two-dimension array of numbers.
	 * @param byteArray The two-dimension array of numbers.
	 * @return Returns a long value representing the sum of all values of the array.*/
	public static long arrayAmount(byte[][] byteArray){
		long x=0;
		for(int i=0;i<byteArray.length;i++){
			for(int c=0;c<byteArray[0].length;c++){
				x=x+byteArray[i][c];
			}
		}
		return x;
	}
	/**Calculate the total amount of all the values in an array of numbers.
	 * @param shortArray The array of numbers.
	 * @return Returns a long value representing the sum of all values of the array.*/
	public static long arrayAmount(short[] shortArray){
		long x=0;
		for(int i=0;i<shortArray.length;i++){
			x=x+shortArray[i];
		}
		return x;
	}
	/**Calculate the total amount of all the values in a two-dimension array of numbers.
	 * @param shortArray The two-dimension array of numbers.
	 * @return Returns a long value representing the sum of all values of the array.*/
	public static long arrayAmount(short[][] shortArray){
		long x=0;
		for(int i=0;i<shortArray.length;i++){
			for(int c=0;c<shortArray[0].length;c++){
				x=x+shortArray[i][c];
			}
		}
		return x;
	}
	/**Calculate the total amount of all the values in an array of numbers.
	 * @param intArray The array of numbers.
	 * @return Returns a long value representing the sum of all values of the array.*/
	public static long arrayAmount(int[] intArray){
		long x=0;
		for(int i=0;i<intArray.length;i++){
			x=x+intArray[i];
		}
		return x;
	}
	/**Calculate the total amount of all the values in a two-dimension array of numbers.
	 * @param intArray The two-dimension array of numbers.
	 * @return Returns a long value representing the sum of all values of the array.*/
	public static long arrayAmount(int[][] intArray){
		long x=0;
		for(int i=0;i<intArray.length;i++){
			for(int c=0;c<intArray[0].length;c++){
				x=x+intArray[i][c];
			}
		}
		return x;
	}
	/**Calculate the total amount of all the values in an array of numbers.
	 * @param longArray The array of numbers.
	 * @return Returns a long value representing the sum of all values of the array.*/
	public static long arrayAmount(long[] longArray){
		long x=0;
		for(int i=0;i<longArray.length;i++){
			x=x+longArray[i];
		}
		return x;
	}
	/**Calculate the total amount of all the values in a two-dimension array of numbers.
	 * @param longArray The two-dimension array of numbers.
	 * @return Returns a long value representing the sum of all values of the array.*/
	public static long arrayAmount(long[][] longArray){
		long x=0;
		for(int i=0;i<longArray.length;i++){
			for(int c=0;c<longArray[0].length;c++){
				x=x+longArray[i][c];
			}
		}
		return x;
	}
	/**Calculate the total amount of all the values in an array of numbers.
	 * @param floatArray The array of numbers.
	 * @return Returns a double value representing the sum of all values of the array.*/
	public static double arrayAmount(float[] floatArray){
		double x=0;
		for(int i=0;i<floatArray.length;i++){
			x=x+floatArray[i];
		}
		return x;
	}
	/**Calculate the total amount of all the values in a two-dimension array of numbers.
	 * @param floatArray The two-dimension array of numbers.
	 * @return Returns a long value representing the sum of all values of the array.*/
	public static double arrayAmount(float[][] floatArray){
		double x=0;
		for(int i=0;i<floatArray.length;i++){
			for(int c=0;c<floatArray[0].length;c++){
				x=x+floatArray[i][c];
			}
		}
		return x;
	}
	/**Calculate the total amount of all the values in an array of numbers.
	 * @param doubleArray The array of numbers.
	 * @return Returns a double value representing the sum of all values of the array.*/
	public static double arrayAmount(double[] doubleArray){
		double x=0;
		for(int i=0;i<doubleArray.length;i++){
			x=x+doubleArray[i];
		}
		return x;
	}
	/**Calculate the total amount of all the values in a two-dimension array of numbers.
	 * @param doubleArray The two-dimension array of numbers.
	 * @return Returns a long value representing the sum of all values of the array.*/
	public static double arrayAmount(double[][] doubleArray){
		double x=0;
		for(int i=0;i<doubleArray.length;i++){
			for(int c=0;c<doubleArray[0].length;c++){
				x=x+doubleArray[i][c];
			}
		}
		return x;
	}
	/**Find the position of a value in an array.
	 * @param <T> The class of the value and array.
	 * @param value The value to find in the array.
	 * @param array The array to search.
	 * @return Return an integer with the position of the value in the array. It will return -1 if the value dosn't exist in the array.*/
	public static <T> int getPosition(T value, T[] array){
		for(int i=0;i<array.length;i++){
			if(array[i]==value){
				return i;
			}
		}
		return -1;
	}
	/**Find the position of a value in an array.
	 * @param value The value to find in the array.
	 * @param array The array to search.
	 * @return Return an integer with the position of the value in the array. It will return -1 if the value dosn't exist in the array.*/
	public static int getPosition(char value, char[] array){
		for(int i=0;i<array.length;i++){
			if(array[i]==value){
				return i;
			}
		}
		return -1;
	}
	/**Find the position of a value in an array.
	 * @param value The value to find in the array.
	 * @param array The array to search.
	 * @return Return an integer with the position of the value in the array. It will return -1 if the value dosn't exist in the array.*/
	public static int getPosition(String value, String[] array){
		for(int i=0;i<array.length;i++){
			if(value.equals(array[i])){
				return i;
			}
		}
		return -1;
	}
	/**Find the position of a value in an array.
	 * @param value The value to find in the array.
	 * @param array The array to search.
	 * @return Return an integer with the position of the value in the array. It will return -1 if the value dosn't exist in the array.*/
	public static int getPosition(byte value, byte[] array){
		for(int i=0;i<array.length;i++){
			if(array[i]==value){
				return i;
			}
		}
		return -1;
	}
	/**Find the position of a value in an array.
	 * @param value The value to find in the array.
	 * @param array The array to search.
	 * @return Return an integer with the position of the value in the array. It will return -1 if the value dosn't exist in the array.*/
	public static int getPosition(short value, short[] array){
		for(int i=0;i<array.length;i++){
			if(array[i]==value){
				return i;
			}
		}
		return -1;
	}
	/**Find the position of a value in an array.
	 * @param value The value to find in the array.
	 * @param array The array to search.
	 * @return Return an integer with the position of the value in the array. It will return -1 if the value dosn't exist in the array.*/
	public static int getPosition(int value, int[] array){
		for(int i=0;i<array.length;i++){
			if(array[i]==value){
				return i;
			}
		}
		return -1;
	}
	/**Find the position of a value in an array.
	 * @param value The value to find in the array.
	 * @param array The array to search.
	 * @return Return an integer with the position of the value in the array. It will return -1 if the value dosn't exist in the array.*/
	public static int getPosition(long value, long[] array){
		for(int i=0;i<array.length;i++){
			if(array[i]==value){
				return i;
			}
		}
		return -1;
	}
	/**Find the position of a value in an array.
	 * @param value The value to find in the array.
	 * @param array The array to search.
	 * @return Return an integer with the position of the value in the array. It will return -1 if the value dosn't exist in the array.*/
	public static int getPosition(float value, float[] array){
		for(int i=0;i<array.length;i++){
			if(array[i]==value){
				return i;
			}
		}
		return -1;
	}
	/**Find the position of a value in an array.
	 * @param value The value to find in the array.
	 * @param array The array to search.
	 * @return Return an integer with the position of the value in the array. It will return -1 if the value dosn't exist in the array.*/
	public static int getPosition(double value, double[] array){
		for(int i=0;i<array.length;i++){
			if(array[i]==value){
				return i;
			}
		}
		return -1;
	}
	/**Returns a reversed copy of an array.
	 * @param <T> The class of the array.
	 * @param array The array to be reversed.
	 * @return Returns an array of the same type and values but with the value's positions in reverse whit regard of the original.*/
	public static <T> T[] returnReverse(T[] array){
		T[] reversedArray=array.clone();
		for(int i=0;i<reversedArray.length/2;i++){
			T temp=reversedArray[i];
			reversedArray[i]=reversedArray[reversedArray.length-1-i];
			reversedArray[reversedArray.length-1-i]=temp;
		}
		return reversedArray;
	}
	/**Turns an array in reverse.
	 * @param <T> The class of the array.
	 * @param array The array to be reversed.*/
	public static <T> void reverse(T[] array){
		for(int i=0;i<array.length/2;i++){
			T temp=array[i];
			array[i]=array[array.length-1-i];
			array[array.length-1-i]=temp;
		}
	}
	/**Returns a reversed copy of an array.
	 * @param array The array to be reversed.
	 * @return Returns an array of the same type and values but with the value's positions in reverse whit regard of the original.*/
	public static char[] returnReverse(char[] array){
		char[] reversedArray=new char[array.length];
		for(int i=0;i<reversedArray.length/2;i++){
			char temp=reversedArray[i];
			reversedArray[i]=reversedArray[reversedArray.length-1-i];
			reversedArray[reversedArray.length-1-i]=temp;
		}
		return reversedArray;
	}
	/**Turns an array in reverse.
	 * @param array The array to be reversed.*/
	public static void reverse(char[] array){
		for(int i=0;i<array.length/2;i++){
			char temp=array[i];
			array[i]=array[array.length-1-i];
			array[array.length-1-i]=temp;
		}
	}
	/**Returns a reversed copy of an array.
	 * @param array The array to be reversed.
	 * @return Returns an array of the same type and values but with the value's positions in reverse whit regard of the original.*/
	public static byte[] returnReverse(byte[] array){
		byte[] reversedArray=new byte[array.length];
		for(int i=0;i<reversedArray.length/2;i++){
			byte temp=reversedArray[i];
			reversedArray[i]=reversedArray[reversedArray.length-1-i];
			reversedArray[reversedArray.length-1-i]=temp;
		}
		return reversedArray;
	}
	/**Turns an array in reverse.
	 * @param array The array to be reversed.*/
	public static void reverse(byte[] array){
		for(int i=0;i<array.length/2;i++){
			byte temp=array[i];
			array[i]=array[array.length-1-i];
			array[array.length-1-i]=temp;
		}
	}
	/**Returns a reversed copy of an array.
	 * @param array The array to be reversed.
	 * @return Returns an array of the same type and values but with the value's positions in reverse whit regard of the original.*/
	public static short[] returnReverse(short[] array){
		short[] reversedArray=new short[array.length];
		for(int i=0;i<reversedArray.length/2;i++){
			short temp=reversedArray[i];
			reversedArray[i]=reversedArray[reversedArray.length-1-i];
			reversedArray[reversedArray.length-1-i]=temp;
		}
		return reversedArray;
	}
	/**Turns an array in reverse.
	 * @param array The array to be reversed.*/
	public static void reverse(short[] array){
		for(int i=0;i<array.length/2;i++){
			short temp=array[i];
			array[i]=array[array.length-1-i];
			array[array.length-1-i]=temp;
		}
	}
	/**Returns a reversed copy of an array.
	 * @param array The array to be reversed.
	 * @return Returns an array of the same type and values but with the value's positions in reverse whit regard of the original.*/
	public static int[] returnReverse(int[] array){
		int[] reversedArray=new int[array.length];
		for(int i=0;i<reversedArray.length/2;i++){
			int temp=reversedArray[i];
			reversedArray[i]=reversedArray[reversedArray.length-1-i];
			reversedArray[reversedArray.length-1-i]=temp;
		}
		return reversedArray;
	}
	/**Turns an array in reverse.
	 * @param array The array to be reversed.*/
	public static void reverse(int[] array){
		for(int i=0;i<array.length/2;i++){
			int temp=array[i];
			array[i]=array[array.length-1-i];
			array[array.length-1-i]=temp;
		}
	}
	/**Returns a reversed copy of an array.
	 * @param array The array to be reversed.
	 * @return Returns an array of the same type and values but with the value's positions in reverse whit regard of the original.*/
	public static long[] returnReverse(long[] array){
		long[] reversedArray=new long[array.length];
		for(int i=0;i<reversedArray.length/2;i++){
			long temp=reversedArray[i];
			reversedArray[i]=reversedArray[reversedArray.length-1-i];
			reversedArray[reversedArray.length-1-i]=temp;
		}
		return reversedArray;
	}
	/**Turns an array in reverse.
	 * @param array The array to be reversed.*/
	public static void reverse(long[] array){
		for(int i=0;i<array.length/2;i++){
			long temp=array[i];
			array[i]=array[array.length-1-i];
			array[array.length-1-i]=temp;
		}
	}
	/**Returns a reversed copy of an array.
	 * @param array The array to be reversed.
	 * @return Returns an array of the same type and values but with the value's positions in reverse whit regard of the original.*/
	public static float[] retrunReverse(float[] array){
		float[] reversedArray=new float[array.length];
		for(int i=0;i<reversedArray.length/2;i++){
			float temp=reversedArray[i];
			reversedArray[i]=reversedArray[reversedArray.length-1-i];
			reversedArray[reversedArray.length-1-i]=temp;
		}
		return reversedArray;
	}
	/**Turns an array in reverse.
	 * @param array The array to be reversed.*/
	public static void reverse(float[] array){
		for(int i=0;i<array.length/2;i++){
			float temp=array[i];
			array[i]=array[array.length-1-i];
			array[array.length-1-i]=temp;
		}
	}
	/**Returns a reversed copy of an array.
	 * @param array The array to be reversed.
	 * @return Returns an array of the same type and values but with the value's positions in reverse whit regard of the original.*/
	public static double[] returnReverse(double[] array){
		double[] reversedArray=new double[array.length];
		for(int i=0;i<reversedArray.length/2;i++){
			double temp=reversedArray[i];
			reversedArray[i]=reversedArray[reversedArray.length-1-i];
			reversedArray[reversedArray.length-1-i]=temp;
		}
		return reversedArray;
	}
	/**Turns an array in reverse.
	 * @param array The array to be reversed.*/
	public static void reverse(double[] array){
		for(int i=0;i<array.length/2;i++){
			double temp=array[i];
			array[i]=array[array.length-1-i];
			array[array.length-1-i]=temp;
		}
	}
	/**Gets the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The higher value of the array.*/
	public static char higherValue(char[] array){
		char x=array[0];
		for(int i=1;i<array.length;i++){
			if(x<array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The higher value of the array.*/
	public static byte higherValue(byte[] array){
		byte x=array[0];
		for(int i=1;i<array.length;i++){
			if(x<array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The higher value of the array.*/
	public static short higherValue(short[] array){
		short x=array[0];
		for(int i=1;i<array.length;i++){
			if(x<array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The higher value of the array.*/
	public static int higherValue(int[] array){
		int x=array[0];
		for(int i=1;i<array.length;i++){
			if(x<array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The higher value of the array.*/
	public static long higherValue(long[] array){
		long x=array[0];
		for(int i=1;i<array.length;i++){
			if(x<array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The higher value of the array.*/
	public static float higherValue(float[] array){
		float x=array[0];
		for(int i=1;i<array.length;i++){
			if(x<array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The higher value of the array.*/
	public static double higherValue(double[] array){
		double x=array[0];
		for(int i=1;i<array.length;i++){
			if(x<array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the position of the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of the higher value of the array.*/
	public static int higherValuePosition(char[] array){
		char x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x<array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the position of the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of higher value of the array.*/
	public static int higherValuePosition(byte[] array){
		byte x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x<array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the position of the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of higher value of the array.*/
	public static int higherValuePosition(short[] array){
		short x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x<array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the position of the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of higher value of the array.*/
	public static int higherValuePosition(int[] array){
		int x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x<array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the position of the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of higher value of the array.*/
	public static int higherValuePosition(long[] array){
		long x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x<array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the position of the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of higher value of the array.*/
	public static int higherValuePosition(float[] array){
		float x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x<array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the position of the higher value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of higher value of the array.*/
	public static int higherValuePosition(double[] array){
		double x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x<array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The smaller value of the array.*/
	public static char smallerValue(char[] array){
		char x=array[0];
		for(int i=1;i<array.length;i++){
			if(x>array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The smaller value of the array.*/
	public static byte smallerValue(byte[] array){
		byte x=array[0];
		for(int i=1;i<array.length;i++){
			if(x>array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The smaller value of the array.*/
	public static short smallerValue(short[] array){
		short x=array[0];
		for(int i=1;i<array.length;i++){
			if(x>array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The smaller value of the array.*/
	public static int smallerValue(int[] array){
		int x=array[0];
		for(int i=1;i<array.length;i++){
			if(x>array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The smaller value of the array.*/
	public static long smallerValue(long[] array){
		long x=array[0];
		for(int i=1;i<array.length;i++){
			if(x>array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The smaller value of the array.*/
	public static float smallerValue(float[] array){
		float x=array[0];
		for(int i=1;i<array.length;i++){
			if(x>array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The smaller value of the array.*/
	public static double smallerValue(double[] array){
		double x=array[0];
		for(int i=1;i<array.length;i++){
			if(x>array[i])
				x=array[i];
		}
		return x;
	}
	/**Gets the position of the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of the smaller value of the array.*/
	public static int smallerValuePosition(char[] array){
		char x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x>array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the position of the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of smaller value of the array.*/
	public static int smallerValuePosition(byte[] array){
		byte x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x>array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the position of the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of smaller value of the array.*/
	public static int smallerValuePosition(short[] array){
		short x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x>array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the position of the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of smaller value of the array.*/
	public static int smallerValuePosition(int[] array){
		int x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x>array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the position of the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of smaller value of the array.*/
	public static int smallerValuePosition(long[] array){
		long x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x>array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the position of the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of smaller value of the array.*/
	public static int smallerValuePosition(float[] array){
		float x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x>array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**Gets the position of the smaller value of an array of numbers.
	 * @param array The array to search.
	 * @return The position of smaller value of the array.*/
	public static int smallerValuePosition(double[] array){
		double x=array[0];
		int position=0;
		for(int i=1;i<array.length;i++){
			if(x>array[i]){
				x=array[i];
				position=i;
			}
		}
		return position;
	}
	/**
	 * Merge two arrays
	 * @param array1 the first array object
	 * @param array2 the second array object
	 * @return the two arrays merged into a new one
	 */
	public static Object[] merge(Object[] array1, Object[] array2){
		Object[] array3=new Object[array1.length+array2.length];
		for(int i=0;i<array1.length;i++) {
			array3[i]=array1[i];
		}
		for(int i=array1.length;i<array3.length;i++) {
			array3[i]=array2[i-array1.length];
		}
		return array3;
	}
}