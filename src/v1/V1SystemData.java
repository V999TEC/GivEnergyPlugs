package v1;

public class V1SystemData {

	private String time;

	private String status;

	private V1SolarData solar;

	private V1GridData grid;

	private V1BatteryData battery;

	private V1Inverter inverter;

	private Integer consumption;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public V1SolarData getSolar() {
		return solar;
	}

	public void setSolar(V1SolarData solar) {
		this.solar = solar;
	}

	public V1GridData getGrid() {
		return grid;
	}

	public void setGrid(V1GridData grid) {
		this.grid = grid;
	}

	public V1BatteryData getBattery() {
		return battery;
	}

	public void setBattery(V1BatteryData battery) {
		this.battery = battery;
	}

	public V1Inverter getInverter() {
		return inverter;
	}

	public void setInverter(V1Inverter inverter) {
		this.inverter = inverter;
	}

	public Integer getConsumption() {
		return consumption;
	}

	public void setConsumption(Integer consumption) {
		this.consumption = consumption;
	}
}
