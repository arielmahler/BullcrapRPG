package enemyPackage;

public class Blob extends Enemy{
	public Blob(int level) {
		this.name = "Blob";
		this.level = level;
		this.damage = 7*level;
		this.experienceValue = 5*level;
		this.health = 20*level;
	}
}
