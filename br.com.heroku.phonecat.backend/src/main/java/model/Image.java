package model;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import org.codehaus.jackson.annotate.JsonProperty;

public class Image {

	@ObjectId
	@Id
	private String id;
	private String imageType;
	private String imageName;
	private String imageData;
	
	public Image() {
		super();
	}

	public Image(String id, String imageType, String imageName, String imageData) {
		super();
		this.id = id;
		this.imageType = imageType;
		this.imageName = imageName;
		this.imageData = imageData;
	}

	@JsonProperty("_id")
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", imageType=" + imageType + ", imageName="
				+ imageName + ", imageData=" + imageData + "]";
	}

	
	
}
