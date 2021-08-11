package jcc.api.objects;
/**The class extends from Person. It represents the standard characteristics of a single cuban person which are name, last name, address and ci. 
 * The Cubano class contains the get and set methods for the four values. 
 * Every cuban person should be implemented as an instance of this class.
 * <pre>Example:
 * 		{@code Cubano juan=new Cubano("Juan","Pérez","Galiano #23 e/ Milagros y Prado","93071007341");}</pre>
 * This creates an instance of the class Cubano with name = "Juan", last name = "Pérez", address = "Galiano #23 e/ Milagros y Prado" and ci = "93071007341".
 * @author Hamlet Arencibia Casanova*/
public class Cubano extends Person{
	/**The fields contains a CI with the identity number.*/
	protected CI ci;
	/**Construct a Person with the specified name, last name, address and ci.
	 * @param name The String representing the name.
	 * @param lastName The String representing the last name.
	 * @param address The String representing the address.
	 * @param ci The CI representing the identity number.*/
	public Cubano(String name, String lastName, String address, CI ci) {
		super(name, lastName, address);
		this.ci=ci;
	}
	/**Returns the ci.
	 * @return Returns a CI object with the value of the ci field.*/
	public CI getCi() {
		return ci;
	}
	/**Sets the value of the identity number(ci) with the specified CI.
	 * @param ci The CI to be set in the ci field.*/
	public void setCi(CI ci) {
		this.ci = ci;
	}
}
