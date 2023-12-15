package v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1DataSettings {

	@JsonProperty("data")
	private List<V1Setting> data;

	public List<V1Setting> getData() {
		return data;
	}

	public void setData(List<V1Setting> data) {
		this.data = data;
	}

}
