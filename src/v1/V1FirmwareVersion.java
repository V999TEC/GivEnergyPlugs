package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1FirmwareVersion {
	@JsonProperty("ARM")
	private Integer arm;

	@JsonProperty("DSP")
	private Integer dsp;

	public Integer getArm() {
		return arm;
	}

	public void setArm(Integer arm) {
		this.arm = arm;
	}

	public Integer getDsp() {
		return dsp;
	}

	public void setDsp(Integer dsp) {
		this.dsp = dsp;
	}

}
