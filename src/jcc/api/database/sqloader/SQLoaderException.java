package jcc.api.database.sqloader;

public class SQLoaderException extends Exception{
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String[] options; 
	public static final int INVALID_CLASS_STRUCTURE = 1;
	public static final int NO_SUCH_METHOD = 2;
	public static final int CLASS_NOT_FOUND = 3;
	public static final int ILLEGAL_ACCESS = 4;
	public static final int NO_SUCH_CONSTRUCTOR = 5;
	public static final int ILLEGAL_ACCESS_CONSTRUCTOR = 6;
	public SQLoaderException(int code) {
		this.errorCode = code;
	}
	
	public SQLoaderException(int code, String... options) {
		this.errorCode = code;
		this.options = options;
	}
	
	@Override
	public String getMessage() {
		switch(errorCode) {
			case INVALID_CLASS_STRUCTURE:
				return "The class provided must have a jcc.api.database.sqloader.@Entity and jcc.api.database.sqloader.@Table annotations.";
			case NO_SUCH_METHOD:
				return String.format("Unable to find method %s in class %s", options[1], options[0]);
			case CLASS_NOT_FOUND:
				return String.format("Unable to find class %s", options[0]);
			case ILLEGAL_ACCESS:
				return String.format("Cannot access \"%s\" method %s in class %s", options[2], options[1], options[0]);
			case NO_SUCH_CONSTRUCTOR:
				return String.format("Unable to find constructor %s in class %s", options[1], options[0]);
			case ILLEGAL_ACCESS_CONSTRUCTOR:
				return String.format("Cannot access \"%s\" constructor %s in class %s", options[2], options[1],options[0]);
		}
		return "There was an error.";
	}
	
	public int getErrorCode() {
		return errorCode;
	}
}
