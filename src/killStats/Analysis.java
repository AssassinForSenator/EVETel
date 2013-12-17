package killStats;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import dataStructures.ItemIdList;
import dataStructures.Kill;
import dataStructures.MapHelpers;
import dataStructures.Pilot;
import dataStructures.ShipAndChar;

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

		if (killboard.isEmpty()) {
			return null;
		}
		for (Kill K : killboard) {
			if (K.getVictim().getPilot().getCharacterName()
					.equalsIgnoreCase(nameOfPilot)) {
				continue;
			} else {
				ArrayList<Pilot> attackers = K.gettAttackingPilots();
				for (Pilot P : attackers) { // excludes the pilot himself from
											// the list of assistants, and some
											// faulty data like null values and
											// empty pilotnames aka rats
					if (P.getCharacterName().equalsIgnoreCase(nameOfPilot)
							|| P.getCharacterName() == null
							|| P.getCharacterName().equalsIgnoreCase("")) {
						continue;
					}
					MapHelpers.incrementValue(occurrence, P);
				}
			}
		}
		return occurrence;
	}

	/**
	 * Counts ships on kb by occurence
	 * 
	 * @param killBoard
	 *            SE
	 * @param attribute
	 *            name of the char/corp/alliance/faction you're looking for
	 * @return treemap<int, int> representing shipid as key and occurrence as
	 *         value
	 */
	public static TreeMap<Integer, Integer> getShiptypesIDOccurrence(
			ArrayList<Kill> killBoard, String attribute) {

		TreeMap<Integer, Integer> result = new TreeMap<Integer, Integer>();

		if (killBoard.isEmpty()) {
			return null;
		}

		for (Kill K : killBoard) {
			if (K.getVictim().findAttribute(attribute)) {
				Integer shipID = K.getVictim().getShipId();
				MapHelpers.incrementValue(result, shipID);
			}
			ArrayList<ShipAndChar> attackers = K.getAttackers();
			for (ShipAndChar SnC : attackers) {
				if (SnC.findAttribute(attribute)) {
					Integer shipID = SnC.getShipId();
					MapHelpers.incrementValue(result, shipID);
				}
			}
		}
		return result;
	}

	/**
	 * piggybacks on IDOccurrence
	 * 
	 * @param killBoard
	 *            SE
	 * @param attribute
	 *            see IDOcurrence
	 * @return treemap<string, int>, string being the name of the pilot, value
	 *         the #
	 */
	public static TreeMap<String, Integer> getShiptypesNameOccurrence(
			ArrayList<Kill> killBoard, String attribute) {
		ItemIdList.getInstance();
		TreeMap<Integer, Integer> intermediary = getShiptypesIDOccurrence(
				killBoard, attribute);
		TreeMap<String, Integer> result = new TreeMap<String, Integer>();
		Iterator<Map.Entry<Integer, Integer>> entries = intermediary.entrySet()
				.iterator();
		while (entries.hasNext()) {
			Entry<Integer, Integer> tmpEntry = entries.next();
			int key = tmpEntry.getKey();
			int value = tmpEntry.getValue();
			result.put(ItemIdList.lookup(key), value);
		}
		return result;
	}

	public static TreeMap<Integer, Integer> getWeaponIdOccurences(
			ArrayList<Kill> killBoard, String attribute) {
		// TODO
		return null;
	}

}
