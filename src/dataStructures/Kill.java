package dataStructures;

import java.util.ArrayList;
import java.util.Calendar;

public class Kill {

	int killId;
	int solarsysId;
	Calendar killTime;
	int moonId;
	ShipAndChar victim;
	ArrayList<ShipAndChar> attackers;

	public boolean checkParticipant(String Participant) { // TODO: Clean up
		if (victim.getPilot().getCharacterName().equalsIgnoreCase(Participant)) {
			return true;
		}
		if (attackers != null) {
			for (ShipAndChar attacker : attackers) {
				try {
					if (attacker.getPilot().getCharacterName()
							.equalsIgnoreCase(Participant)) {
						return true;
					}
				} catch (Exception e) {

				}
			}
		}
		return false;
	}

	public int getKillId() {
		return killId;
	}

	public void setKillId(int killId) {
		this.killId = killId;
	}

	public int getSolarsysId() {
		return solarsysId;
	}

	public void setSolarsysId(int solarsysId) {
		this.solarsysId = solarsysId;
	}

	public Calendar getKillTime() {
		return killTime;
	}

	public void setKillTime(Calendar killTime) {
		this.killTime = killTime;
	}

	public int getMoonId() {
		return moonId;
	}

	public void setMoonId(int moonId) {
		this.moonId = moonId;
	}

	public ShipAndChar getVictim() {
		return victim;
	}

	public void setVictim(ShipAndChar victim) {
		this.victim = victim;
	}

	public ArrayList<ShipAndChar> getAttackers() {
		return attackers;
	}

	public ArrayList<Pilot> gettAttackingPilots() {
		ArrayList<Pilot> tmp = new ArrayList<Pilot>();
		for (ShipAndChar S : attackers) {
			tmp.add(S.getPilot());
		}
		return tmp;
	}

	public void setAttackers(ArrayList<ShipAndChar> attackers) {
		this.attackers = attackers;
	}

}
