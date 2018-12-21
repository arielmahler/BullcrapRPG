package rootPackage;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import classPackage.*;
import enemyPackage.*;

public class Main {
	public static void printArray(String[] options, Boolean list) {
		int i = 0;
		while (i < options.length) {
			if(list) {
				System.out.print((i + 1) + ". ");
			}
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
		String[] classDesc = new String[] {"The Warrior, a headstrong heavy fighter, relying on his sheer strength","The Assassin, moves like an eagle, strikes like... well, an eagle."}; // class list and description
		printWithDelays("Hey... You...", TimeUnit.MILLISECONDS, 200);
		printWithDelays("What's your name?", TimeUnit.MILLISECONDS, 75);
		String playerName = input.nextLine();
		printWithDelays(playerName + ", eh?", TimeUnit.MILLISECONDS, 75);
		narrationPrintDelay("Prepare yourself for the journey of a lifetime!");
		System.out.println("But first, I want to know something:");
		CharacterRoot player = null;
		TimeUnit.MILLISECONDS.sleep(500);
		while (inputLock) {
			System.out.println("What kind of fighter are you?");
			System.out.println("");
			printArray(classDesc, true);
			int userInput = input.nextInt();
			switch (userInput) {
				case 1:
					player = new Warrior(playerName);
					narrationPrintDelay("I see you've chosen the warrior.");
					inputLock = false;
					break;
				case 2:
					player = new Assassin(playerName);
					narrationPrintDelay("I see you've chosen the assassin.");
					inputLock = false;
					break;
				default:
					narrationPrintDelay("Sorry, I don't think I understood you.");
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
					break;
			}
		}
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //This refreshes tabs
		narrationPrintDelay("This game's combat is turn-based, so when fighting you will be taking turns with the enemy.");
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
			TimeUnit.MILLISECONDS.sleep(150);
			i++;
		}
		narrationPrintDelay("And with this, your story begins");
	}
}