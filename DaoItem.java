package dao;

import java.sql.*;
import java.util.*;
import model.Item;

public class DaoItem extends DAO{
	
	public void show(ResultSet rs){ //select確認用
		try {
			rs.first();
			while(rs.next()){
				System.out.println("番号:" + rs.getInt("id") + " 名前:" + rs.getString("name")
				+ "\n効果:" + rs.getString("effect") + " 効果量:" + rs.getInt("effectsize")
				+ " ステータス回復:" + rs.getString("statuseffect") + "\n");
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
			String sql = "select * from item";
			PreparedStatement pStmt = conn.prepareStatement(sql);
	
			//SELECT文を実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();
			
			//結果表に格納されたレコードの内容を表示
			this.show(rs);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//アイテム一種類のパラメータを取得するメソッド
	public Item daoGetParameter(int id){
	
	Item item = new Item();
	DAO dao = new DAO();
	int countId = dao.count("item");
	
		//idが範囲内であれば実行
		if(id < countId){
		try(Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {
			
			//SELECT文を準備
			String sql =
			"select * from item where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
		
			//SELECT文の?に値を設定しSQLを完成させる
			pStmt.setInt(1, id);
			
			//SELECT文を実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容で代入
			rs.next();

			if(rs.getString("name") != null){
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setEffect(rs.getString("effect"));
			item.setEffectSize(rs.getInt("effectsize"));
			item.setStatusEffect(rs.getString("statuseffect"));
			}
	
		} catch (SQLException e) {
			System.out.println("SQLエラーです。");
			e.printStackTrace();
		}
		return item;
		
		} else {
		return null;
		}
	}

	//アイテム全種類をリストに格納して返すメソッド
	public ArrayList<Item> daoGetParameters(){
		
		Item item = new Item();
		DAO dao = new DAO();
		ArrayList<Item> itemList = new ArrayList<>();
		
		
		//現在の種類数を取得
		int countId = dao.count("item");
		
		//GetParameterクラスで種類分のITEMを取得
		
		for(int i=0; i<countId; i++){
			item = this.daoGetParameter(i);
			itemList.add(item);	
		}	
		
		return itemList;
	}

	//指定の値を1つ取得するメソッド
	public int intParameterGet(int id, String column){
	
		DAO dao = new DAO();
		int parameter = 0;

		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	
			
			String selectSql = 
			"select " + column + " from item where id = " + id;
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
			"select " + column + " from item where id = " + id;
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
			
		