package rootPackage;

import java.util.Scanner;
import classPackage.*;
import enemyPackage.*;

public class FightEngine {

	public static void Fight(CharacterRoot player, Enemy enemy, Scanner input) {
		String[] fightOptions = new String[] {"Attack"}; //options during fight
		System.out.println("A level " + enemy.level + " " + enemy.name + " has appeared!");
		while(player.health > 0 && enemy.health > 0){
			Boolean fightLock = true; //for navigating through while loops in menus
			System.out.println("Level " + enemy.level + " " + enemy.name + ":");
			System.out.println("Health: " + enemy.health);
			System.out.println("");
			System.out.println("");
			while(fightLock) {
				System.out.println("Player: ");
				System.out.println("Health: " + player.health);
				Main.printArray(fightOptions);
				int userInput = input.nextInt();
				switch(userInput) {
					case 1:
						enemy.health -= player.damage;
						System.out.println(player.name + " attacks the enemy " + enemy.name + " for " + player.damage + "!");
						fightLock = false;
						break;
					
					default:
						System.out.println("That's not an option!");
						break;
				}
			}
			if(enemy.health > 0){
				player.health -= enemy.damage;
				System.out.println(enemy.name + " attacks " + player.name + " for " + enemy.damage + "!");
			}
		}
		if(player.health <= 0) {
			System.out.println("You lose!");
		} else {
			System.out.println("You win!");
			player.experiencePoint += enemy.experienceValue;
			System.out.println("Gained " + enemy.experienceValue + " EXP");
		}
	}
}
