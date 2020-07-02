package dao;

import java.sql.*;
import java.util.*;

public class DaoInsertParameter extends DAO{

	DAO dao = new DAO();

	public void daoInsertParameter(){
		try (Connection conn = DriverManager.getConnection(
		dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	
		
			Scanner sc = new Scanner(System.in);
			//ID��SQL�ɂĎ����Ŏ擾	
			DAO dao = new DAO();
			String id = Integer.toString(dao.count("parameter"));
			
			System.out.print("�ǉ�����L�����N�^�[�̖��O����͂��Ă�������:");
			String name = sc.nextLine();
			System.out.print("�̗͂���͂��Ă�������:");
			String hp = sc.nextLine();
			System.out.print("���͂���͂��Ă�������:");
			String mp = sc.nextLine();
			System.out.print("�U���͂���͂��Ă�������:");
			String atk = sc.nextLine();
			sc.close();

			//SQL�̗p�� Insert���̎��s
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
		