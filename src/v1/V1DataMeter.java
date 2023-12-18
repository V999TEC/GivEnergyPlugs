package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1DataMeter {

	@JsonProperty("data")
	private V1MeterData data;

	public V1MeterData getData() {
		return data;
	}

	public void setData(V1MeterData data) {
		this.data = data;
	}

}
