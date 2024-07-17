package internal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalData {

	@JsonProperty("data")
	private List<InternalDataPoint> data;

	public List<InternalDataPoint> getData() {
		return data;
	}

	public void setData(List<InternalDataPoint> data) {
		this.data = data;
	}

}
