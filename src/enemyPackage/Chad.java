package enemyPackage;

public class Chad extends Enemy {
	public Chad(int level) {
		this.name = "Absolute Chad";
		this.health = (int) (69*(level));
		this.level = level;
		this.experienceValue = 420*level;
		this.damage = (int)(6.9*(level*.5));
	}
}
