package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1SmartDevice {

	@JsonProperty("uuid")
	private String uuid;

	@JsonProperty("alias")
	private String alias;

	@JsonProperty("other_data")
	private V1OtherData otherData;

	@JsonProperty("product")
	private String product;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public V1OtherData getOtherData() {
		return otherData;
	}

	public void setOtherData(V1OtherData otherData) {
		this.otherData = otherData;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

}
