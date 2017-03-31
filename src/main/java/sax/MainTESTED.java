package sax;

public class MainTESTED {

	public static void main(String[] args) {
		
		DBParametersResource resource = (DBParametersResource) ReadSAXXMLFile.readXML("TEST/XMLTest.xml");
		System.out.println(resource.getUser());

	}

}
