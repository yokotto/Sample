package model;

import dao.DaoItem;
import java.util.*;

public class ItemGetParameter extends Item{
	//確認用
	public void checkParameter(){
	
	DaoItem di = new DaoItem();
	
	di.daoCheckParameter();
	}
	
	//指定の一種類の情報を取得
	public Item getParameter(int id){

		DaoItem di = new DaoItem();

		Item item = di.daoGetParameter(id);

		return item;
	}
	
	//全種類の情報を取得
	public ArrayList<Item> getParameters(){

		DaoItem di = new DaoItem();
		ArrayList<Item> itemList = new ArrayList<>();

		itemList = di.daoGetParameters();

		return itemList;
	}
	//指定の数値を一つ取得するメソッド
	public int intParameterGet(int id, String column){
	
		DaoItem di = new DaoItem();
		int parameter = 0;

		parameter = di.intParameterGet(id, column);
		
		return parameter;
	}
	//指定の文字列を一つ取得するメソッド
	public String stringParameterGet(int id, String column){
		
		DaoItem di = new DaoItem();
		String parameter = "";

		parameter = di.stringParameterGet(id, column);
	
		return parameter;
	}

}