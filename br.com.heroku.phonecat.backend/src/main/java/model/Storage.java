package model;

import org.codehaus.jackson.annotate.JsonProperty;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;




public class Storage {

	@ObjectId
	@Id
	private String id;
	private String flash;
	private String ram;

	public Storage() {
		super();
	}

	public Storage(String id, String flash, String ram) {
		super();
		this.id = id;
		this.flash = flash;
		this.ram = ram;
	}

	@JsonProperty("_id")
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getFlash() {
		return flash;
	}

	public void setFlash(String flash) {
		this.flash = flash;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	@Override
	public String toString() {
		return "Storage [id=" + id + ", flash=" + flash + ", ram=" + ram + "]";
	}

	

}
