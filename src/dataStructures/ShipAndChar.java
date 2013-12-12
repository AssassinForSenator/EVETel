package dataStructures;

public class ShipAndChar {

	Character character;
	int shipId;
	int damageDone;
	int weaponId;
	boolean finalBlow;
	
	public Character getCharacter() {
		return character;
	}
	
	public void setCharacter(Character character) {
		this.character = character;
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
}
