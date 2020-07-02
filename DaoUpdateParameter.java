package dao;

import java.sql.*;
import model.Hero;
import java.util.*;
import dao.DaoGetParameter;


public class DaoUpdateParameter extends DAO{

	DAO dao = new DAO();
	DaoGetParameter dgp = new DaoGetParameter();
	ArrayList<Hero> heroList = new ArrayList<>();

	
	//�v���C���[�����͂���Hp,Mp,Atk�̑S�Ēl��ύX����
	public void daoUpdateParameter(){
		try (Connection conn = DriverManager.getConnection(
		dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

		Scanner sc = new Scanner(System.in);

		//SQL�̗p��
		String selectSql =
		"select * from parameter where id = ?";

		String updateSql = 
		"update parameter set maxhp=?, hp=?, maxmp=?, mp=?, maxatk=?, atk=? where id=?";
		

		//�ύX����L�����N�^�[ID�̎w��
		System.out.println("�ύX�������L�����N�^�[��ID����͂��Ă��������B");
		String id = sc.nextLine();
		
		//�m�F�p��select���̎��s
		PreparedStatement S_pStmt = conn.prepareStatement(selectSql);
		S_pStmt.setString(1,id);
		ResultSet rs = S_pStmt.executeQuery();

		//�e�p�����[�^�̓���
		rs.absolute(1);
		String name = rs.getString("name");
		System.out.println(name + "��HP,MP,ATK�̕ύX��̒l����͂��Ă��������B");
		System.out.print("HP:");
		String hp = sc.nextLine();
		System.out.print("MP:");
		String mp = sc.nextLine();
		System.out.print("ATK:");
		String atk = sc.nextLine();
		sc.close();

		//UPDATE���̎��s
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
	//�����𒼂ɕύX�@�w���1column ���l�p
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
	//������𒼂ɕύX�@�w���1column ������p
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

	//�ύX�ł��鐔������I��
	public void daoUpdateParameterSelect(){
		try (Connection conn = DriverManager.getConnection(
		dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

		Scanner sc = new Scanner(System.in);

		//�ύX����L�����N�^�[ID�̎w��
		System.out.println("�ύX�������L�����N�^�[��ID����͂��Ă��������B");
		String id = sc.nextLine();
		
		String selectSql =
		"select * from parameter where id =" + id;

		//�m�F�p��select���̎��s
		PreparedStatement S_pStmt = conn.prepareStatement(selectSql);
		ResultSet rs = S_pStmt.executeQuery();

		//�e�p�����[�^�̓���
		rs.absolute(1);
		String name = rs.getString("name");
		System.out.println(name + "�̕ύX���������ڂ�������I�����Ă��������B");
		System.out.println("lv, maxhp, hp, maxmp, mp, maxatk, atk, exp");
		String parameter = sc.nextLine();
		int parameterSize = rs.getInt(parameter);
		System.out.println("�ω��ʂ�I�����Ă��������B��: 2, -2");
		String sizeK = sc.nextLine();
		int size = Integer.parseInt(sizeK);
		String pSize = Integer.toString((parameterSize + size));
		sc.close();

		//SQL�̗p�ӎ��s
		String updateSql =		
		"update parameter set " + parameter + " = " + pSize + " where id =" + id;
		PreparedStatement U_pStmt = conn.prepareStatement(updateSql);
		U_pStmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//HP,MP,ATK�ύX �����ɕύX������ID,HP,MP,ATK�̏��ɓ����B
	public void daoUpdateParameterAll(int id, int maxHp, int maxMp, int maxAtk){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			//SQL�̗p�ӎ��s
			String updateSql = 
			"update parameter set maxhp=" + maxHp + " ,maxmp=" + maxMp + 
			" ,maxatk=" + maxAtk + " where id =" + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//���O�̕ύX
	public void daoUpdateParameterName(int id, String name){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			//SQL_C�̗p�ӎ��s
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

	//Status�̕ύX
	public void daoUpdateParameterStatus(int id, String status){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			//SQL�̗p�ӎ��s
			String updateSql = 
			"update parameter set status = \'" + status + "\' where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	//Lv�̕ύX
	public void daoUpdateParameterLv(int id, int lv){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	
			
			int exLv = dgp.intParameterGet(id, "lv");
			lv = (exLv + lv);
			//SQL�̗p�ӎ��s
			String updateSql = 
			"update parameter set lv = " + lv + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	//Exp�̕ύX
	public void daoUpdateParameterExp(int id, int exp){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			int exExp = dgp.intParameterGet(id, "exp");
			exp = (exExp + exp);
			//SQL�̗p�ӎ��s
			String updateSql = 
			"update parameter set exp = " + exp + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	//HP�̕ύX
	public void daoUpdateParameterHp(int id, int hp){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			int exHp = dgp.intParameterGet(id, "hp");
			hp = (exHp + hp);
			//SQL�̗p�ӎ��s
			String updateSql = 
			"update parameter set hp = " + hp + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	//maxHP�̕ύX
	public void daoUpdateParameterMaxHp(int id, int maxHp){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			int exMaxHp = dgp.intParameterGet(id, "maxHp");
			maxHp = (exMaxHp + maxHp); 
			//SQL�̗p�ӎ��s
			String updateSql = 
			"update parameter set maxhp = " + maxHp + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	//MP�̕ύX
	public void daoUpdateParameterMp(int id, int mp){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			int exMp = dgp.intParameterGet(id, "mp");
			mp = (exMp + mp);
			//SQL�̗p�ӎ��s
			String updateSql = 
			"update parameter set mp = " + mp + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//MaxMp�̕ύX
	public void daoUpdateParameterMaxMp(int id, int maxMp){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {

			int exMaxMp = dgp.intParameterGet(id, "maxMp");	
			maxMp = (exMaxMp + maxMp);
			//SQL�̗p�ӎ��s
			String updateSql = 
			"update parameter set maxmp = " + maxMp + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	//Atk�̕ύX
	public void daoUpdateParameterAtk(int id, int atk){
			try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			int exAtk = dgp.intParameterGet(id, "atk");
			atk = (exAtk + atk);
			//SQL�̗p�ӎ��s
			String updateSql = 
			"update parameter set atk = " + atk + " where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//MaxAtk�̕ύX
	public void daoUpdateParameterMaxAtk(int id, int maxAtk){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			int exMaxAtk = dgp.intParameterGet(id, "maxAtk");
			maxAtk = (exMaxAtk + maxAtk);
			//SQL�̗p�ӎ��s
			String updateSql = 
			"update parameter set maxatk = " + maxAtk + "where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	//Weapon�̕ύX
	public void daoUpdateParameterWeapon(int id, String weapon){
		try (Connection conn = DriverManager.getConnection(
			dao.DB_URL, dao.DB_USER, dao.DB_PASS)) {	

			//SQL�̗p�ӎ��s
			String updateSql = 
			"update parameter set weapon = \'" + weapon + "\' where id = " + id;
			PreparedStatement pStmt = conn.prepareStatement(updateSql);
			pStmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

}