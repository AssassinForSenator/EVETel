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

	public SIIS() {
		SI = new TreeMap<String, Integer>();
		IS = new TreeMap<Integer, String>();
	}

	public String get(int i) {
		return IS.get(i);
	}

	public int get(String s) {
		return SI.get(s);
	}

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

	public void put(String s, int i) {
		SI.put(s, i);
	}

	public void put(int i, String s) {
		IS.put(i, s);
	}

}
