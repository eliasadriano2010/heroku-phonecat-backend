package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

public class Camera {
	@ObjectId
	@Id
	private String id;
	private boolean flash;
	private boolean video;
	private String primary;

	public Camera() {
		super();
	}

	

	public Camera(String id, boolean flash, boolean video, String primary) {
		super();
		this.id = id;
		this.flash = flash;
		this.video = video;
		this.primary = primary;
	}

	@JsonProperty("_id")
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public boolean isFlash() {
		return flash;
	}

	public void setFlash(boolean flash) {
		this.flash = flash;
	}

	public boolean isVideo() {
		return video;
	}

	public void setVideo(boolean video) {
		this.video = video;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}



	@Override
	public String toString() {
		return "Camera [id=" + id + ", flash=" + flash + ", video=" + video
				+ ", primary=" + primary + "]";
	}

	

}