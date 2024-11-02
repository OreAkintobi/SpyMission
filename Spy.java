package SpyMission;

import java.util.ArrayList;

class Spy {
	private String spyCodeName;
	private ArrayList<String> spyLocation;

	public String checkTargetStatus(String guess) {
		String result = "miss";
		int index = spyLocation.indexOf(guess);

		if (index >= 0) {
			spyLocation.remove(index);

			if (spyLocation.isEmpty()) {
				result = "kill";
			} else {
				result = "hit";
			}
		}
		return result;
	}

	public void setSpyLocation(ArrayList<String> locs) {
		spyLocation = locs;
	}

	public void setSpyCodeName(String name) {
		spyCodeName = name;
	}

	public String getSpyCodeName() {
		return spyCodeName;
	}
}
