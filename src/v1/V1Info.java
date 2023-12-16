package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1Info {

	@JsonProperty("battery_type")
	private String batteryType;

	private V1Battery battery;

	private String model;

	@JsonProperty("max_charge_rate")
	private Integer maxChargeRate;

	public Integer getMaxChargeRate() {
		return maxChargeRate;
	}

	public void setMaxChargeRate(Integer maxChargeRate) {
		this.maxChargeRate = maxChargeRate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBatteryType() {
		return batteryType;
	}

	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}

	public V1Battery getBattery() {
		return battery;
	}

	public void setBattery(V1Battery battery) {
		this.battery = battery;
	}

}
