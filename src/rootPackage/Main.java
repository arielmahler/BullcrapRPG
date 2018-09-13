package rootPackage;

import java.util.Scanner;
import classPackage.*;
import enemyPackage.*;

public class Main {
	public static void printArray(String[] options) {
		int i = 0;
		while (i < options.length) {
			System.out.print((i + 1) + ". ");
			System.out.println(options[i]);
			i++;
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Boolean inputLock = true;
		String[] classDesc = new String[] {"Warrior, a headstrong heavy fighter, relying on his sheer strength"}; // class list and description
		System.out.println("Hey...You...What is your name???");
		String playerName = input.nextLine();
		System.out.println(playerName + ", you are about to embark on a journey of a lifetime...");
		System.out.println("But first, I want to know something:");
		CharacterRoot player = null;
		while (inputLock) {
			System.out.println("What kind of fighter are you?");
			System.out.println("");
			printArray(classDesc);
			int userInput = input.nextInt();
			switch (userInput) {
				case 1:
					player = new Warrior(playerName);
					System.out.println("Very well, I see you are a warrior.");
					inputLock = false;
					break;
				default:
					System.out.println("Sorry, I don't think I understood you.");
					break;
			}
		}
		System.out.println("This game's combat is turn-based, where you and an enemy will take turns attacking each other.");
		System.out.println("in order to see how you do, here is a quick, easy, fight to begin you with");
		System.out.println("");
		Enemy firstBadGuy = new Blob(1);
		FightEngine.Fight(player, firstBadGuy, input);
		System.out.println("");
		System.out.println("See? It's not so hard!");
		System.out.println("Oh, wait! you don't have any health potions!");
		System.out.println("Here, have some.");
		int i = 0;
		while (i < 4) {
			Shop.addPotion(player);
			i++;
		}
		System.out.println("At the moment, there is no available story, because I suck at storytelling. but, you can fight bad guys!");
		String[] yesNo = new String[] {"Yes", "No"};
		System.out.println("What do you think?");
		printArray(yesNo);
		inputLock = true;
		Boolean fightLock = false;
		while(inputLock) {
			int userInput = input.nextInt();
			switch (userInput) {
				case 1:
					inputLock = false;
					fightLock = true;
					System.out.println("Sweet! Here we go!");
					break;
				case 2:
					inputLock = false;
					System.out.println("Fine... Be that way!");
					break;
				default:
					System.out.println("That isn't an option...");
			}
		}
		while(fightLock) {
			Enemy opponent = FightEngine.generateEnemyRandom(player);
			FightEngine.Fight(player, opponent, input);
			System.out.println("Play again?");
			System.out.println("1) Yes");
			System.out.println("2) No");
			inputLock = true;
			while(inputLock) {
				int userInput = input.nextInt();
				switch(userInput) {
				case 1:
					fightLock = true;
					inputLock = false;
					break;
				case 2:
					fightLock = false;
					inputLock = false;
					break;
				default:
					System.out.println("Please use one of the numbers available.");
					break;
				}
			}
		}
	}
}