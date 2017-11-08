package com.dot.pojo;

public class msgReportPacket {
	private String mac;
	private String esn;
	private String ip;
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getEsn() {
		return esn;
	}
	public void setEsn(String esn) {
		this.esn = esn;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "msgReportPacket [mac=" + mac + ", esn=" + esn + ", ip=" + ip + "]";
	}
	
}
