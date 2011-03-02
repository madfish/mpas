package maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import algorithms.myPoint;

public class GridMapUtility {

	public static TileBasedMap loadMap(File file) {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			Element root = doc.getDocumentElement();
			int length = Integer.parseInt(root.getAttribute("Length"));
			// boolean diagonal =
			// Boolean.parseBoolean(root.getAttribute("Diagonal"));
			TileBasedMap map = new TiledMapImpl(length, length, false);
			NodeList nList = doc.getElementsByTagName("*");
			for (int i = 0; i < nList.getLength(); i++) {
				Element tElement = (Element) nList.item(i);
				String type = tElement.getNodeName();
				if (type.equals("BlockedTile")) {
					int x = Integer.parseInt(tElement.getAttribute("x"));
					int y = Integer.parseInt(tElement.getAttribute("y"));
					if (x >= length || y >= length) {
						// throw exception
					} else {
						map.setTile(x, y, TileStatus.blocked);
					}
				}

			}
			return map;

		} catch (FileNotFoundException e) {

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void saveMap(File file, TileBasedMap tileBasedMap) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Map");
			doc.appendChild(rootElement);
			rootElement.setAttribute("Length", "" + tileBasedMap.getHeightInTiles());
			for (int i = 0; i < tileBasedMap.getHeightInTiles(); i++) {
				for (int j = 0; j < tileBasedMap.getWidthInTiles(); j++) {
					if (tileBasedMap.blocked(i, j)) {
						Element blockedTile = doc.createElement("BlockedTile");
						blockedTile.setAttribute("x", "" + i);
						blockedTile.setAttribute("y", "" + j);
						rootElement.appendChild(blockedTile);
					}
				}
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();

		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void saveSecnario(Scenario scenario, String filename) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			TileBasedMap map = scenario.getMap();
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Map");
			doc.appendChild(rootElement);
			rootElement.setAttribute("Length", "" + map.getHeightInTiles());
			for (int i = 0; i < map.getHeightInTiles(); i++) {
				for (int j = 0; j < map.getWidthInTiles(); j++) {
					if (map.blocked(i, j)) {
						Element blockedTile = doc.createElement("BlockedTile");
						blockedTile.setAttribute("x", "" + i);
						blockedTile.setAttribute("y", "" + j);
						rootElement.appendChild(blockedTile);
					}
				}
			}
			for (int i = 0 ; i < scenario.getStartLocations().size();i++){
				myPoint tStart = scenario.getStartLocations().elementAt(i);
				myPoint tGoal = scenario.getGoalLocations().elementAt(i);
				Element tAgent = doc.createElement("Agent");
				tAgent.setAttribute("sx", "" + tStart.getX());
				tAgent.setAttribute("sy", "" + tStart.getY());
				tAgent.setAttribute("gx", "" + tGoal.getX());
				tAgent.setAttribute("gy", "" + tGoal.getY());
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();

		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Scenario loadScenario(File file) {
		Scenario scenario = null;
		try {
			TileBasedMap map = loadMap(file);
			Vector<myPoint> starts = new Vector<myPoint>();
			Vector<myPoint> goals  = new Vector<myPoint>();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			Element root = doc.getDocumentElement();
			NodeList nList = root.getElementsByTagName("*");
			for (int i = 0; i < nList.getLength(); i++){
				Element tElement = (Element)nList.item(i);
				if (tElement.getNodeName().equals("Agent")){
					int sx = Integer.parseInt(tElement.getAttribute("sx"));
					int sy = Integer.parseInt(tElement.getAttribute("sy"));
					int gx = Integer.parseInt(tElement.getAttribute("gx"));
					int gy = Integer.parseInt(tElement.getAttribute("gy"));
					myPoint tStart = new myPoint(sx,sy);
					myPoint tGoal = new myPoint(gx,gy);
					starts.add(tStart);
					goals.add(tGoal);
				}
			}
			scenario = new Scenario(map,starts,goals);
			return scenario;
		} catch (FileNotFoundException e) {

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			return scenario;
		}
	}
}