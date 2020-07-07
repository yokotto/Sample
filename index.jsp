<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="java.util.Date, java.text.SimpleDateFormat, model.Hero"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, user-scalable=yes,initial-scale=1.0">
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p:400,500" rel="stylesheet">
    
<link rel="stylesheet" href="http://localhost:8080/rpg/css/reset.css">
<link rel="stylesheet" href="http://localhost:8080/rpg/css/rpg.css">

<title>タイトル</title>

</head>
<body>

<p class="title-image">
<img src="image\title.jpg"  alt="タイトル" >
</p>

<div class="title-table">
<form action="/rpg/Battle" method="get">
<table>
<tr><td><a href= "/rpg/Battle">開始</a></td></tr>
<tr><td><a href= "/rpg/Battle">ロード</a></td></tr>
<tr><td><a href= "/rpg/Battle">終了</a></td></tr>
</table>
</form>
</div>

<div class="title-logo">
HeroBattle!
</div>

<div class = "copyright">
<jsp:include page="/footer.jsp" />
</div>
</body>
</html>
