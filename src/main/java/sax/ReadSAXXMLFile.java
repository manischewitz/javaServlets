package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReadSAXXMLFile {

	public static Object readXML(String file){
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			saxParser.parse(file, handler);
			return handler.getObject();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
