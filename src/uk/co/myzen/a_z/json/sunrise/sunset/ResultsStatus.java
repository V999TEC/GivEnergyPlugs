package uk.co.myzen.a_z.json.sunrise.sunset;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultsStatus {

	@JsonProperty("results")
	private List<DayResult> results;

	@JsonProperty("status")
	private String status;

	public List<DayResult> getResults() {
		return results;
	}

	public void setResults(List<DayResult> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
