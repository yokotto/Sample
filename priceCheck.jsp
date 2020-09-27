<!-- 価格を入力する為のビュー  -->
<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お値段チェック</title>
</head>
<body>
<h1>お値段チェック</h1>
<form action="/port1/PriceCheck" method="post">
家具1:<input type="text" name="price1">価格<br>
家具2:<input type="text" name="price2">価格<br>
<input type="submit" value="合計">
</form>
</body>
</html>