package dao;

import java.sql.*;

/*このクラスをDAOパッケージで継承することでデータベースの
URL,USER,PASSを一括で変更する。*/
class DAO{

	String DB_URL, DB_USER, DB_PASS;

	protected DAO(){
		DB_URL =
		"jdbc:h2:tcp:localhost/~/example";
		DB_USER = "sa";
		DB_PASS = ""; 
	}

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
		
		