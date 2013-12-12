package killStats;

import gui.StartFrame;

import java.util.*;

import dataStructures.Kill;
import dataStructures.StringInt;

import api.KillBoard;

public class TempMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		StartFrame form = new StartFrame();
		
		ArrayList<Kill> Kills = KillBoard.getKillMails(1142939290);
		
		System.out.println("Downloaded a total of:" + Kills.size() + " of killmails");
		
		weaponTest(Kills, "Pendulum of Doom");
		shipTest(Kills, "Pendulum of Doom");
		systemTest(Kills);
		
		timeZoneTest(Kills);
		
		corpTest(Kills, "Wounded Asteroid Management and Protection Squad");
		allianceTest(Kills, "Casoff");
	}
	
	private static void weaponTest(ArrayList<Kill> kills, String name){
		ArrayList<StringInt> weaponList = killStats.Stats.getWeaponTypes(kills, name);
	
		System.out.println(weaponList.get(0).getString() + ", " + weaponList.get(1).getString() + ", " + weaponList.get(2).getString() + " are " + name + "'s Favorit weapon!");
	}
	private static void shipTest(ArrayList<Kill> kills, String name){
		ArrayList<StringInt> shipList = killStats.Stats.getShipTypes(kills, name);
				
		System.out.println(shipList.get(0).getString() + ", " + shipList.get(1).getString() + ", " + shipList.get(2).getString() + " are " + name + "'s Favorit Ship!");
	}
	private static void systemTest(ArrayList<Kill> kills){
		ArrayList<StringInt> systemList = killStats.Stats.getSystems(kills);
	
		System.out.println(systemList.get(0).getString() + ", " + systemList.get(1).getString() + ", " + systemList.get(2).getString() + " are the killboard owner's Favorit system!");
	}
	
	private static void timeZoneTest(ArrayList<Kill> kills){
		System.out.println(killStats.Stats.averageTimeZone(kills) + " is the killboard owner's Favorit time zone!");
	}
	
	private static void corpTest(ArrayList<Kill> kills, String name){
		ArrayList<Kill> commonList = killStats.Stats.compairCorp(kills, name);
		Kill latestLoss = new Kill();
		latestLoss.setKillTime(new GregorianCalendar(0,0,0,0,0,0));
		
		for(Kill k : commonList){
			if(k.getVictim().getCharacter().getCharacterName().equalsIgnoreCase(name) && k.getKillTime().after(latestLoss.getKillTime())){
				latestLoss = k;
			}
		}
				
		System.out.println(commonList.size() + " are in common with " + name + "'s kills, the latest Loss is https://zkillboard.com/detail/" + latestLoss.getKillId() + "/ !");
	}
	private static void allianceTest(ArrayList<Kill> kills, String name){
		ArrayList<Kill> commonList = killStats.Stats.compairAlliance(kills, name);
		Kill latestLoss = new Kill();
		latestLoss.setKillTime(new GregorianCalendar(0,0,0,0,0,0));
		
		for(Kill k : commonList){
			if(k.getVictim().getCharacter().getCharacterName().equalsIgnoreCase(name) && k.getKillTime().after(latestLoss.getKillTime())){
				latestLoss = k;
			}
		}
				
		System.out.println(commonList.size() + " are in common with " + name + "'s kills, the latest Loss is https://zkillboard.com/detail/" + latestLoss.getKillId() + "/ !");
	}
	
	

}
