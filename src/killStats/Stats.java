package killStats;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeMap;

import dataStructures.Kill;
import dataStructures.ShipAndChar;

public class Stats {

	public static ArrayList<Kill> compaireEntityLoss(ArrayList<Kill> Kills,
			String name) {
		ArrayList<Kill> output = new ArrayList<Kill>();

		for (Kill k : Kills) {
			if (k.getVictim().findAttribute(name)) {
				output.add(k);
			}
		}

		return output;
	}

	public static ArrayList<Kill> compairEntityKill(ArrayList<Kill> Kills,
			String name) {
		ArrayList<Kill> output = new ArrayList<Kill>();

		for (Kill k : Kills) {
			if (k.getAttackers().size() != 0) {
				for (ShipAndChar attacker : k.getAttackers()) {
					if (attacker.getPilot().findAttribute(name)) {
						output.add(k);
					}
				}
			}
		}

		return output;
	}

	public static TreeMap<String, Integer> getShipTypes(ArrayList<Kill> Kills,
			String name) {
		TreeMap<String, Integer> output = new TreeMap<String, Integer>(); // the
																			// output
		// result
		// (Name,
		// Number of
		// occurance)
		ArrayList<int[]> shipStats = new ArrayList<int[]>(); // (ID, number of
																// occurance)
		ArrayList<Integer> shipTypeList = new ArrayList<Integer>(); // list of
																	// Ship id's
		TreeMap<Integer, String> shipID = new TreeMap<Integer, String>(); // from
																			// the
		// API
		// (Name, ID
		int id; // a single ID

		for (Kill k : Kills) {
			if (k.getVictim().findAttribute(name)) {

				boolean found = false;
				id = k.getVictim().getShipId();

				for (int[] i : shipStats) {
					if (i[0] == id) {
						i[1] = i[1] + 1;
						found = true;
					}
				}
				if (!found) {
					shipStats.add(new int[] { id, 1 });
				}
			}
			if (k.getAttackers() != null) {
				for (ShipAndChar attacker : k.getAttackers()) {
					if (attacker.getPilot().findAttribute(name)) {

						boolean found = false;
						id = attacker.getShipId();

						for (int[] i : shipStats) {
							if (i[0] == id) {
								i[1] = i[1] + 1;
								found = true;
							}
						}
						if (!found) {
							shipStats.add(new int[] { id, 1 });
						}
					}
				}
			}
		}

		for (int[] i : shipStats) {
			shipTypeList.add(i[0]);
		}

		if (shipTypeList.size() < 220) {
			shipID = api.Eve.getItemName(shipTypeList);
		} else {
			shipID = api.Eve.getItemName(new ArrayList<Integer>(shipTypeList
					.subList(0, 220)));
			shipTypeList.remove(new ArrayList<Integer>(shipTypeList.subList(0,
					220)));
			while (shipTypeList.size() > 0) {
				ArrayList<Integer> currentList = new ArrayList<Integer>(
						shipTypeList.subList(0, min(shipTypeList.size(), 220)));
				shipTypeList.removeAll(currentList);
				shipID.putAll(api.Eve.getItemName(currentList));
			}
			// System.out.println("Warning: ship lookup overloaded!");
			// return null;
		}

		for (int[] j : shipStats) {
			output.put(shipID.get(j[0]), j[1]);
		}

		return output;
	}

