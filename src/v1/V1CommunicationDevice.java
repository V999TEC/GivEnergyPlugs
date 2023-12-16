package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1CommunicationDevice {

	@JsonProperty("serial_number")
	private String serialNumber;

	private String type;

	@JsonProperty("commission_date")
	private String commissionDate;

	private V1Device inverter;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCommissionDate() {
		return commissionDate;
	}

	public void setCommissionDate(String commissionDate) {
		this.commissionDate = commissionDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public V1Device getInverter() {
		return inverter;
	}

	public void setInverter(V1Device inverter) {
		this.inverter = inverter;
	}

}
