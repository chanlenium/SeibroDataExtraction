package apiRequest;

import model.CpCompanyModel;
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
import java.util.ArrayList;

public class ShortmSvc {
    public ArrayList<CpCompanyModel> getSTBInfo(GeneralCompanyModel company) throws IOException {
        ArrayList<CpCompanyModel> cpSingleCompany = new ArrayList<CpCompanyModel>();
        String className = this.getClass().getSimpleName();

        StringBuilder urlBuilder = new StringBuilder("http://api.seibro.or.kr/openapi/service/" + className + "/getSTBInfo"); /*URL*/
        try {
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + Servicekey.getServicekey());
            //urlBuilder.append("&" + URLEncoder.encode("isin","UTF-8") + "=" + URLEncoder.encode("KRZSAB526202", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("isin","UTF-8") + "=" + URLEncoder.encode(company.getIsin(), "UTF-8"));

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

                if(nList.getLength() > 0) {
                    for (int i = 0; i < nList.getLength(); i++) {
                        cpSingleCompany.add(new CpCompanyModel(company));

                        Node nNode = nList.item(i);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            cpSingleCompany.get(i).setCpIssucoCustno(eElement.getElementsByTagName("issucoCustno").item(0).getTextContent());
                            cpSingleCompany.get(i).setCpIssucoNm(eElement.getElementsByTagName("issucoNm").item(0).getTextContent());
                            cpSingleCompany.get(i).setCpIsin(eElement.getElementsByTagName("isin").item(0).getTextContent());
                            cpSingleCompany.get(i).setCpKorSecnNm(eElement.getElementsByTagName("korSecnNm").item(0).getTextContent());
                            cpSingleCompany.get(i).setCpIssuDt(eElement.getElementsByTagName("issuDt").item(0).getTextContent());
                            cpSingleCompany.get(i).setCpXpirDt(eElement.getElementsByTagName("xpirDt").item(0).getTextContent());
                            cpSingleCompany.get(i).setCpIssuCurCd(eElement.getElementsByTagName("issuCurCd").item(0).getTextContent());
                            cpSingleCompany.get(i).setCpPayinAmt(eElement.getElementsByTagName("payinAmt").item(0).getTextContent());
                        }
                    }
                }else {
                    cpSingleCompany.add(new CpCompanyModel(company));
                }
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cpSingleCompany;
    }
}
