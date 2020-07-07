package dao;

import java.sql.*;
import java.util.*;
import model.Monster;

public class DaoMonster extends DAO{

	public void show(ResultSet rs){ //select後確認用
		try {
			rs.first();
			while(rs.next()){
				System.out.println("番号:" + rs.getInt("id") + " 名前:" + rs.getString("name")
				+ " 状態:" + rs.getString("status"));  
				System.out.println(" HP:" + rs.getInt("hp") + " MP:" + rs.getInt("mp")
			    + " ATK:" + rs.getInt("atk") + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void daoCheckParameter(){

	DAO dao = new DAO();	

		//データベースに接続
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {

			//SELECT文を準備
			String sql = "select * from monster";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT文を実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();
			
			//結果表に格納されたレコードの内容を表示
			this.show(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//モンスター一体のパラメータを取得するメソッド
	public Monster daoGetParameter(int id){
		
	Monster monster = new Monster();
	DAO dao = new DAO();
	int countId = dao.count("monster");

		//idが範囲内であれば実行
		if(id < countId){
		//データベースに接続
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {
	
			//SELECT文を準備
			String sql = 
			"select * from monster where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT文の?に値を設定しSQLを完成させる
			pStmt.setInt(1, id);
		
			//SELECT文を実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();
			
			//結果表に格納されたレコードの内容で代入
			rs.next();

			if(rs.getString("name") != null){
			monster.setId(rs.getInt("id"));
			monster.setName(rs.getString("name"));
			monster.setStatus(rs.getString("status"));
			monster.setHp(rs.getInt("hp"));
			monster.setMp(rs.getInt("mp"));
			monster.setAtk(rs.getInt("atk"));
			}

		} catch (SQLException e) {
			System.out.println("SQLエラーです。");
			e.printStackTrace();
		}
		return monster;

		} else {
		return null;
		}
	}

	//モンスター全員分をリストに格納して返すメソッド
	public ArrayList<Monster> daoGetParameters(){
		
		Monster monster = new Monster();
		DAO dao = new DAO();
		ArrayList<Monster> monsterList = new ArrayList<>();
		
		
		//現在の人数を取得
		int countId = dao.count("monster");
		
		//GetParameterクラスで全てのMONSTERを取得
		
		for(int i=0; i<countId; i++){
			monster = this.daoGetParameter(i);
			monsterList.add(monster);	
		}	
		
		return monsterList;
	}

	//指定の値を1つ取得するメソッド
	public int intParameterGet(int id, String column){
	
		DAO dao = new DAO();
		int parameter = 0;

		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	
			
			String selectSql = 
			"select " + column + " from monster where id = " + id;
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
	
		DAO dao = new DAO();
		String parameter = "";

		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	
			
			String selectSql = 
			"select " + column + " from monster where id = " + id;
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