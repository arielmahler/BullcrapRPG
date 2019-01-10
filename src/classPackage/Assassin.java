package classPackage;

import movesPackage.*;
import rootPackage.Main;

public class Assassin extends CharacterRoot{
	public Move[] fullAssassinMoveset;
	
	public Assassin(String name) {
		super(name);
		this.className = "Assassin";
		this.health = 80;
		this.damage = 25;
		BoostMove assassinStrike = new BoostMove("Assassin's Strike", "Hide before striking at the opportune moment, giving you a 150% damage boost", 150);	
		BoostMove inspectEnemy = new BoostMove("Inspect Enemy", "You find weak spots granting you twice the damage", 200);
		HealMove medicalSalve = new HealMove("Medical Salve", "Use makeshift bandages to heal you for 10 health", 10);
		DamageMove furyBlades = new DamageMove("Fury Blades", "Use your twin daggers to cut the enemy to shreds for 75 damage", 75);
		DamageMove assassination = new DamageMove("Assassination", "Combine all skills you have accrued to efficiently take out the enemy with no mercy. Deals enough damage to kill enemy", 1000);
		this.fullAssassinMoveset = new Move [] {
				assassinStrike,
				inspectEnemy,
				medicalSalve,
				furyBlades,
				assassination,
			};
	}
	public void assassinMoves(int level) throws Exception {
		if(level == 2) {
			this.Moveset[0] = fullAssassinMoveset[0];
			Main.narrationPrintDelay("You learned Assassin's Strike!");
		} else if(level == 4) {
			this.Moveset[1] = fullAssassinMoveset[1];
			Main.narrationPrintDelay("You learned Inspect Enemy!");
		} else if(level == 5) {
			this.Moveset[2] = fullAssassinMoveset[2];
			Main.narrationPrintDelay("You learned Medical Salve!");
		} else if(level == 6) {
			this.Moveset[3] = fullAssassinMoveset[3];
			Main.narrationPrintDelay("You learned Fury Blades!");
		} else if(level == 10) {
			this.Moveset[4] = fullAssassinMoveset[4];
			Main.narrationPrintDelay("You learned Assassination!");
			Main.narrationPrintDelay("You've learned all moves! I'm so proud!");
		}
	}
}
