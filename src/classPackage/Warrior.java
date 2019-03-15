package classPackage;

import itemPackage.*;

public class Warrior extends CharacterRoot {
	public Warrior(String name) {
		this.className = "Warrior";
		this.name = name;
		this.level = 1;
		this.health = 100;
		this.damage = 15;
		this.experiencePoint = 0.0;
		this.experiencePointThreshold = 30.0;
		this.Inventory = new Item[10];
		this.Medicine = new Item[10];
	}
}
