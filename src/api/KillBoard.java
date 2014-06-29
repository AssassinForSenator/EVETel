package api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import unitTests.Stopwatch;
import dataStructures.Kill;

public class KillBoard {

	static String urlPartID = null;

	public static ArrayList<Kill> getKillMails(int charID) {
		InputStream data;
		ArrayList<Kill> downloaded = new ArrayList<Kill>();
		ArrayList<Kill> totalBoard = new ArrayList<Kill>();
		
		Stopwatch t = new Stopwatch();

		data = Download.getFromHTTPS(formURL(charID, 1));
		downloaded = XMLParser.Killboard(data);

		totalBoard.addAll(downloaded);
		int currentPage = 1;

		while (downloaded.size() >= 200 && totalBoard.size() < 10000) {
			currentPage++;
			data = Download.getFromHTTPS(formURL(charID, currentPage));
			downloaded = XMLParser.Killboard(data);
			totalBoard.addAll(downloaded);
			System.out.println(totalBoard.size()
					+ " is the current number of downloaded kills.");
		}
		
		System.out.println("Done in " + t.elapsedTime() + "s.");

		return totalBoard;
	}

	public static ArrayList<Kill> getKillMailsFromFile(String filename) {
		InputStream data = null;

		try {
			data = new BufferedInputStream(new FileInputStream(
					new File(filename)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return XMLParser.Killboard(data);

	}

	private static String formURL(int ID, int page) {
		String urlPartPage;
		String urlPartOptions = "no-items/xml/";
		
		String urlStart = "https://zkillboard.com/api/";
		if (urlPartID == null || !urlPartID.contains(String.valueOf(ID))) {
			if (api.Eve.isCorporationID(ID)) {
				urlPartID = "corporationID/" + ID + "/";
			} else if (api.Eve.isAllianceID(ID)) {
				urlPartID = "allianceID/" + ID + "/";
			} else {
				urlPartID = "characterID/" + ID + "/";
			}
		}

		if (page > 1) {
			urlPartPage = "page/" + page + "/";
		} else {
			urlPartPage = "";
		}

		return urlStart + urlPartID + urlPartPage + urlPartOptions;
	}

}
