package model;

import java.io.Serializable;
import java.util.*;

public class Monster implements Serializable {

	private String name, status;
	private int id, hp, mp, atk;
	private Map<String, Integer> skillMap = new LinkedHashMap<>();

	public Monster(){ }

	public Monster(int id, String name, int hp, int mp, int atk){
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.mp = mp;
		this.atk = atk;
		this.status = "•½í";
		this.skillMap.put("W’†",0);
	}
	
	public int getId() { return id; }
	public String getName() { return name; }
	public String getStatus() { return status; }
	public int getHp() { return hp; }
	public int getMp() { return mp; }
	public int getAtk() { return atk; }
	public Map getSkillMap() { return skillMap; }
	
	public void setId(int id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setStatus(String status) {this.status = status; }
	public void setHp(int hp) { this.hp = hp; }
	public void setMp(int mp) { this.mp = mp; }
	public void setAtk(int atk) { this.atk = atk; }
	public void setSkillMap(Map<String, Integer> skillMap) { this.skillMap = skillMap; }
	
}
	