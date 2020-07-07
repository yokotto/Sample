package model;

import model.Hero;
import model.Monster;

//UŒ‚Žž‚Ìƒƒ\ƒbƒh
public class AttackLogic{
	public Hero heroAttackLogic(Hero hero, Monster monster){		

		int o_atk = monster.getAtk();

		int result = hero.getHp() - o_atk;
		
		if(hero.getHp() >= o_atk){
			hero.setHp(result);
		} else if(hero.getHp() < o_atk) {
		    hero.setHp(0);
		}
		return hero;
	}

	public Monster monsterAttackLogic(Hero hero, Monster monster){

		int atk = hero.getAtk();

		int o_result = monster.getHp() - atk;

		if(monster.getHp() >= atk){
			monster.setHp(o_result);
		} else if(monster.getHp() < atk){
			monster.setHp(0);
		}
		return monster;
	}

}