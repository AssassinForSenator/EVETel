package dataStructures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Singleton object to interface with all ItemIds
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
		} else {
			idSIIS = SIIS.deSerializeFile(datadump);
			System.out.println(idSIIS.size());
		}
	}

	/*
	 * ------------------- Singleton
	 */
	private static class IdListHolder {
		private final static ItemIdList INSTANCE = new ItemIdList();
	}

	public static ItemIdList getInstance() {
		return IdListHolder.INSTANCE;
	}

	/*
	 * ------------------- Methods
	 */

	/**
	 * Small txt Parser to parse from marketdump.txt in case nothing has been
	 * found
	 */
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

	public static String lookup(int id) {
		if (idSIIS.containsKey(id)) {
			return idSIIS.get(id);
		} else {
			String tmp = api.Eve.getEntityName(id);
			if (tmp == null) {
				return null;
			}
			idSIIS.put(id, tmp);
			return tmp;
		}
	}

	public static int lookup(String s) {
		// TODO what if tmp == null?
		if (idSIIS.getSI().containsKey(s)) {
			return idSIIS.get(s);
		} else {
			int tmp = api.Eve.getEntityID(s);
			idSIIS.put(s, tmp);
			return tmp;
		}
	}

}
