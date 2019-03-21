package rootPackage;

import java.util.Scanner;
import java.util.Random;
import classPackage.*;
import enemyPackage.*;

public class FightEngine {

	public static void Fight(CharacterRoot player, Enemy enemy, Scanner input) throws Exception {
		String[] fightOptions = new String[] {"Attack","Special Moves","Use a potion"}; //options during fight
		Main.narrationPrintDelay("A level " + enemy.level + " " + enemy.name + " has appeared!");
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
					case 1: //regular attack
						enemy.health -= player.damage;
						Main.narrationPrintDelay(player.name + " attacks the enemy " + enemy.name + " for " + player.damage + "!");
						fightLock = false;
						break;
					case 2: //Using a special move
						System.out.println("Moves:");
						int i = 0;
						while(i < player.Moveset.length) {
							System.out.print((i + 1) + ". ");
							if(player.Moveset[i] == null) {
								System.out.println("Return");
								break;
							}
							System.out.println(player.Moveset[i].name);
							System.out.println("     " + player.Moveset[i].desc);
							i++;
						}
						Boolean moveLock = true;
						while(moveLock) {
							userInput = input.nextInt();
							switch(userInput) {
								case 1:
									if(player.level >= 2) {
										player.Moveset[0].useMove(player, enemy);
										fightLock = false;
										moveLock = false;
									} else {
										moveLock = false;
									}
									break;
								case 2:
									if(player.level >= 4) {
										player.Moveset[1].useMove(player, enemy);
										fightLock = false;
										moveLock = false;
									} else if (player.level >= 2) {
										moveLock = false;
									} else {
										Main.narrationPrintDelay("That's not an option!");
										fightLock = true;
									}
									break;
								case 3:
									if(player.level >= 5) {
										player.Moveset[2].useMove(player, enemy);
										fightLock = false;
										moveLock = false;
									} else if(player.level >= 4) {
										moveLock = false;
									} else {
										Main.narrationPrintDelay("That's not an option!");
										fightLock = true;
									}
									break;
								case 4:
									if(player.level >= 6) {
										player.Moveset[3].useMove(player, enemy);
										fightLock = false;
										moveLock = false;
									} else if(player.level >= 5) {
										moveLock = false;
									} else {
										Main.narrationPrintDelay("That's not an option!");
										fightLock = true;
									}
									break;
								case 5:
									if(player.level >= 10) {
										player.Moveset[4].useMove(player, enemy);
										fightLock = false;
										moveLock = false;
									} else if(player.level >= 6) {
										moveLock = false;
									} else {
										Main.narrationPrintDelay("That's not an option!");
										fightLock = true;
									}
									break;
								default:
									Main.narrationPrintDelay("That's not an option!");
									moveLock = true;
									break;
							}
						}
						break;
					case 3: //Using a Potion
						fightLock = usePotion(player);
						break;
					default:
						Main.narrationPrintDelay("That's not an option!");
						break;
				}
			}
			if(enemy.health > 0){
				player.health -= enemy.damage;
				Main.narrationPrintDelay(enemy.name + " attacks " + player.name + " for " + enemy.damage + "!");
			}
			System.out.println("");
		}
		if(player.health <= 0) {
			Main.narrationPrintDelay("You lose!");
		} else {
			Main.narrationPrintDelay("You win!");
			player.experiencePoint += enemy.experienceValue;
			Main.narrationPrintDelay("Gained " + enemy.experienceValue + " EXP");
			while(player.experiencePoint >= player.experiencePointThreshold){
				player.levelUp();
			}
		}
	}
	
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
			nearestPotion = -1;
			i = 9;
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
		int enemyLevel = 1 + numberGenerate.nextInt(player.level);
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
	
}
