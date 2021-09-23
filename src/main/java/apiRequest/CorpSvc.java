package apiRequest;

import model.GeneralCompanyModel;
import model.Servicekey;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.ConvertXmlstrToDocument;
import utils.URLrequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CorpSvc {
    public void getIssucoBasicInfo(GeneralCompanyModel company) throws IOException {
        String className = this.getClass().getSimpleName();

        StringBuilder urlBuilder = new StringBuilder("http://api.seibro.or.kr/openapi/service/" + className + "/getIssucoBasicInfo"); /*URL*/
        try {
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + Servicekey.getServicekey());
            urlBuilder.append("&" + URLEncoder.encode("issucoCustno","UTF-8") + "=" + URLEncoder.encode(company.getIssucoCustno(), "UTF-8")); /*페이지번호*/

            URLrequest urlRequestObj = new URLrequest();
            StringBuilder xmlString = urlRequestObj.urlRequest(urlBuilder.toString());
            //System.out.println(xmlString.toString());
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
                    company.setBizno(eElement.getElementsByTagName("bizno").item(0).getTextContent());
                    company.setPval(eElement.getElementsByTagName("pval").item(0).getTextContent());
                    company.setTotalStkCnt(eElement.getElementsByTagName("totalStkCnt").item(0).getTextContent());
                    company.setCaltotMartTpcd(eElement.getElementsByTagName("caltotMartTpcd").item(0).getTextContent());
                    company.setCaltotMartTpcdNm(eElement.getElementsByTagName("caltotMartTpcdNm").item(0).getTextContent());
                }
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void getIssucoBondIssuStatus(GeneralCompanyModel company) throws IOException{
        String className = this.getClass().getSimpleName();

        StringBuilder urlBuilder = new StringBuilder("http://api.seibro.or.kr/openapi/service/" + className + "/getIssucoBondIssuStatus"); /*URL*/
        try {
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + Servicekey.getServicekey());
            urlBuilder.append("&" + URLEncoder.encode("issucoCustno","UTF-8") + "=" + URLEncoder.encode(company.getIssucoCustno(), "UTF-8")); /*페이지번호*/

            URLrequest urlRequestObj = new URLrequest();
            StringBuilder xmlString = urlRequestObj.urlRequest(urlBuilder.toString());
            //System.out.println(xmlString.toString());
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
                    company.setEctAmt(eElement.getElementsByTagName("ectAmt").item(0).getTextContent());
                    company.setFaceAmt(eElement.getElementsByTagName("faceAmt").item(0).getTextContent());
                    company.setIssuKorRemaBw(eElement.getElementsByTagName("issuKorRemaBw").item(0).getTextContent());
                    company.setIssuKorRemaEb(eElement.getElementsByTagName("issuKorRemaEb").item(0).getTextContent());
                    company.setIssuKorRemaStk(eElement.getElementsByTagName("issuKorRemaStk").item(0).getTextContent());
                    company.setIssuRema(eElement.getElementsByTagName("issuRema").item(0).getTextContent());
                }
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
