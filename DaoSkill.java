package dao;

import java.sql.*;
import java.util.*;
import model.Skill;

public class DaoSkill extends DAO{
	
	public void show(ResultSet rs){ //select�m�F�p
		try {
			rs.first();
			while(rs.next()){
				System.out.println("�ԍ�:" + rs.getInt("id") + " ���O:" + rs.getString("name")
				+ "\n���ʑΏ�:" + rs.getString("target") + " ����:" + rs.getString("effect")
				+ " ���ʗ�:" + rs.getInt("effectSize") + " MP:" + rs.getInt("mp") + "\n"); 
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
			String sql = "select * from skill";
			PreparedStatement pStmt = conn.prepareStatement(sql);
	
			//SELECT�������s���A���ʕ\(ResultSet)���擾
			ResultSet rs = pStmt.executeQuery();
			
			//���ʕ\�Ɋi�[���ꂽ���R�[�h�̓��e��\��
			this.show(rs);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�X�L�����ނ̏ڍׂ��擾���郁�\�b�h
	public Skill daoGetParameter(int id){
	
	Skill skill = new Skill();
	DAO dao = new DAO();
	int countId = dao.count("skill");
	
		//id���͈͓��ł���Ύ��s
		if(id < countId){
		try(Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {
			
			//SELECT��������
			String sql =
			"select * from skill where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
		
			//SELECT����?�ɒl��ݒ肵SQL������������
			pStmt.setInt(1, id);
			
			//SELECT�������s���A���ʕ\(ResultSet)���擾
			ResultSet rs = pStmt.executeQuery();

			//���ʕ\�Ɋi�[���ꂽ���R�[�h�̓��e�ő��
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
			System.out.println("SQL�G���[�ł��B");
			e.printStackTrace();
		}
		return skill;
		
		} else {
		return null;
		}
	}

	//�S�X�L�������X�g�Ɋi�[���ĕԂ����\�b�h
	public ArrayList<Skill> daoGetParameters(){
		
		Skill skill = new Skill();
		DAO dao = new DAO();
		ArrayList<Skill> skillList = new ArrayList<>();
		
		
		//���݂̎�ސ����擾
		int countId = dao.count("skill");
		
		//GetParameter�N���X�Ŏ�ޕ���SKILL���擾
		
		for(int i=0; i<countId; i++){
			skill = this.daoGetParameter(i);
			skillList.add(skill);	
		}	
		
		return skillList;
	}

	//�w��̒l��1�擾���郁�\�b�h
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
	//�w��̕��������擾���郁�\�b�h
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
			
		