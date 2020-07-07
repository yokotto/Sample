package dao;

import java.sql.*;
import java.util.*;
import model.Item;

public class DaoItem extends DAO{
	
	public void show(ResultSet rs){ //select�m�F�p
		try {
			rs.first();
			while(rs.next()){
				System.out.println("�ԍ�:" + rs.getInt("id") + " ���O:" + rs.getString("name")
				+ "\n����:" + rs.getString("effect") + " ���ʗ�:" + rs.getInt("effectsize")
				+ " �X�e�[�^�X��:" + rs.getString("statuseffect") + "\n");
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
			String sql = "select * from item";
			PreparedStatement pStmt = conn.prepareStatement(sql);
	
			//SELECT�������s���A���ʕ\(ResultSet)���擾
			ResultSet rs = pStmt.executeQuery();
			
			//���ʕ\�Ɋi�[���ꂽ���R�[�h�̓��e��\��
			this.show(rs);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�A�C�e�����ނ̃p�����[�^���擾���郁�\�b�h
	public Item daoGetParameter(int id){
	
	Item item = new Item();
	DAO dao = new DAO();
	int countId = dao.count("item");
	
		//id���͈͓��ł���Ύ��s
		if(id < countId){
		try(Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {
			
			//SELECT��������
			String sql =
			"select * from item where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
		
			//SELECT����?�ɒl��ݒ肵SQL������������
			pStmt.setInt(1, id);
			
			//SELECT�������s���A���ʕ\(ResultSet)���擾
			ResultSet rs = pStmt.executeQuery();

			//���ʕ\�Ɋi�[���ꂽ���R�[�h�̓��e�ő��
			rs.next();

			if(rs.getString("name") != null){
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setEffect(rs.getString("effect"));
			item.setEffectSize(rs.getInt("effectsize"));
			item.setStatusEffect(rs.getString("statuseffect"));
			}
	
		} catch (SQLException e) {
			System.out.println("SQL�G���[�ł��B");
			e.printStackTrace();
		}
		return item;
		
		} else {
		return null;
		}
	}

	//�A�C�e���S��ނ����X�g�Ɋi�[���ĕԂ����\�b�h
	public ArrayList<Item> daoGetParameters(){
		
		Item item = new Item();
		DAO dao = new DAO();
		ArrayList<Item> itemList = new ArrayList<>();
		
		
		//���݂̎�ސ����擾
		int countId = dao.count("item");
		
		//GetParameter�N���X�Ŏ�ޕ���ITEM���擾
		
		for(int i=0; i<countId; i++){
			item = this.daoGetParameter(i);
			itemList.add(item);	
		}	
		
		return itemList;
	}

	//�w��̒l��1�擾���郁�\�b�h
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
	//�w��̕��������擾���郁�\�b�h
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
			
		