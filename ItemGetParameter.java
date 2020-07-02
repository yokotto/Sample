package model;

import dao.DaoItem;
import java.util.*;

public class ItemGetParameter extends Item{
	//�m�F�p
	public void checkParameter(){
	
	DaoItem di = new DaoItem();
	
	di.daoCheckParameter();
	}
	
	//�w��̈��ނ̏����擾
	public Item getParameter(int id){

		DaoItem di = new DaoItem();

		Item item = di.daoGetParameter(id);

		return item;
	}
	
	//�S��ނ̏����擾
	public ArrayList<Item> getParameters(){

		DaoItem di = new DaoItem();
		ArrayList<Item> itemList = new ArrayList<>();

		itemList = di.daoGetParameters();

		return itemList;
	}
	//�w��̐��l����擾���郁�\�b�h
	public int intParameterGet(int id, String column){
	
		DaoItem di = new DaoItem();
		int parameter = 0;

		parameter = di.intParameterGet(id, column);
		
		return parameter;
	}
	//�w��̕��������擾���郁�\�b�h
	public String stringParameterGet(int id, String column){
		
		DaoItem di = new DaoItem();
		String parameter = "";

		parameter = di.stringParameterGet(id, column);
	
		return parameter;
	}

}