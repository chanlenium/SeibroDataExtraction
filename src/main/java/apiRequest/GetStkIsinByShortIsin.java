package apiRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import model.GeneralCompanyModel;
import model.Servicekey;
import utils.ConvertXmlstrToDocument;
import utils.URLrequest;

public class GetStkIsinByShortIsin {
	public void getStkIsinByShortIsin(GeneralCompanyModel company) throws IOException{
		String className = this.getClass().getSimpleName();		
		char classNameCharArray[] = className.toCharArray();
		classNameCharArray[0] = Character.toLowerCase(classNameCharArray[0]);
		className = new String(classNameCharArray);	
		
		StringBuilder urlBuilder = new StringBuilder("http://api.seibro.or.kr/openapi/service/StockSvc/" + className + "N1"); /*URL*/
        try {
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + Servicekey.getServicekey());
			urlBuilder.append("&" + URLEncoder.encode("shortIsin","UTF-8") + "=" + URLEncoder.encode(company.getShortlsin(), "UTF-8")); /*페이지번호*/
			
			URLrequest urlRequestObj = new URLrequest();
	        StringBuilder xmlString = urlRequestObj.urlRequest(urlBuilder.toString());
	        System.out.println(xmlString.toString());
	        if(xmlString != null) {
	        	/** Convert String to XML Document **/
	        	ConvertXmlstrToDocument convertXmlstrToDocumentObj = new ConvertXmlstrToDocument();
	        	Document document = convertXmlstrToDocumentObj.convertXmlstrToDocument(xmlString.toString());
	        	document.getDocumentElement().normalize();
		        //System.out.println("Root element :" + document.getDocumentElement().getNodeName());
		        NodeList nList = document.getElementsByTagName("item");	// Tag이름으로 element를 찾음 <item></item>에 기업정보가 있으므로 "item"으로 검색
		        //System.out.println("----------------------------");
		        
		        Node nNode = nList.item(0);  
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	            	Element eElement = (Element) nNode;
	            	company.setIsin(eElement.getElementsByTagName("isin").item(0).getTextContent());
	            	company.setIssucoCustno(eElement.getElementsByTagName("issucoCustno").item(0).getTextContent());
	            }
	        }
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     	
	}
}
