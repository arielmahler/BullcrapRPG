package rootPackage;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
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
	public static void printWithDelays(String phrase, TimeUnit unit, long delay) 
			throws Exception {
		for (char ch:phrase.toCharArray()) {
			System.out.print(ch);
			unit.sleep(delay);
		}
		System.out.println("");
	}
	public static void narrationPrintDelay(String phrase)
			throws Exception {
		for (char ch:phrase.toCharArray()) {
			System.out.print(ch);
			TimeUnit.MILLISECONDS.sleep(25);
		}
		System.out.println("");
	}

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		Boolean inputLock = true;
		String[] classDesc = new String[] {"Warrior, a headstrong heavy fighter, relying on his sheer strength"}; // class list and description
		printWithDelays("Hey... You...", TimeUnit.MILLISECONDS, 200);
		printWithDelays("What's your name?", TimeUnit.MILLISECONDS, 75);
		String playerName = input.nextLine();
		printWithDelays(playerName + ", eh?", TimeUnit.MILLISECONDS, 75);
		narrationPrintDelay("Prepare yourself for the journey of a lifetime!");
		System.out.println("But first, I want to know something:");
		CharacterRoot player = null;
		TimeUnit.MILLISECONDS.sleep(300);
		while (inputLock) {
			System.out.println("What kind of fighter are you?");
			System.out.println("");
			printArray(classDesc);
			int userInput = input.nextInt();
			switch (userInput) {
				case 1:
					player = new Warrior(playerName);
					inputLock = false;
					break;
				default:
					System.out.println("Sorry, I don't think I understood you.");
					break;
			}
		}
		narrationPrintDelay("This game's combat is turn-based, where you and an enemy will take turns attacking each other.");
		narrationPrintDelay("to show you what I mean, I want you to kill this little guy:");
		System.out.println("");
		Enemy firstBadGuy = new Blob(1);
		TimeUnit.MILLISECONDS.sleep(250);
		FightEngine.Fight(player, firstBadGuy, input);
		System.out.println("");
		narrationPrintDelay("See? It's not so hard!");
		narrationPrintDelay("Oh, wait! you don't have any health potions!");
		narrationPrintDelay("Here, have some.");
		TimeUnit.MILLISECONDS.sleep(250);
		int i = 0;
		while (i < 4) {
			Shop.addPotion(player);
			TimeUnit.MILLISECONDS.sleep(20);
			i++;
		}
/*		System.out.println("At the moment, there is no available story, because I suck at storytelling. but, you can fight bad guys!");
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
		} */
	}
}