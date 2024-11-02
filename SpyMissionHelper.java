package SpyMission;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SpyMissionHelper {
	private static final String GRID_ALPHABET = "abcdefg";
	private static final int GRID_WIDTH = 7;
	private static final int BOARD_SIZE = 49;
	private static final int MAX_TRIES = 200;
	static final int ROW_INCREMENT = 1;
	static final int COLUMN_INCREMENT = GRID_WIDTH;

	private final int[] missionGrid = new int[BOARD_SIZE];
	private final Random spyRandomizer = new Random();
	private int spyCount = 0;

	public String getAgentInput(String message) {
		System.out.print(message + ": ");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine().toLowerCase();
	}

	public ArrayList<String> placeSpy(int spyUnitSize) {
		// holds index to grid (0 - 48)
		int[] spyCoords = new int[spyUnitSize];
		int attempts = 0;
		boolean success = false; // current candidate co-ordinates
		// current attempts counter
		// flag = found a good location?
		spyCount++;
		int increment = getIncrement(); // nth Spy to place
		// alternate vert & horiz alignment
		while (!success & attempts++ < MAX_TRIES) {
			int location = spyRandomizer.nextInt(BOARD_SIZE); // main search loop
			// get random starting point
			for (int i = 0; i < spyCoords.length; i++) {
				spyCoords[i] = location;
				location += increment; // create array of proposed coords
				// put current location in array
				// calculate the next location
			}
			// System.out.println("Trying: " + Arrays.toString(spyCoords));
			if (targetFits(spyCoords, increment)) { // spy fits on the grid?
				success = isLocationClear(spyCoords); // ...and locations aren't taken?
			} // end loop
		} // end while
		markTargetLocation(spyCoords); // coords passed checks, save
		ArrayList<String> alphaCells = convertCoordsToAlphaFormat(spyCoords);
		// System.out.println("Placed at: "+ alphaCells);
		return alphaCells;
	}

	private boolean targetFits(int[] targetCoords, int increment) {
		int finalLocation = targetCoords[targetCoords.length - 1];
		if (increment == ROW_INCREMENT) {
			// check end is on same row as start
			return calcRowFromIndex(targetCoords[0]) == calcRowFromIndex(finalLocation);
		} else {
			return finalLocation < BOARD_SIZE; // check end isn't off the bottom
		}
	} // end targetFits

	private boolean isLocationClear(int[] targetCoords) {
		for (int coord : targetCoords) { // check all potential positions
			if (missionGrid[coord] != 0) { // this position already taken
				// System.out.println("position: " + coord + " already taken.");
				return false; // NO success
			}
		}
		return true;
	} // end coordsAvailable

	private void markTargetLocation(int[] targetCoords) {
		for (int index : targetCoords) {
			missionGrid[index] = 1; // there were no clashes, yay!
			// mark grid position as 'used'
		}
	} // end savePositionToGrid

	private ArrayList<String> convertCoordsToAlphaFormat(int[] spyCoords) {
		ArrayList<String> alphaCells = new ArrayList<String>();
		for (int index : spyCoords) { // for each grid coordinate
			String alphaCoords = getAlphaCoordsFromIndex(index); // turn it into an "a0" style
			alphaCells.add(alphaCoords); // add to a list
		}
		return alphaCells; // return the "a0"-style coords
	} // end convertCoordsToAlphaFormat

	private String getAlphaCoordsFromIndex(int index) {
		int row = calcRowFromIndex(index); // get row value
		int column = index % GRID_WIDTH; // get numeric column value
		String letter = GRID_ALPHABET.substring(column, column + 1); // convert to letter
		return letter + row;
	} // end getAlphaCoordsFromIndex

	private int calcRowFromIndex(int index) {
		return index / GRID_WIDTH;
	} // end calcRowFromIndex

	private int getIncrement() {
		if (spyCount % 2 == 0) { // if EVEN Spy
			return ROW_INCREMENT; // place horizontally
		} else { // else ODD
			return COLUMN_INCREMENT; // place vertically
		}
	} // end getIncrement
} // end class
