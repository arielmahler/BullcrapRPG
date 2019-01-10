package classPackage;

import movesPackage.*;
import rootPackage.Main;

public class Warrior extends CharacterRoot {
	public Move[] fullWarriorMoveset;
	
	public Warrior(String name) {
		super(name);
		this.className = "Warrior";
		this.health = 100;
		this.damage = 15;
		DamageMove headbutt = new DamageMove("Headbutt", "You finally use your head for something, hitting the enemy for 20 damage", 20);	
		HealMove restorativeAle = new HealMove("Restorative Ale", "Consume some of your fine booze, recovering 15 health", 15);
		BoostMove defensePosition = new BoostMove("Defense Position", "Noticing an upcoming attack, you prepare yourself for it. Resist 50% of damage", 50);
		DamageMove dirtyFighting = new DamageMove("Dirty Fighting", "Swallow your pride and scratch out an attack for 150 damage", 150);
		DamageMove berserker = new DamageMove("Berserker", "Your vision goes red as you channel your strength into your blade, dispatching any enemy in your way", 1000);
		this.fullWarriorMoveset = new Move[] {
				headbutt,
				restorativeAle,
				defensePosition,
				dirtyFighting,
				berserker
		};
	}
	
	public void warriorMoves(int level) throws Exception {
		if(level == 2) {
			this.Moveset[0] = fullWarriorMoveset[0];
			Main.narrationPrintDelay("You learned Headbutt!");
		} else if(level == 4) {
			this.Moveset[1] = fullWarriorMoveset[1];
			Main.narrationPrintDelay("You learned Restorative Ale!");
		} else if(level == 5) {
			this.Moveset[2] = fullWarriorMoveset[2];
			Main.narrationPrintDelay("You learned Defense Position!");
		} else if(level == 6) {
			this.Moveset[3] = fullWarriorMoveset[3];
			Main.narrationPrintDelay("You learned Dirty Fighting!");
		} else if(level == 10) {
			this.Moveset[4] = fullWarriorMoveset[4];
			Main.narrationPrintDelay("You learned Berserker!");
			Main.narrationPrintDelay("You've learned all moves! I'm so proud!");
		}
	}
}
