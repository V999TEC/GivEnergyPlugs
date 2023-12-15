package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1StringValue extends V1ResponseMessage {

	@JsonProperty("value")
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
