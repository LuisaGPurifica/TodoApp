package learn.programming.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TodoUser")
@NamedQuery(name = User.FIND_ALL_USERS, query = "select u from")
public class User extends AbstractEntity {
	
	public static final String FIND_ALL_USERS = "User.findAllUsers"; 
	
	@NotNull(message = "Full name must be set")
//	@Pattern(regexp = "", message="Full name must be in alphabets")
	private String fullName;

	@NotNull(message = "E-mail must be set")
	@Email(message = "E-mail must be in the form user@domain.com")
	private String email;
	
	@NotNull(message = "Password cannot be empty")
	@Size(min = 8, message = "Password must have a minimum size of 8 characters")
//	@Pattern(regexp = "", message = "Password must be a combination of alphabets, numbers and special characters")
	private String password;

	@OneToMany
	private final Collection<Todo> todos = new ArrayList<Todo>();
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
