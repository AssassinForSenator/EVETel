package killStats;

import java.util.*;

import dataStructures.Kill;
import dataStructures.ShipAndChar;
import dataStructures.StringInt;

public class Stats {

	public static ArrayList<Kill> compaireCharacter(ArrayList<Kill> Kills, String character){
		ArrayList<Kill> output = new ArrayList<Kill>();
		
		for(Kill k : Kills){
			if(k.checkParticipant(character)){
				output.add(k);
			}
		}
		
		return output;
	}	
	public static ArrayList<Kill> compairCorp(ArrayList<Kill> Kills, String corp){
		ArrayList<Kill> output = new ArrayList<Kill>();
		
		for(Kill k : Kills){
			if(k.getVictim().getCharacter().getCorporationName().equalsIgnoreCase(corp)){
				output.add(k);
			} else {
				for(ShipAndChar attacker : k.getAttackers()){ //TODO: fix no attacker possibilty
					if(attacker.getCharacter().getCorporationName().equalsIgnoreCase(corp)){
						output.add(k);
					}
				}
			}
		}
		
		return output;
	}
	public static ArrayList<Kill> compairAlliance(ArrayList<Kill> Kills, String alliance){
		ArrayList<Kill> output = new ArrayList<Kill>();
		
		for(Kill k : Kills){
			if(k.getVictim().getCharacter().getAllianceName().equalsIgnoreCase(alliance)){
				output.add(k);
			} else {
				for(ShipAndChar attacker : k.getAttackers()){ //TODO: fix no attacker possibilty
					if(attacker.getCharacter().getAllianceName().equalsIgnoreCase(alliance)){
						output.add(k);
					}
				}
			}
		}
		
		return output;
	}
	
