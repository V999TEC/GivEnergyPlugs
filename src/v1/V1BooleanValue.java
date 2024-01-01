package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1BooleanValue extends V1ResponseMessage {

	@JsonProperty("value")
	private Boolean value;

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}
}
