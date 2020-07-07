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
		
		//���N�G�X�g�X�R�[�v�ɕۑ�
		request.setAttribute("heroD", heroD);
		request.setAttribute("monsterD",monsterD);


		//�Z�b�V�����X�R�[�v�ɕۑ�
		HttpSession session = request.getSession();
		session.setAttribute("hero" ,hero);
		session.setAttribute("monster",monster);
		session.setAttribute("weapon", weapon);	
		session.setAttribute("skill", skill);
		session.setAttribute("item",item);	

		//�t�H���[�h
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
		
		
		// �X�L���y�ѓ���̗L������Ə��̎擾
		boolean commandError = true;

		int skillNo = 0;
		if(select.equals("�Z�\")){
			skillNo = Integer.parseInt(request.getParameter("skillNo"));
			if(skillNo >= 0 && skillNo < skillCount){
				SkillGetParameter sgp = new SkillGetParameter();
				skill = sgp.getParameter(skillNo);
			} else {
			commandError = false;
			}
		}

		int itemNo = 0;
		if(select.equals("����")){
			itemNo = Integer.parseInt(request.getParameter("itemNo"));
			if(itemNo >= 0 && itemNo < itemCount){
			ItemGetParameter igp = new ItemGetParameter();
			item = igp.getParameter(itemNo); 
			} else {
			commandError = false;
			}
		}
		
		//�ȉ��R�}���h���[��
		
		if(commandError){

			if(select.equals("�U��")){
				AttackLogic al = new AttackLogic();
				hero = al.heroAttackLogic(hero, monster);
				monster = al.monsterAttackLogic(hero, monster);
	
				selectR = "����ōU�����܂����B";
				heroD = monster.getAtk();
				monsterD = hero.getAtk();
		
			} else if(select.equals("�Z�\")){
				if(hero.getMp() >= skill.getMp()){
					SkillLogic sl = new SkillLogic();
					hero = sl.heroSkillLogic(hero, monster,skillNo);
					monster = sl.monsterSkillLogic(hero, monster,skillNo);

					AttackLogic al = new AttackLogic();
					hero = al.heroAttackLogic(hero,monster);

					selectR = select + ":" + skill.getName() + "���g�p���܂����B\nMP��"
					+ skill.getMp() + "������:" + skill.getTarget() + "��" + skill.getEffect() + "��\n"
    	   		 	+ skill.getEffectSize() + "�ω������܂����B";
					heroD = monster.getAtk();
					if(skill.getTarget().equals("opponent") || skill.getTarget().equals("o_all")){
						monsterD = skill.getEffectSize();
					} else {
						monsterD = monster.getAtk();
					}
				} else {
					selectR = "MP������܂���B";
				}
		
			} else if(select.equals("����")){

				String h_status = hero.getStatus();

				ItemLogic il = new ItemLogic();
				hero = il.heroItemLogic(hero, monster,itemNo);
				//monster = il.monsterItemLogic(hero, monster);

				AttackLogic al = new AttackLogic();
				hero = al.heroAttackLogic(hero,monster);

				heroD = monster.getAtk();

				if(item.getEffect().equals("hp") || item.getEffect().equals("mp")){
					selectR = item.getName() + "���g�p���܂����B\n"
					+ item.getEffect() + "��" + item.getEffectSize() + "�񕜂��܂����B";
				} else if(item.getEffect().equals("atk")){
					selectR = item.getName() + "���g�p���܂����B\n"
					+ item.getEffect() + "��" + item.getEffectSize() + "�㏸���܂����B";				
				}

				if(item.getStatusEffect().equals("��") && h_status.equals("��")){
					selectR = selectR + "\n�ł������܂����B";
				} else if(item.getStatusEffect().equals("�Ώ�") && h_status.equals("�Ώ�")){
					selectR = selectR + "\n�Ώ��������܂����B";
				} else if(item.getStatusEffect().equals("����") && h_status.equals("����")){
					selectR = selectR + "\n����������܂����B";
				} else if(item.getStatusEffect().equals("��") || item.getStatusEffect().equals("�Ώ�") || item.getStatusEffect().equals("����") && h_status.equals("����")){
					selectR = selectR + "\n�X�e�[�^�X�͊��ɕ���ł��B";
				}	
			}
		} else {
		selectR = "���̔ԍ��͑��݂��܂���B";
		}


		//�R�}���h���[�����I���������x�q�[���[�̍U���͂�߂��đ���B��������ł�����x����̍U���͂����Z�����B
		hero.setAtk(hero.getAtk() - weapon.getStrength());

		request.setCharacterEncoding("UTF-8");
		
		//���N�G�X�g�X�R�[�v�ɕۑ�
		request.setAttribute("heroD", heroD);
		request.setAttribute("monsterD",monsterD);
		request.setAttribute("selectR", selectR);		

		//�Z�b�V�����X�R�[�v�ɕۑ�
		session.setAttribute("hero" ,hero);
		session.setAttribute("monster",monster);
		session.setAttribute("weapon", weapon);
		session.setAttribute("skill", skill);
		session.setAttribute("item", item);

		//�t�H���[�h
		RequestDispatcher dispatcher = 
			request.getRequestDispatcher("/WEB-INF/jsp/battle.jsp;");
		dispatcher.forward(request, response);		
	}
}
