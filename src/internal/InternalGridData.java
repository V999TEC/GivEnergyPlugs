package internal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalGridData {

	@JsonProperty("data")
	private List<InternalGridDataPoint> data;

	public List<InternalGridDataPoint> getData() {
		return data;
	}

	public void setData(List<InternalGridDataPoint> data) {
		this.data = data;
	}
}
