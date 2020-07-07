package model;

import dao.DaoMonster;
import java.util.*;

public class MonsterGetParameter extends Monster{
	//確認用
	public void checkParameter(){
	
	DaoMonster dm = new DaoMonster();
	
	dm.daoCheckParameter();
	}
	
	//指定の一体分の情報を取得
	public Monster getParameter(int id){

		DaoMonster dm = new DaoMonster();

		Monster monster = dm.daoGetParameter(id);

		return monster;
	}
	
	//全員分の情報を取得
	public ArrayList<Monster> getParameters(){

		DaoMonster dm = new DaoMonster();
		ArrayList<Monster> monsterList = new ArrayList<>();

		monsterList = dm.daoGetParameters();

		return monsterList;
	}
	//指定の数値を一つ取得するメソッド
	public int intParameterGet(int id, String column){
	
		DaoMonster dm = new DaoMonster();
		int parameter = 0;

		parameter = dm.intParameterGet(id, column);
		
		return parameter;
	}
	//指定の文字列を一つ取得するメソッド
	public String stringParameterGet(int id, String column){
		
		DaoMonster dm = new DaoMonster();
		String parameter = "";

		parameter = dm.stringParameterGet(id, column);
	
		return parameter;
	}

}