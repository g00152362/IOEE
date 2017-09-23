package com.dot.pojo;

public class TbGatewayInfoStat {
	private int online;
	private int offline;
	private int unregistered;
	
	@Override
	public String toString() {
		return "TbGatewayInfoStat [online=" + online + ", offline=" + offline + ", unregistered=" + unregistered + "]";
	}

	
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public int getOffline() {
		return offline;
	}
	public void setOffline(int offline) {
		this.offline = offline;
	}
	public int getUnregistered() {
		return unregistered;
	}
	public void setUnregistered(int unregistered) {
		this.unregistered = unregistered;
	}


}
