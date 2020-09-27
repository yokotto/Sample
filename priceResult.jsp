<!-- 価格の金額レベルを表示するビュー -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.BeanInfo" %>
<%
//リクエストスコープに保存されたBeanInfoインスタンスを取得
BeanInfo beaninfo = (BeanInfo) request.getAttribute("beaninfo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お値段チェック</title>
</head>
<body>
<h1>お値段チェックの結果</h1>
<p>
家具1の価格:<%= beaninfo.getPrice1() %><br>
家具2の価格:<%= beaninfo.getPrice2() %><br>
合計金額:<%= beaninfo.getTotalprice() %><br>
金額レベル:<%= beaninfo.getType() %>
</p>
<a href="/port1/PriceCheck">戻る</a>
</body>
</html>