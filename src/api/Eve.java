package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.xml.sax.InputSource;

import dataStructures.StringInt;


public class Eve {
	public static ArrayList<StringInt> getCharacterID(ArrayList<String> name){
		InputSource data;
		String characters = "";
		
		if(name.size() == 0){
			return null;
		}
		
		for(String s : name){
			if(characters.equalsIgnoreCase("")){
				characters = s;
			} else {
			characters = characters + "," + s;
			}
		}
		
		data = Download.getFromHTTPS("https://api.eveonline.com/eve/CharacterID.xml.aspx?names=" + characters);
		
		return XMLParser.getID(data);
	}
	public static StringInt getCharacterID(String name){
		InputSource data;
		
		data = Download.getFromHTTPS("https://api.eveonline.com/eve/CharacterID.xml.aspx?names=" + name);
		
		return (StringInt) XMLParser.getID(data).toArray()[0];
	}
	
	public static ArrayList<StringInt> getCorporationID(ArrayList<String> name){
		InputSource data;
		String corpList = "";
		
		if(name.size() == 0){
			return null;
		}
		
		for(String s : name){
			if(corpList.equalsIgnoreCase("")){
				corpList = s;
			} else {
				corpList = corpList + "," + s;
			}
		}
		
		data = Download.getFromHTTPS("https://api.eveonline.com/eve/CharacterID.xml.aspx?names=" + corpList);
		
		return XMLParser.getID(data);
	}
	public static StringInt getCorporationID(String name){
		InputSource data;
		
		data = Download.getFromHTTPS("https://api.eveonline.com/eve/CharacterID.xml.aspx?names=" + name);
		
		return (StringInt) XMLParser.getID(data).toArray()[0];
	}
	
	public static ArrayList<StringInt> getAllianceID(ArrayList<String> name){
		InputSource data;
		String allianceList = "";
		
		if(name.size() == 0){
			return null;
		}
		
		for(String s : name){
			if(allianceList.equalsIgnoreCase("")){
				allianceList = s;
			} else {
				allianceList = allianceList + "," + s;
			}
		}
		
		data = Download.getFromHTTPS("https://api.eveonline.com/eve/CharacterID.xml.aspx?names=" + allianceList);
		
		return XMLParser.getID(data);
	}
	public static StringInt getAllianceID(String name){
		InputSource data;
		
		data = Download.getFromHTTPS("https://api.eveonline.com/eve/CharacterID.xml.aspx?names=" + name);
		
		return (StringInt) XMLParser.getID(data).toArray()[0];
	}
	
	public static ArrayList<StringInt> getSystemName(ArrayList<Integer> ids){
		InputSource data;
		String systemList = "";
		
		if(ids.size() == 0){
			return null;
		}
		
		for(int i : ids){
			if(systemList.equalsIgnoreCase("")){
				systemList = String.valueOf(i);
			} else {
				systemList = systemList + "," + i;
			}
		}
		
		data = Download.getFromHTTPS("https://api.eveonline.com/eve/CharacterName.xml.aspx?ids=" + systemList);
		
		return XMLParser.getID(data);
	}
	public static StringInt getSystemName(int system){
		InputSource data;
		
		data = Download.getFromHTTPS("https://api.eveonline.com/eve/CharacterName.xml.aspx?ids=" + system);
		
		return (StringInt) XMLParser.getID(data).toArray()[0];
	}
	
	public static ArrayList<StringInt> getItemID(ArrayList<Integer> id){
		InputSource data;
		String idList = "";
		
		if(id.size() == 0){
			return null;
		}
		
		for(int s : id){
			if(idList.equalsIgnoreCase("")){
				idList = String.valueOf(s);
			} else {
				idList = idList + "," + s;
			}
		}
		
		data = Download.getFromHTTPS("https://api.eveonline.com/eve/TypeName.xml.aspx?ids=" + idList);
		
		return XMLParser.getID(data);
	}
	public static StringInt getItemID(int id){
		InputSource data;

		data = Download.getFromHTTPS("https://api.eveonline.com/eve/TypeName.xml.aspx?ids=" + id);
		
		return (StringInt) XMLParser.getID(data).toArray()[0];
	}

	public static boolean isCharacterID(int id){
		InputSource data;
		
		data = Download.getFromHTTPS("https://api.eveonline.com/eve/CharacterInfo.xml.aspx?characterID=" + id);
		
		if(data == null){
			return false;
		}
		
		StringInt result = (StringInt) XMLParser.getID(data).toArray()[0];
		
		if(result.getInteger() == -1 && result.getString().equalsIgnoreCase("error")){
			return false;
		}

		return true;
	}
	public static boolean isAllianceID(int id){
		try{
			InputSource data;
			String notCorp = "{\"info\":null,\"characters\":[]}";
		
			data = Download.getFromHTTP("http://evewho.com/api.php?type=allilist&id=" + id);
		
        	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(data.getByteStream(), "UTF-8"));
            String line = bufferedReader.readLine();
            
            bufferedReader.close();
        	
            
        	if(line.equalsIgnoreCase(notCorp)){
        		return false;
        	}
        
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean isCorporationID(int id){
		try{
			InputSource data;
			String notCorp = "{\"info\":null,\"characters\":[]}";
		
			data = Download.getFromHTTP("http://evewho.com/api.php?type=corplist&id=" + id);
		
        	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(data.getByteStream(), "UTF-8"));
            String line = bufferedReader.readLine();
            
            bufferedReader.close();
        	
            
        	if(line.equalsIgnoreCase(notCorp)){
        		return false;
        	}
        
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

