package enemyPackage;

public class Neckbeard extends Enemy {
	public Neckbeard(int level) {
		this.name = "Neckbeard";
		this.health = (int)(200*(level * .5));
		this.level = level;
		this.experienceValue = 35*level;
		this.damage = (int)(10*(level * .5));
	}
}
