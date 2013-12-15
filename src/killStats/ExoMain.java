package killStats;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

import api.KillBoard;
import dataStructures.ItemIdList;
import dataStructures.Kill;
import dataStructures.MapHelpers;
import dataStructures.Pilot;

public class ExoMain {

	public static void main(String[] args) {

		String dude = "lubomir penev";
		ItemIdList.getInstance();
		ArrayList<Kill> killboard = new ArrayList<Kill>();
		killboard = KillBoard.getKillMailsFromFile("lubomir.xml");
		TreeMap<Pilot, Integer> tmp = Analysis.findMostFrequentAssistants(
				killboard, dude);
		LinkedList<Entry<Pilot, Integer>> list = MapHelpers
				.sortbyValueLinkedListDsc(tmp);
	}

}
