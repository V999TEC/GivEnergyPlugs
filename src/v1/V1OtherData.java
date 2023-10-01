package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1OtherData {

	@JsonProperty("asset_id")
	private String assetId;

	@JsonProperty("local_key")
	private String localKey;

	@JsonProperty("graph_color")
	private String graphColor;

	@JsonProperty("hardware_id")
	private String hardwareId;

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getLocalKey() {
		return localKey;
	}

	public void setLocalKey(String localKey) {
		this.localKey = localKey;
	}

	public String getGraphColor() {
		return graphColor;
	}

	public void setGraphColor(String graphColor) {
		this.graphColor = graphColor;
	}

	public String getHardwareId() {
		return hardwareId;
	}

	public void setHardwareId(String hardwareId) {
		this.hardwareId = hardwareId;
	}

}
