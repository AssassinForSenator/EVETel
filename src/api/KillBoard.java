package api;

import java.util.ArrayList;

import org.xml.sax.InputSource;

import dataStructures.Kill;


public class KillBoard {
	
	static String urlPartID = null;
	
	public static ArrayList<Kill> getKillMails(int charID){
		InputSource data;
		ArrayList<Kill> downloaded = new ArrayList<Kill>();
		ArrayList<Kill> totalBoard = new ArrayList<Kill>();
		
		data = Download.getFromHTTPS(formURL(charID, 1));
		downloaded = XMLParser.Killboard(data);
		
		totalBoard.addAll(downloaded);
		int currentPage = 1;
		
		while(downloaded.size() >= 200 && totalBoard.size() < 3000){
			currentPage++;
			data = Download.getFromHTTPS(formURL(charID, currentPage));
			downloaded = XMLParser.Killboard(data);
			totalBoard.addAll(downloaded);
			System.out.println(totalBoard.size() + " is the current number of downloaded kills.");
		}
		
		return totalBoard;
	}
	
	private static String formURL(int ID, int page){
		String urlPartPage;
		String urlPartOptions = "no-items/xml/";
		
		String urlStart = "https://zkillboard.com/api/";
		if(urlPartID == null || !urlPartID.contains(String.valueOf(ID))){
			if(api.Eve.isCorporationID(ID)){
				urlPartID = "corporationID/" + ID + "/";
			} else if(api.Eve.isAllianceID(ID)){
				urlPartID = "allianceID/" + ID + "/";
			} else {
				urlPartID = "characterID/" + ID + "/";
			}
		}
		
		if(page > 1){
			urlPartPage = "page/" + page + "/";
		} else {
			urlPartPage = "";
		}
		
		return urlStart + urlPartID + urlPartPage + urlPartOptions;
	}
	
}
