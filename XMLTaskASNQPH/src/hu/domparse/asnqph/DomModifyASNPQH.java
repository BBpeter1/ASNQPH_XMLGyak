package hu.domparse.asnqph;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

public class DomModifyASNPQH {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		//File megnyitása
		File inputFile = new File("XMLASNQPH.xml");
		
		//Builderek létrehozása
		DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docfactory.newDocumentBuilder();
		
		//Dokumentum beállítása
		Document doc = docBuilder.parse(inputFile);
		
		//Elemek megkeresése
		Node konyvek = doc.getElementsByTagName("konyvek").item(0);
		Node kiadok = doc.getElementsByTagName("kiadok").item(0);
		Node olvasok = doc.getElementsByTagName("olvasok").item(0);
		Node alkalmazott = doc.getElementsByTagName("alkalmazott").item(0);
		Node rendszer = doc.getElementsByTagName("rendszer").item(0); 
		
		NodeList konyvekList = konyvek.getChildNodes();
		NodeList kiadokList = kiadok.getChildNodes();
		NodeList olvasokList = olvasok.getChildNodes();
		NodeList alkalmazottList = alkalmazott.getChildNodes();
		NodeList rendszerList = rendszer.getChildNodes();
		
		//Konyvek elemenen torteno modosítas
		for (int i = 0; i < konyvekList.getLength(); i++) 
		{
			Node node = konyvekList.item(i);
					
			if(node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element) node;
						
				if("cim".equals(element.getNodeName()))
				{
					if("Egy konyv".equals(element.getTextContent()))
					{
						element.setTextContent("Semmivalami");
					}
				}
			}
		}
		
		//Kiadok elemen torteno valtozas
		
		for (int i = 0; i < kiadokList.getLength(); i++) 
		{
			Node node = kiadokList.item(i);
					
			if(node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element) node;
						
				if("nev".equals(element.getNodeName()))
				{
					if("Legjobb kiado".equals(element.getTextContent()))
					{
						element.setTextContent("Nem jo kiado");
					}
				}
			}
		}
		
		
		//Olvasok elemen torteno valtozas
		for (int i = 0; i < olvasokList.getLength(); i++) 
		{
			Node node = olvasokList.item(i);
					
			if(node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element) node;
						
				if("veznev".equals(element.getNodeName()))
				{
					if("Balog".equals(element.getTextContent()))
					{
						element.setTextContent("Huszar");
					}
				}
			}
		}
		
		//Alkalmazott elemen torteno valtozas
		for (int i = 0; i < alkalmazottList.getLength(); i++) 
		{
			Node node = alkalmazottList.item(i);
					
			if(node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element) node;
						
				if("nev".equals(element.getNodeName()))
				{
					if("Kiss Eszter".equals(element.getTextContent()))
					{
						element.setTextContent("Kiss Gabor");
					}
				}
			}
		}
		
		//Rendszer elemen torteno valtozas
		for (int i = 0; i < rendszerList.getLength(); i++) 
		{
			Node node = rendszerList.item(i);
					
			if(node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element) node;
						
				if("jelszo".equals(element.getNodeName()))
				{
					if("jelszo1".equals(element.getTextContent()))
					{
						element.setTextContent("valtozottjelszo");
					}
				}
			}
		}
		

		//Valtoztatasok kiirasa
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		DOMSource source = new DOMSource(doc);
		System.out.println("Modositott fajl: ");
		StreamResult consoleResult = new StreamResult(System.out);
		transf.transform(source,consoleResult);

	}

}