	public static TreeMap<String, Integer> getWeaponTypes(
			ArrayList<Kill> Kills, String name) {
		TreeMap<String, Integer> output = new TreeMap<String, Integer>(); // the
																			// output
		// result
		// (Name,
		// Number of
		// occurance)
		ArrayList<int[]> weaponStats = new ArrayList<int[]>(); // (ID, number of
																// occurance)
		ArrayList<Integer> weaponTypeList = new ArrayList<Integer>(); // list of
																		// weapon
																		// id's
		TreeMap<Integer, String> weaponID = new TreeMap<Integer, String>(); // from
																			// the
		// API
		// (Name,
		// ID)
		int id; // a single ID

		for (Kill k : Kills) {
			if (k.getAttackers() != null) { // there can be no attackers on a KM
				for (ShipAndChar attacker : k.getAttackers()) {
					if (attacker.getPilot().findAttribute(name)) {
						boolean found = false;
						id = attacker.getWeaponId();

						if (id != attacker.getShipId()) { // make sure not to
															// say that a
															// thrasher is a
															// weapon, even if
															// you and i know it
															// is...
							for (int[] i : weaponStats) {
								if (i[0] == id) {
									i[1] = i[1] + 1;
									found = true;
								}
							}
							if (!found) {
								weaponStats.add(new int[] { id, 1 });
							}
						}
					}
				}
			}
		}

		for (int[] i : weaponStats) {
			weaponTypeList.add(i[0]);
		}

		if (weaponTypeList.size() < 220) {
			weaponID = api.Eve.getItemName(weaponTypeList);
		} else {
			weaponID = api.Eve.getItemName(new ArrayList<Integer>(
					weaponTypeList.subList(0, 220)));
			weaponTypeList.remove(new ArrayList<Integer>(weaponTypeList
					.subList(0, 220)));
			while (weaponTypeList.size() > 0) {
				ArrayList<Integer> currentList = new ArrayList<Integer>(
						weaponTypeList.subList(0,
								min(weaponTypeList.size(), 220)));
				weaponTypeList.removeAll(currentList);
				weaponID.putAll(api.Eve.getItemName(currentList));
			}
			// System.out.println("Warning: weapon lookup overloaded!");
			// return null;
		}

		for (int[] j : weaponStats) {
			output.put(weaponID.get(j[0]), j[1]);
		}

		return output;
	}

	public static TreeMap<String, Integer> getSystems(ArrayList<Kill> Kills) {
		TreeMap<String, Integer> output = new TreeMap<String, Integer>(); // the
																			// output
		// result
		// (Name,
		// Number of
		// occurance)
		ArrayList<int[]> systemStats = new ArrayList<int[]>(); // (ID, number of
																// occurance)
		ArrayList<Integer> systemTypeList = new ArrayList<Integer>(); // list of
																		// system
																		// id's
		TreeMap<Integer, String> systemID = new TreeMap<Integer, String>(); // from
																			// the
		// API
		// (Name,
		// ID)
		int id; // a single ID

		for (Kill k : Kills) {
			boolean found = false;
			id = k.getSolarsysId();

			for (int[] i : systemStats) {
				if (i[0] == id) {
					i[1] = i[1] + 1;
					found = true;
				}
			}
			if (!found) {
				systemStats.add(new int[] { id, 1 });
			}
		}

		for (int[] i : systemStats) {
			systemTypeList.add(i[0]);
		}

		if (systemTypeList.size() < 220) {
			systemID = api.Eve.getEntityName(systemTypeList);
		} else {
			systemID = api.Eve.getEntityName(new ArrayList<Integer>(
					systemTypeList.subList(0, 220)));
			systemTypeList.remove(new ArrayList<Integer>(systemTypeList
					.subList(0, 220)));
			while (systemTypeList.size() > 0) {
				ArrayList<Integer> currentList = new ArrayList<Integer>(
						systemTypeList.subList(0,
								min(systemTypeList.size(), 220)));
				systemTypeList.removeAll(currentList);
				systemID.putAll(api.Eve.getEntityName(currentList));
			}
			// System.out.println("Warning: system lookup overloaded!");
			// return null;
		}

		for (int[] j : systemStats) {
			output.put(systemID.get(j[0]), j[1]);
		}

		return output;
	}

	private static int min(int a, int b) {
		if (a < b) {
			return a;
		}
		return b;
	}

	public static String averageTimeZone(ArrayList<Kill> Kills) {
		String result;
		int[] quartile = new int[3];
		int[] hours = new int[24];
		int total = 0;

		for (Kill k : Kills) {
			hours[k.getKillTime().get(Calendar.HOUR_OF_DAY)]++;
		}

		int[] min = new int[] { 0, hours[0] };

		for (int i = 0; i < 24; i++) {
			total = total + hours[i];
			if (min[1] > hours[i]) {
				min[0] = i;
				min[1] = hours[i];
			}
		}

		int x = 0;

		for (int i = min[0]; i < 24 + min[0]; i++) {
			x = x + hours[i % 24];
			if (x < total * 1 / 4) {
				quartile[0] = i % 24;
			} else if (x < total / 2) {
				quartile[1] = i % 24;
			} else if (x < total * 3 / 4) {
				quartile[2] = i % 24;
			}
		}

		result = quartile[1] + "h +-" + (quartile[1] - quartile[0]);

		return result;
	}

}
