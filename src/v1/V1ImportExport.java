package v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class V1ImportExport {

	@JsonProperty("import")
	private Float imp;

	private Float export;

	public Float getImp() {
		return imp;
	}

	public void setImp(Float imp) {
		this.imp = imp;
	}

	public Float getExport() {
		return export;
	}

	public void setExport(Float export) {
		this.export = export;
	}

}
