package killStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import api.KillBoard;
import dataStructures.ItemIdList;
import dataStructures.Kill;
import dataStructures.Pilot;
import dataStructures.ShipAndChar;

public class ExoMain {

	public static void main(String[] args) {
		// TODO cleanup of this neutral alt analysis
		String dude = "gavjack bunk";
		ItemIdList.getInstance();
		ArrayList<Kill> killboard = new ArrayList<Kill>();
		killboard = KillBoard.getKillMailsFromFile("gavjack.xml");
		LinkedHashSet<Pilot> resultSet = new LinkedHashSet<Pilot>();
		TreeMap<Pilot, Integer> occurence = new TreeMap<Pilot, Integer>();

		for (Kill K : killboard) {
			if (K.getVictim().getPilot().getPilotName()
					.equalsIgnoreCase(dude)) {
				continue;
			} else {
				ArrayList<Pilot> attackers = new ArrayList<Pilot>();

				for (ShipAndChar att : K.getAttackers()) {
					if (att.getPilot().getPilotName()
							.equalsIgnoreCase(dude)) {
						continue;
					}
					Pilot P = att.getPilot();
					attackers.add(P);
					Integer count = occurence.get(P);
					if (count == null) {
						occurence.put(P, 1);
					} else {
						occurence.put(P, count + 1);
					}
				}
				resultSet.addAll(attackers);
			}
		}
		System.out.println(resultSet.size());

		List list = new LinkedList(occurence.entrySet());
		Collections.sort(list, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		printMap(sortedMap);
	}

	public static void printMap(Map<String, String> map) {
		for (Map.Entry entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : "
					+ entry.getValue());
		}
	}

}
