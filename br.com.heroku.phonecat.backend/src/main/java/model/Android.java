package model;


public class Android {
	
	private Long id;
	private String os;
	private String ui;
	
	public Android() {
		super();
	}

	public Android(Long id, String os, String ui) {
		super();
		this.id = id;
		this.os = os;
		this.ui = ui;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Android [id=" + id + ", os=" + os + ", ui=" + ui + "]";
	}

	

}