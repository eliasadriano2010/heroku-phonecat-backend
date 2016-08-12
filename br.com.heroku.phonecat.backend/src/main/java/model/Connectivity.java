package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;


public class Connectivity {
	@ObjectId
	@Id
	private String id;
	private String bluetooth;
	private String cell;
	private boolean gps;
	private boolean infrared;
	private String wifi;

	public Connectivity() {
		super();
	}

	
	public Connectivity(String id, String bluetooth, String cell, boolean gps,
			boolean infrared, String wifi) {
		super();
		this.id = id;
		this.bluetooth = bluetooth;
		this.cell = cell;
		this.gps = gps;
		this.infrared = infrared;
		this.wifi = wifi;
	}

	@JsonProperty("_id")
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getBluetooth() {
		return bluetooth;
	}

	public void setBluetooth(String bluetooth) {
		this.bluetooth = bluetooth;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public boolean isGps() {
		return gps;
	}

	public void setGps(boolean gps) {
		this.gps = gps;
	}

	public boolean isInfrared() {
		return infrared;
	}

	public void setInfrared(boolean infrared) {
		this.infrared = infrared;
	}

	public String getWifi() {
		return wifi;
	}

	public void setWifi(String wifi) {
		this.wifi = wifi;
	}


	@Override
	public String toString() {
		return "Connectivity [id=" + id + ", bluetooth=" + bluetooth
				+ ", cell=" + cell + ", gps=" + gps + ", infrared=" + infrared
				+ ", wifi=" + wifi + "]";
	}

	
}