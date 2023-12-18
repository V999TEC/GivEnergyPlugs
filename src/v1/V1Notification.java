package v1;

import java.util.List;

public class V1Notification {

	private List<String> platforms;

	private String title;

	private String body;

	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<String> platforms) {
		this.platforms = platforms;
	}

}
