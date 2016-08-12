package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;



public class User {
	@ObjectId
	@Id
	private String id;
	private String email;
	private String password;

	public User() {
		super();
	}

	
	public User(String id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	@JsonProperty("_id")
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password
				+ "]";
	}

	

}
