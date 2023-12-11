package v1;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class V1Descriptor {

	private String id;

	private String name;

	private String description;

	@JsonDeserialize(using = V1MiscellaneousJsonDeserializer.class)
	private V1Miscellaneous parameters;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public V1Miscellaneous getParameters() {
		return parameters;
	}

	public void setParameters(V1Miscellaneous parameters) {
		this.parameters = parameters;
	}

}
