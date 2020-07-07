package servlet;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import dao.*;

@WebServlet("/Battle")
public class Battle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//doGet
	protected void doGet(HttpServletRequest request, 
		HttpServletResponse response)
		throws ServletException, IOException {

		Hero hero = new Hero();
		HeroGetParameter hgp = new HeroGetParameter();
		hero = hgp.getParameter(0);

		Monster monster = new Monster();
		MonsterGetParameter mgp = new MonsterGetParameter();
		monster = mgp.getParameter(0);			
		
		Weapon weapon = new Weapon();
		WeaponGetParameter wgp = new WeaponGetParameter();
		weapon = wgp.getParameter(0);

		Skill skill = new Skill();
		SkillGetParameter sgp = new SkillGetParameter();
		skill = sgp.getParameter(0);

		Item item = new Item();		
		ItemGetParameter igp = new ItemGetParameter();
		item = igp.getParameter(0);

		Integer heroD = 0;
		Integer monsterD = 0;

		request.setCharacterEncoding("UTF-8");
		
		//リクエストスコープに保存
		request.setAttribute("heroD", heroD);
		request.setAttribute("monsterD",monsterD);


		//セッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("hero" ,hero);
		session.setAttribute("monster",monster);
		session.setAttribute("weapon", weapon);	
		session.setAttribute("skill", skill);
		session.setAttribute("item",item);	

		//フォワード
		RequestDispatcher dispatcher = 
			request.getRequestDispatcher("/WEB-INF/jsp/battle.jsp;");
			dispatcher.forward(request, response);
	}

	//doPost 
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		Hero hero = (Hero) session.getAttribute("hero");
		Monster monster = (Monster) session.getAttribute("monster");
		Weapon weapon = (Weapon) session.getAttribute("weapon");
		Skill skill = (Skill) session.getAttribute("skill");
		Item item = (Item) session.getAttribute("item");

		Count cou = new Count();
		int skillCount = cou.count("skill");
		int itemCount = cou.count("item");

		Integer heroD = 0;
		Integer monsterD = 0;
		
		String select = request.getParameter("select");		
		String selectR = "";
		
		
		// スキル及び道具の有効判定と情報の取得
		boolean commandError = true;

		int skillNo = 0;
		if(select.equals("技能")){
			skillNo = Integer.parseInt(request.getParameter("skillNo"));
			if(skillNo >= 0 && skillNo < skillCount){
				SkillGetParameter sgp = new SkillGetParameter();
				skill = sgp.getParameter(skillNo);
			} else {
			commandError = false;
			}
		}

		int itemNo = 0;
		if(select.equals("道具")){
			itemNo = Integer.parseInt(request.getParameter("itemNo"));
			if(itemNo >= 0 && itemNo < itemCount){
			ItemGetParameter igp = new ItemGetParameter();
			item = igp.getParameter(itemNo); 
			} else {
			commandError = false;
			}
		}
		
		//以下コマンドロール
		
		if(commandError){

			if(select.equals("攻撃")){
				AttackLogic al = new AttackLogic();
				hero = al.heroAttackLogic(hero, monster);
				monster = al.monsterAttackLogic(hero, monster);
	
				selectR = "武器で攻撃しました。";
				heroD = monster.getAtk();
				monsterD = hero.getAtk();
		
			} else if(select.equals("技能")){
				if(hero.getMp() >= skill.getMp()){
					SkillLogic sl = new SkillLogic();
					hero = sl.heroSkillLogic(hero, monster,skillNo);
					monster = sl.monsterSkillLogic(hero, monster,skillNo);

					AttackLogic al = new AttackLogic();
					hero = al.heroAttackLogic(hero,monster);

					selectR = select + ":" + skill.getName() + "を使用しました。\nMPを"
					+ skill.getMp() + "を消費:" + skill.getTarget() + "の" + skill.getEffect() + "を\n"
    	   		 	+ skill.getEffectSize() + "変化させました。";
					heroD = monster.getAtk();
					if(skill.getTarget().equals("opponent") || skill.getTarget().equals("o_all")){
						monsterD = skill.getEffectSize();
					} else {
						monsterD = monster.getAtk();
					}
				} else {
					selectR = "MPが足りません。";
				}
		
			} else if(select.equals("道具")){

				String h_status = hero.getStatus();

				ItemLogic il = new ItemLogic();
				hero = il.heroItemLogic(hero, monster,itemNo);
				//monster = il.monsterItemLogic(hero, monster);

				AttackLogic al = new AttackLogic();
				hero = al.heroAttackLogic(hero,monster);

				heroD = monster.getAtk();

				if(item.getEffect().equals("hp") || item.getEffect().equals("mp")){
					selectR = item.getName() + "を使用しました。\n"
					+ item.getEffect() + "が" + item.getEffectSize() + "回復しました。";
				} else if(item.getEffect().equals("atk")){
					selectR = item.getName() + "を使用しました。\n"
					+ item.getEffect() + "が" + item.getEffectSize() + "上昇しました。";				
				}

				if(item.getStatusEffect().equals("毒") && h_status.equals("毒")){
					selectR = selectR + "\n毒を治しました。";
				} else if(item.getStatusEffect().equals("火傷") && h_status.equals("火傷")){
					selectR = selectR + "\n火傷を治しました。";
				} else if(item.getStatusEffect().equals("衰弱") && h_status.equals("衰弱")){
					selectR = selectR + "\n衰弱を治しました。";
				} else if(item.getStatusEffect().equals("毒") || item.getStatusEffect().equals("火傷") || item.getStatusEffect().equals("衰弱") && h_status.equals("平常")){
					selectR = selectR + "\nステータスは既に平常です。";
				}	
			}
		} else {
		selectR = "その番号は存在しません。";
		}


		//コマンドロールが終了したら一度ヒーローの攻撃力を戻して送る。送った先でもう一度武器の攻撃力が加算される。
		hero.setAtk(hero.getAtk() - weapon.getStrength());

		request.setCharacterEncoding("UTF-8");
		
		//リクエストスコープに保存
		request.setAttribute("heroD", heroD);
		request.setAttribute("monsterD",monsterD);
		request.setAttribute("selectR", selectR);		

		//セッションスコープに保存
		session.setAttribute("hero" ,hero);
		session.setAttribute("monster",monster);
		session.setAttribute("weapon", weapon);
		session.setAttribute("skill", skill);
		session.setAttribute("item", item);

		//フォワード
		RequestDispatcher dispatcher = 
			request.getRequestDispatcher("/WEB-INF/jsp/battle.jsp;");
		dispatcher.forward(request, response);		
	}
}
