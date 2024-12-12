package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1Power {

	@JsonProperty("solar")
	private V1SolarData solarData;

	@JsonProperty("grid")
	private V1GridData gridData;

	@JsonProperty("battery")
	private V1BatteryData batteryData;

	@JsonProperty("consumption")
	private Object consumption;

	@JsonProperty("inverter")
	private V1Inverter inverter;

	public V1SolarData getSolarData() {
		return solarData;
	}

	public void setSolarData(V1SolarData solarData) {
		this.solarData = solarData;
	}

	public V1GridData getGridData() {
		return gridData;
	}

	public void setGridData(V1GridData gridData) {
		this.gridData = gridData;
	}

	public V1BatteryData getBatteryData() {
		return batteryData;
	}

	public void setBatteryData(V1BatteryData batteryData) {
		this.batteryData = batteryData;
	}

	public V1Inverter getInverter() {
		return inverter;
	}

	public void setInverter(V1Inverter inverter) {
		this.inverter = inverter;
	}

}
