package v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1Device {

	private String serial;

	private String status;

	@JsonProperty("last_online")
	private String lastOnline;

	@JsonProperty("last_updated")
	private String lastUpdated;

	@JsonProperty("commission_date")
	private String commissionDate;

	private V1Info info;

	private V1Warranty warranty;

	@JsonProperty("firmware_version")
	private V1FirmwareVersion firmwareVersion;

	private V1Connections connections;

	private List<String> flags;

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getCommissionDate() {
		return commissionDate;
	}

	public void setCommissionDate(String commissionDate) {
		this.commissionDate = commissionDate;
	}

	public String getLastOnline() {
		return lastOnline;
	}

	public void setLastOnline(String lastOnline) {
		this.lastOnline = lastOnline;
	}

	public V1Info getInfo() {
		return info;
	}

	public void setInfo(V1Info info) {
		this.info = info;
	}

	public V1Warranty getWarranty() {
		return warranty;
	}

	public void setWarranty(V1Warranty warranty) {
		this.warranty = warranty;
	}

	public V1FirmwareVersion getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(V1FirmwareVersion firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	public V1Connections getConnections() {
		return connections;
	}

	public void setConnections(V1Connections connections) {
		this.connections = connections;
	}

	public List<String> getFlags() {
		return flags;
	}

	public void setFlags(List<String> flags) {
		this.flags = flags;
	}

}
