package jcc.api.objects;
/**The class extends from CrossObject. It represents the standard characteristics of a single person which are name, last name and address. 
 * The Person class contains the get and set methods for the three values. 
 * Every person should be implemented as an instance of this class.
 * <pre>Example:
 * 		{@code Person jhon=new Person("Jhon","Doe","221B Baker Street");}</pre>
 * This creates an instance of the class Person with name = "Jhon", last name = "Doe" and address = "221B Baker Street".
 * @author Hamlet Arencibia Casanova*/
public class Person{
	/**The fields contains a String with the name.*/
	protected String name;
	/**The fields contains a String with the last name.*/
	protected String lastName;
	/**The fields contains a String with the address.*/
	protected String address;
	/**Construct a Person with the specified name, last name and address.
	 * @param name The String representing the name.
	 * @param lastName The String representing the last name.
	 * @param address The String representing the address.*/
	public Person(String name, String lastName,  String address) {
		this.name = name;
		this.lastName = lastName;
		this.address = address;
	}
	/**Returns the name.
	 * @return Returns a String with the value of the name field.*/
	public String getName() {
		return name;
	}
	/**Sets the value of the name with the specified String.
	 * @param name The String to be set in the name field.*/
	public void setName(String name) {
		this.name = name;
	}
	/**Returns the last name.
	 * @return Returns a String with the value of the last name field.*/
	public String getLastName() {
		return lastName;
	}
	/**Sets the value of the last name with the specified String.
	 * @param lastName The String to be set in the last name field.*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**Returns the address.
	 * @return Returns a String with the value of the address field.*/
	public String getAddress() {
		return address;
	}
	/**Sets the value of the address with the specified String.
	 * @param address The String to be set in the address field.*/
	public void setAddress(String address) {
		this.address = address;
	}	
	/**Returns the name and last name.
	 * @return Returns a String with the values of name and last name, separated by a blank space.*/
	@Override
	public String toString(){
		return name+" "+lastName;
	}
}