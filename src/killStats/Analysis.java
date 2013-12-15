package killStats;

import java.util.ArrayList;
import java.util.TreeMap;

import dataStructures.Kill;
import dataStructures.Pilot;

public class Analysis {

	/**
	 * finds victim pilot(s) on a killboard
	 * 
	 * @param killboard
	 *            arraylist<Kill> to represent a killboard of a
	 *            pilot/corp/alliance/faction
	 * @param victim
	 *            String that represents a victom pilot/corp/alliance/faction
	 * @return a list of Kills in form of an ArrayList<Kill>
	 */
	public static ArrayList<Kill> findVictim(ArrayList<Kill> killboard,
			String victim) {

		ArrayList<Kill> resultBoard = new ArrayList<Kill>();
		for (Kill K : killboard) {
			if (K.getVictim().findAttribute(victim)) {
				resultBoard.add(K);
			}
		}
		return resultBoard;
	}

	/**
	 * find attacking pilot(s) on a killboard
	 * 
	 * @param killboard
	 *            arraylist<Kill> to represent a killboard of a
	 *            pilot/corp/alliance/faction
	 * @param attacker
	 *            String that represents an attacking
	 *            pilot/corp/alliance/faction
	 * @return a list of Kills in form of an ArrayList<Kill>
	 */
	public static ArrayList<Kill> findAttacker(ArrayList<Kill> killboard,
			String attacker) {

		ArrayList<Kill> resultBoard = new ArrayList<Kill>();
		for (Kill K : killboard) {
			ArrayList<Pilot> attackingPilots = K.gettAttackingPilots();
			for (Pilot P : attackingPilots) {
				if (P.findAttribute(attacker)) {
					resultBoard.add(K);
				}
			}
		}
		return resultBoard;
	}

	/**
	 * returns a treemap<Pilot, Integer> which represents the occurences of
	 * assitants on killmails of a certain pilot
	 * 
	 * @param killboard
	 *            killboard of pilot to be searched
	 * @param nameOfPilot
	 *            name of pilot to be searched
	 * @return Treemap<Pilot, Integer> occurrence
	 */
	public static TreeMap<Pilot, Integer> findMostFrequentAssistants(
			ArrayList<Kill> killboard, String nameOfPilot) {
		TreeMap<Pilot, Integer> occurrence = new TreeMap<Pilot, Integer>();
		for (Kill K : killboard) {
			if (K.getVictim().getPilot().getCharacterName()
					.equalsIgnoreCase(nameOfPilot)) {
				continue;
			} else {
				ArrayList<Pilot> attackers = K.gettAttackingPilots();
				for (Pilot P : attackers) { // excludes the pilot himself from
											// the list of assitants, and some
											// faulty data like null values and
											// empty pilotnames aka rats
					if (P.getCharacterName().equalsIgnoreCase(nameOfPilot)
							|| P.getCharacterName() == null
							|| P.getCharacterName().equalsIgnoreCase("")) {
						continue;
					}
					Integer count = occurrence.get(P);
					if (count == null) {
						occurrence.put(P, 1);
					} else {
						occurrence.put(P, count + 1);
					}
				}
			}
		}
		return occurrence;
	}
}
