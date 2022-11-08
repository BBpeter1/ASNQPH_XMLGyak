package hu.domparse.asnqph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadASNQPH {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		//Fileok megnyitása
		File xmlFile = new File("XMLASNQPH.xml");
		File myFile = new File("XMLASNQPH1.xml");
		
		//Builderek létehozása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//Dokumentumok beállítása
		Document doc = dBuilder.parse(xmlFile);
		Document doc2 = dBuilder.newDocument();
		
		//Gyökérelem meghatározása
		Element root = doc2.createElementNS("XMLASNQPH", "nyilvantartas");
		doc2.appendChild(root);
		
		//Gyerekelemek létrehozása XML alapján
		root.appendChild(createKiadok(doc2,"1", "Legjobb Kiadó", "1900"));
		root.appendChild(createKiadok(doc2,"2", "Kevesbe Jo Kiado","1970"));
		root.appendChild(createKiadok(doc2,"3", "Rossz Kiado", "2011"));
		root.appendChild(createKonyvek(doc2,"1","1", "Egy Konyv", "2500", "Misztikus", "Hegedus Gabor"));
		root.appendChild(createKonyvek(doc2,"2", "2", "Masik Konyv","5000", "Drama", "Toth Miklos"));
		root.appendChild(createKonyvek(doc2,"3","3", "Harmadik Konyv", "12000", "Tortenelmi","Kantor Bela"));
		root.appendChild(createO_K(doc2,"1","1", "2022.10.17", "2022.10.29"));
		root.appendChild(createO_K(doc2,"2", "2", "2022.09.13","2022.09.17"));
		root.appendChild(createO_K(doc2,"3","3", "2022.08.23", "2022.09.01"));
		root.appendChild(createOlvasok(doc2,"1","Balog", "Levente", "18", "06703325674"));
		root.appendChild(createOlvasok(doc2,"2", "Kovacs","Denes", "33", "06302324586"));
		root.appendChild(createOlvasok(doc2,"3","Gyuran", "Elek", "23","06207767854"));
		root.appendChild(createAlkalmazott(doc2,"1","1", "Kiss Eszter", "Penztaros", "2001"));
		root.appendChild(createAlkalmazott(doc2,"2", "2", "Varga Zsombor","Asszisztens", "1998"));
		root.appendChild(createAlkalmazott(doc2,"3","3", "Szabo Bence", "Raktaros", "2000"));
		root.appendChild(createRendszer(doc2,"1", "Kiss", "jelszo1"));
		root.appendChild(createKiadok(doc2,"2", "Kovacs","jelszo2"));
		root.appendChild(createKiadok(doc2,"3", "Szabo", "jelszo3"));
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		//Output megformázása
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(doc2);
		
		//Kiíratás
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult (myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
		
		//Megnyitott xml file kezelése
		doc.getDocumentElement().normalize();
		
		//Gyökér elem kiíratás
		System.out.println("\nGyoker elem: " + doc.getDocumentElement().getNodeName());
		
		//Gyermek elemek listába rendezése
		NodeList kiadoList = doc.getElementsByTagName("kiadok");
		NodeList konyvekList = doc.getElementsByTagName("konyvek");
		NodeList O_KList = doc.getElementsByTagName("O_K");
		NodeList olvasokList = doc.getElementsByTagName("olvasok");
		NodeList alkalmazottList = doc.getElementsByTagName("alkalmazott");
		NodeList rendszerList = doc.getElementsByTagName("rendszer");
		
		//File-ba írás
		StringWriter sw = new StringWriter();
		transf.transform(source, new StreamResult(sw));
		FileWriter fw = new FileWriter("file.txt");
		fw.write(sw.toString());
		fw.close();
		
		//Listák feltöltése
		for (int i = 0; i < kiadoList.getLength(); i++) {
			
			Node kiadoNode = kiadoList.item(i);
			
			System.out.println("\nJelenlegi elem: " + kiadoNode.getNodeName());
			
			if (kiadoNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element elem = (Element) kiadoNode;
				String kiadoid = elem.getAttribute("kiadoid");
				
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String nev = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("kiadaseve").item(0);
				String kiadaseve = node2.getTextContent();
				
				System.out.println("Kiadoid: " + kiadoid);
				System.out.println("Nev: " + nev);
				System.out.println("Kiadaseve: " + kiadaseve);
			}
		}
		
		for (int i = 0; i < konyvekList.getLength(); i++) {
			
			Node konyvekNode = konyvekList.item(i);
			
			System.out.println("\nJelenlegi elem: " + konyvekNode.getNodeName());
			
			if (konyvekNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element elem = (Element) konyvekNode;
				String kid = elem.getAttribute("kid");
				
				Element elem2 = (Element) konyvekNode;
				String kiadoid = elem2.getAttribute("kiadoid");
				
				Node node1 = elem.getElementsByTagName("cim").item(0);
				String cim = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("ar").item(0);
				String ar = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("kategoria").item(0);
				String kategoria = node3.getTextContent();
				
				Node node4 = elem.getElementsByTagName("szerzo").item(0);
				String szerzo = node4.getTextContent();
				
				System.out.println("Kid: " + kid);
				System.out.println("Kiadoid: " + kiadoid);
				System.out.println("Cim: " + cim);
				System.out.println("Ar: " + ar);
				System.out.println("Kategoria: " +kategoria);
				System.out.println("Szerzo: " + szerzo);
			}
		}
		
		for (int i = 0; i < O_KList.getLength(); i++) {
			
			Node O_KNode = O_KList.item(i);
			
			System.out.println("\nJelenlegi elem: " + O_KNode.getNodeName());
			
			if (O_KNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element elem = (Element) O_KNode;
				String kid = elem.getAttribute("kid");
				
				Element elem2 = (Element) O_KNode;
				String oid = elem2.getAttribute("oid");
				
				Node node1 = elem.getElementsByTagName("kiaddatum").item(0);
				String kiaddatum = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("visszdatum").item(0);
				String visszdatum = node2.getTextContent();
				
				System.out.println("Kid: " + kid);
				System.out.println("Oid: " + oid);
				System.out.println("Kiaddatum: " + kiaddatum);
				System.out.println("Visszdatum: " + visszdatum);
			}
		}
		
		for (int i = 0; i < olvasokList.getLength(); i++) {
			
			Node olvasokNode = olvasokList.item(i);
			
			System.out.println("\nJelenlegi elem: " + olvasokNode.getNodeName());
			
			if (olvasokNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element elem = (Element) olvasokNode;
				String oid = elem.getAttribute("oid");
				
				Node node1 = elem.getElementsByTagName("veznev").item(0);
				String veznev = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("keresztnev").item(0);
				String keresztnev = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("kor").item(0);
				String kor = node3.getTextContent();
				
				Node node4 = elem.getElementsByTagName("telefonszam").item(0);
				String telefonszam = node4.getTextContent();
				
				System.out.println("Oid: " + oid);
				System.out.println("Vezeteknev: " + veznev);
				System.out.println("Keresztnev: " + keresztnev);
				System.out.println("Kor: " + kor);
				System.out.println("Telefonszam: " + telefonszam);
			}
		}
		
		for (int i = 0; i < alkalmazottList.getLength(); i++) {
			
			Node alkalmazottNode = alkalmazottList.item(i);
			
			System.out.println("\nJelenlegi elem: " + alkalmazottNode.getNodeName());
			
			if (alkalmazottNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element elem = (Element) alkalmazottNode;
				String aid = elem.getAttribute("aid");
				
				Element elem2 = (Element) alkalmazottNode;
				String loginid = elem2.getAttribute("loginid");
				
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String nev = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("beosztas").item(0);
				String beosztas = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("szulev").item(0);
				String szulev = node3.getTextContent();
				
				System.out.println("Aid: " + aid);
				System.out.println("Loginid: " + loginid);
				System.out.println("Nev: " + nev);
				System.out.println("Beosztas: "  + beosztas);
				System.out.println("Szulev: " + szulev);
			}
		}
		
		for (int i = 0; i < rendszerList.getLength(); i++) {
			
			Node rendszerNode = rendszerList.item(i);
			
			System.out.println("\nJelenlegi elem: " + rendszerNode.getNodeName());
			
			if (rendszerNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element elem = (Element) rendszerNode;
				String loginid = elem.getAttribute("loginid");
				
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String nev = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("jelszo").item(0);
				String jelszo = node2.getTextContent();
				
				System.out.println("Loginid: " + loginid);
				System.out.println("Nev: " + nev);
				System.out.println("Jelszo: " + jelszo);
			}
		}

	}
	
	//Függvények a feltöltéshez
	
	private static Node createKiadok(Document doc2, String kiadoid, String nev, String kiadaseve)
	{
		Element kiadok = doc2.createElement("kiadok");
		
		kiadok.setAttribute("kiadoid", kiadoid);	
		kiadok.appendChild(createKiadokElement(doc2,"nev", nev));
		kiadok.appendChild(createKiadokElement(doc2,"kiadaseve", kiadaseve));
		
		return kiadok;
	}
	
	private static Node createKiadokElement(Document doc2, String name, String value)
	{
		Element node = doc2.createElement(name);
		node.appendChild(doc2.createTextNode(value));
		
		return node;
	}
	
	private static Node createKonyvek(Document doc2, String kiadoid, String kid, String cim, String ar, String kategoria, String szerzo)
	{
		Element konyvek = doc2.createElement("konyvek");
		
		konyvek.setAttribute("kiadoid", kiadoid);
		konyvek.setAttribute("kid", kid);
		
		konyvek.appendChild(createKonyvekElement(doc2,"cim", cim));
		konyvek.appendChild(createKonyvekElement(doc2,"ar", ar));
		konyvek.appendChild(createKonyvekElement(doc2,"kategoria", kategoria));
		konyvek.appendChild(createKonyvekElement(doc2,"szerzo", szerzo));
		
		return konyvek;
	}
	
	private static Node createKonyvekElement(Document doc2, String name, String value)
	{
		Element node = doc2.createElement(name);
		node.appendChild(doc2.createTextNode(value));
		
		return node;
	}
	
	private static Node createO_K(Document doc2, String kid, String oid, String kiaddatum, String visszdatum)
	{
		Element O_K = doc2.createElement("O_K");
		
		O_K.setAttribute("kid", kid);
		O_K.setAttribute("oid", oid);
		O_K.appendChild(createO_KElement(doc2,"kiaddatum", kiaddatum));
		O_K.appendChild(createO_KElement(doc2,"kiadaseve", visszdatum));
		
		return O_K;
	}
	
	private static Node createO_KElement(Document doc2, String name, String value)
	{
		Element node = doc2.createElement(name);
		node.appendChild(doc2.createTextNode(value));
		
		return node;
	}
	
	private static Node createOlvasok(Document doc2, String oid, String veznev, String keresztnev, String kor, String telefonszam)
	{
		Element olvasok = doc2.createElement("olvasok");
		
		olvasok.setAttribute("oid", oid);
		
		olvasok.appendChild(createOlvasokElement(doc2,"veznev", veznev));
		olvasok.appendChild(createOlvasokElement(doc2,"keresztnev", keresztnev));
		olvasok.appendChild(createOlvasokElement(doc2,"kor", kor));
		olvasok.appendChild(createOlvasokElement(doc2,"telefonszam", telefonszam));
		
		return olvasok;
	}
	
	private static Node createOlvasokElement(Document doc2, String name, String value)
	{
		Element node = doc2.createElement(name);
		node.appendChild(doc2.createTextNode(value));
		
		return node;
	}
	
	private static Node createAlkalmazott(Document doc2, String aid, String loginid, String nev, String beosztas, String szulev)
	{
		Element alkalmazott = doc2.createElement("alkalmazott");
		
		alkalmazott.setAttribute("aid", aid);
		alkalmazott.setAttribute("loginid", loginid);
		
		alkalmazott.appendChild(createAlkalmazottElement(doc2,"nev", nev));
		alkalmazott.appendChild(createAlkalmazottElement(doc2,"beosztas", beosztas));
		alkalmazott.appendChild(createAlkalmazottElement(doc2,"szulev", szulev));
		
		return alkalmazott;
	}
	
	private static Node createAlkalmazottElement(Document doc2, String name, String value)
	{
		Element node = doc2.createElement(name);
		node.appendChild(doc2.createTextNode(value));
		
		return node;
	}
	
	private static Node createRendszer(Document doc2, String loginid, String nev, String jelszo)
	{
		Element rendszer = doc2.createElement("rendszer");
		
		rendszer.setAttribute("loginid", loginid);	
		rendszer.appendChild(createRendszerElement(doc2,"nev", nev));
		rendszer.appendChild(createRendszerElement(doc2,"jelszo", jelszo));
		
		return rendszer;
	}
	
	private static Node createRendszerElement(Document doc2, String name, String value)
	{
		Element node = doc2.createElement(name);
		node.appendChild(doc2.createTextNode(value));
		
		return node;
	}

}
