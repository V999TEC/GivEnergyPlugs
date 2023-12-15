package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1DataIntegerValue {

	@JsonProperty("data")
	private V1IntegerValue data;

	public V1IntegerValue getData() {
		return data;
	}

	public void setData(V1IntegerValue data) {
		this.data = data;
	}

}
