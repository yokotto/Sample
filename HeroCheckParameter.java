package model;

import dao.DaoGetParameter;

public class HeroCheckParameter extends Hero{
	public void heroCheckParameter(){

		DaoGetParameter dgp = new DaoGetParameter();
		
		dgp.daoCheckParameter();		

	}
}