package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1Links {

	@JsonProperty("first")
	private String first;

	@JsonProperty("last")
	private String last;

	@JsonProperty("prev")
	private String prev;

	@JsonProperty("next")
	private String next;

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

}
