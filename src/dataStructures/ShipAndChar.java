package dataStructures;

public class ShipAndChar {

	private Pilot pilot;
	private int shipId;
	private int damageDone;
	private int weaponId;
	private boolean finalBlow;

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public int getShipId() {
		return shipId;
	}

	public void setShipId(int shipId) {
		this.shipId = shipId;
	}

	public int getDamageDone() {
		return damageDone;
	}

	public void setDamageDone(int damageDone) {
		this.damageDone = damageDone;
	}

	public int getWeaponId() {
		return weaponId;
	}

	public void setWeaponId(int weaponId) {
		this.weaponId = weaponId;
	}

	public boolean getFinalBlow() {
		return finalBlow;
	}

	public void setFinalBlow(boolean finalBlow) {
		this.finalBlow = finalBlow;
	}

	public boolean findAttribute(String attribute) {
		return pilot.findAttribute(attribute);
	}
}
