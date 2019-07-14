package classPackage;

import itemPackage.*;
import movesPackage.Move;

public class CharacterRoot {
	public String name;
	public String className;
	public int level;
	public int health;
	public double experiencePoint;
	public double experiencePointThreshold;
	public int damage;
	public Item[] Inventory;
	public Item[] Medicine;
	public Move[] Moveset;
	
	public CharacterRoot() {
		this.Inventory = new Item[10];
		this.Medicine = new Item[10];
		this.Moveset = new Move[5];
	}
	
	public void levelUp() throws Exception {
		this.level++;
		this.health = (int) (this.health*1.75);
		this.damage = (int) (this.damage*1.6);
		this.experiencePoint -= this.experiencePointThreshold;
		this.experiencePointThreshold = this.experiencePointThreshold*1.25;
		System.out.println(this.name + " is now level " + this.level + "!");
	}
	
	public void addItemMedicine(Item item, CharacterRoot player) {
		int i = 0;
		int indexValue = -1;
		while(i < 9 && indexValue == -1) {
			if(player.Medicine[i]!=null) {
				indexValue = i;
			}
			i++;
		}
		if(indexValue == -1) {
			System.out.println("Can't add the item to you medicine pouch!");
		} else {
			player.Medicine[indexValue] = item;
			System.out.println("Item " + item.name + " added to your medicine pouch");
		}
	}
	
	public void addItemInventory(Item item, CharacterRoot player) {
		int i = 0;
		int indexValue = -1;
		while(i < 9 && indexValue == -1) {
			if(player.Inventory[i]!=null) {
				indexValue = i;
			}
			i++;
		}
		if(indexValue == -1) {
			System.out.println("Can't add the item to the inventory!");
		} else {
			player.Inventory[indexValue] = item;
			System.out.println("Item " + item.name + " added to inventory");
		}
	}
}
