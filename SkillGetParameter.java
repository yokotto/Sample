package model;

import dao.DaoSkill;
import java.util.*;

public class SkillGetParameter extends Skill{
	//確認用
	public void checkParameter(){
	
	DaoSkill ds = new DaoSkill();
	
	ds.daoCheckParameter();
	}
	
	//指定の一つ分の情報を取得
	public Skill getParameter(int id){

		DaoSkill ds = new DaoSkill();

		Skill skill = ds.daoGetParameter(id);

		return skill;
	}
	
	//全スキルの情報を取得
	public ArrayList<Skill> getParameters(){

		DaoSkill ds = new DaoSkill();
		ArrayList<Skill> skillList = new ArrayList<>();

		skillList = ds.daoGetParameters();

		return skillList;
	}
	//指定の数値を一つ取得するメソッド
	public int intParameterGet(int id, String column){
	
		DaoSkill ds = new DaoSkill();
		int parameter = 0;

		parameter = ds.intParameterGet(id, column);
		
		return parameter;
	}
	//指定の文字列を一つ取得するメソッド
	public String stringParameterGet(int id, String column){
		
		DaoSkill ds = new DaoSkill();
		String parameter = "";

		parameter = ds.stringParameterGet(id, column);
	
		return parameter;
	}

}