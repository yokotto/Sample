package model;

import java.io.Serializable;
import java.util.*;

public class Hero implements Serializable{

	//�e��p�����[�^�p�̕ϐ��@�y�� �L�����̏�������A�A�C�e���A�X�L��������Map
	private String name, status, weapon;
	private int id, lv, maxHp, hp, maxMp, mp, maxAtk, atk, exp;
	private Map<String, Integer> skillMap = new LinkedHashMap<>();

	public Hero(){ }

	//�V�K�L�����쐬�p�R���X�g���N�^
	public Hero(int id, String name, int maxHp, int maxMp, int maxAtk){
		this.id = id;
		this.name = name;
		this.maxHp = maxHp;
		this.maxMp = maxMp;
		this.maxAtk = maxAtk;

		this.hp = maxHp;
		this.mp = maxMp;
		this.atk = maxAtk;
		this.status = "����";
		this.exp = 0;
		this.lv = 1;
		this.weapon = "�Ȃ�";
		this.skillMap.put("�W��",0); //mp�̏����
	}
	
	//�ȉ�get�p���\�b�h
	public int getId() { return id; }
	public int getlv() { return lv; }
	public int getExp() { return exp; }
	public String getName() { return name; }
	public String getStatus() { return status; }
	public int getHp() { return hp; }
	public int getMaxHp() { return maxHp; }
	public int getMp() { return mp; }
	public int getMaxMp() { return maxMp; }
	public int getAtk() { return atk; }
	public int getMaxAtk() { return maxAtk; }
	public String getWeapon() { return weapon; }
	public Map getSkillMap() { return skillMap; }
	
	//�ȉ�set�p���\�b�h
	public void setId(int id) { this.id = id; }
	public void setLv(int lv) { this.lv = lv; }
	public void setExp(int exp) { this.exp = exp; }
	public void setName(String name) { this.name = name; }
	public void setStatus(String status) {this.status = status; }
	public void setHp(int hp) { this.hp = hp; }
	public void setMaxHp(int maxHp) { this.maxHp = maxHp; }
	public void setMp(int mp) { this.mp = mp; }
	public void setMaxMp(int maxMp) { this.maxMp = maxMp; }
	public void setAtk(int atk) { this.atk = atk; }
	public void setMaxAtk(int maxAtk) { this.maxAtk = maxAtk; }
	public void setWeapon(String weapon) { this.weapon = weapon; }
	public void setSkillMap(Map<String, Integer> skillMap) { this.skillMap = skillMap; }
	
}
	