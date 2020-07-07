package dao;

import java.sql.*;
import java.util.*;

public class DaoInsertParameter extends DAO{

	DAO dao = new DAO();

	public void daoInsertParameter(){
		try (Connection conn = DriverManager.getConnection(
		dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	
		
			Scanner sc = new Scanner(System.in);
			//IDはSQLにて自動で取得	
			DAO dao = new DAO();
			String id = Integer.toString(dao.count("parameter"));
			
			System.out.print("追加するキャラクターの名前を入力してください:");
			String name = sc.nextLine();
			System.out.print("体力を入力してください:");
			String hp = sc.nextLine();
			System.out.print("魔力を入力してください:");
			String mp = sc.nextLine();
			System.out.print("攻撃力を入力してください:");
			String atk = sc.nextLine();
			sc.close();

			//SQLの用意 Insert文の実行
			String insertSql_C =
			"insert into character (id, name) values (?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(insertSql_C);
			pStmt.setString(1, id);
			pStmt.setString(2, name);
			pStmt.executeUpdate();
			
			String insertSql_P = 
			"insert into parameter (id, name, maxhp, hp, maxmp, mp, maxatk, atk) values (?, ?, ?, ?, ?, ?, ?, ?)";
			pStmt = conn.prepareStatement(insertSql_P);
			pStmt.setString(1, id);
			pStmt.setString(2, name);
			pStmt.setString(3, hp);
			pStmt.setString(4, hp);
			pStmt.setString(5, mp);
			pStmt.setString(6, mp);
			pStmt.setString(7, atk);
			pStmt.setString(8, atk);
			pStmt.executeUpdate();

		} catch(SQLException e){
			e.printStackTrace();
		}
	}
}
		