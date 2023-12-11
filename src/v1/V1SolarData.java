package v1;

import java.util.List;

public class V1SolarData {

	private Integer power;

	private List<V1Array> arrays;

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public List<V1Array> getArrays() {
		return arrays;
	}

	public void setArrays(List<V1Array> arrays) {
		this.arrays = arrays;
	}

}
