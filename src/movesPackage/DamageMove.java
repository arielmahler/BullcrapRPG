package movesPackage;

public class DamageMove extends Move{
	public DamageMove(String name, String desc, int value) {
		super(name, desc, value);
		this.givenEffect = "Damage";
	}
}
