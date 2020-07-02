package model;

import dao.DaoMonster;
import java.util.*;

public class MonsterGetParameter extends Monster{
	//�m�F�p
	public void checkParameter(){
	
	DaoMonster dm = new DaoMonster();
	
	dm.daoCheckParameter();
	}
	
	//�w��̈�̕��̏����擾
	public Monster getParameter(int id){

		DaoMonster dm = new DaoMonster();

		Monster monster = dm.daoGetParameter(id);

		return monster;
	}
	
	//�S�����̏����擾
	public ArrayList<Monster> getParameters(){

		DaoMonster dm = new DaoMonster();
		ArrayList<Monster> monsterList = new ArrayList<>();

		monsterList = dm.daoGetParameters();

		return monsterList;
	}
	//�w��̐��l����擾���郁�\�b�h
	public int intParameterGet(int id, String column){
	
		DaoMonster dm = new DaoMonster();
		int parameter = 0;

		parameter = dm.intParameterGet(id, column);
		
		return parameter;
	}
	//�w��̕��������擾���郁�\�b�h
	public String stringParameterGet(int id, String column){
		
		DaoMonster dm = new DaoMonster();
		String parameter = "";

		parameter = dm.stringParameterGet(id, column);
	
		return parameter;
	}

}