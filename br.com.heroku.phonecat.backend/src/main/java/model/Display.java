package model;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import org.codehaus.jackson.annotate.JsonProperty;


public class Display {
	@ObjectId
	@Id
	private String id;
	private String screenResolution;
	private String screenSize;
	private boolean touchscreen;

	public Display() {
		super();
	}

	

	public Display(String id, String screenResolution, String screenSize,
			boolean touchscreen) {
		super();
		this.id = id;
		this.screenResolution = screenResolution;
		this.screenSize = screenSize;
		this.touchscreen = touchscreen;
	}


	@JsonProperty("_id")
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getScreenResolution() {
		return screenResolution;
	}

	public void setScreenResolution(String screenResolution) {
		this.screenResolution = screenResolution;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public boolean isTouchscreen() {
		return touchscreen;
	}

	public void setTouchscreen(boolean touchscreen) {
		this.touchscreen = touchscreen;
	}



	@Override
	public String toString() {
		return "Display [id=" + id + ", screenResolution=" + screenResolution
				+ ", screenSize=" + screenSize + ", touchscreen=" + touchscreen
				+ "]";
	}

	

}
