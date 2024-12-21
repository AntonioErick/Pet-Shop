package Utilities;

public class Client {
	
	private String name;
	private int phone;
	private String address;
	private int id;
	
	public Client() {
	}
	
	public Client(String name, int phone, String address, int id) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "ID: "+id+
				", NAME: "+name+
				", PHONE: "+phone+
				", ADDRESS: "+address;
	}
}
