package rootPackage;

import classPackage.*;
import itemPackage.*;

public class Shop {
	public static void addPotion(CharacterRoot player) {
		int i = 0;
		int index = -1;
		while(i <= 9 && index == -1) {
			if(player.Medicine[i] == null) {
				index = i;
			}
			i++;
		}
		if(index != -1) {
			player.Medicine[index] = new HealthPotion();
			System.out.println(player.name + " received a health potion!");
		} else {
			System.out.println("You don't have any space!");
		}
	}
}
