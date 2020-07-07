package dao;

import java.util.*;
import java.io.*;
import java.sql.*;
import model.Hero;

public class DaoGetParameter extends DAO{

	DAO dao = new DAO();
	int countId = 0;

	public void show(ResultSet rs){ //select後確認用
	try {
			rs.first();
			while(rs.next()){
				System.out.println("番号:" + rs.getInt("id") + " 名前:" + rs.getString("name")
				+ " LV:" + rs.getInt("lv") + " 経験値:" + rs.getInt("exp") + " 状態:" + rs.getString("status"));  
				System.out.println(" 最大HP:" + rs.getInt("maxhp") + " HP:" + rs.getInt("hp")
				+ "最大MP:" + rs.getInt("maxmp") + " MP:" + rs.getInt("mp")
			    + " 最大ATK:" + rs.getInt("maxatk") + " ATK:" + rs.getInt("atk"));
				System.out.println("装備武器:" + rs.getString("weapon") + "\n");
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public void daoCheckParameter(){

		//データベースに接続
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {

			//SELECT文を準備
			String sql = "select * from parameter";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT文を実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();
			
			//結果表に格納されたレコードの内容を表示
			this.show(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//キャラ一人のパラメータを取得するメソッド
	public Hero daoGetParameter(int id){

		Hero hero = new Hero();
		boolean bl = true;
		int countId = dao.count("parameter");
		
		//idが範囲内であれば実行
		if(id < countId){
		//データベースに接続
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {
	
			//SELECT文を準備
			String sql = 
			"select * from parameter where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT文の?に値を設定しSQLを完成させる
			pStmt.setInt(1, id);
		
			//SELECT文を実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();
			
			//結果表に格納されたレコードの内容で代入
			rs.next();

			if(rs.getString("name") != null){
			hero.setId(rs.getInt("id"));
			hero.setName(rs.getString("name"));
			hero.setLv(rs.getInt("lv"));
			hero.setExp(rs.getInt("exp"));
			hero.setStatus(rs.getString("status"));
			hero.setMaxHp(rs.getInt("maxhp"));
			hero.setHp(rs.getInt("hp"));
			hero.setMaxMp(rs.getInt("maxmp"));
			hero.setMp(rs.getInt("mp"));
			hero.setMaxAtk(rs.getInt("maxatk"));
			hero.setAtk(rs.getInt("atk"));
			hero.setWeapon(rs.getString("weapon"));
			}

		} catch (SQLException e) {
			System.out.println("SQLエラーです。");
			e.printStackTrace();
		}
		return hero;

		} else {
		return null;
		}
	}

	//ヒーロー全員分をリストに格納して返すメソッド
	public ArrayList<Hero> daoGetParameters(){
		
		Hero hero = new Hero();
		ArrayList<Hero> heroList = new ArrayList<>();
		
		//現在の人数を取得
		int countId = dao.count("parameter");
		
		//GetParameterクラスで人数分のHeroを取得
		
		for(int i=0; i<countId; i++){
			hero = this.daoGetParameter(i);
			heroList.add(hero);	
		}	
		
		return heroList;
	}

	//指定の値を1つ取得するメソッド
	public int intParameterGet(int id, String column){
	
		int parameter = 0;

		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	
			
			String selectSql = 
			"select " + column + " from parameter where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(selectSql);
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			parameter = rs.getInt(column);

		} catch(SQLException e) {
			e.printStackTrace();
		}
	return parameter;
	}
	//指定の文字列を一つ取得するメソッド
	public String stringParameterGet(int id, String column){
	
		String parameter = "";

		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	
			
			String selectSql = 
			"select " + column + " from parameter where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(selectSql);
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			parameter = rs.getString(column);

		} catch(SQLException e) {
			e.printStackTrace();
		}
	return parameter;
	}

}