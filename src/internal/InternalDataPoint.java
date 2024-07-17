package internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalDataPoint {

	@JsonProperty("grid_power")
	private Integer gridPower;

	@JsonProperty("grid_import")
	private Integer gridImport;

	@JsonProperty("grid_export")
	private Integer gridExport;

	@JsonProperty("generation")
	private Integer generation;

	@JsonProperty("string_generation_1")
	private Integer stringGeneration1;

	@JsonProperty("string_generation_2")
	private Integer stringGeneration2;

	@JsonProperty("string_current_1")
	private Integer stringCurrent1;

	@JsonProperty("string_current_2")
	private Integer stringCurrent2;

	@JsonProperty("string_voltage_1")
	private Float stringVoltage1;

	@JsonProperty("string_voltage_2")
	private Float stringVoltage2;

	@JsonProperty("battery_power")
	private Integer batteryPower;

	@JsonProperty("battery_charge")
	private Integer batteryCharge;

	@JsonProperty("battery_discharge")
	private Integer batteryDischarge;

	@JsonProperty("demanded_power")
	private Integer demandedPower;

	@JsonProperty("inverter_power")
	private Integer inverterPower;

	@JsonProperty("grid_voltage")
	private Float gridVoltage;

	@JsonProperty("battery_voltage")
	private Float batteryVoltage;

	@JsonProperty("battery_percentage")
	private Integer batteryPercentage;

	@JsonProperty("battery_temperature")
	private Float batteryTemperature;

	@JsonProperty("eps_output_power")
	private Integer epsOutputPower;

	@JsonProperty("power_factor")
	private Float powerFactor;

	@JsonProperty("grid_current")
	private Float gridCurrent;

	@JsonProperty("grid_frequency")
	private Float gridFrequency;

	@JsonProperty("inverter_voltage")
	private Float inverterVoltage;

	@JsonProperty("inverter_frequency")
	private Float inverterFrequency;

	@JsonProperty("inverter_temperature")
	private Float inverterTemperature;

	@JsonProperty("solar_generation_energy_today")
	private Float solarGenerationEnergyToday;

	@JsonProperty("solar_generation_energy_total")
	private Float solarGenerationEnergyTotal;

	@JsonProperty("battery_throughput_energy_today")
	private Float batteryThroughputEnergyToday;

	@JsonProperty("battery_throughput_energy_total")
	private Float batteryThroughputEnergyTotal;

	@JsonProperty("battery_charge_energy_today")
	private Float batteryChargeEnergyToday;

	@JsonProperty("battery_charge_energy_total")
	private Float batteryChargeEnergyTotal;

	@JsonProperty("battery_discharge_energy_today")
	private Float batteryDischargeEnergyToday;

	@JsonProperty("battery_discharge_energy_total")
	private Float batteryDischargeEnergyTotal;

	@JsonProperty("grid_import_energy_today")
	private Float gridImportEnergyToday;

	@JsonProperty("grid_import_energy_total")
	private Float gridImportEnergyTotal;

	@JsonProperty("grid_export_energy_today")
	private Float gridExportEnergyToday;

	@JsonProperty("grid_export_energy_total")
	private Float gridExportEnergyTotal;

	@JsonProperty("ac_charge_energy_today")
	private Float acChargeEnergyToday;

	@JsonProperty("ac_charge_energy_total")
	private Float acChargeEnergyTotal;

	@JsonProperty("inverter_energy_today")
	private Float inverterEnergyToday;

	@JsonProperty("inverter_energy_total")
	private Float inverterEnergyTotal;

	@JsonProperty("consumption_energy_today")
	private Float consumptionEnergyToday;

	@JsonProperty("consumption_energy_total")
	private Float consumptionEnergyTotal;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("time")
	private String time;

	@JsonProperty("ftime")
	private String ftime;

	@JsonProperty("timestamp")
	private Integer timestamp;

	@JsonProperty("status")
	private String status;

	public Integer getGridPower() {
		return gridPower;
	}

	public void setGridPower(Integer gridPower) {
		this.gridPower = gridPower;
	}

	public Integer getGridImport() {
		return gridImport;
	}

	public void setGridImport(Integer gridImport) {
		this.gridImport = gridImport;
	}

	public Integer getGridExport() {
		return gridExport;
	}

	public void setGridExport(Integer gridExport) {
		this.gridExport = gridExport;
	}

	public Float getGridVoltage() {
		return gridVoltage;
	}

	public void setGridVoltage(Float gridVoltage) {
		this.gridVoltage = gridVoltage;
	}

	public Float getGridCurrent() {
		return gridCurrent;
	}

	public void setGridCurrent(Float gridCurrent) {
		this.gridCurrent = gridCurrent;
	}

	public Float getGridFrequency() {
		return gridFrequency;
	}

	public void setGridFrequency(Float gridFrequency) {
		this.gridFrequency = gridFrequency;
	}

	public Float getGridImportEnergyToday() {
		return gridImportEnergyToday;
	}

	public void setGridImportEnergyToday(Float gridImportEnergyToday) {
		this.gridImportEnergyToday = gridImportEnergyToday;
	}

	public Float getGridExportEnergyToday() {
		return gridExportEnergyToday;
	}

	public void setGridExportEnergyToday(Float gridExportEnergyToday) {
		this.gridExportEnergyToday = gridExportEnergyToday;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
