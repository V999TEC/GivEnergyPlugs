package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1DataPoint {

	@JsonProperty("time")
	private String time;

	@JsonProperty("status")
	private String status;

	@JsonProperty("power")
	private V1Power power;

	@JsonProperty("today")
	private Object today;

	@JsonProperty("total")
	private Object total;

	@JsonProperty("is_metered")
	private Boolean isMetered;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsMetered() {
		return isMetered;
	}

	public void setIsMetered(Boolean isMetered) {
		this.isMetered = isMetered;
	}

	public V1Power getPower() {
		return power;
	}

	public void setPower(V1Power power) {
		this.power = power;
	}

}
