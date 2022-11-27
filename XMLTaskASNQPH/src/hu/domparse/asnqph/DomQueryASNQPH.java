package hu.domparse.asnqph;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryASNQPH {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

		try {
			
			// XML file kiválasztása
			File inputFile = new File("XMLASNQPH.xml");

			// Dokumentum builderek létehozása
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			// Dokumentumok beállítása
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			// Gyökérelem meghatározása, konzolon feltüntetése
			System.out.println("---------------------");
			System.out.print("Gyokerelem: ");
			System.out.println(doc.getDocumentElement().getNodeName());

			// 1. Lekérdezés fõ részének meghatározása
			NodeList nList1 = doc.getElementsByTagName("alkalmazott");
			System.out.println("---------------------");
			System.out.println("\n------------------------------------");
			System.out.println("1. Alkalmazottak adatainak lekerdezese:");
			System.out.println("------------------------------------");

			// Iterálás az elemeken és adott elemek kiíratása a konzolra
			for (int temp = 0; temp < nList1.getLength(); temp++) {
				Node nNode = nList1.item(temp);
				System.out.println("\nAktualis elem :");

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nNode;
					System.out.print("Alkalmazott ID: ");
					System.out.println(element.getAttribute("aid"));
					NodeList alkalmazottNevList = element.getElementsByTagName("nev");
					NodeList alkalmazottBeoList = element.getElementsByTagName("beosztas");
					NodeList alkalmazottSzulevList = element.getElementsByTagName("szulev");

					for (int count = 0; count < alkalmazottNevList.getLength(); count++) {
						Node node1 = alkalmazottNevList.item(count);
						Node node2 = alkalmazottBeoList.item(count);
						Node node3 = alkalmazottSzulevList.item(count);

						Element alkalmazottNev = (Element) node1;
						System.out.print("Alkalmazott neve: ");
						System.out.println(alkalmazottNev.getTextContent());

						Element alkalmazottBeo = (Element) node2;
						System.out.print("Alkalmazott beosztasa: ");
						System.out.println(alkalmazottBeo.getTextContent());

						Element alkalmazottSzulev = (Element) node3;
						System.out.print("Alkalmazott szuletesi eve: ");
						System.out.println(alkalmazottSzulev.getTextContent());
					}
				}
			}

			// 2. Lekérdezés fõ részének meghatározása
			NodeList nList2 = doc.getElementsByTagName("rendszer");
			System.out.println("\n---------------------------------------------------------------");
			System.out.println("2. Rendszerhez szükséges nevek es jelszavak lekerdezese:");
			System.out.println("---------------------------------------------------------------");

			// Iterálás az elemeken és adott elemek kiíratása a konzolra
			for (int temp = 0; temp < nList2.getLength(); temp++) {
				Node nNode = nList2.item(temp);
				System.out.println("\nAktualis elem :");

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nNode;
					System.out.print("Login ID: ");
					System.out.println(element.getAttribute("loginid"));
					NodeList rendszerNevList = element.getElementsByTagName("nev");
					NodeList rendszerJelszoList = element.getElementsByTagName("jelszo");

					for (int count = 0; count < rendszerNevList.getLength(); count++) {
						Node node1 = rendszerNevList.item(count);
						Node node2 = rendszerJelszoList.item(count);

						Element rendszerNev = (Element) node1;
						System.out.print("Rendszerhasznalo neve: ");
						System.out.println(rendszerNev.getTextContent());

						Element rendszerJelszo = (Element) node2;
						System.out.print("Rendszerhasznalo jelszava: ");
						System.out.println(rendszerJelszo.getTextContent());
					}
				}
			}

			// 3. Lekérdezés fõ részének meghatározása
			NodeList nList3 = doc.getElementsByTagName("konyvek");
			System.out.println("\n----------------------------");
			System.out.println("3. Konyvek osszegei:");
			System.out.println("----------------------------");

			// Iterálás az elemeken és adott elemek kiíratása a konzolra
			for (int temp = 0; temp < nList3.getLength(); temp++) {
				Node nNode = nList3.item(temp);
				System.out.println("\nAktualis elem :");

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nNode;
					System.out.print("Konyvek ID: ");
					System.out.println(element.getAttribute("kid"));
					NodeList konyvekNevList = element.getElementsByTagName("cim");
					NodeList konyvekArList = element.getElementsByTagName("ar");

					for (int count = 0; count < konyvekNevList.getLength(); count++) {
						Node node1 = konyvekNevList.item(count);
						Node node2 = konyvekArList.item(count);

						Element konyvekNev = (Element) node1;
						Element konyvekAr = (Element) node2;
						System.out.print("Konyvek neve: " + konyvekNev.getTextContent());
						System.out.println("\nKonyvek ara: " + konyvekAr.getTextContent());
					}
				}
			}

			// 4. Lekérdezés fõ részének meghatározása
			NodeList nList4 = doc.getElementsByTagName("konyvek");
			System.out.println("\n---------------------------------------------------");
			System.out.println("4. Konyvek azonositoja, ahol misztikus a kategoria:");
			System.out.println("---------------------------------------------------");

			// Iterálás az elemeken és adott elemek kiíratása a konzolra
			for (int temp = 0; temp < nList4.getLength(); temp++) {
				Node nNode = nList4.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nNode;
					NodeList konyvekList = element.getElementsByTagName("kategoria");

					for (int count = 0; count < konyvekList.getLength(); count++) {
						Node node = konyvekList.item(count);

						Element kategoria = (Element) node;

						if (kategoria.getTextContent().equalsIgnoreCase("Misztikus")) {
							System.out.println("\nID-k: " + element.getAttribute("kid"));
						}
					}
				}
			}

			// 5. Lekérdezés fõ részének meghatározása
			NodeList nList5 = doc.getElementsByTagName("olvasok");
			System.out.println("\n---------------------------------------");
			System.out.println("5. Olvasok, akik 18 evnel idosebbek:");
			System.out.println("---------------------------------------");

			// Iterálás az elemeken és adott elemek kiíratása a konzolra
			for (int temp = 0; temp < nList5.getLength(); temp++) {
				Node nNode = nList5.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nNode;
					NodeList olvasokKeresztnevList = element.getElementsByTagName("keresztnev");
					NodeList olvasokVezeteknevList = element.getElementsByTagName("veznev");
					NodeList olvasokKorList = element.getElementsByTagName("kor");

					for (int count = 0; count < olvasokKeresztnevList.getLength(); count++) {
						Node node1 = olvasokKeresztnevList.item(count);
						Node node2 = olvasokVezeteknevList.item(count);
						Node node3 = olvasokKorList.item(count);

						Element olvasokKeresztnev = (Element) node1;
						Element olvasokVezeteknev = (Element) node2;
						Element olvasokKor = (Element) node3;

						if (Integer.parseInt(olvasokKor.getTextContent()) > 18) {
							System.out.println("\nOlvaso Vezetekneve: " + olvasokVezeteknev.getTextContent());
							System.out.println("Olvaso Keresztneve: " + olvasokKeresztnev.getTextContent());
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

	} // end main

}
