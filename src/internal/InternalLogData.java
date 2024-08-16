package internal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalLogData {

	@JsonProperty("data")
	private List<InternalLogDataEvent> data;

	public List<InternalLogDataEvent> getData() {
		return data;
	}

	public void setData(List<InternalLogDataEvent> data) {
		this.data = data;
	}

}
