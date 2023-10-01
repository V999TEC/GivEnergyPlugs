package v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1SmartDeviceData extends V1Base {

	@JsonProperty("data")
	private List<V1TimeAndPower> timeAndPower;

	public List<V1TimeAndPower> getTimeAndPower() {

		return timeAndPower;
	}

	public void setTimeAndPower(List<V1TimeAndPower> timeAndPower) {

		this.timeAndPower = timeAndPower;
	}
}
