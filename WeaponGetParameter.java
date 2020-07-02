package model;

import dao.DaoWeapon;
import java.util.*;

public class WeaponGetParameter extends Weapon{
	//�m�F�p
	public void checkParameter(){
	
	DaoWeapon dw = new DaoWeapon();
	
	dw.daoCheckParameter();
	}
	
	//�w��̈�{���̏����擾
	public Weapon getParameter(int id){

		DaoWeapon dw = new DaoWeapon();

		Weapon weapon = dw.daoGetParameter(id);

		return weapon;
	}
	
	//�S����̏����擾
	public ArrayList<Weapon> getParameters(){

		DaoWeapon dw = new DaoWeapon();
		ArrayList<Weapon> weaponList = new ArrayList<>();

		weaponList = dw.daoGetParameters();

		return weaponList;
	}
	//�w��̐��l����擾���郁�\�b�h
	public int intParameterGet(int id, String column){
	
		DaoWeapon dw = new DaoWeapon();
		int parameter = 0;

		parameter = dw.intParameterGet(id, column);
		
		return parameter;
	}
	//�w��̕��������擾���郁�\�b�h
	public String stringParameterGet(int id, String column){
		
		DaoWeapon dw = new DaoWeapon();
		String parameter = "";

		parameter = dw.stringParameterGet(id, column);
	
		return parameter;
	}

}