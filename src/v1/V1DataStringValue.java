package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1DataStringValue {

	@JsonProperty("data")
	private V1StringValue data;

	public V1StringValue getData() {
		return data;
	}

	public void setData(V1StringValue data) {
		this.data = data;
	}

}
