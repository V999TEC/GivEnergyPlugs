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

	public Integer getGeneration() {
		return generation;
	}

	public void setGeneration(Integer generation) {
		this.generation = generation;
	}

	public Integer getStringGeneration1() {
		return stringGeneration1;
	}

	public void setStringGeneration1(Integer stringGeneration1) {
		this.stringGeneration1 = stringGeneration1;
	}

	public Integer getStringGeneration2() {
		return stringGeneration2;
	}

	public void setStringGeneration2(Integer stringGeneration2) {
		this.stringGeneration2 = stringGeneration2;
	}

	public Integer getStringCurrent1() {
		return stringCurrent1;
	}

	public void setStringCurrent1(Integer stringCurrent1) {
		this.stringCurrent1 = stringCurrent1;
	}

	public Integer getStringCurrent2() {
		return stringCurrent2;
	}

	public void setStringCurrent2(Integer stringCurrent2) {
		this.stringCurrent2 = stringCurrent2;
	}

	public Float getStringVoltage1() {
		return stringVoltage1;
	}

	public void setStringVoltage1(Float stringVoltage1) {
		this.stringVoltage1 = stringVoltage1;
	}

	public Float getStringVoltage2() {
		return stringVoltage2;
	}

	public void setStringVoltage2(Float stringVoltage2) {
		this.stringVoltage2 = stringVoltage2;
	}

	public Integer getBatteryPower() {
		return batteryPower;
	}

	public void setBatteryPower(Integer batteryPower) {
		this.batteryPower = batteryPower;
	}

	public Integer getBatteryCharge() {
		return batteryCharge;
	}

	public void setBatteryCharge(Integer batteryCharge) {
		this.batteryCharge = batteryCharge;
	}

	public Integer getBatteryDischarge() {
		return batteryDischarge;
	}

	public void setBatteryDischarge(Integer batteryDischarge) {
		this.batteryDischarge = batteryDischarge;
	}

	public Integer getDemandedPower() {
		return demandedPower;
	}

	public void setDemandedPower(Integer demandedPower) {
		this.demandedPower = demandedPower;
	}

	public Integer getInverterPower() {
		return inverterPower;
	}

	public void setInverterPower(Integer inverterPower) {
		this.inverterPower = inverterPower;
	}

	public Float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(Float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public Integer getBatteryPercentage() {
		return batteryPercentage;
	}

	public void setBatteryPercentage(Integer batteryPercentage) {
		this.batteryPercentage = batteryPercentage;
	}

	public Float getBatteryTemperature() {
		return batteryTemperature;
	}

	public void setBatteryTemperature(Float batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
	}

	public Integer getEpsOutputPower() {
		return epsOutputPower;
	}

	public void setEpsOutputPower(Integer epsOutputPower) {
		this.epsOutputPower = epsOutputPower;
	}

	public Float getPowerFactor() {
		return powerFactor;
	}

	public void setPowerFactor(Float powerFactor) {
		this.powerFactor = powerFactor;
	}

	public Float getInverterVoltage() {
		return inverterVoltage;
	}

	public void setInverterVoltage(Float inverterVoltage) {
		this.inverterVoltage = inverterVoltage;
	}

	public Float getInverterFrequency() {
		return inverterFrequency;
	}

	public void setInverterFrequency(Float inverterFrequency) {
		this.inverterFrequency = inverterFrequency;
	}

	public Float getInverterTemperature() {
		return inverterTemperature;
	}

	public void setInverterTemperature(Float inverterTemperature) {
		this.inverterTemperature = inverterTemperature;
	}

	public Float getSolarGenerationEnergyToday() {
		return solarGenerationEnergyToday;
	}

	public void setSolarGenerationEnergyToday(Float solarGenerationEnergyToday) {
		this.solarGenerationEnergyToday = solarGenerationEnergyToday;
	}

	public Float getSolarGenerationEnergyTotal() {
		return solarGenerationEnergyTotal;
	}

	public void setSolarGenerationEnergyTotal(Float solarGenerationEnergyTotal) {
		this.solarGenerationEnergyTotal = solarGenerationEnergyTotal;
	}

	public Float getBatteryThroughputEnergyToday() {
		return batteryThroughputEnergyToday;
	}

	public void setBatteryThroughputEnergyToday(Float batteryThroughputEnergyToday) {
		this.batteryThroughputEnergyToday = batteryThroughputEnergyToday;
	}

	public Float getBatteryThroughputEnergyTotal() {
		return batteryThroughputEnergyTotal;
	}

	public void setBatteryThroughputEnergyTotal(Float batteryThroughputEnergyTotal) {
		this.batteryThroughputEnergyTotal = batteryThroughputEnergyTotal;
	}

	public Float getBatteryChargeEnergyToday() {
		return batteryChargeEnergyToday;
	}

	public void setBatteryChargeEnergyToday(Float batteryChargeEnergyToday) {
		this.batteryChargeEnergyToday = batteryChargeEnergyToday;
	}

	public Float getBatteryChargeEnergyTotal() {
		return batteryChargeEnergyTotal;
	}

	public void setBatteryChargeEnergyTotal(Float batteryChargeEnergyTotal) {
		this.batteryChargeEnergyTotal = batteryChargeEnergyTotal;
	}

	public Float getBatteryDischargeEnergyTotal() {
		return batteryDischargeEnergyTotal;
	}

	public void setBatteryDischargeEnergyTotal(Float batteryDischargeEnergyTotal) {
		this.batteryDischargeEnergyTotal = batteryDischargeEnergyTotal;
	}

	public Float getGridExportEnergyTotal() {
		return gridExportEnergyTotal;
	}

	public void setGridExportEnergyTotal(Float gridExportEnergyTotal) {
		this.gridExportEnergyTotal = gridExportEnergyTotal;
	}

	public Float getAcChargeEnergyToday() {
		return acChargeEnergyToday;
	}

	public void setAcChargeEnergyToday(Float acChargeEnergyToday) {
		this.acChargeEnergyToday = acChargeEnergyToday;
	}

	public Float getAcChargeEnergyTotal() {
		return acChargeEnergyTotal;
	}

	public void setAcChargeEnergyTotal(Float acChargeEnergyTotal) {
		this.acChargeEnergyTotal = acChargeEnergyTotal;
	}

	public Float getInverterEnergyToday() {
		return inverterEnergyToday;
	}

	public void setInverterEnergyToday(Float inverterEnergyToday) {
		this.inverterEnergyToday = inverterEnergyToday;
	}

	public Float getInverterEnergyTotal() {
		return inverterEnergyTotal;
	}

	public void setInverterEnergyTotal(Float inverterEnergyTotal) {
		this.inverterEnergyTotal = inverterEnergyTotal;
	}

	public Float getConsumptionEnergyToday() {
		return consumptionEnergyToday;
	}

	public void setConsumptionEnergyToday(Float consumptionEnergyToday) {
		this.consumptionEnergyToday = consumptionEnergyToday;
	}

	public Float getConsumptionEnergyTotal() {
		return consumptionEnergyTotal;
	}

	public void setConsumptionEnergyTotal(Float consumptionEnergyTotal) {
		this.consumptionEnergyTotal = consumptionEnergyTotal;
	}

	public String getFtime() {
		return ftime;
	}

	public void setFtime(String ftime) {
		this.ftime = ftime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
