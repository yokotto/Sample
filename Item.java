package model;

import java.io.Serializable;

public class Item implements Serializable{
	
	private String name, effect, statusEffect;
	private int id, effectSize;

	public Item() { }
	
	public Item(int id, String name, String effect, int effectSize, String statusEffect){

		this.id = id;
		this.name = name;
		this.effect = effect;
		this.effectSize = effectSize;
		this.statusEffect = statusEffect;
	}

	public int getId() { return id; }
	public String getName() { return name; }
	public String getEffect() { return effect; }
	public int getEffectSize() { return effectSize; }
	public String getStatusEffect() { return statusEffect; }

	public void setId(int id) { this.id = id; }
	public void setName(String name) { this.name = name; }	
	public void setEffect(String effect) { this.effect = effect; }
	public void setEffectSize(int effectSize) { this.effectSize = effectSize; }
	public void setStatusEffect(String statusEffect) { this.statusEffect = statusEffect; }
	
}
		
	
		
	