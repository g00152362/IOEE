package com.dot.pojo;


public class msgCarport {
	private int  id;
	
	private int status;
	
	private String userMac;
	
    private String username;

    private String carPlate;

    private String email;

    private String telephone;

    private String address;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserMac() {
		return userMac;
	}

	public void setUserMac(String userMac) {
		this.userMac = userMac;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCarPlate() {
		return carPlate;
	}

	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "msgCarport [id=" + id + ", status=" + status + ", userMac=" + userMac + ", username=" + username
				+ ", carPlate=" + carPlate + ", email=" + email + ", telephone=" + telephone + ", address=" + address
				+ "]";
	}



}
