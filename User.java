
@SuppressWarnings("serial")
public abstract class User implements java.io.Serializable{
	// variables for both Admin and Studen Class
	String username;
	String password;
	String firstname;
	String lastname;

	// constructor
	public User(String username, String password, String firstname, String lastname) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
}
