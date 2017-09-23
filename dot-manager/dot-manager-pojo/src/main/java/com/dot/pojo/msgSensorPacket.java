package com.dot.pojo;

public class msgSensorPacket {
	private String mac;
	private float temperature;
	private float humidity;
	private float ir;
	private int  pressure;
	private float light;
	private int  noise;
	private int  battery;
	private float accelerate_X;
	private float accelerate_Y;
	private float accelerate_Z;
	private float gyroscope_X;
	private float gyroscope_Y;
	private float gyroscope_Z;
	private float hall;
	

	public float getHall() {
		return hall;
	}
	public void setHall(float hall) {
		this.hall = hall;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	public float getIr() {
		return ir;
	}
	public void setIr(float ir) {
		this.ir = ir;
	}
	public int getPressure() {
		return pressure;
	}
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	public float getLight() {
		return light;
	}
	public void setLight(float light) {
		this.light = light;
	}
	public int getNoise() {
		return noise;
	}
	public void setNoise(int noise) {
		this.noise = noise;
	}
	public int getBattery() {
		return battery;
	}
	public void setBattery(int battery) {
		this.battery = battery;
	}
	public float getAccelerate_X() {
		return accelerate_X;
	}
	public void setAccelerate_X(float accelerate_X) {
		this.accelerate_X = accelerate_X;
	}
	public float getAccelerate_Y() {
		return accelerate_Y;
	}
	public void setAccelerate_Y(float accelerate_Y) {
		this.accelerate_Y = accelerate_Y;
	}
	public float getAccelerate_Z() {
		return accelerate_Z;
	}
	public void setAccelerate_Z(float accelerate_Z) {
		this.accelerate_Z = accelerate_Z;
	}
	public float getGyroscope_X() {
		return gyroscope_X;
	}
	public void setGyroscope_X(float gyroscope_X) {
		this.gyroscope_X = gyroscope_X;
	}
	public float getGyroscope_Y() {
		return gyroscope_Y;
	}
	public void setGyroscope_Y(float gyroscope_Y) {
		this.gyroscope_Y = gyroscope_Y;
	}
	public float getGyroscope_Z() {
		return gyroscope_Z;
	}
	public void setGyroscope_Z(float gyroscope_Z) {
		this.gyroscope_Z = gyroscope_Z;
	}
	@Override
	public String toString() {
		return "msgSensorPacket [mac=" + mac + ", temperature=" + temperature + ", humidity=" + humidity + ", ir=" + ir
				+ ", pressure=" + pressure + ", light=" + light + ", noise=" + noise + ", battery=" + battery
				+ ", accelerate_X=" + accelerate_X + ", accelerate_Y=" + accelerate_Y + ", accelerate_Z=" + accelerate_Z
				+ ", gyroscope_X=" + gyroscope_X + ", gyroscope_Y=" + gyroscope_Y + ", gyroscope_Z=" + gyroscope_Z
				+ ", hall=" + hall + "]";
	}

}
