package com.dot.pojo;

public class reqDataCondition {
	private String mac;
	private String type;
	private long startTimestamp;
	private long endTimestamp;
	
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(long startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public long getEndTimestamp() {
		return endTimestamp;
	}
	public void setEndTimestamp(long endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
	@Override
	public String toString() {
		return "reqDataCondition [mac=" + mac + ", type=" + type + ", startTimestamp=" + startTimestamp
				+ ", endTimestamp=" + endTimestamp + "]";
	}
	
}
