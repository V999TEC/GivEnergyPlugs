package uk.co.myzen.a_z.json.sunrise.sunset;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DayResult {

	@JsonProperty("date")
	private String date;

	@JsonProperty("sunrise")
	private String sunrise;

	@JsonProperty("sunset")
	private String sunset;

	@JsonProperty("solar_noon")
	private String solarNoon;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSunrise() {
		return sunrise;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

	public String getSolarNoon() {
		return solarNoon;
	}

	public void setSolarNoon(String solarNoon) {
		this.solarNoon = solarNoon;
	}

}
