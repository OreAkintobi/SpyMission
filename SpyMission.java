package SpyMission;

import java.util.ArrayList;

public class SpyMission {
	SpyMissionHelper missionHelper = new SpyMissionHelper();
	static ArrayList<Spy> spies = new ArrayList<Spy>();
	private int attempts = 0;
	private int MAX_ATTEMPTS = 20;

	private void initializeMission() {
		// create and initialize the Spy objects with names and locations. Display
		// brief instructions to the user

		// create and name spies
		Spy spy1 = new Spy();
		spy1.setSpyCodeName("James Bond");
		Spy spy2 = new Spy();
		spy2.setSpyCodeName("Ethan Hunt");
		Spy spy3 = new Spy();
		spy3.setSpyCodeName("Natasha Romanoff");

		// add spies to spies ArrayList
		spies.add(spy1);
		spies.add(spy2);
		spies.add(spy3);

		System.out.println(
				"Your mission, should you choose to accept it, is to eliminate three Spies by guessing their locations for focused attacks with pinpoint accuracy.");
		System.out
				.println("The spies are " + spy1.getSpyCodeName() + ", " + spy2.getSpyCodeName() + " and "
						+ spy3.getSpyCodeName() + ".");
		System.out.println("Try to eliminate them all in " + MAX_ATTEMPTS + " guesses or fewer");

		// place spies and set their locations
		for (Spy spy : spies) {
			ArrayList<String> spyLocation = missionHelper.placeSpy(3);
			spy.setSpyLocation(spyLocation);
		}
	}

	private void startMission() {
		// to ask the player for guesses and calls the evaluateAgentGuess() method until
		// all
		// the Spy objects are removed from play.
		while (!spies.isEmpty()) {
			String agentGuess = missionHelper.getAgentInput("Enter a guess");
			evaluateAgentGuess(agentGuess);
		}
		endMission();
	}

	private void evaluateAgentGuess(String guess) {
		// to loop through all remaining Spy objects and call each Spy object’s
		// checkTargetStatus() method & find out if there’s a hit (and kill) on any Spy
		attempts++;
		String result = "miss";

		for (Spy spy : spies) {
			result = spy.checkTargetStatus(guess);
			if (result.equals("hit")) {
				System.out.println(spy.getSpyCodeName() + " hit!");
				break;
			}
			if (result == "kill") {
				spies.remove(spy);
				System.out.println(spy.getSpyCodeName() + " eliminated!");
				break;
			}
			if (result == "miss") {
				System.out.println(result);
				break;
			}
		}
	}

	private void endMission() {
		// to print a message about the user’s performance, based on how many guesses it
		// took to sink all of the Spy objects.
		System.out.println("All Spies are dead! Mission Accomplished.");
		System.out.println("==================================== MISSION REPORT ====================================");
		if (attempts <= MAX_ATTEMPTS) {
			System.out.println("Good job! It only took you " + attempts + " guesses to win!");
		} else {
			System.out
			.println("Finally! It took you " + attempts + " guesses to finish. Maybe next time you can do better.");
		}
	}
	
	public static void forfeit() {
		// to print a message forfeiting the mission
		System.out.println("");
		System.out.println("==================================== MISSION REPORT ====================================");
		System.out.println("Mission Failed. " + (3 - spies.size()) + " spies eliminated, " + spies.size() + " escaped.");
		System.out.println("I guess you can't cut it at this level.");
	}

	public static void main(String[] args) {
		// Register a shutdown hook to handle the forfeit
		Runtime.getRuntime().addShutdownHook(new Thread(() -> forfeit()));
		SpyMission mission = new SpyMission();
		mission.initializeMission();
		mission.startMission();
	}
}
