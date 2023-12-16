package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1Battery {

	@JsonProperty("nominal_capacity")
	private Integer nominalCapacity;

	@JsonProperty("nominal_voltage")
	private Float nominalVoltage;

	public Integer getNominalCapacity() {
		return nominalCapacity;
	}

	public void setNominalCapacity(Integer nominalCapacity) {
		this.nominalCapacity = nominalCapacity;
	}

	public Float getNominalVoltage() {
		return nominalVoltage;
	}

	public void setNominalVoltage(Float nominalVoltage) {
		this.nominalVoltage = nominalVoltage;
	}

}
