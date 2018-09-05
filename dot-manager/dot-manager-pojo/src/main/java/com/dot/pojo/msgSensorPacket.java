package com.dot.pojo;

public class msgSensorPacket {
	private String mac;
	private float temperature;
	private float humidity;
	private float ir;
	private int  pressure;
	private float light;
	private int  noise;
	private float  battery;
	private float accelerate_X;
	private float accelerate_Y;
	private float accelerate_Z;
	private float gyroscope_X;
	private float gyroscope_Y;
	private float gyroscope_Z;
	private float hall;
	private float hall_X;
	private float hall_Y;
	private float hall_Z;	
	private int water;	
	private int smoke;	

	public msgSensorPacket() {
		super();
		setToDefault();
		// TODO Auto-generated constructor stub
	}
	public float getHall_X() {
		return hall_X;
	}
	public void setHall_X(float hall_X) {
		this.hall_X = hall_X;
	}
	public float getHall_Y() {
		return hall_Y;
	}
	public void setHall_Y(float hall_Y) {
		this.hall_Y = hall_Y;
	}
	public float getHall_Z() {
		return hall_Z;
	}
	public void setHall_Z(float hall_Z) {
		this.hall_Z = hall_Z;
	}
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
	public float getBattery() {
		return battery;
	}
	public void setBattery(float battery) {
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
	public int getWater() {
		return water;
	}
	public void setWater(int water) {
		this.water = water;
	}
	public int getSmoke() {
		return smoke;
	}
	public void setSmoke(int smoke) {
		this.smoke = smoke;
	}
	@Override
	public String toString() {
		return "msgSensorPacket [mac=" + mac + ", temperature=" + temperature + ", humidity=" + humidity + ", ir=" + ir
				+ ", pressure=" + pressure + ", light=" + light + ", noise=" + noise + ", battery=" + battery
				+ ", accelerate_X=" + accelerate_X + ", accelerate_Y=" + accelerate_Y + ", accelerate_Z=" + accelerate_Z
				+ ", gyroscope_X=" + gyroscope_X + ", gyroscope_Y=" + gyroscope_Y + ", gyroscope_Z=" + gyroscope_Z
				+ ", hall=" + hall + "]";
	}
	
	public  void setToDefault(){
		int defaultValue = -65535;
		temperature = defaultValue;
		humidity = defaultValue;
		ir = defaultValue;
		pressure = defaultValue;
		light = defaultValue;
		noise = defaultValue;
		battery = defaultValue;
		accelerate_X = defaultValue;
		accelerate_Y = defaultValue;
		accelerate_Z = defaultValue;
		gyroscope_X = defaultValue;
		gyroscope_Y = defaultValue;
		gyroscope_Z = defaultValue;
		hall = defaultValue;
		hall_X = defaultValue;
		hall_Y = defaultValue;
		hall_Z = defaultValue;	
		
	
	}

}
