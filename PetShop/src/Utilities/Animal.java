package Utilities;

public class Animal {
	
	private String name;
	private String specie;
	private String health;
	
	public Animal() {
	}
	
	public Animal(String name, String specie, String health) {
		this.name = name;
		this.specie = specie;
		this.health = health;
	}
	
	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}
	
	public String toString() {
		return "NAME: "+name+
				", SPECIE: "+specie+
				", HEALTH: "+health;
	}
}
