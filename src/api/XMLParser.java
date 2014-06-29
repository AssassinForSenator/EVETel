package api;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import dataStructures.Kill;
import dataStructures.Pilot;
import dataStructures.SIIS;
import dataStructures.ShipAndChar;

public class XMLParser {

	private static ArrayList<Kill> killList;
	private static SIIS idMap;
	
	private static DatatypeFactory dataFactory;

	public static ArrayList<Kill> Killboard(InputStream xmlStream) {

		try {
			System.out.println("parsingXML...");
			//Stopwatch t = new Stopwatch();
			
			dataFactory = DatatypeFactory.newInstance();

			SAXParserFactory factory = SAXParserFactory.newInstance();
			final SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				boolean kmList = false;
				boolean attackerList = false;

				ArrayList<ShipAndChar> attackers;
				Kill singleKill;
				Pilot singleCharacter;
				ShipAndChar singleShipAndChar;

				@Override
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {

					// System.out.println("Start Element :" + qName);

					if (qName.equalsIgnoreCase("ROWSET")
							&& attributes.getValue(0).equalsIgnoreCase("KILLS")) {
						kmList = true;
						killList = new ArrayList<Kill>();
					}

					else if (qName.equalsIgnoreCase("ROWSET")
							&& attributes.getValue(0).equalsIgnoreCase(
									"ATTACKERS")) {
						attackerList = true;
						attackers = new ArrayList<ShipAndChar>();
					}

					else if (qName.equalsIgnoreCase("ROW") && kmList
							&& !attackerList) {
						singleKill = new Kill();
						for (int i = 0; i < attributes.getLength(); i++) {
							if (attributes.getLocalName(i).equalsIgnoreCase(
									"killID")) {
								singleKill.setKillId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"solarSystemID")) {
								singleKill.setSolarsysId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"moonID ")) {
								singleKill.setMoonId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"killTime")) {
								singleKill.setKillTime(convertToCalendar(
										attributes.getValue(i),
										singleKill.getKillId()));
							}
							// System.out.println("	" +
							// attributes.getLocalName(i) + " : " +
							// attributes.getValue(i));
						}
					}

