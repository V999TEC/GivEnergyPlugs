package internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalLogDataEvent {

	@JsonProperty("time")
	private String time;

	@JsonProperty("user")
	private InternalUser user;

	@JsonProperty("register")
	private String register;

	@JsonProperty("message")
	private String message;

	@JsonProperty("raw_previous")
	private Object rawPrevious;

	@JsonProperty("previous")
	private Object previous;

	@JsonProperty("raw_setting")
	private Object rawSetting;

	@JsonProperty("setting")
	private Object setting;

	@JsonProperty("response")
	private Object response;

	@JsonProperty("raw_response")
	private Object rawResponse;

	@JsonProperty("via")
	private String via;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public InternalUser getUser() {
		return user;
	}

	public void setUser(InternalUser user) {
		this.user = user;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getRawPrevious() {
		return rawPrevious;
	}

	public void setRawPrevious(Object rawPrevious) {
		this.rawPrevious = rawPrevious;
	}

	public Object getPrevious() {
		return previous;
	}

	public void setPrevious(Object previous) {
		this.previous = previous;
	}

	public Object getRawSetting() {
		return rawSetting;
	}

	public void setRawSetting(Object rawSetting) {
		this.rawSetting = rawSetting;
	}

	public Object getSetting() {
		return setting;
	}

	public void setSetting(Object setting) {
		this.setting = setting;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Object getRawResponse() {
		return rawResponse;
	}

	public void setRawResponse(Object rawResponse) {
		this.rawResponse = rawResponse;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

}
