package AppUser;

import java.util.ArrayList;

public class User {
	
	private String name;
	private String password;
	private String userId;
	
	public User(String name, String password, String userId) {
		this.setName(name);
		this.setPassword(password);
		this.setUserId(userId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
