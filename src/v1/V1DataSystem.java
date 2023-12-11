package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1DataSystem {

	@JsonProperty("data")
	private V1SystemData data;

	public V1SystemData getData() {
		return data;
	}

	public void setData(V1SystemData data) {
		this.data = data;
	}

}
