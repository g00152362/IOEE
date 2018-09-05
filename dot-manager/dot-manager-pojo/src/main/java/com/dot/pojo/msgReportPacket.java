package com.dot.pojo;

public class msgReportPacket {

	private String mac;
	private String esn;
	private String ip;
	private int type;
	private int version;
	private String gateway;	

	private int reboot;
	
	public msgReportPacket() {
		super();
		reboot = 0;

	}
	
	public void setReboot(int reboot) {
		this.reboot = reboot;
	}
	public int getReboot() {
		return reboot;
	}	
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getEsn() {
		return esn;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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

	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	@Override
	public String toString() {
		return "msgReportPacket [mac=" + mac + ", esn=" + esn + ", ip=" + ip + ", type=" + type + ", version=" + version
				+ ", gateway=" + gateway + "]";
	}

}
