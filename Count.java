package model;

import dao.DaoCount;

public class Count {
	public int count (String table) {
		
		DaoCount dao = new DaoCount();
		
		int c = dao.count(table);
		
		return c;
	}
}