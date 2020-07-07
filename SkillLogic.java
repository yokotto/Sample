package model;

import model.Hero;
import model.Monster;
import java.util.List;
//攻撃時のメソッド
public class SkillLogic extends Skill{
	public Hero heroSkillLogic(Hero hero, Monster monster, int skillNo){		
		
		SkillGetParameter sgp= new SkillGetParameter();			

			Skill skill = sgp.getParameter(skillNo);
		
			String target = skill.getTarget();
			String effect = skill.getEffect();
			int mp = skill.getMp();
			//target
			//myself,opponent,o_all,ally,a_all
			//effect
			//atk, hp
			//複数戦闘が可能になったら改良してください。
			if(target.equals("myself") || target.equals("ally") || target.equals("a_all")){
				if(effect.equals("atk")){
					hero.setAtk(hero.getAtk() + skill.getEffectSize());
					hero.setMp(hero.getMp() - mp);
				} else if(effect.equals("hp")){
					hero.setHp(hero.getHp() + skill.getEffectSize());
					hero.setMp(hero.getMp() - mp);
				}
			}
		//}
		return hero;
	}

	public Monster monsterSkillLogic(Hero hero, Monster monster, int skillNo){

		SkillGetParameter sgp= new SkillGetParameter();			

			Skill skill = sgp.getParameter(skillNo);
			
			String target = skill.getTarget();
			String effect = skill.getEffect();
			int mp = skill.getMp();
			//target
			//myself,opponent,o_all,ally,a_all
			//effect
			//atk, hp
			//複数戦闘が可能になったら改良してください。
			if(target.equals("opponent") || target.equals("o_all")){
				if(effect.equals("atk")){
					monster.setAtk(monster.getAtk() - skill.getEffectSize());
					hero.setMp(hero.getMp() - mp);
				} else if(effect.equals("hp")){
					if(monster.getHp() >= skill.getEffectSize()){
						monster.setHp(monster.getHp() - skill.getEffectSize());
						hero.setMp(hero.getMp() - mp);
					} else if(monster.getHp() < skill.getEffectSize()){
						monster.setHp(0);
						hero.setMp(hero.getMp() - mp);
					}
				}
			}
		//}
		return monster;
	}
}