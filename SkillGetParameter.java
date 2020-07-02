package model;

import dao.DaoSkill;
import java.util.*;

public class SkillGetParameter extends Skill{
	//�m�F�p
	public void checkParameter(){
	
	DaoSkill ds = new DaoSkill();
	
	ds.daoCheckParameter();
	}
	
	//�w��̈���̏����擾
	public Skill getParameter(int id){

		DaoSkill ds = new DaoSkill();

		Skill skill = ds.daoGetParameter(id);

		return skill;
	}
	
	//�S�X�L���̏����擾
	public ArrayList<Skill> getParameters(){

		DaoSkill ds = new DaoSkill();
		ArrayList<Skill> skillList = new ArrayList<>();

		skillList = ds.daoGetParameters();

		return skillList;
	}
	//�w��̐��l����擾���郁�\�b�h
	public int intParameterGet(int id, String column){
	
		DaoSkill ds = new DaoSkill();
		int parameter = 0;

		parameter = ds.intParameterGet(id, column);
		
		return parameter;
	}
	//�w��̕��������擾���郁�\�b�h
	public String stringParameterGet(int id, String column){
		
		DaoSkill ds = new DaoSkill();
		String parameter = "";

		parameter = ds.stringParameterGet(id, column);
	
		return parameter;
	}

}