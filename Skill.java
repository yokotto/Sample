package model;

import java.io.Serializable;

public class Skill implements Serializable {
	private String name, target, effect;
	private int id, effectSize, mp;

	public Skill() { }

	public Skill(int id, String name, String target, String effect, int effectSize, int mp){
		
		this.id = id;
		this.name = name;
		this.target = target;
		this.effect = effect;
		this.effectSize = effectSize;
		this.mp = mp;	
	}

	public int getId() { return id; }
	public String getName() { return name; }
	public String getTarget() { return target; }
	public String getEffect() { return effect; }
	public int getEffectSize() { return effectSize; }
	public int getMp() { return mp; }
	
	public void setId(int id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setTarget(String target) { this.target = target; }
	public void setEffect(String effect) { this.effect = effect; }
	public void setEffectSize(int effectSize) { this.effectSize = effectSize; }
	public void setMp(int mp) { this.mp = mp; }
}
	