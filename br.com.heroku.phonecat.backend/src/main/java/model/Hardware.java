package model;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import org.codehaus.jackson.annotate.JsonProperty;

public class Hardware {

	@ObjectId
	@Id
	private String id;
	private boolean accelerometer;
	private String audioJack;
	private String cpu;
	private boolean fmRadio;
	private boolean physicalKeyboard;
	private String usb;

	public Hardware() {
	}

	public Hardware(String id, boolean accelerometer, String audioJack,
			String cpu, boolean fmRadio, boolean physicalKeyboard, String usb) {
		super();
		this.id = id;
		this.accelerometer = accelerometer;
		this.audioJack = audioJack;
		this.cpu = cpu;
		this.fmRadio = fmRadio;
		this.physicalKeyboard = physicalKeyboard;
		this.usb = usb;
	}

	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAccelerometer() {
		return accelerometer;
	}

	public void setAccelerometer(boolean accelerometer) {
		this.accelerometer = accelerometer;
	}

	public String getAudioJack() {
		return audioJack;
	}

	public void setAudioJack(String audioJack) {
		this.audioJack = audioJack;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public boolean isFmRadio() {
		return fmRadio;
	}

	public void setFmRadio(boolean fmRadio) {
		this.fmRadio = fmRadio;
	}

	public boolean isPhysicalKeyboard() {
		return physicalKeyboard;
	}

	public void setPhysicalKeyboard(boolean physicalKeyboard) {
		this.physicalKeyboard = physicalKeyboard;
	}

	public String getUsb() {
		return usb;
	}

	public void setUsb(String usb) {
		this.usb = usb;
	}

	@Override
	public String toString() {
		return "Hardware [id=" + id + ", accelerometer=" + accelerometer
				+ ", audioJack=" + audioJack + ", cpu=" + cpu + ", fmRadio="
				+ fmRadio + ", physicalKeyboard=" + physicalKeyboard + ", usb="
				+ usb + "]";
	}

	

}