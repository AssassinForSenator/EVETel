package dataStructures;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TreeMap;

/**
 * Realisation of a naive bidirectional Map by using a containerclass that
 * includes 2 TreeMaps StringInt = SI IntString = IS
 * 
 * @author Thomas
 * 
 */

public class SIIS implements Serializable {

	private static final long serialVersionUID = 2377953583710351404L;
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

	public void serializeFile(String filename) {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream o = new ObjectOutputStream(file);
			o.writeObject(this);
			o.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public boolean containsKey(String key) {
		return SI.containsKey(key);
	}

	public boolean containsKey(int key) {
		return IS.containsKey(key);
	}

	public int size() {
		return SI.size();
	}

	public static SIIS deSerializeFile(File filename) {
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream o = new ObjectInputStream(file);
			SIIS tmp = (SIIS) o.readObject();
			o.close();
			return tmp;
		} catch (IOException e) {
			System.err.println(e);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
		return null;
	}

}
