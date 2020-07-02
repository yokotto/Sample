package model;

import dao.DaoGetParameter;
import java.util.*;

public class HeroGetParameter extends Hero{
	//�m�F�p
	public void checkParameter(){

		DaoGetParameter dgp = new DaoGetParameter();
		
		dgp.daoCheckParameter();
	}

	//�w��̐��l���擾	
	public int intParameterGet(int id, String column){
	
		DaoGetParameter dgp = new DaoGetParameter();
		
		int parameter = dgp.intParameterGet(id, column);
	
		return parameter;
	}

	//�w��̕�������擾
	public String stringParameterGet(int id, String column){
	
		DaoGetParameter dgp = new DaoGetParameter();

		String parameter = dgp.stringParameterGet(id, column);
	
		return parameter;
	}

	//�w��̈�l���̏����擾
	public Hero getParameter(int id){

		DaoGetParameter dgp = new DaoGetParameter();
		
		Hero hero = dgp.daoGetParameter(id);		

		return hero;
	}
	
	//�S�����̏����擾
	public ArrayList<Hero> getParameters(){
		
		ArrayList<Hero> heroList = new ArrayList<>();
		DaoGetParameter dgp = new DaoGetParameter();
		
		heroList = dgp.daoGetParameters();

		return heroList;
		}
}