					else if (qName.equalsIgnoreCase("VICTIM")) {
						singleShipAndChar = new ShipAndChar();
						singleCharacter = new Pilot();
						for (int i = 0; i < attributes.getLength(); i++) {
							if (attributes.getLocalName(i).equalsIgnoreCase(
									"characterID")) {
								singleCharacter.setCharacterId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"characterName")) {
								singleCharacter.setCharacterName(attributes
										.getValue(i));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"corporationID")) {
								singleCharacter.setCorporationId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"corporationName")) {
								singleCharacter.setCorporationName(attributes
										.getValue(i));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"allianceID")) {
								singleCharacter.setAllianceId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"allianceName")) {
								singleCharacter.setAllianceName(attributes
										.getValue(i));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"factionID")) {
								singleCharacter.setFactionId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"factionName")) {
								singleCharacter.setFactionName(attributes
										.getValue(i));
							}

							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"shipTypeID")) {
								singleShipAndChar.setShipId(Integer
										.parseInt(attributes.getValue(i)));
							}

							
							// System.out.println("	" +
							// attributes.getLocalName(i) + " : " +
							// attributes.getValue(i));
						}
						singleShipAndChar.setPilot(singleCharacter);
					}

					else if (qName.equalsIgnoreCase("ROW") && attackerList) {
						singleShipAndChar = new ShipAndChar();
						singleCharacter = new Pilot();
						for (int i = 0; i < attributes.getLength(); i++) {
							if (attributes.getLocalName(i).equalsIgnoreCase(
									"characterID")) {
								singleCharacter.setCharacterId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"characterName")) {
								singleCharacter.setCharacterName(attributes
										.getValue(i));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"corporationID")) {
								singleCharacter.setCorporationId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"corporationName")) {
								singleCharacter.setCorporationName(attributes
										.getValue(i));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"allianceID")) {
								singleCharacter.setAllianceId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"allianceName")) {
								singleCharacter.setAllianceName(attributes
										.getValue(i));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"factionID")) {
								singleCharacter.setFactionId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"factionName")) {
								singleCharacter.setFactionName(attributes
										.getValue(i));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"securityStatus")) {
								singleCharacter.setSecurityStatus(Double
										.parseDouble(attributes.getValue(i)));
							}

							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"damageDone")) {
								singleShipAndChar.setShipId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"weaponTypeID")) {
								singleShipAndChar.setWeaponId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"shipTypeID")) {
								singleShipAndChar.setShipId(Integer
										.parseInt(attributes.getValue(i)));
							}
							else if (attributes.getLocalName(i).equalsIgnoreCase(
									"finalBlow")) {
								if (Integer.parseInt(attributes.getValue(i)) == 1) {
									singleShipAndChar.setFinalBlow(true);
								} else {
									singleShipAndChar.setFinalBlow(false);
								}
							}

							
							// System.out.println("		" +
							// attributes.getLocalName(i) + " : " +
							// attributes.getValue(i));
						}
						singleShipAndChar.setPilot(singleCharacter);
					}

					// System.out.println();

				}

				@Override
				public void endElement(String uri, String localName,
						String qName) throws SAXException {

					// System.out.println("End Element :" + qName);

					if (qName.equalsIgnoreCase("VICTIM")) { // victim has been
															// indicated
						singleKill.setVictim(singleShipAndChar);
					}
					else if (qName.equalsIgnoreCase("ROW") && !attackerList) { // is
																			// this
																			// a
																			// kill?

						killList.add(singleKill);
					}
					else if (qName.equalsIgnoreCase("ROW") && attackerList) { // is
																			// this
																			// a
																			// attacker
																			// of
																			// a
																			// kill?
						attackers.add(singleShipAndChar);
					}
					else if (qName.equalsIgnoreCase("ROWSET") && attackerList) { // attackers
																			// are
																			// all
																			// listed
						attackerList = false;
						singleKill.setAttackers(attackers);
					} else if (qName.equalsIgnoreCase("ROWSET") && kmList) { // all
																				// kills
																				// are
																				// listed
						kmList = false;
					}

				}

			};

			saxParser.parse(new BufferedInputStream(xmlStream), handler);//xmlStream, handler);
			
			xmlStream.close();

			System.out.println("XML Parsing Complete.");
			//System.out.println("done in: " + t.elapsedTime());
			return killList;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("XML Parsing failed.");
			return null;
		}

	}

	public static SIIS getID(InputStream xmlStream) {

		try {

			System.out.println("parsingXML...");

			SAXParserFactory factory = SAXParserFactory.newInstance();
			final SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				boolean isAllianceList = false;
				String tmpString;
				int tmpInt;

				@Override
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {

					// System.out.println("Start Element :" + qName);

					if (qName.equalsIgnoreCase("ROWSET")
							&& (attributes.getValue(0).equalsIgnoreCase(
									"CHARACTERS")
									|| attributes.getValue(0).equalsIgnoreCase(
											"employmentHistory") || attributes
									.getValue(0).equalsIgnoreCase("TYPES"))) {
						idMap = new SIIS();
					}

					if (qName.equalsIgnoreCase("ROWSET")
							&& attributes.getValue(0).equalsIgnoreCase(
									"ALLIANCES")) {
						idMap = new SIIS();
						isAllianceList = true;
					}

					if (qName.equalsIgnoreCase("ROW") && !isAllianceList) {
						for (int i = 0; i < attributes.getLength(); i++) {
							if (attributes.getLocalName(i).equalsIgnoreCase(
									"name")) {
								tmpString = attributes.getValue(i);
							}
							if (attributes.getLocalName(i).equalsIgnoreCase(
									"CharacterID")) {
								tmpInt = Integer.parseInt(attributes
										.getValue(i));
							}

							if (attributes.getLocalName(i).equalsIgnoreCase(
									"typeName")) {
								tmpString = attributes.getValue(i);
							}
							if (attributes.getLocalName(i).equalsIgnoreCase(
									"typeID")) {
								tmpInt = Integer.parseInt(attributes
										.getValue(i));
							}

							if (attributes.getLocalName(i).equalsIgnoreCase(
									"startDate")) {
								tmpString = attributes.getValue(i);
							}
							if (attributes.getLocalName(i).equalsIgnoreCase(
									"corporationID")) {
								tmpInt = Integer.parseInt(attributes
										.getValue(i));
							}

							// System.out.println("	" +
							// attributes.getLocalName(i) + " : " +
							// attributes.getValue(i));
						}
						idMap.getIS().put(tmpInt, tmpString);
						idMap.getSI().put(tmpString, tmpInt);
					}

					if (qName.equalsIgnoreCase("ROW") && isAllianceList) {
						for (int i = 0; i < attributes.getLength(); i++) {
							if (attributes.getLocalName(i).equalsIgnoreCase(
									"name")) {
								tmpString = attributes.getValue(i);
							}
							if (attributes.getLocalName(i).equalsIgnoreCase(
									"allianceID")) {
								tmpInt = Integer.parseInt(attributes
										.getValue(i));
							}
							// System.out.println("	" +
							// attributes.getLocalName(i) + " : " +
							// attributes.getValue(i));
						}
						idMap.getIS().put(tmpInt, tmpString);
						idMap.getSI().put(tmpString, tmpInt);
					}

					// System.out.println();

				}

				@Override
				public void endElement(String uri, String localName,
						String qName) throws SAXException {

					// System.out.println("End Element :" + qName);
					if (qName.equalsIgnoreCase("ROWSET")) {
						isAllianceList = false;
					}

				}

			};

			saxParser.parse(xmlStream, handler);

			System.out.println("XML Parsing Complete.");

			return idMap;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("XML Parsing failed.");
			return null;
		}

	}

	private static Calendar convertToCalendar(String value, int killId) {
		
		int year = Integer.parseInt(value.substring(0, 4));
		int month = Integer.parseInt(value.substring(5, 7));
		int day = Integer.parseInt(value.substring(8, 10));
		int hour = Integer.parseInt(value.substring(11, 13));
		int minute = Integer.parseInt(value.substring(14, 16));
		int second = Integer.parseInt(value.substring(17, 19));

		return dataFactory.newXMLGregorianCalendar(year,month,day,hour,minute,second,0,0).toGregorianCalendar();
	}

}
