package classPackage;

import itemPackage.*;

public class Warrior extends CharacterRoot {
	public Warrior(String name) {
		super();
		this.name = name;
		this.level = 1;
		this.health = 100;
		this.damage = 15;
		this.experiencePoint = 0.0;
		this.experiencePointThreshold = 30.0;
	}
}
