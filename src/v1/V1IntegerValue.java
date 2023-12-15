package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1IntegerValue extends V1ResponseMessage {

	@JsonProperty("value")
	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
