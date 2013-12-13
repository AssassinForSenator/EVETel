package dataStructures;

import java.util.TreeMap;

/**
 * Realisation of a naive bidirectional Map by using a containerclass that
 * includes 2 TreeMaps StringInt = SI IntString = IS
 * 
 * @author Thomas
 * 
 */

public class SIIS {
	private TreeMap<String, Integer> SI;
	private TreeMap<Integer, String> IS;

	public TreeMap<String, Integer> getSI() {
		return SI;
	}

	public void setSI(TreeMap<String, Integer> sI) {
		SI = sI;
	}

	public TreeMap<Integer, String> getIS() {
		return IS;
	}

	public void setIS(TreeMap<Integer, String> iS) {
		IS = iS;
	}

}
