package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Study;

public class StudyDAO {
  //データベース接続に使用する情報
  private final String JDBC_URL =
    "jdbc:h2:tcp://localhost/~/example";
  private final String DB_USER = "sa";
  private final String DB_PASS = "";

  public List<Study> findAll() { //全レコードの取得をするメソッド
    List<Study> studyList = new ArrayList<>();

  //データベース接続 
  try(Connection conn = DriverManager.getConnection(
    JDBC_URL, DB_USER, DB_PASS)) {
  //select文の準備
  String sql =
    "select id, name, text, type from study order by id";
  PreparedStatement pStmt = conn.prepareStatement(sql);

  //selectを実行
  ResultSet rs = pStmt.executeQuery();

  //select文の結果をArrayListに格納
  while (rs.next()) {
    int id = rs.getInt("ID");
    String name = rs.getString("name");
    String text = rs.getString("text");
    String type = rs.getString("type");
    Study study = new Study(id, name, text, type);
    studyList.add(study);
    }
  } catch (SQLException e) {
    e.printStackTrace();
    return null;
  }
  return studyList;
  }
  public boolean create(Study study) {
    //データベース接続
    try(Connection conn = DriverManager.getConnection(
    JDBC_URL, DB_USER, DB_PASS)) {

    //insert文の準備(idは自動連番
    String sql = "insert into study(name, text, type) values(?, ?, ?)";
    PreparedStatement pStmt = conn.prepareStatement(sql);

    //insert文中の?に使用する値を設定しSQLを完成
    pStmt.setString(1, study.getName());
    pStmt.setString(2, study.getText());
    pStmt.setString(3, study.getType());

    //insert文を実行(resultには追加された行数が代入される
    int result = pStmt.executeUpdate();
    if ( result != 1) {
      return false;
    }
  } catch (SQLException e) {
    e.printStackTrace();
    return false;
  }
  return true;
  }
}