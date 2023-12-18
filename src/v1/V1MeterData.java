package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1MeterData {

	private String time;

	private V1Meters today;

	private V1Meters total;

	@JsonProperty("is_metered")
	private Boolean isMetered;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public V1Meters getToday() {
		return today;
	}

	public void setToday(V1Meters today) {
		this.today = today;
	}

	public V1Meters getTotal() {
		return total;
	}

	public void setTotal(V1Meters total) {
		this.total = total;
	}

	public Boolean getIsMetered() {
		return isMetered;
	}

	public void setIsMetered(Boolean isMetered) {
		this.isMetered = isMetered;
	}

}
