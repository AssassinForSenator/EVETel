package killStats;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeMap;

import dataStructures.Kill;
import dataStructures.ShipAndChar;

public class DemoStats {
	public static TreeMap<String, Integer> getTimeZone(ArrayList<Kill> kills) {
		TreeMap<String, Integer> output = new TreeMap<String, Integer>(); // the output
		
		int hour;
		for (Kill k : kills) {
			hour = k.getKillTime().get(Calendar.HOUR_OF_DAY);
			
			if(!output.containsKey(String.valueOf(hour)+"h")){
				output.put(String.valueOf(hour)+"h", 1);
			} else {
				output.put(String.valueOf(hour)+"h", output.get(String.valueOf(hour)+"h") + 1);
			}
		}

		return output;
	}
	
	public static TreeMap<String, Integer> getShipPilots(ArrayList<Kill> kills, ArrayList<Integer> shipList, String name){
		TreeMap<String, Integer> output = new TreeMap<String, Integer>(); // the output
		
		String charName;
		for (Kill k : kills) {
			charName = k.getVictim().getPilot().getCharacterName();
			
			if(k.getVictim().findAttribute(name) && k.getVictim().isShip(shipList)){
				if(output.containsKey(charName)){
					output.put(charName, output.get(charName) + 1);
				} else {
					output.put(charName, 1);
				}
			}
			if(k.getAttackers() != null){
			for(ShipAndChar attacker : k.getAttackers()){
				charName = attacker.getPilot().getCharacterName();
				
				if(attacker.findAttribute(name) && attacker.isShip(shipList)){
						if(output.containsKey(charName)){
							output.put(charName, output.get(charName) + 1);
						} else {
							output.put(charName, 1);
						}
					}
				}
			}
		}
		
		return output;
	}
	
}
