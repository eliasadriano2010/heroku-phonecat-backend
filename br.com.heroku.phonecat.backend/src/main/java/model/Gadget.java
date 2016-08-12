package model;

import java.util.ArrayList;
import java.util.List;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import org.codehaus.jackson.annotate.JsonProperty;

public class Gadget {
	@ObjectId
	@Id
	private String id;
	private String additionalFeatures;

	private Android android;

	private String availability;

	private Battery battery;

	private Camera camera;

	private Connectivity connectivity;

	private String description;

	private Display display;
	private Hardware hardware;

	private List<Image> images;

	private String name;

	private String height;
	private String width;
	private String lenght;

	private String weight;

	private Storage storage;

	private String snippet;

	public Gadget() {
		super();
	}

	public Gadget(String id, String additionalFeatures, Android android,
			String availability, Battery battery, Camera camera,
			Connectivity connectivity, String description, Display display,
			Hardware hardware, List<Image> images, String name, String height,
			String width, String lenght, String weight, Storage storage,
			String snippet) {
		super();
		this.id = id;
		this.additionalFeatures = additionalFeatures;
		this.android = android;
		this.availability = availability;
		this.battery = battery;
		this.camera = camera;
		this.connectivity = connectivity;
		this.description = description;
		this.display = display;
		this.hardware = hardware;
		this.images = images;
		this.name = name;
		this.height = height;
		this.width = width;
		this.lenght = lenght;
		this.weight = weight;
		this.storage = storage;
		this.snippet = snippet;
	}

	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdditionalFeatures() {
		return additionalFeatures;
	}

	public void setAdditionalFeatures(String additionalFeatures) {
		this.additionalFeatures = additionalFeatures;
	}

	public Android getAndroid() {
		return android;
	}

	public void setAndroid(Android android) {
		this.android = android;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Battery getBattery() {
		return battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Connectivity getConnectivity() {
		return connectivity;
	}

	public void setConnectivity(Connectivity connectivity) {
		this.connectivity = connectivity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Hardware getHardware() {
		return hardware;
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void addImage(Image image) {
		if (this.images != null) {
			this.images.add(image);
		} else {
			this.images = new ArrayList<Image>();
			this.images.add(image);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getLenght() {
		return lenght;
	}

	public void setLenght(String lenght) {
		this.lenght = lenght;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	@Override
	public String toString() {
		return "Gadget [id=" + id + ", additionalFeatures="
				+ additionalFeatures + ", android=" + android
				+ ", availability=" + availability + ", battery=" + battery
				+ ", camera=" + camera + ", connectivity=" + connectivity
				+ ", description=" + description + ", display=" + display
				+ ", hardware=" + hardware + ", images=" + images + ", name="
				+ name + ", height=" + height + ", width=" + width
				+ ", lenght=" + lenght + ", weight=" + weight + ", storage="
				+ storage + ", snippet=" + snippet + "]";
	}

	

}