	public static ArrayList<StringInt> getShipTypes(ArrayList<Kill> Kills, String name){
		ArrayList<StringInt> output = new ArrayList<StringInt>(); // the output result (Name, Number of occurance)
		ArrayList<int[]> shipStats = new ArrayList<int[]>(); // (ID, number of occurance)
		ArrayList<Integer> shipTypeList = new ArrayList<Integer>(); // list of Ship id's
		ArrayList<StringInt> shipID = new ArrayList<StringInt>(); // from the API (Name, ID
		StringInt ship; // a single ship
		int id; // a single ID
		
		for(Kill k : Kills){
			if(k.getVictim().getCharacter().getCharacterName().equalsIgnoreCase(name) ||
					k.getVictim().getCharacter().getCorporationName().equalsIgnoreCase(name) ||
					k.getVictim().getCharacter().getAllianceName().equalsIgnoreCase(name) ||
					k.getVictim().getCharacter().getFactionName().equalsIgnoreCase(name)){
				
				boolean found = false;
				id = k.getVictim().getShipId();
				
				for(int[] i : shipStats){
					if(i[0] == id){
						i[1] = i[1] + 1;
						found = true;
					}
				}
				if(!found){
					shipStats.add(new int[] { id, 1});
				}
			}
			if(k.getAttackers() != null){
				for(ShipAndChar attacker : k.getAttackers()){
					if(attacker.getCharacter().getCharacterName().equalsIgnoreCase(name) ||
							attacker.getCharacter().getCorporationName().equalsIgnoreCase(name) ||
							attacker.getCharacter().getAllianceName().equalsIgnoreCase(name) ||
							attacker.getCharacter().getFactionName().equalsIgnoreCase(name)){
					
						boolean found = false;
						id = attacker.getShipId();
					
						for(int[] i : shipStats){
							if(i[0] == id){
								i[1] = i[1] + 1;
								found = true;
							}
						}
						if(!found){
							shipStats.add(new int[] { id, 1});
						}
					}
				}	
			}
		}	
				
		for(int[] i : shipStats){
			shipTypeList.add(i[0]);
		}
		
		if(shipTypeList.size() < 220){
			shipID = api.Eve.getItemID(shipTypeList);
		} else {
			shipID = api.Eve.getItemID(new ArrayList<Integer>(shipTypeList.subList(0, 220)));
			shipTypeList.remove(new ArrayList<Integer>(shipTypeList.subList(0, 220)));
			while(shipTypeList.size() > 0){
				ArrayList<Integer> currentList = new ArrayList<Integer>(shipTypeList.subList(0, min(shipTypeList.size(),220)));
				shipTypeList.removeAll(currentList);
				shipID.addAll(api.Eve.getItemID(currentList));
			}
			//System.out.println("Warning: ship lookup overloaded!");
			//return null;
		}
		
		for(StringInt i : shipID){
			for(int[] j :shipStats){
				if(i.getInteger() == j[0]){
					ship = new StringInt();
					ship.setString(i.getString());
					ship.setInteger(j[1]);
					
					output.add(ship);
				}
			}
		}
		
		Collections.sort(output);
		
		return output;
	}
	public static ArrayList<StringInt> getWeaponTypes(ArrayList<Kill> Kills, String name){
		ArrayList<StringInt> output = new ArrayList<StringInt>(); // the output result (Name, Number of occurance)
		ArrayList<int[]> weaponStats = new ArrayList<int[]>(); // (ID, number of occurance)
		ArrayList<Integer> weaponTypeList = new ArrayList<Integer>(); // list of weapon id's
		ArrayList<StringInt> weaponID = new ArrayList<StringInt>(); // from the API (Name, ID)
		StringInt weapon; // a single weapon
		int id; // a single ID
		
		for(Kill k : Kills){
			if(k.getAttackers() != null){
				for(ShipAndChar attacker : k.getAttackers()){
					if(attacker.getCharacter().getCharacterName().equalsIgnoreCase(name) ||
							attacker.getCharacter().getCorporationName().equalsIgnoreCase(name) ||
							attacker.getCharacter().getAllianceName().equalsIgnoreCase(name) ||
							attacker.getCharacter().getFactionName().equalsIgnoreCase(name)){
						
						boolean found = false;
						id = attacker.getWeaponId();
					
						for(int[] i : weaponStats){
							if(i[0] == id){
								i[1] = i[1] + 1;
								found = true;
							}
						}
						if(!found){
							weaponStats.add(new int[] { id, 1});
						}
					}
				}	
			}
		}	
				
		for(int[] i : weaponStats){
			weaponTypeList.add(i[0]);
		}
		
		if(weaponTypeList.size() < 220){
			weaponID = api.Eve.getItemID(weaponTypeList);
		} else {
			weaponID = api.Eve.getItemID(new ArrayList<Integer>(weaponTypeList.subList(0, 220)));
			weaponTypeList.remove(new ArrayList<Integer>(weaponTypeList.subList(0, 220)));
			while(weaponTypeList.size() > 0){
				ArrayList<Integer> currentList = new ArrayList<Integer>(weaponTypeList.subList(0, min(weaponTypeList.size(),220)));
				weaponTypeList.removeAll(currentList);
				weaponID.addAll(api.Eve.getItemID(currentList));
			}
			//System.out.println("Warning: weapon lookup overloaded!");
			//return null;
		}
		
		
		for(StringInt i : weaponID){
			for(int[] j :weaponStats){
				if(i.getInteger() == j[0]){
					weapon = new StringInt();
					weapon.setString(i.getString());
					weapon.setInteger(j[1]);
					
					output.add(weapon);
				}
			}
		}
		
		Collections.sort(output);
		
		return output;
	}
	public static ArrayList<StringInt> getSystems(ArrayList<Kill> Kills){
		ArrayList<StringInt> output = new ArrayList<StringInt>(); // the output result (Name, Number of occurance)
		ArrayList<int[]> systemStats = new ArrayList<int[]>(); // (ID, number of occurance)
		ArrayList<Integer> systemTypeList = new ArrayList<Integer>(); // list of system id's
		ArrayList<StringInt> systemID = new ArrayList<StringInt>(); // from the API (Name, ID)
		StringInt system; // a single system
		int id; // a single ID
		
		for(Kill k : Kills){		
			boolean found = false;
			id = k.getSolarsysId();
			
			for(int[] i : systemStats){
				if(i[0] == id){
					i[1] = i[1] + 1;
					found = true;
				}
			}
			if(!found){
				systemStats.add(new int[] { id, 1});
			}	
		}	
				
		for(int[] i : systemStats){
			systemTypeList.add(i[0]);
		}
		
		if(systemTypeList.size() < 220){
			systemID = api.Eve.getSystemName(systemTypeList);
		} else {
			systemID = api.Eve.getSystemName(new ArrayList<Integer>(systemTypeList.subList(0, 220)));
			systemTypeList.remove(new ArrayList<Integer>(systemTypeList.subList(0, 220)));
			while(systemTypeList.size() > 0){
				ArrayList<Integer> currentList = new ArrayList<Integer>(systemTypeList.subList(0, min(systemTypeList.size(),220)));
				systemTypeList.removeAll(currentList);
				systemID.addAll(api.Eve.getSystemName(currentList));
			}
			//System.out.println("Warning: system lookup overloaded!");
			//return null;
		}
		
		for(StringInt i : systemID){
			for(int[] j :systemStats){
				if(i.getInteger() == j[0]){
					system = new StringInt();
					system.setString(i.getString());
					system.setInteger(j[1]);
					
					output.add(system);
				}
			}
		}
		
		Collections.sort(output);
		
		return output;
	}

	private static int min(int a, int b) {
		if(a<b){
			return a;
		}
		return b;
	}
	public static String averageTimeZone(ArrayList<Kill> Kills){
		String result;
		int[] quartile = new int[3];
		int[] hours = new int[24];
		int total = 0;
		
		for(Kill k : Kills){
			hours[k.getKillTime().get(Calendar.HOUR_OF_DAY)]++;
		}
		
		int[] min = new int[] {0 , hours[0] };
		
		for(int i = 0; i < 24; i++){
			total = total + hours[i];
			if(min[1] > hours[i]){
				min[0] = i;
				min[1] = hours[i];
			}
		}
		
		int x = 0;
		
		for(int i = min[0]; i < 24 + min[0]; i ++){
			x = x + hours[i%24];
			if(x < total*1/4){
				quartile[0] = i%24;
			} else if(x < total/2){
				quartile[1] = i%24;
			} else if(x < total*3/4){
				quartile[2] = i%24;
			}
		}
		
		result = quartile[1] + "h +-" + (quartile[1] - quartile[0]);
		
		return result;
	}

}

