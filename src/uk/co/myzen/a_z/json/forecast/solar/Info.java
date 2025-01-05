package uk.co.myzen.a_z.json.forecast.solar;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Info {

	@JsonProperty("latitude")
	private Float latitude;

	@JsonProperty("longitude")
	private Float longitude;

	@JsonProperty("distance")
	private Integer distance;

	@JsonProperty("place")
	private String place;

	@JsonProperty("timezone")
	private String timezone;

	@JsonProperty("time")
	private String time;

	@JsonProperty("time_utc")
	private String timeUTC;

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimeUTC() {
		return timeUTC;
	}

	public void setTimeUTC(String timeUTC) {
		this.timeUTC = timeUTC;
	}

}
