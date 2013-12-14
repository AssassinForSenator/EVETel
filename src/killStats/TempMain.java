package killStats;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import api.KillBoard;
import dataStructures.ItemIdList;
import dataStructures.Kill;

public class TempMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<Kill> Kills = KillBoard.getKillMailsFromFile("exoapi.xml");
		// ArrayList<Kill> Kills = KillBoard.getKillMails(538316455);

		System.out.println("Downloaded a total of:" + Kills.size()
				+ " of killmails");

		// weaponTest(Kills, "Exo Iljonen");
		// shipTest(Kills, "Exo Iljonen");
		// systemTest(Kills);

		// timeZoneTest(Kills);

		// corpTest(Kills, "Wounded Asteroid Management and Protection Squad");
		// allianceTest(Kills, "Casoff");

		// ArrayList<Kill> kills = KillBoard.getKillMailsFromFile("exoapi.xml");
		// System.out.println(kills.size());
		// weaponTest(kills, "The Rising Stars Academy");

		ItemIdList.getInstance();
	}

	private static void weaponTest(ArrayList<Kill> kills, String name) {
		TreeMap<String, Integer> weaponList = killStats.Stats.getWeaponTypes(
				kills, name);

		System.out.println(entriesSortedByValues(weaponList) + " are " + name
				+ "'s Favorit weapon!");
	}

	private static void shipTest(ArrayList<Kill> kills, String name) {
		TreeMap<String, Integer> shipList = killStats.Stats.getShipTypes(kills,
				name);

		System.out.println(entriesSortedByValues(shipList) + " are " + name
				+ "'s Favorit Ship!");
	}

	private static void systemTest(ArrayList<Kill> kills) {
		TreeMap<String, Integer> systemList = killStats.Stats.getSystems(kills);

		System.out.println(entriesSortedByValues(systemList)
				+ " are the killboard owner's Favorit system!");
	}

	private static void timeZoneTest(ArrayList<Kill> kills) {
		System.out.println(killStats.Stats.averageTimeZone(kills)
				+ " is the killboard owner's Favorit time zone!");
	}

	private static void corpTest(ArrayList<Kill> kills, String name) {
		ArrayList<Kill> commonList = killStats.Stats.compairEntityKill(kills,
				name);
		Kill latestLoss = new Kill();
		latestLoss.setKillTime(new GregorianCalendar(0, 0, 0, 0, 0, 0));

		for (Kill k : commonList) {
			if (k.getVictim().getPilot().getPilotName()
					.equalsIgnoreCase(name)
					&& k.getKillTime().after(latestLoss.getKillTime())) {
				latestLoss = k;
			}
		}

		System.out.println(commonList.size() + " are in common with " + name
				+ "'s kills, the latest Loss is https://zkillboard.com/detail/"
				+ latestLoss.getKillId() + "/ !");
	}

	private static void allianceTest(ArrayList<Kill> kills, String name) {
		ArrayList<Kill> commonList = killStats.Stats.compairEntityKill(kills,
				name);
		Kill latestLoss = new Kill();
		latestLoss.setKillTime(new GregorianCalendar(0, 0, 0, 0, 0, 0));

		for (Kill k : commonList) {
			if (k.getVictim().getPilot().getPilotName()
					.equalsIgnoreCase(name)
					&& k.getKillTime().after(latestLoss.getKillTime())) {
				latestLoss = k;
			}
		}

		System.out.println(commonList.size() + " are in common with " + name
				+ "'s kills, the latest Loss is https://zkillboard.com/detail/"
				+ latestLoss.getKillId() + "/ !");
	}

	static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(
			Map<K, V> map) {
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
				new Comparator<Map.Entry<K, V>>() {
					@Override
					public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
						return e2.getValue().compareTo(e1.getValue());
					}
				});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

}
