package v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1DataDescriptor {

	@JsonProperty("data")
	private List<V1Descriptor> data;

	public List<V1Descriptor> getData() {
		return data;
	}

	public void setData(List<V1Descriptor> data) {
		this.data = data;
	}

}
