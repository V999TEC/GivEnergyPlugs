package v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1SmartDevices extends V1Base {

	@JsonProperty("data")
	protected List<V1SmartDevice> smartDeviceData;

	public List<V1SmartDevice> getSmartDeviceData() {

		return smartDeviceData;
	}

	public void setSmartDeviceData(List<V1SmartDevice> smartDeviceData) {

		this.smartDeviceData = smartDeviceData;
	}

}
