package dao;

import java.sql.*;

/*���̃N���X��DAO�p�b�P�[�W�Ōp�����邱�ƂŃf�[�^�x�[�X��
URL,USER,PASS���ꊇ�ŕύX����B*/
class DAO{

	String DB_URL, DB_USER, DB_PASS;

	protected DAO(){
		DB_URL =
		"jdbc:h2:tcp:localhost/~/example";
		DB_USER = "sa";
		DB_PASS = ""; 
	}

	//�L�����̐l���m�F�p�̃��\�b�h
	public int count(String table){

		int countId = 0;
	
		try (Connection conn = DriverManager.getConnection(
				this.DB_URL, this.DB_USER, this.DB_PASS)) {
	
				//SELECT��������
				String sql = 
				"select count(id) from " + table;
				PreparedStatement pStmt = conn.prepareStatement(sql);
		
				//SELECT�������s���A���ʕ\(ResultSet)���擾
				ResultSet rs = pStmt.executeQuery();
			
				//���ʕ\�Ɋi�[���ꂽ���R�[�h�̓��e�ő��
				rs.next();
				countId = rs.getInt("count(id)");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		return countId;
	}
}
		
		