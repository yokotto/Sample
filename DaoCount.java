package dao;

import java.sql.*;

public class DaoCount extends DAO {

	public DaoCount() { }

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