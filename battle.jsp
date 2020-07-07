<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="java.util.* , java.text.SimpleDateFormat , java.util.* , model.*" %>

<%
//サーブレットにて変化した値を再度取得
Hero hero = (Hero) session.getAttribute("hero");
Monster monster = (Monster) session.getAttribute("monster");
Weapon weapon = (Weapon) session.getAttribute("weapon");
Skill skill = (Skill) session.getAttribute("skill");
Item item = (Item) session.getAttribute("item");

//双方の受けたダメージと行動結果を取得
Integer heroD = (Integer) request.getAttribute("heroD");
Integer monsterD = (Integer) request.getAttribute("monsterD");
String selectR = (String) request.getAttribute("selectR");

//スキル及びアイテムのリストを取得
SkillGetParameter sgp = new SkillGetParameter();
List<Skill> skillList = sgp.getParameters();
ItemGetParameter igp = new ItemGetParameter();
List<Item> itemList = igp.getParameters();

//装備武器の攻撃力を加算
hero.setAtk(hero.getAtk() + weapon.getStrength());

//for文用カウント
Count cou = new Count();
int skillCount = cou.count("skill");
int itemCount = cou.count("item");

//画像の処理用
ImageGet img = new ImageGet();
String imageName_h = img.imageGet(hero.getName());
String imageName_m = img.imageGet(monster.getName());
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, user-scalable=yes,initial-scale=1.0">
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p:400,500" rel="stylesheet">

<link rel="stylesheet" href="http://localhost:8080/rpg/css/reset.css">
<link rel="stylesheet" href="http://localhost:8080/rpg/css/rpg.css">

<title>ヒーローバトル</title>

</head>
<body>
<div class="hero"> <!-- ヒーローのパラメータ表示 -->
<div class="heroImage">
<img src="image\<%= imageName_h  %>"  alt="<%= hero.getName() %>" ></div>
<br><br>
<%= hero.getName() %> Lv.<%= hero.getLv() %> [<%= hero.getStatus() %>]<br> 
HP:<%= hero.getHp() %> &nbsp; MP:<%= hero.getMp() %> &nbsp; ATK:<%= hero.getAtk() %><br>
武器:<%= weapon.getName() %> 強さ:<%= weapon.getStrength() %>
<br>

<% if(monster.getHp() != 0){ %> <!-- 戦闘中に表示 -->

	<form action="/rpg/Battle" method="post"> <!-- 武器攻撃 -->
	<input type="hidden" name="hero" value="<%= hero %>">
	<input type="hidden" name="monster" value="<%= monster %>">
	<input type="hidden" name="select" value="攻撃">
	<input type="submit" value="攻撃"></form> &nbsp;
	</form>

	<div class="skill-table"> <!-- スキル発動 -->
	<table>
	<tr>
	<th>No.</th><th>スキル名</th><th>効果対象</th><th>効果</th><th>効果量</th><th>MP</th><th>使用</th>
	</tr>

	<% for(int i=0; i<skillCount; i++){ %>
		<% skill = skillList.get(i); %>
		<tr>
		<td><%= skill.getId() %></td>
		<td><%= skill.getName() %></td>
		<td><%= skill.getTarget() %></td>
		<td><%= skill.getEffect() %></td>
		<td><%= skill.getEffectSize() %></td>
		<td><%= skill.getMp() %></td>
		<td>調整中</td>
		</tr>	
	<% } %>
	</table>
	 

	<form action="/rpg/Battle" method="post">
	<input type="hidden" name="hero" value="<%= hero %>">
	<input type="hidden" name="monster" value="<%= monster %>">
	<input type="hidden" name="skill" value="<%= skill %>" >
	<input type="hidden" name="select" value="技能">
	使用したいスキルの番号<input type="text" name="skillNo">
	<input type="submit" value="使用">
	</form> &nbsp;
	</div>

	<div class="item-table"> <!-- アイテム使用 -->
	<table>
	<tr>
	<th>No.</th><th>アイテム名</th><th>効果</th><th>効果量</th><th>ステータス効果</th><th>使用</th>
	</tr>

	<% for(int i=0; i<itemCount; i++){ %>
		<% item = itemList.get(i); %>
		<tr>
		<td><%= item.getId() %></td>
		<td><%= item.getName() %></td>
		<td><%= item.getEffect() %></td>
		<td><%= item.getEffectSize() %></td>
		<td><%= item.getStatusEffect() %></td>
		<td>調整中</td>
		</tr>	
	<% } %>
	</table>
	
	<form action="/rpg/Battle" method="post">
	<input type="hidden" name="hero" value="<%= hero %>">
	<input type="hidden" name="monster" value="<%= monster %>">
	<input type="hidden" name="item" value="<%= item %>" >
	<input type="hidden" name="select" value="道具">
	使用したいアイテムの番号<input type="text" name="itemNo">
	<input type="submit" name="道具" value="道具">
	</form> &nbsp;
	</div>

<% } %> <!-- 戦闘中に表示終わり -->
</div> <!-- ここまでヒーロー側 -->

<div class="monster">
<div class="monsterImage">
<img src="image\<%= imageName_m %>"  alt="<%= monster.getName() %>" ></div>
<br><br>
<%= monster.getName() %>  [<%= monster.getStatus() %>] <br>
HP:<%= monster.getHp() %> &nbsp; MP:<%= monster.getMp() %> &nbsp; ATK:<%= monster.getAtk() %>
</div> <!-- ここまでモンスター側 -->

<div class="result">
<% if(selectR != null){ %>
	<%= selectR %><br>
<% } %>

<% if(monsterD > 0 || heroD > 0){ %>
	敵から受けたダメージ:<%= heroD %><br>
	敵に与えたダメージ:<%= monsterD %><br>
<% } %>

<% if(monster.getHp() == 0 && hero.getHp() > 0){ %>
	勝利!!
	<form action="/rpg/NextBattle" method="post">
	<input type="hidden" name="monsterId" value="<%= monster.getId()  %>">
	<input type="submit" value="次へ"></form>
<% } else if(hero.getHp() == 0){ %>
	敗北…
<% } %>
</div>

<div class ="vs">
VS
</div>

<!-- ${hero.hp} コメント-->

<div class="copyright">
<jsp:include page="/footer.jsp" />
</div>
</body>


<script>

</script>


</html>