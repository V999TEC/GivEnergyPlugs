package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1Meters {

	private Float solar;

	private V1ImportExport grid;

	private V1ChargeDischarge battery;

	private Float consumption;

	@JsonProperty("ac_charge")
	private Float acCharge;

	public Float getAcCharge() {
		return acCharge;
	}

	public void setAcCharge(Float acCharge) {
		this.acCharge = acCharge;
	}

	public Float getConsumption() {
		return consumption;
	}

	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	public Float getSolar() {
		return solar;
	}

	public void setSolar(Float solar) {
		this.solar = solar;
	}

	public V1ImportExport getGrid() {
		return grid;
	}

	public void setGrid(V1ImportExport grid) {
		this.grid = grid;
	}

	public V1ChargeDischarge getBattery() {
		return battery;
	}

	public void setBattery(V1ChargeDischarge battery) {
		this.battery = battery;
	}

}
