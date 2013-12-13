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

public class IdList {

	private static SIIS idSIIS;

	private IdList() {
		IdList.idSIIS = new SIIS();
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
		private final static IdList INSTANCE = new IdList();
	}

	public static IdList getInstance() {
		return IdListHolder.INSTANCE;
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

				System.out.println("id: " + tmpId + " " + "text:" + tmpString);
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

}
