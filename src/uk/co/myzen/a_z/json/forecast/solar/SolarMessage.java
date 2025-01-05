package uk.co.myzen.a_z.json.forecast.solar;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolarMessage {

	@JsonProperty("code")
	private Integer code;

	@JsonProperty("type")
	private String type;

	@JsonProperty("text")
	private String text;

	@JsonProperty("pid")
	private String pid;

	@JsonProperty("info")
	private Info info;

	@JsonProperty("ratelimit")
	private RateLimit rateLimit;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public RateLimit getRateLimit() {
		return rateLimit;
	}

	public void setRateLimit(RateLimit rateLimit) {
		this.rateLimit = rateLimit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
