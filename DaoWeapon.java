package dao;

import java.sql.*;
import java.util.*;
import model.Weapon;

public class DaoWeapon extends DAO{
	
	public void show(ResultSet rs){ //select�m�F�p
		try {
			rs.first();
			while(rs.next()){
				System.out.println("�ԍ�:" + rs.getInt("id") + " ���O:" + rs.getString("name")
				+ " �U����:" + rs.getInt("strength") + "\n");
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
			String sql = "select * from weapon";
			PreparedStatement pStmt = conn.prepareStatement(sql);
	
			//SELECT�������s���A���ʕ\(ResultSet)���擾
			ResultSet rs = pStmt.executeQuery();
			
			//���ʕ\�Ɋi�[���ꂽ���R�[�h�̓��e��\��
			this.show(rs);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�����{�̃p�����[�^���擾���郁�\�b�h
	public Weapon daoGetParameter(int id){
	
	Weapon weapon = new Weapon();
	DAO dao = new DAO();
	int countId = dao.count("weapon");
	
		//id���͈͓��ł���Ύ��s
		if(id < countId){
		try(Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {
			
			//SELECT��������
			String sql =
			"select * from weapon where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
		
			//SELECT����?�ɒl��ݒ肵SQL������������
			pStmt.setInt(1, id);
			
			//SELECT�������s���A���ʕ\(ResultSet)���擾
			ResultSet rs = pStmt.executeQuery();

			//���ʕ\�Ɋi�[���ꂽ���R�[�h�̓��e�ő��
			rs.next();

			if(rs.getString("name") != null){
			weapon.setId(rs.getInt("id"));
			weapon.setName(rs.getString("name"));
			weapon.setStrength(rs.getInt("strength"));
			}
	
		} catch (SQLException e) {
			System.out.println("SQL�G���[�ł��B");
			e.printStackTrace();
		}
		return weapon;
		
		} else {
		return null;
		}
	}

	//�S��������X�g�Ɋi�[���ĕԂ����\�b�h
	public ArrayList<Weapon> daoGetParameters(){
		
		Weapon weapon = new Weapon();
		DAO dao = new DAO();
		ArrayList<Weapon> weaponList = new ArrayList<>();
		
		
		//���݂̎�ސ����擾
		int countId = dao.count("weapon");
		
		//GetParameter�N���X�Ől������Hero���擾
		
		for(int i=0; i<countId; i++){
			weapon = this.daoGetParameter(i);
			weaponList.add(weapon);	
		}	
		
		return weaponList;
	}

	//�w��̒l��1�擾���郁�\�b�h
	public int intParameterGet(int id, String column){
	
		DAO dao = new DAO();
		int parameter = 0;

		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	
			
			String selectSql = 
			"select " + column + " from weapon where id = " + id;
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
			"select " + column + " from weapon where id = " + id;
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
			
		