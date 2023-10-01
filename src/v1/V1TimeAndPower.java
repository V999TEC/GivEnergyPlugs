package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1TimeAndPower {

	@JsonProperty("time")
	private String time;

	@JsonProperty("power")
	private Float power;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Float getPower() {
		return power;
	}

	public void setPower(Float power) {
		this.power = power;
	}

}
