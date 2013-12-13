package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;

import org.xml.sax.InputSource;

public class Eve {
	private static int maxAPIRequest = 230;

	public static TreeMap<String, Integer> getEntityID(ArrayList<String> name) {
		InputSource data;
		StringBuilder characters = new StringBuilder();

		if (name.size() == 0 || name.size() > maxAPIRequest) {
			return null;
		}

		for (String s : name) {
			if (characters.length() == 0) {
				characters.append(s);
			} else {
				characters.append(',');
				characters.append(s);
			}
		}

		data = Download
				.getFromHTTPS("https://api.eveonline.com/eve/CharacterID.xml.aspx?names="
						+ characters);

		return XMLParser.getID(data).getSI();
	}

	public static int getEntityID(String name) {
		InputSource data;

		data = Download
				.getFromHTTPS("https://api.eveonline.com/eve/CharacterID.xml.aspx?names="
						+ name);

		return XMLParser.getID(data).getSI().get(name);
	}

	public static String getEntityName(int id) {
		InputSource data;

		data = Download
				.getFromHTTPS("https://api.eveonline.com/eve/CharacterName.xml.aspx?ids="
						+ id);

		return XMLParser.getID(data).getIS().get(id);
	}

	public static TreeMap<Integer, String> getEntityName(ArrayList<Integer> id) {
		InputSource data;
		StringBuilder characters = new StringBuilder();

		if (id.size() == 0 || id.size() > maxAPIRequest) {
			return null;
		}

		for (int i : id) {
			if (characters.length() == 0) {
				characters.append(i);
			} else {
				characters.append(',');
				characters.append(i);
			}
		}

		data = Download
				.getFromHTTPS("https://api.eveonline.com/eve/CharacterName.xml.aspx?ids="
						+ characters);

		return XMLParser.getID(data).getIS();
	}

	public static TreeMap<Integer, String> getItemName(ArrayList<Integer> id) {
		InputSource data;
		StringBuilder idList = new StringBuilder();

		if (id.size() == 0 || id.size() > maxAPIRequest) {
			return null;
		}

		for (int i : id) {
			if (idList.length() == 0) {
				idList.append(i);
			} else {
				idList.append(',');
				idList.append(i);
			}
		}

		data = Download
				.getFromHTTPS("https://api.eveonline.com/eve/TypeName.xml.aspx?ids="
						+ idList);

		return XMLParser.getID(data).getIS();
	}

	public static String getItemName(int id) {
		InputSource data;

		data = Download
				.getFromHTTPS("https://api.eveonline.com/eve/TypeName.xml.aspx?ids="
						+ id);

		return XMLParser.getID(data).getIS().get(id);
	}

	public static TreeMap<String, Integer> getItemID(ArrayList<String> id) {
		InputSource data;
		StringBuilder idList = new StringBuilder();

		if (id.size() == 0 || id.size() > maxAPIRequest) {
			return null;
		}

		for (String s : id) {
			if (idList.length() == 0) {
				idList.append(s);
			} else {
				idList.append(',');
				idList.append(s);
			}
		}

		data = Download
				.getFromHTTPS("https://api.eveonline.com/eve/TypeName.xml.aspx?ids="
						+ idList);

		return XMLParser.getID(data).getSI();
	}

	public static int getItemId(String name) {
		InputSource data;

		data = Download
				.getFromHTTPS("https://api.eveonline.com/eve/TypeName.xml.aspx?ids="
						+ name);

		return XMLParser.getID(data).getSI().get(name);
	}

	public static boolean isCharacterID(int id) {

		InputSource data;
		data = Download
				.getFromHTTPS("https://api.eveonline.com/eve/CharacterInfo.xml.aspx?characterID="
						+ id);

		if (data == null) {
			return false;
		}

		if (XMLParser.getID(data).getIS().containsKey(-1)) {
			return false;
		}

		return true;
	}

	public static boolean isAllianceID(int id) {
		try {
			InputSource data;
			String notCorp = "{\"info\":null,\"characters\":[]}";

			data = Download
					.getFromHTTP("http://evewho.com/api.php?type=allilist&id="
							+ id);

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(data.getByteStream(), "UTF-8"));
			String line = bufferedReader.readLine();

			bufferedReader.close();

			if (line.equalsIgnoreCase(notCorp)) {
				return false;
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isCorporationID(int id) {
		try {
			InputSource data;
			String notCorp = "{\"info\":null,\"characters\":[]}";

			data = Download
					.getFromHTTP("http://evewho.com/api.php?type=corplist&id="
							+ id);

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(data.getByteStream(), "UTF-8"));
			String line = bufferedReader.readLine();

			bufferedReader.close();

			if (line.equalsIgnoreCase(notCorp)) {
				return false;
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
