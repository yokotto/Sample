package model;

import dao.DaoGetParameter;
import java.util.*;

public class HeroGetParameter extends Hero{
	//確認用
	public void checkParameter(){

		DaoGetParameter dgp = new DaoGetParameter();
		
		dgp.daoCheckParameter();
	}

	//指定の数値を取得	
	public int intParameterGet(int id, String column){
	
		DaoGetParameter dgp = new DaoGetParameter();
		
		int parameter = dgp.intParameterGet(id, column);
	
		return parameter;
	}

	//指定の文字列を取得
	public String stringParameterGet(int id, String column){
	
		DaoGetParameter dgp = new DaoGetParameter();

		String parameter = dgp.stringParameterGet(id, column);
	
		return parameter;
	}

	//指定の一人分の情報を取得
	public Hero getParameter(int id){

		DaoGetParameter dgp = new DaoGetParameter();
		
		Hero hero = dgp.daoGetParameter(id);		

		return hero;
	}
	
	//全員分の情報を取得
	public ArrayList<Hero> getParameters(){
		
		ArrayList<Hero> heroList = new ArrayList<>();
		DaoGetParameter dgp = new DaoGetParameter();
		
		heroList = dgp.daoGetParameters();

		return heroList;
		}
}