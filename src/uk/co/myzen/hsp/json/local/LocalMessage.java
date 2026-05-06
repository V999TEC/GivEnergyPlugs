package uk.co.myzen.hsp.json.local;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocalMessage {

	@JsonProperty("state")
	private String state;
	
	@JsonProperty("entity_id")
	private String entityId;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

}
