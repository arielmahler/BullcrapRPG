package rootPackage;

import java.util.Scanner;
import java.util.Random;
import movesPackage.*;
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
			nearestPotion = -1;
			i = 9;
			fightLock = false;
		} else {
			System.out.println("You don't have any!");
		}
		return fightLock;
	}
	
	public static void displayMoveset(CharacterRoot player) {
		int i = 0;
		while(i < 5) {
			if(!player.Moveset[i].name.equals(null)) {
				System.out.println((i + 1) + ". " + player.Moveset[i].name);
			}
			i++;
		}
	}
	/*
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
	*/
	
	public static void Fight(CharacterRoot player, Enemy enemy, Scanner input) throws Exception {
		String[] fightOptions = new String[] {"Attack","Moves","Use a potion"}; //options during fight
		Main.narrationPrintDelay("A level " + enemy.level + " " + enemy.name + " has appeared!");
		String[] menu = new String[] {"Level " + enemy.level + " " + enemy.name + ":","Health: " + enemy.health,"","","Player: ","Health: " + player.health};
		while(player.health > 0 && enemy.health > 0){
			Boolean fightLock = true; //for navigating through while loops in menus
			while(fightLock) {
				Main.printArray(menu, false); //print the stats
				Main.printArray(fightOptions, true); //print the options the player has
				int userInput = input.nextInt();
				switch(userInput) {
					case 1: //player chooses regular attack
						enemy.health -= player.damage;
						Main.narrationPrintDelay(player.name + " attacks the enemy " + enemy.name + " for " + player.damage + "!");
						fightLock = false;
						break;
					case 2: //player chooses to use a move
						if(player.level >= 2) {
							Boolean moveLock = true; //navigated the move code
							displayMoveset(player);
							System.out.println("0. Return");
							while(moveLock) {
								userInput = input.nextInt();
								switch(userInput) {
									case 1:
										player.Moveset[0].useMove(player, enemy);
										fightLock = false;
										moveLock = false;
										break;
									case 2:
										if (player.level >= 4) {
											player.Moveset[1].useMove(player, enemy);
											fightLock = false;
											moveLock = false;
										} else {
											System.out.println("That's not an option!");
										}
										break;
									case 3:
										if(player.level >= 5) {
											player.Moveset[2].useMove(player, enemy);
											fightLock = false;
											moveLock = false;
										} else {
											System.out.println("That's not an option!");
										}
										break;
									case 4:
										if(player.level >= 6) {
											player.Moveset[3].useMove(player, enemy);
											fightLock = false;
											moveLock = false;
										} else {
											System.out.println("That's not an option!");
										}
										break;
									case 5:
										if(player.level >= 10) {
											player.Moveset[4].useMove(player, enemy);
											fightLock = false;
											moveLock = false;
										} else {
											System.out.println("That's not an option!");
										}
										break;
									case 0:
										moveLock = false;
										break;
									default:
										System.out.println("That's not an option!");
										break;
								}	
							}
						} else {
							System.out.println("You haven't learned any moves!");
						}
						break;
					case 3:
						fightLock = usePotion(player);
						break;
					default:
						Main.narrationPrintDelay("That's not an option!");
						new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
						break;
				}
			}
			if(enemy.health > 0){
				player.health -= enemy.damage;
				Main.narrationPrintDelay(enemy.name + " attacks " + player.name + " for " + enemy.damage + "!");
			}
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		if(player.health <= 0) {
			Main.narrationPrintDelay("You lose!");
		} else {
			Main.narrationPrintDelay("You win!");
			player.experiencePoint += enemy.experienceValue;
			Main.narrationPrintDelay("Gained " + enemy.experienceValue + " EXP");
			while(player.experiencePoint >= player.experiencePointThreshold){
				player.levelUp();
				if(player.className.equals("Assassin")) {
					((Assassin) player).assassinMoves(player.level);
				} else if(player.className.equals("Warrior")) {
					((Warrior) player).warriorMoves(player.level);
				}
			}
		}
	}
}
