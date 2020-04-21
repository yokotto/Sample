<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スタディ</title>
</head>
<body>
<h1>スタディ</h1>
<p><a href="/port/Main">更新</a></p>
<form action="/port/Main" method="post">
用語名:<input type="text" name="name"><br>
説明:<input type="text" name="text"><br>
カテゴリ:
テクノロジ<input type="radio" name="type" value="テクノロジ">
マネジメント<input type="radio" name="type" value="マネジメント">
ストラテジ<input type="radio" name="type" value="ストラテジ"><br>

<input type="submit" value="登録">
</form>

<c:if test="${not empty errorMsg}">
<p>${errorMsg}</p>
</c:if>
<c:forEach var="study" items="${studyList}">

<p>
<c:out value="${study.id}" />:<c:out value="${study.type}" /><br>
   <c:out value="${study.name}" /><br>
   <c:out value="${study.text}" /></p>
</c:forEach>
</body>
</html>
