package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;


public class Android {
	@ObjectId @Id
	private String id;
	private String os;
	private String ui;

	public Android() {
		super();
	}

	public Android(String id, String os, String ui) {
		super();
		this.id = id;
		this.os = os;
		this.ui = ui;
	}
	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getUi() {
		return ui;
	}

	public void setUi(String ui) {
		this.ui = ui;
	}

	

}