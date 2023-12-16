package v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1CommunicationDeviceData extends V1Base {

	@JsonProperty("data")
	private List<V1CommunicationDevice> communicationDevices;

	public List<V1CommunicationDevice> getCommunicationDevices() {
		return communicationDevices;
	}

	public void setCommunicationDevices(List<V1CommunicationDevice> communicationDevices) {
		this.communicationDevices = communicationDevices;
	}

}
