package dataStructures;

import java.util.Comparator;

public class Pilot implements Comparable<Pilot>, Comparator<Pilot> {

	private int pilotId;
	private String pilotName;
	private int corporationId;
	private String corporationName;
	private int allianceId;
	private String allianceName;
	private int factionId;
	private String factionName;
	private double securityStatus;

	public int getPilotId() {
		return pilotId;
	}

	public void setPilotId(int pilotId) {
		this.pilotId = pilotId;
	}

	public String getPilotName() {
		return pilotName;
	}

	public void setPilotName(String pilotName) {
		this.pilotName = pilotName;
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
		return attribute.equalsIgnoreCase(pilotName)
				|| attribute.equalsIgnoreCase(corporationName)
				|| attribute.equalsIgnoreCase(allianceName)
				|| attribute.equalsIgnoreCase(factionName);
	}

	@Override
	public String toString() {
		return pilotName;
	}

	@Override
	public int compareTo(Pilot other) {
		return this.pilotName.compareTo(other.getPilotName());
	}

	@Override
	public int compare(Pilot charA, Pilot charB) {
		return charA.getPilotName().compareToIgnoreCase(
				charB.getPilotName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pilotId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pilot other = (Pilot) obj;
		if (pilotId != other.pilotId) {
			return false;
		}
		return true;
	}

}
