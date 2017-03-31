package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler{

	private static final String CLASSNAME = "class";
	private String element = null;
	private Object object = null;
	
	public void startDocument() throws SAXException {
		System.out.println("Start document");
	}
	
	public void endDocument() throws SAXException {
        System.out.println("End document ");
    }
	
	public void startElement(
			String uri,
			String localName,
			String qName,
			Attributes atr) throws SAXException{
		
		if(!qName.equals(CLASSNAME)){
			element = qName;
		}else{
			this.object = ReflectionHelper.createInstanse(atr.getValue(0));
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
        element = null;
    }
	
	public void characters(char[] chars, int start, int length) throws SAXException{
		if(element != null){
			String value = new String(chars,start,length);
			System.out.println(element + " = " + value);
			ReflectionHelper.setFieldValue(object, element, value);
		}
	}
	
	public Object getObject() {
        return object;
    }
}




















