package model;

import dao.DaoUpdateParameter;

public class HeroUpdateParameter extends Hero{

	//プレイヤー入力 Hp,Mp,Atk
	public void updateParameter(){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameter();
	}

	//数字から選択
	public void updateParameterSelect(){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterSelect();
	}
	
	//Hp,Mp,Atk
	public void updateParameterAll(int id, int maxHp, int maxMp, int maxAtk){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterAll(id, maxHp, maxMp, maxAtk);
	}

	//指定変更 数値
	public void updateParameterChange(int id, String column, int size){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterChange(id, column, size);
	}

	//指定変更 文字列
	public void updateParameterChange(int id, String column, String thing){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterChange(id, column, thing);
	}

	//Name 
	public void updateParameterName(int id, String name){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterName(id, name);
	}
	
	//Status
	public void updateParameterStatus(int id,  String status){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterStatus(id, status);
	}

	//Lv
	public void updateParameterLv(int id, int lv){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterLv(id, lv);
	}

	//Exp
	public void updateParameterExp(int id, int exp){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterExp(id, exp);
	}

	//Hp
	public void updateParameterHp(int id, int hp){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterHp(id, hp);
	}

	//MaxHp
	public void updateParameterMaxHp(int id, int maxHp){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterMaxHp(id, maxHp);
	}

	//Mp
	public void updateParameterMp(int id, int mp){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterMp(id, mp);
	}

	//MaxMp
	public void updateParameterMaxMp(int id, int maxMp){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterMaxMp(id, maxMp);
	}

	//Atk
	public void updateParameterAtk(int id, int atk){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterAtk(id, atk);
	}

	//MaxAtk
	public void updateParameterMaxAtk(int id, int maxAtk){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterMaxAtk(id, maxAtk);
	}

	//Weapon
	public void updateParameterWeapon(int id, String weapon){
		DaoUpdateParameter dup = new DaoUpdateParameter();
			dup.daoUpdateParameterWeapon(id, weapon);
	}

}