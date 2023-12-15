package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1DataBooleanValue extends V1ResponseMessage {

	@JsonProperty("data")
	private V1BooleanValue data;

	public V1BooleanValue getData() {
		return data;
	}

	public void setData(V1BooleanValue data) {
		this.data = data;
	}

}
