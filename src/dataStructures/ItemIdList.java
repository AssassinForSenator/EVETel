package dataStructures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Singleton object to interface with all typeIds
 * 
 * @author Thomas
 * 
 */

public class ItemIdList {

	private static SIIS idSIIS;

	private ItemIdList() {
		ItemIdList.idSIIS = new SIIS();
		File datadump = new File("data.dump");
		if (!datadump.exists()) {
			parse();
			// buildFromScratch();
		} else {
			idSIIS = SIIS.deSerializeFile(datadump);
			System.out.println(idSIIS.size());
		}
	}

	/*
	 * ------------------- Singleton
	 */
	private static class ItemIdListHolder {
		private final static ItemIdList INSTANCE = new ItemIdList();
	}

	public static ItemIdList getInstance() {
		return ItemIdListHolder.INSTANCE;
	}

	public void parse() {
		File sourceFile = new File("marketdump.txt");
		FileReader fr = null;
		try {
			fr = new FileReader(sourceFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BufferedReader br = new BufferedReader(fr);
		String myline = null;
		int tmpId;
		String tmpString;

		try {
			while ((myline = br.readLine()) != null) {
				tmpString = "";
				StringTokenizer token = new StringTokenizer(myline);
				tmpId = Integer.parseInt(token.nextToken());

				tmpString += token.nextToken();
				while (token.hasMoreTokens()) {
					tmpString += ' ';
					tmpString += token.nextToken();
				}

				// System.out.println("id: " + tmpId + " " + "text:" +
				// tmpString);
				idSIIS.put(tmpId, tmpString);
				idSIIS.put(tmpString, tmpId);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save() {
		idSIIS.serializeFile("data.dump");
	}

	// in case of extreme emergency
	private static void buildFromScratch() {
		for (int i = 2; i <= 365994; i++) {
			lookup(i);
		}
		save();
	}

	public static int lookup(String name) {
		if (idSIIS.containsKey(name)) {
			return idSIIS.get(name);
		} else {
			int tmp = api.Eve.getItemId(name);
			idSIIS.put(name, tmp);
			return tmp;
		}
	}

	public static String lookup(int id) {
		if (idSIIS.containsKey(id)) {
			return idSIIS.get(id);
		} else {
			String tmp = api.Eve.getItemName(id);
			if (tmp == null) {
				return null;
			}
			System.out.println("looked up id: " + id + " name: " + tmp);
			idSIIS.put(id, tmp);
			return tmp;
		}
	}

	public static void put(String name, int id) {
		idSIIS.put(name, id);
	}

	public static void put(int id, String name) {
		idSIIS.put(id, name);
	}

}
