package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1Warranty {

	private String type;

	@JsonProperty("expiry_date")
	private String expiryDate;

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
