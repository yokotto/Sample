package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BeanInfo;
import model.BeanMath;

@WebServlet("/PriceCheck")
public class PriceCheck extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException 
    {
    //Getメソッドのフォワード 最初のフォワード
    RequestDispatcher dispatcher = 
    request.getRequestDispatcher("/WEB-INF/jsp/priceCheck.jsp");
    dispatcher.forward(request, response);
}
//以上Getメソッド
//以下Postメソッド
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException {
//リクエストパラメータの取得
String price1 = request.getParameter("price1");
String price2 = request.getParameter("price2");
    
//入力値をプロパティに設定
BeanInfo beaninfo = new BeanInfo();
beaninfo.setPrice1(Double.parseDouble(price1));
beaninfo.setPrice2(Double.parseDouble(price2));

//価格計算を実行し結果を設定
BeanMath beanmath = new BeanMath();
beanmath.execute(beaninfo);

//リクエストスコープに保存
request.setAttribute("beaninfo", beaninfo);

//PriceResult.jspに、フォワード
RequestDispatcher dispatcher = 
request.getRequestDispatcher
("/WEB-INF/jsp/priceResult.jsp");
dispatcher.forward(request,response);
    }
//以上Postメソッド
}
    

                                