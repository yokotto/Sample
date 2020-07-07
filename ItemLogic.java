package model;

import model.Hero;
import model.Monster;
import java.util.List;
//�U�����̃��\�b�h
public class ItemLogic extends Skill{
	public Hero heroItemLogic(Hero hero, Monster monster, int itemNo){		
		
		ItemGetParameter igp= new ItemGetParameter();			

		Item item = igp.getParameter(itemNo);
		
		//hp,mp,atk�̕ω�
		if(item.getEffect().equals("hp")){
			hero.setHp(hero.getHp() + item.getEffectSize());
		} else if(item.getEffect().equals("mp")){
			hero.setMp(hero.getMp() + item.getEffectSize());
		} else if(item.getEffect().equals("atk")){
			hero.setAtk(hero.getAtk() + item.getEffectSize());
		}
		//�X�e�[�^�X�̉�
		if(item.getStatusEffect().equals("��") && hero.getStatus().equals("��")){
			hero.setStatus("����");
		} else if(item.getStatusEffect().equals("�Ώ�") && hero.getStatus().equals("�Ώ�")){
			hero.setStatus("����");
		} else if(item.getStatusEffect().equals("����") && hero.getStatus().equals("����")){
			hero.setStatus("����");
		}
	return hero;
	}

	public Monster monsterItemLogic(Hero hero, Monster monster, int itemNo){

		ItemGetParameter igp= new ItemGetParameter();			

		Item item = igp.getParameter(itemNo);

		//hp,mp,atk�̕ω�
		if(item.getEffect().equals("hp")){
			monster.setHp(monster.getHp() + item.getEffectSize());
		} else if(item.getEffect().equals("mp")){
			monster.setMp(monster.getMp() + item.getEffectSize());
		} else if(item.getEffect().equals("atk")){
			monster.setAtk(monster.getAtk() + item.getEffectSize());
		}
		//�X�e�[�^�X�̉�
		if(item.getStatusEffect().equals("��") && monster.getStatus().equals("��")){
			monster.setStatus("����");
		} else if(item.getStatusEffect().equals("�Ώ�") && monster.getStatus().equals("�Ώ�")){
			monster.setStatus("����");
		} else if(item.getStatusEffect().equals("����") && monster.getStatus().equals("����")){
			monster.setStatus("����");
		}
	return monster;
	}
}