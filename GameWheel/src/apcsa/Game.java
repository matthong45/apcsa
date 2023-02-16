package apcsa;

import java.util.ArrayList;

public class Game {
	public static void play(GameWheel g) {
		// Implement the play method here
		// Create an ArrayList for spin results and variables to calculate prize total
		ArrayList<Slice> spins = new ArrayList<Slice>();
		int prize = 0;
		String color0 = "";
		boolean doubleIt = true;

		for (int i = 0; i < 3; i++) {
			// Get the result of the next spin, add to array and update prize
			Slice s = g.spinWheel();
			spins.add(s);
			prize += s.getPrizeAmount();

			/*
			 * On the first run of this loop, the variable color0 is set to the color of the
			 * result of the spin. Then on the subsequent runs, the value of doubleIt is set
			 * to false if the spin does not match color0. Only if all other spins match the
			 * first color will doubleIt be true after all runs of the loop.
			 */
			if (i == 0)
				color0 = s.getColor();
			else if (!s.getColor().equals(color0))
				doubleIt = false;
		}

		// Double prize money if color is the same for all spins
		if (doubleIt)
			prize *= 2;

		// Print total prize and results of each spin
		System.out.println("Total prize money: $" + prize + "\n");
		for (int i = 0; i < 3; i++)
			System.out.println("Spin " + (i + 1) + " - " + spins.get(i));

		// Print if color is the same for all spins
		if (doubleIt)
			System.out.println("Three " + color0 + "s = double your money!");
	}

}