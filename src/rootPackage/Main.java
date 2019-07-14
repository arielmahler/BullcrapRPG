package rootPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import classPackage.*;
import enemyPackage.Blob;
import enemyPackage.Enemy;
import itemPackage.HealthPotion;

public class Main {
	public static void main(String[] args) throws Exception {
		
		Properties characters = new Properties();
		InputStream propStream = null;
		PrintWriter writer = null;
		Boolean saveExists = false;
		Scanner input = new Scanner(System.in);
		Boolean inputLock = true;

	//Looking for a save file
		try {
			propStream = new FileInputStream("characters.properties");
			saveExists = true;
		} catch(FileNotFoundException e) {
			System.out.println("No save found.");
			writer = new PrintWriter("characters.properties", "UTF-8");
		}
	//Looking for a save file
		
		CharacterRoot player = null;
		
		if (!saveExists) { //If no existing save exists, run the tutorial
			String[] classDesc = new String[] {"Warrior, a headstrong heavy fighter, relying on his sheer strength","The Assassin, moves like an eagle, strikes like... well, an eagle."}; // class list and description
			printWithDelays("Hey... You...", TimeUnit.MILLISECONDS, 200);
			printWithDelays("What's your name?", TimeUnit.MILLISECONDS, 75);
			String playerName = input.nextLine();
			printWithDelays(playerName + ", eh?", TimeUnit.MILLISECONDS, 75);
			narrationPrintDelay("Prepare yourself for the journey of a lifetime!");
			System.out.println("But first, I want to know something:");
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
					case 2:
						player = new Assassin(playerName);
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
			narrationPrintDelay("Oh, wait! You don't have any health potions!");
			narrationPrintDelay("Here, have some.");
			TimeUnit.MILLISECONDS.sleep(250);
			int i = 0;
			while (i < 4) {
				Shop.addPotion(player);
				TimeUnit.MILLISECONDS.sleep(20);
				i++;
			}
		//Writing the save file
			System.out.println(writeCharacterInfo(player, writer, characters)); 
			writer.close();
			System.out.println("Game Saved.");
		//Writing the save file
		} else { //If there is a save file...
			characters.load(propStream);
			
			player = loadCharacterInfo(characters);
			System.out.println("Save loaded.");
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
						System.out.println(writeCharacterInfo(player, writer, characters)); 
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
/*	RANDOM FIGHT GENERATOR CODE
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
		} */
	}
	
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
	
	public static String writeCharacterInfo(CharacterRoot player, PrintWriter writer, Properties data) {
		writer.println("player.name="+player.name);
		writer.println("player.className="+player.className);
		writer.println("player.level="+player.level);
		writer.println("player.health="+player.health);
		writer.println("player.damage="+player.damage);
		writer.println("player.experiencePoint="+player.experiencePoint);
		writer.println("player.experiencePointThreshold="+player.experiencePointThreshold);
		int i = 0;
		while(i < 10) {
			if(player.Medicine[i]!=null) {
				writer.println("player.Medicine["+i+"]="+player.Medicine[i].itemKey);
			}
			i++;
		}
		i = 0;
		while(i < 10) {
			if(player.Inventory[i]!=null) {
				writer.println("player.Inventory["+i+"]="+player.Inventory[i].itemKey);
			}
			i++;
		}
		return "Character Data Written.";
	}
	
	public static CharacterRoot loadCharacterInfo(Properties data) {
		String playerClass = data.getProperty("player.className");
		CharacterRoot player = null;
		switch(playerClass) {
			case "Warrior":
				player = new Warrior(data.getProperty("player.name"));
				break;
			case "Assassin":
				player = new Assassin(data.getProperty("player.name"));
				break;
		}
		player.level = Integer.parseInt(data.getProperty("player.level"));
		player.health = Integer.parseInt(data.getProperty("player.health"));
		player.damage = Integer.parseInt(data.getProperty("player.damage"));
		player.experiencePoint = Double.parseDouble(data.getProperty("player.experiencePoint"));
		player.experiencePointThreshold = Double.parseDouble(data.getProperty("player.experiencePointThreshold"));
		int i = 0;
		while(i < 10) {
			try {
				int itemKey = Integer.parseInt(data.getProperty("player.Medicine["+i+"]"));
				switch(itemKey) {
					case 1:
						player.Medicine[i] = new HealthPotion();
						break;
				}
			} catch(NumberFormatException e) {
			}
			i++;
		}
		i = 0;
		while(i < 10) {
			try {
				int itemKey = Integer.parseInt(data.getProperty("player.Inventory["+i+"]"));
				switch(itemKey) {
					case 1:
						player.Inventory[i] = new HealthPotion();
						break;
				}
			} catch(NumberFormatException e) {
			}
			i++;
		}
		System.out.println("Character Data Loaded.");
		return player;
	}
}