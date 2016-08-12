package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

public class Battery {

	@ObjectId
	@Id
	private String id;
	private String standbyTime;
	private String talkTime;
	private String type;

	public Battery() {

	}

	public Battery(String id, String standbyTime, String talkTime, String type) {
		super();
		this.id = id;
		this.standbyTime = standbyTime;
		this.talkTime = talkTime;
		this.type = type;
	}

	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStandbyTime() {
		return standbyTime;
	}

	public void setStandbyTime(String standbyTime) {
		this.standbyTime = standbyTime;
	}

	public String getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(String talkTime) {
		this.talkTime = talkTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Battery [id=" + id + ", standbyTime=" + standbyTime
				+ ", talkTime=" + talkTime + ", type=" + type + "]";
	}

	

}