package enemyPackage;

public class Thief extends Enemy {
	public Thief(int level) {
		this.name = "Thief";
		this.health = (int)(40*(level * .5));
		this.level = level;
		this.experienceValue = 15*level;
		this.damage = (int)(15*(level * .5));
	}
}
