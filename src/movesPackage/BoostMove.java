package movesPackage;

public class BoostMove extends Move{
	public BoostMove(String name, String desc, int value) {
		super(name, desc, value);
		this.givenEffect = "Boost";
	}
}
