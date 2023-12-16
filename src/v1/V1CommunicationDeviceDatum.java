package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1CommunicationDeviceDatum {

	@JsonProperty("data")
	private V1CommunicationDevice data;

	public V1CommunicationDevice getData() {
		return data;
	}

	public void setData(V1CommunicationDevice data) {
		this.data = data;
	}
}
