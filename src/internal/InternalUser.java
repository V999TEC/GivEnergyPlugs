package internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalUser {

	@JsonProperty("name")
	private String name;

	@JsonProperty("username")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
