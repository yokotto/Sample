package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import model.*;

@WebServlet("/NextBattle")
public class NextBattle extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		Integer monsterId = Integer.parseInt(request.getParameter("monsterId"));

		HttpSession session = request.getSession();
		Hero hero = (Hero) session.getAttribute("hero");
		Monster monster = (Monster) session.getAttribute("monster");
		Weapon weapon = (Weapon) session.getAttribute("weapon");

		Integer heroD = 0;
		Integer monsterD = 0;

		Count cou = new Count();
		int monsterCount = cou.count("monster");

		if(monster.getId() < monsterCount-1){
			MonsterGetParameter mgp = new MonsterGetParameter();
			monster = mgp.getParameter(monsterId+1);
		} else {
			//フォワード
			RequestDispatcher dispatcher = 
			request.getRequestDispatcher("/WEB-INF/jsp/clear.jsp;");
			dispatcher.forward(request, response);	
		}

		//ネクストロールの場合もJSPに戻る時に再度加算される為、調整
		hero.setAtk(hero.getAtk() - weapon.getStrength());

		request.setCharacterEncoding("UTF-8");
		
		//リクエストスコープに保存
		request.setAttribute("heroD" ,heroD);
		request.setAttribute("monsterD", monsterD);

		//セッションスコープに保存
		session.setAttribute("hero" ,hero);
		session.setAttribute("monster",monster);
		
		//フォワード
		RequestDispatcher dispatcher = 
			request.getRequestDispatcher("/WEB-INF/jsp/battle.jsp;");
		dispatcher.forward(request, response);
	}

}
