package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1Meta {

	@JsonProperty("current_page")
	private Integer currentPage;

	@JsonProperty("from")
	private Integer from;

	@JsonProperty("last_page")
	private Integer lastPage;

	@JsonProperty("path")
	private String path;

	@JsonProperty("per_page")
	private Integer perPage;

	@JsonProperty("to")
	private Integer to;

	@JsonProperty("total")
	private Integer total;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getPerPage() {
		return perPage;
	}

	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
