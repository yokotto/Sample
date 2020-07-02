package dao;

import java.util.*;
import java.io.*;
import java.sql.*;
import model.Hero;

public class DaoGetParameter extends DAO{

	DAO dao = new DAO();
	int countId = 0;

	public void show(ResultSet rs){ //select��m�F�p
	try {
			rs.first();
			while(rs.next()){
				System.out.println("�ԍ�:" + rs.getInt("id") + " ���O:" + rs.getString("name")
				+ " LV:" + rs.getInt("lv") + " �o���l:" + rs.getInt("exp") + " ���:" + rs.getString("status"));  
				System.out.println(" �ő�HP:" + rs.getInt("maxhp") + " HP:" + rs.getInt("hp")
				+ "�ő�MP:" + rs.getInt("maxmp") + " MP:" + rs.getInt("mp")
			    + " �ő�ATK:" + rs.getInt("maxatk") + " ATK:" + rs.getInt("atk"));
				System.out.println("��������:" + rs.getString("weapon") + "\n");
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public void daoCheckParameter(){

		//�f�[�^�x�[�X�ɐڑ�
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {

			//SELECT��������
			String sql = "select * from parameter";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT�������s���A���ʕ\(ResultSet)���擾
			ResultSet rs = pStmt.executeQuery();
			
			//���ʕ\�Ɋi�[���ꂽ���R�[�h�̓��e��\��
			this.show(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//�L������l�̃p�����[�^���擾���郁�\�b�h
	public Hero daoGetParameter(int id){

		Hero hero = new Hero();
		boolean bl = true;
		int countId = dao.count("parameter");
		
		//id���͈͓��ł���Ύ��s
		if(id < countId){
		//�f�[�^�x�[�X�ɐڑ�
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {
	
			//SELECT��������
			String sql = 
			"select * from parameter where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT����?�ɒl��ݒ肵SQL������������
			pStmt.setInt(1, id);
		
			//SELECT�������s���A���ʕ\(ResultSet)���擾
			ResultSet rs = pStmt.executeQuery();
			
			//���ʕ\�Ɋi�[���ꂽ���R�[�h�̓��e�ő��
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
			System.out.println("SQL�G���[�ł��B");
			e.printStackTrace();
		}
		return hero;

		} else {
		return null;
		}
	}

	//�q�[���[�S���������X�g�Ɋi�[���ĕԂ����\�b�h
	public ArrayList<Hero> daoGetParameters(){
		
		Hero hero = new Hero();
		ArrayList<Hero> heroList = new ArrayList<>();
		
		//���݂̐l�����擾
		int countId = dao.count("parameter");
		
		//GetParameter�N���X�Ől������Hero���擾
		
		for(int i=0; i<countId; i++){
			hero = this.daoGetParameter(i);
			heroList.add(hero);	
		}	
		
		return heroList;
	}

	//�w��̒l��1�擾���郁�\�b�h
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
	//�w��̕��������擾���郁�\�b�h
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