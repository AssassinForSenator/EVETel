package dataStructures;

import java.util.Comparator;

public class Character implements Comparable<Character>, Comparator<Character> {

	private int characterId;
	private String characterName;
	private int corporationId;
	private String corporationName;
	private int allianceId;
	private String allianceName;
	private int factionId;
	private String factionName;
	private double securityStatus;

	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public int getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(int corporationId) {
		this.corporationId = corporationId;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public int getAllianceId() {
		return allianceId;
	}

	public void setAllianceId(int allianceId) {
		this.allianceId = allianceId;
	}

	public String getAllianceName() {
		return allianceName;
	}

	public void setAllianceName(String allianceName) {
		this.allianceName = allianceName;
	}

	public int getFactionId() {
		return factionId;
	}

	public void setFactionId(int factionId) {
		this.factionId = factionId;
	}

	public String getFactionName() {
		return factionName;
	}

	public void setFactionName(String factionName) {
		this.factionName = factionName;
	}

	public double getSecurityStatus() {
		return securityStatus;
	}

	public void setSecurityStatus(double securityStatus) {
		this.securityStatus = securityStatus;
	}

	public boolean findAttribute(String attribute) {
		return attribute.equalsIgnoreCase(characterName)
				|| attribute.equalsIgnoreCase(corporationName)
				|| attribute.equalsIgnoreCase(allianceName)
				|| attribute.equalsIgnoreCase(factionName);

	}

	@Override
	public int compareTo(Character other) {
		return this.characterName.compareTo(other.getCharacterName());
	}

	@Override
	public int compare(Character charA, Character charB) {
		return charA.getCharacterName().compareToIgnoreCase(
				charB.getCharacterName());
	}
}
