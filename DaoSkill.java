package dao;

import java.sql.*;
import java.util.*;
import model.Skill;

public class DaoSkill extends DAO{
	
	public void show(ResultSet rs){ //select確認用
		try {
			rs.first();
			while(rs.next()){
				System.out.println("番号:" + rs.getInt("id") + " 名前:" + rs.getString("name")
				+ "\n効果対象:" + rs.getString("target") + " 効果:" + rs.getString("effect")
				+ " 効果量:" + rs.getInt("effectSize") + " MP:" + rs.getInt("mp") + "\n"); 
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
			String sql = "select * from skill";
			PreparedStatement pStmt = conn.prepareStatement(sql);
	
			//SELECT文を実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();
			
			//結果表に格納されたレコードの内容を表示
			this.show(rs);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//スキル一種類の詳細を取得するメソッド
	public Skill daoGetParameter(int id){
	
	Skill skill = new Skill();
	DAO dao = new DAO();
	int countId = dao.count("skill");
	
		//idが範囲内であれば実行
		if(id < countId){
		try(Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {
			
			//SELECT文を準備
			String sql =
			"select * from skill where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
		
			//SELECT文の?に値を設定しSQLを完成させる
			pStmt.setInt(1, id);
			
			//SELECT文を実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容で代入
			rs.next();

			if(rs.getString("name") != null){
			skill.setId(rs.getInt("id"));
			skill.setName(rs.getString("name"));
			skill.setTarget(rs.getString("target"));
			skill.setEffect(rs.getString("effect"));
			skill.setEffectSize(rs.getInt("effectsize"));
			skill.setMp(rs.getInt("mp"));		
			}
	
		} catch (SQLException e) {
			System.out.println("SQLエラーです。");
			e.printStackTrace();
		}
		return skill;
		
		} else {
		return null;
		}
	}

	//全スキルをリストに格納して返すメソッド
	public ArrayList<Skill> daoGetParameters(){
		
		Skill skill = new Skill();
		DAO dao = new DAO();
		ArrayList<Skill> skillList = new ArrayList<>();
		
		
		//現在の種類数を取得
		int countId = dao.count("skill");
		
		//GetParameterクラスで種類文のSKILLを取得
		
		for(int i=0; i<countId; i++){
			skill = this.daoGetParameter(i);
			skillList.add(skill);	
		}	
		
		return skillList;
	}

	//指定の値を1つ取得するメソッド
	public int intParameterGet(int id, String column){
	
		DAO dao = new DAO();
		int parameter = 0;

		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	
			
			String selectSql = 
			"select " + column + " from skill where id = " + id;
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
			"select " + column + " from skill where id = " + id;
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
			
		