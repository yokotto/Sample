package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.GetStudyLogic;
import model.Study;
import model.StudyLogic;

@WebServlet("/Main")
public class Main extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {
    //入力リストを取得して,リクエストスコープに保存
    GetStudyLogic getStudyLogic = new GetStudyLogic();
    List<Study> studyList = getStudyLogic.execute();
    request.setAttribute("studyList", studyList);

    //フォワード
    RequestDispatcher dispatcher =
    request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
    dispatcher.forward(request, response);
  }
  
  protected void doPost(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {
    //リクエストパラメータの取得
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");
    String text = request.getParameter("text");
    String type = request.getParameter("type");

    //入力値チェック
    if(name != null && name.length() != 0 &&
       text != null && text.length() != 0 &&
       type != null && type.length() != 0) {
    //セッションスコープに保存された情報を取得
      HttpSession session = request.getSession();


    //入力を入力リストに追加
      Study study = new Study(name, text, type);
      StudyLogic studyLogic = new StudyLogic();
      studyLogic.execute(study);
    } else {
      //エラーメッセージをリクエストスコープに保存
      request.setAttribute("errorMsg",
	"全ての項目に入力されていません");
    }

    //入力リストを取得して,リクエストスコープに保存
    GetStudyLogic getStudyLogic = new GetStudyLogic();
    List<Study> studyList = getStudyLogic.execute();;
    request.setAttribute("studyList", studyList);
    
    //フォワード
    RequestDispatcher dispatcher = request.getRequestDispatcher(
	"/WEB-INF/jsp/main.jsp");
    dispatcher.forward(request, response);
  }
} 