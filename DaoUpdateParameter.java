package dao;

import java.sql.*;
import model.Hero;
import java.util.*;
import dao.DaoGetParameter;


public class DaoUpdateParameter extends DAO{

	DAO dao = new DAO();
	DaoGetParameter dgp = new DaoGetParameter();
	ArrayList<Hero> heroList = new ArrayList<>();

	
	//プレイヤーが入力してHp,Mp,Atkの全て値を変更する
	public void daoUpdateParameter(){
		try (Connection conn = DriverManager.getConnection(
		dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

		Scanner sc = new Scanner(System.in);

		//SQLの用意
		String selectSql =
		"select * from parameter where id = ?";

		String updateSql = 
		"update parameter set maxhp=?, hp=?, maxmp=?, mp=?, maxatk=?, atk=? where id=?";
		

		//変更するキャラクターIDの指定
		System.out.println("変更したいキャラクターのIDを入力してください。");
		String id = sc.nextLine();
		
		//確認用のselect文の実行
		PreparedStatement S_pStmt = conn.prepareStatement(selectSql);
		S_pStmt.setString(1,id);
		ResultSet rs = S_pStmt.executeQuery();

		//各パラメータの入力
		rs.absolute(1);
		String name = rs.getString("name");
		System.out.println(name + "のHP,MP,ATKの変更後の値を入力してください。");
		System.out.print("HP:");
		String hp = sc.nextLine();
		System.out.print("MP:");
		String mp = sc.nextLine();
		System.out.print("ATK:");
		String atk = sc.nextLine();
		sc.close();

		//UPDATE文の実行
		PreparedStatement U_pStmt = conn.prepareStatement(updateSql);

		U_pStmt.setString(1, hp);
		U_pStmt.setString(2, hp);
		U_pStmt.setString(3, mp);
		U_pStmt.setString(4, mp);
		U_pStmt.setString(5, atk);
		U_pStmt.setString(6, atk);
		U_pStmt.setString(7, id);

		U_pStmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//数字を直に変更　指定は1column 数値用
	public void daoUpdateParameterChange(int id, String column, int size){
		try (Connection conn = DriverManager.getConnection(
		dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {		

		String sql = 
			"update parameter set " + column + " = " + size + " where id = " + id;
		
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.executeUpdate();

		} catch(SQLException e) {
		e.printStackTrace();
		}
	}
	//文字列を直に変更　指定は1column 文字列用
	public void daoUpdateParameterChange(int id, String column, String thing){
		try (Connection conn = DriverManager.getConnection(
		dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {		

		String sql = 
			"update parameter set " + column + " = \'" + thing + "\' where id = " + id;
		
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.executeUpdate();

		} catch(SQLException e) {
		e.printStackTrace();
		}		
	}

	//変更できる数字から選択
	public void daoUpdateParameterSelect(){
		try (Connection conn = DriverManager.getConnection(
		dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

		Scanner sc = new Scanner(System.in);

		//変更するキャラクターIDの指定
		System.out.println("変更したいキャラクターのIDを入力してください。");
		String id = sc.nextLine();
		
		String selectSql =
		"select * from parameter where id =" + id;

		//確認用のselect文の実行
		PreparedStatement S_pStmt = conn.prepareStatement(selectSql);
		ResultSet rs = S_pStmt.executeQuery();

		//各パラメータの入力
		rs.absolute(1);
		String name = rs.getString("name");
		System.out.println(name + "の変更したい項目を次から選択してください。");
		System.out.println("lv, maxhp, hp, maxmp, mp, maxatk, atk, exp");
		String parameter = sc.nextLine();
		int parameterSize = rs.getInt(parameter);
		System.out.println("変化量を選択してください。例: 2, -2");
		String sizeK = sc.nextLine();
		int size = Integer.parseInt(sizeK);
		String pSize = Integer.toString((parameterSize + size));
		sc.close();

		//SQLの用意実行
		String updateSql =		
		"update parameter set " + parameter + " = " + pSize + " where id =" + id;
		PreparedStatement U_pStmt = conn.prepareStatement(updateSql);
		U_pStmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//HP,MP,ATK変更 引数に変更したいID,HP,MP,ATKの順に入れる。
	public void daoUpdateParameterAll(int id, int maxHp, int maxMp, int maxAtk){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			//SQLの用意実行
			String updateSql = 
			"update parameter set maxhp=" + maxHp + " ,maxmp=" + maxMp + 
			" ,maxatk=" + maxAtk + " where id =" + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//名前の変更
	public void daoUpdateParameterName(int id, String name){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			//SQL_Cの用意実行
			String updateSql =
			"alter table character set referential_integrity false" +
			";alter table parameter set referential_integrity false" +
			";update character set name = \'" + name + "\' where id = " + id +
			";update parameter set name = \'" + name + "\' where id = " + id +
			";alter table character set referential_integrity true" +
			";alter table parameter set referential_integrity true";
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();		

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//Statusの変更
	public void daoUpdateParameterStatus(int id, String status){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			//SQLの用意実行
			String updateSql = 
			"update parameter set status = \'" + status + "\' where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	//Lvの変更
	public void daoUpdateParameterLv(int id, int lv){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	
			
			int exLv = dgp.intParameterGet(id, "lv");
			lv = (exLv + lv);
			//SQLの用意実行
			String updateSql = 
			"update parameter set lv = " + lv + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	//Expの変更
	public void daoUpdateParameterExp(int id, int exp){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			int exExp = dgp.intParameterGet(id, "exp");
			exp = (exExp + exp);
			//SQLの用意実行
			String updateSql = 
			"update parameter set exp = " + exp + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	//HPの変更
	public void daoUpdateParameterHp(int id, int hp){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			int exHp = dgp.intParameterGet(id, "hp");
			hp = (exHp + hp);
			//SQLの用意実行
			String updateSql = 
			"update parameter set hp = " + hp + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	//maxHPの変更
	public void daoUpdateParameterMaxHp(int id, int maxHp){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			int exMaxHp = dgp.intParameterGet(id, "maxHp");
			maxHp = (exMaxHp + maxHp); 
			//SQLの用意実行
			String updateSql = 
			"update parameter set maxhp = " + maxHp + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	//MPの変更
	public void daoUpdateParameterMp(int id, int mp){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			int exMp = dgp.intParameterGet(id, "mp");
			mp = (exMp + mp);
			//SQLの用意実行
			String updateSql = 
			"update parameter set mp = " + mp + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//MaxMpの変更
	public void daoUpdateParameterMaxMp(int id, int maxMp){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {

			int exMaxMp = dgp.intParameterGet(id, "maxMp");	
			maxMp = (exMaxMp + maxMp);
			//SQLの用意実行
			String updateSql = 
			"update parameter set maxmp = " + maxMp + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	//Atkの変更
	public void daoUpdateParameterAtk(int id, int atk){
			try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			int exAtk = dgp.intParameterGet(id, "atk");
			atk = (exAtk + atk);
			//SQLの用意実行
			String updateSql = 
			"update parameter set atk = " + atk + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//MaxAtkの変更
	public void daoUpdateParameterMaxAtk(int id, int maxAtk){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			int exMaxAtk = dgp.intParameterGet(id, "maxAtk");
			maxAtk = (exMaxAtk + maxAtk);
			//SQLの用意実行
			String updateSql = 
			"update parameter set maxatk = " + maxAtk + "where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	//Weaponの変更
	public void daoUpdateParameterWeapon(int id, String weapon){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			//SQLの用意実行
			String updateSql = 
			"update parameter set weapon = \'" + weapon + "\' where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

}