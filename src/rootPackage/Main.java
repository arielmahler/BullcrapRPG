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
	}
}