package model;

import dao.DaoWeapon;
import java.util.*;

public class WeaponGetParameter extends Weapon{
	//確認用
	public void checkParameter(){
	
	DaoWeapon dw = new DaoWeapon();
	
	dw.daoCheckParameter();
	}
	
	//指定の一本分の情報を取得
	public Weapon getParameter(int id){

		DaoWeapon dw = new DaoWeapon();

		Weapon weapon = dw.daoGetParameter(id);

		return weapon;
	}
	
	//全武器の情報を取得
	public ArrayList<Weapon> getParameters(){

		DaoWeapon dw = new DaoWeapon();
		ArrayList<Weapon> weaponList = new ArrayList<>();

		weaponList = dw.daoGetParameters();

		return weaponList;
	}
	//指定の数値を一つ取得するメソッド
	public int intParameterGet(int id, String column){
	
		DaoWeapon dw = new DaoWeapon();
		int parameter = 0;

		parameter = dw.intParameterGet(id, column);
		
		return parameter;
	}
	//指定の文字列を一つ取得するメソッド
	public String stringParameterGet(int id, String column){
		
		DaoWeapon dw = new DaoWeapon();
		String parameter = "";

		parameter = dw.stringParameterGet(id, column);
	
		return parameter;
	}

}