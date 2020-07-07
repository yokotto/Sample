package dao;

import java.sql.*;

public class DaoCount extends DAO {

	public DaoCount() { }

	//キャラの人数確認用のメソッド
	public int count(String table){

		int countId = 0;
	
		try (Connection conn = DriverManager.getConnection(
				this.DB_URL, this.DB_USER, this.DB_PASS)) {
	
				//SELECT文を準備
				String sql = 
				"select count(id) from " + table;
				PreparedStatement pStmt = conn.prepareStatement(sql);
		
				//SELECT文を実行し、結果表(ResultSet)を取得
				ResultSet rs = pStmt.executeQuery();
			
				//結果表に格納されたレコードの内容で代入
				rs.next();
				countId = rs.getInt("count(id)");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		return countId;
	}
}