package rootPackage;

import java.util.Scanner;
import java.util.Random;
import classPackage.*;
import enemyPackage.*;

public class FightEngine {

	public static Boolean usePotion(CharacterRoot player) {
		int i = 9;
		int nearestPotion = -1;
		Boolean fightLock = true;
		while(i >= 0 && nearestPotion == -1) {
			if(player.Medicine[i] != null) {
				nearestPotion = i;
			}
			i--;
		}
		if(nearestPotion != -1){
			player.health += player.Medicine[nearestPotion].healing;
			System.out.println("You gained " + player.Medicine[nearestPotion].healing + " health");
			player.Medicine[nearestPotion] = null;
			fightLock = false;
		} else {
			System.out.println("You don't have any!");
		}
		return fightLock;
	}
	
	public static Enemy generateEnemyRandom(CharacterRoot player) {
		Random numberGenerate = new Random();
		Enemy enemyReturned = null;
		int enemyType = numberGenerate.nextInt(2);
		int enemyLevel = 1 + numberGenerate.nextInt(player.level - 1);
		switch(enemyType) {
			case 0: //blob
				enemyReturned = new Blob(enemyLevel);
				break;
			case 1: //Neckbeard
				enemyReturned = new Neckbeard(enemyLevel);
				break;
			case 2: //Thief
				enemyReturned = new Thief(enemyLevel);
				break;
		}
		return enemyReturned;
	}
	
	public static void Fight(CharacterRoot player, Enemy enemy, Scanner input) {
		String[] fightOptions = new String[] {"Attack","Use a potion"}; //options during fight
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
					case 2:
						fightLock = usePotion(player);
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
			System.out.println("");
		}
		if(player.health <= 0) {
			System.out.println("You lose!");
		} else {
			System.out.println("You win!");
			player.experiencePoint += enemy.experienceValue;
			System.out.println("Gained " + enemy.experienceValue + " EXP");
			while(player.experiencePoint >= player.experiencePointThreshold){
				player.levelUp();
			}
		}
	}
}
