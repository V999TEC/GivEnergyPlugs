package uk.co.myzen.a_z.json.forecast.solar;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class SolarResult {

	private Map<String, Integer> data = new HashMap<String, Integer>();

	public Set<String> getKeySet() {

		return data.keySet();
	}

	@JsonAnySetter
	public void setValue(String key, Integer value) {

		data.put(key, value);
	}

	@JsonAnyGetter
	public Integer getValue(String key) {

		return data.get(key);
	}

}
