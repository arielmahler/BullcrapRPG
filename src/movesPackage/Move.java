package movesPackage;

import classPackage.*;
import enemyPackage.*;

public class Move {
	
	public String name;
	protected String desc;
	protected String givenEffect;
	protected int value;
	
	
	public Move(String name, String desc, int value) {
		this.name = name;
		this.desc = desc;
		this.value = value;
	}
	public void useMove(CharacterRoot player, Enemy enemy) {
		if(this.givenEffect.equals("Heal")) {
			player.health += this.value;
		} else if(this.givenEffect.equals("Damage")) {
			enemy.health -= this.value;
		} else if(this.givenEffect.equals("Boost")) {
			//Don't know what I want boosts to do yet
		}
		System.out.println(player.name + " used " + this.name + "!");
	}
}
