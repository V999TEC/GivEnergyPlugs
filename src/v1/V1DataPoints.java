package v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1DataPoints extends V1Base {

	@JsonProperty("data")
	private List<V1DataPoint> dataPoints;

	public List<V1DataPoint> getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(List<V1DataPoint> dataPoints) {
		this.dataPoints = dataPoints;
	}

}
