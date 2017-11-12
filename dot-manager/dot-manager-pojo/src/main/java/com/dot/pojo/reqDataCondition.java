package com.dot.pojo;

public class reqDataCondition {
	private int pageSize;
	private int pageNumber;
	private String mac;
	private String type;
	private long startTimestamp;
	private long endTimestamp;
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
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
		return "reqDataCondition [pageSize=" + pageSize + ", pageNumber=" + pageNumber + ", mac=" + mac + ", type="
				+ type + ", startTimestamp=" + startTimestamp + ", endTimestamp=" + endTimestamp + "]";
	}

	
}
