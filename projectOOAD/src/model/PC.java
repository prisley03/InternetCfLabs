package model;

public class PC {
	private int pcId;
	private String pcCondition;
	public PC(int pcId, String pcCondition) {
		super();
		this.pcId = pcId;
		this.pcCondition = pcCondition;
	}
	public int getPcId() {
		return pcId;
	}
	public void setPcId(int pcId) {
		this.pcId = pcId;
	}
	public String getPcCondition() {
		return pcCondition;
	}
	public void setPcCondition(String pcCondition) {
		this.pcCondition = pcCondition;
	}
}
