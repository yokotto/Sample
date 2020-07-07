package model;

import model.Hero;
import model.Monster;
import java.util.List;
//攻撃時のメソッド
public class ItemLogic extends Skill{
	public Hero heroItemLogic(Hero hero, Monster monster, int itemNo){		
		
		ItemGetParameter igp= new ItemGetParameter();			

		Item item = igp.getParameter(itemNo);
		
		//hp,mp,atkの変化
		if(item.getEffect().equals("hp")){
			hero.setHp(hero.getHp() + item.getEffectSize());
		} else if(item.getEffect().equals("mp")){
			hero.setMp(hero.getMp() + item.getEffectSize());
		} else if(item.getEffect().equals("atk")){
			hero.setAtk(hero.getAtk() + item.getEffectSize());
		}
		//ステータスの回復
		if(item.getStatusEffect().equals("毒") && hero.getStatus().equals("毒")){
			hero.setStatus("平常");
		} else if(item.getStatusEffect().equals("火傷") && hero.getStatus().equals("火傷")){
			hero.setStatus("平常");
		} else if(item.getStatusEffect().equals("衰弱") && hero.getStatus().equals("衰弱")){
			hero.setStatus("平常");
		}
	return hero;
	}

	public Monster monsterItemLogic(Hero hero, Monster monster, int itemNo){

		ItemGetParameter igp= new ItemGetParameter();			

		Item item = igp.getParameter(itemNo);

		//hp,mp,atkの変化
		if(item.getEffect().equals("hp")){
			monster.setHp(monster.getHp() + item.getEffectSize());
		} else if(item.getEffect().equals("mp")){
			monster.setMp(monster.getMp() + item.getEffectSize());
		} else if(item.getEffect().equals("atk")){
			monster.setAtk(monster.getAtk() + item.getEffectSize());
		}
		//ステータスの回復
		if(item.getStatusEffect().equals("毒") && monster.getStatus().equals("毒")){
			monster.setStatus("平常");
		} else if(item.getStatusEffect().equals("火傷") && monster.getStatus().equals("火傷")){
			monster.setStatus("平常");
		} else if(item.getStatusEffect().equals("衰弱") && monster.getStatus().equals("衰弱")){
			monster.setStatus("平常");
		}
	return monster;
	}
}