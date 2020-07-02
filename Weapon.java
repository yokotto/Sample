package model;

import java.io.Serializable;

public class Weapon implements Serializable {
	
	private String name;
	private int id, strength;

	public Weapon() { }
	
	public Weapon(int id, String name, int strength){
		this.id = id;
		this.name = name;
		this.strength = strength;
	}
	public int getId() { return id; }
	public String getName() { return name; }
	public int getStrength() { return strength; }
	
	public void setId(int id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setStrength(int strength) { this.strength = strength; }

}
		 