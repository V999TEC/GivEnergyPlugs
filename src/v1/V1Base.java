package v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1Base {

	@JsonProperty("data")
	private List<Object> data;

	@JsonProperty("links")
	private V1Links links;

	@JsonProperty("meta")
	private V1Meta meta;

	public V1Meta getMeta() {
		return meta;
	}

	public void setMeta(V1Meta meta) {
		this.meta = meta;
	}

	public V1Links getLinks() {
		return links;
	}

	public void setLinks(V1Links links) {
		this.links = links;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

}
