package utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ConvertXmlstrToDocument {
	public Document convertXmlstrToDocument(String xmlStr) {
		/** Convert String to XML Document **/
        // Create a DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        
		try {
			builder = factory.newDocumentBuilder();
			// Create a Document from a file or stream
	        ByteArrayInputStream input = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
	        document = builder.parse(input);
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
        return document;
	}
}
