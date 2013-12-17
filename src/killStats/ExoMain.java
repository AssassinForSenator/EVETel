package killStats;

import java.util.ArrayList;
import java.util.TreeMap;

import api.KillBoard;
import dataStructures.ItemIdList;
import dataStructures.Kill;
import dataStructures.MapHelpers;

public class ExoMain {

	public static void main(String[] args) {

		ItemIdList.getInstance();
		System.out.println(ItemIdList.lookup(670));

		String dude = "lubomir penev";
		ItemIdList.getInstance();

		ArrayList<Kill> killboard = new ArrayList<Kill>();
		killboard = KillBoard.getKillMailsFromFile("lubomir.xml");
		// TreeMap<Pilot, Integer> tmp =
		// Analysis.findMostFrequentAssistants(killboard, dude);
		// LinkedList<Entry<Pilot, Integer>> list =
		// MapHelpers.sortbyValueLinkedListDsc(tmp);
		TreeMap<Integer, Integer> tmp = Analysis.getShiptypesIDOccurrence(
				killboard, "our terms are simple");
		// MapHelpers.sortbyValueLinkedListDsc(tmp);

		TreeMap<String, Integer> tmp2 = Analysis.getShiptypesNameOccurrence(
				killboard, "our terms are simple");
		MapHelpers.sortbyValueLinkedListDsc(tmp2);

		System.out.println("xen");
		TreeMap<String, Integer> xen = Stats.getShipTypes(killboard,
				"our terms are simple");
		MapHelpers.sortbyValueLinkedListDsc(xen);

	}

}
