/*以下の要領でデータベースから値を取得します。
今回はＤＡＯモデルではなく、単体で作成しています。*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcSample1 {
public static void main(String[] args) {

/*データベースに接続 今回はH2でデータベースを作りました。

今回はtomcatを使用しないパターンにつき、ドライバーへ環境変数でクラスパスを通しています。
topcatの場合はホームディレクトリ/libにjarファイルを配置すれば大丈夫です。

getConnectionに続きデータベースのURL,ユーザー名、パスワード（今回は空白）を入れます。*/
try (Connection cone1 = DriverManager.getConnection(
"?", "?", "?")) {

//SQL文を準備 変数splにSQL文を代入し、データベースに送る準備をします。
String sqlIn = "insert into employee values(4,'日本太郎',25)";
String sqlSe = "select id,name,age from employee";

//Connectionを利用してPreparedStatementオブジェクトにプリコンパイルして格納します。→pS0に代入。
PreparedStatement pS0 = cone1.prepareStatement(sqlIn); 
PreparedStatement pS1 = cone1.prepareStatement(sqlSe);


//SQLを実行(select:executeQuery() 他:executeUpdate)し、処理件数結果(int),結果表(ResultSet)を取得
int rs0 = pS0.executeUpdate();
ResultSet rs1 = pS1.executeQuery();
System.out.println("insert文の結果は" + rs0);
System.out.println("select文の結果は" + rs1); //SQLの結果を表示してみます…。

//結果表に格納されたレコードの内容を指定の形式でgetし、while文でカーソルを動かし順番に代入します。
while (rs1.next()) {
int idInt = rs1.getInt("ID"); //列"ID"の値をint型で取り出します。
String id = rs1.getString("ID"); //String型で取り出します。
String name = rs1.getString("NAME");
int age = rs1.getInt("AGE");
// String id2 = rs1.getString("ID");while文が進むまでは同じ内容を複数回代入できます。

//取得したデータを出力
System.out.println("ID:" + idInt);
System.out.println("ID:" + id);
System.out.println("名前:" + name);
System.out.println("年齢:" + age + "\n");
  }
//エラーの際に使用します。原因はパスの通し忘れや、ＵＲＬ違い、データベースを起動していない等々。
} catch (SQLException e) {
e.printStackTrace();
    }
  }
}
