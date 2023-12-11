package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1Inverter {

	private Float temperature;

	private Integer power;

	@JsonProperty("output_voltage")
	private Float outputVoltage;

	@JsonProperty("output_frequency")
	private Float outputFrequency;

	@JsonProperty("eps_power")
	private Integer epsPower;

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Float getOutputVoltage() {
		return outputVoltage;
	}

	public void setOutputVoltage(Float outputVoltage) {
		this.outputVoltage = outputVoltage;
	}

	public Float getOutputFrequency() {
		return outputFrequency;
	}

	public void setOutputFrequency(Float outputFrequency) {
		this.outputFrequency = outputFrequency;
	}

	public Integer getEpsPower() {
		return epsPower;
	}

	public void setEpsPower(Integer epsPower) {
		this.epsPower = epsPower;
	}

}
