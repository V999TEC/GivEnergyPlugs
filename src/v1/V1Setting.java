package v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1Setting {

	private String id;

	private String name;

	private String validation;

	@JsonProperty("validation_rules")
	private List<String> validationRules;

	public List<String> getValidationRules() {
		return validationRules;
	}

	public void setValidationRules(List<String> validationRules) {
		this.validationRules = validationRules;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
