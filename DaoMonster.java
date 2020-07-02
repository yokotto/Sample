package dao;

import java.sql.*;
import java.util.*;
import model.Monster;

public class DaoMonster extends DAO{

	public void show(ResultSet rs){ //select��m�F�p
		try {
			rs.first();
			while(rs.next()){
				System.out.println("�ԍ�:" + rs.getInt("id") + " ���O:" + rs.getString("name")
				+ " ���:" + rs.getString("status"));  
				System.out.println(" HP:" + rs.getInt("hp") + " MP:" + rs.getInt("mp")
			    + " ATK:" + rs.getInt("atk") + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void daoCheckParameter(){

	DAO dao = new DAO();	

		//�f�[�^�x�[�X�ɐڑ�
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {

			//SELECT��������
			String sql = "select * from monster";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT�������s���A���ʕ\(ResultSet)���擾
			ResultSet rs = pStmt.executeQuery();
			
			//���ʕ\�Ɋi�[���ꂽ���R�[�h�̓��e��\��
			this.show(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//�����X�^�[��̂̃p�����[�^���擾���郁�\�b�h
	public Monster daoGetParameter(int id){
		
	Monster monster = new Monster();
	DAO dao = new DAO();
	int countId = dao.count("monster");

		//id���͈͓��ł���Ύ��s
		if(id < countId){
		//�f�[�^�x�[�X�ɐڑ�
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {
	
			//SELECT��������
			String sql = 
			"select * from monster where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT����?�ɒl��ݒ肵SQL������������
			pStmt.setInt(1, id);
		
			//SELECT�������s���A���ʕ\(ResultSet)���擾
			ResultSet rs = pStmt.executeQuery();
			
			//���ʕ\�Ɋi�[���ꂽ���R�[�h�̓��e�ő��
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
			System.out.println("SQL�G���[�ł��B");
			e.printStackTrace();
		}
		return monster;

		} else {
		return null;
		}
	}

	//�����X�^�[�S���������X�g�Ɋi�[���ĕԂ����\�b�h
	public ArrayList<Monster> daoGetParameters(){
		
		Monster monster = new Monster();
		DAO dao = new DAO();
		ArrayList<Monster> monsterList = new ArrayList<>();
		
		
		//���݂̐l�����擾
		int countId = dao.count("monster");
		
		//GetParameter�N���X�őS�Ă�MONSTER���擾
		
		for(int i=0; i<countId; i++){
			monster = this.daoGetParameter(i);
			monsterList.add(monster);	
		}	
		
		return monsterList;
	}

	//�w��̒l��1�擾���郁�\�b�h
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
	//�w��̕��������擾���郁�\�b�h
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