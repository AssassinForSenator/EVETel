package killStats;

import java.util.ArrayList;

import dataStructures.Kill;

public class Analysis {

	public ArrayList<Kill> findVictim(ArrayList<Kill> killboard, String victim) {

		ArrayList<Kill> resultBoard = new ArrayList<Kill>();
		for (Kill K : killboard) {
			if (K.getVictim().findAttribute(victim)) {
				resultBoard.add(K);
			}
		}
	}

}
