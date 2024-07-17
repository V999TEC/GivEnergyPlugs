package internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalGridDataPoint {

	@JsonProperty("grid_power")
	private Integer gridPower;

	@JsonProperty("grid_import")
	private Integer gridImport;

	@JsonProperty("grid_export")
	private Integer gridExport;

	@JsonProperty("grid_voltage")
	private Float gridVoltage;

	@JsonProperty("grid_current")
	private Float gridCurrent;

	@JsonProperty("grid_frequency")
	private Float gridFrequency;

	@JsonProperty("grid_import_energy_today")
	private Float gridImportEnergyToday;

	@JsonProperty("grid_export_energy_today")
	private Float gridExportEnergyToday;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("time")
	private String time;

	@JsonProperty("timestamp")
	private Integer timestamp;

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
