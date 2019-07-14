package movesPackage;

public class HealMove extends Move{
		
	public HealMove(String name, String desc, int value) {
		super(name, desc, value);
		this.givenEffect = "Heal";
	}
}
