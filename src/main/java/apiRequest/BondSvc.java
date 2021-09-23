package apiRequest;

import model.BondCompanyModel;
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

public class BondSvc {
    public ArrayList<BondCompanyModel> getIssurBondIssuDetailsInfo(GeneralCompanyModel company) throws IOException {
        ArrayList<BondCompanyModel> bondSingleCompany = new ArrayList<BondCompanyModel>();
        String className = this.getClass().getSimpleName();

        StringBuilder urlBuilder = new StringBuilder("http://api.seibro.or.kr/openapi/service/" + className + "/getIssurBondIssuDetailsInfo"); /*URL*/
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

                if(nList.getLength() > 0) {
                    for (int i = 0; i < nList.getLength(); i++) {
                        bondSingleCompany.add(new BondCompanyModel(company));

                        Node nNode = nList.item(i);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            bondSingleCompany.get(i).setBondIssucoCustno(eElement.getElementsByTagName("issucoCustno").item(0).getTextContent());
                            bondSingleCompany.get(i).setBondIsin(eElement.getElementsByTagName("isin").item(0).getTextContent());
                            bondSingleCompany.get(i).setBondKorSecnNm(eElement.getElementsByTagName("korSecnNm").item(0).getTextContent());
                            bondSingleCompany.get(i).setBondSecnKacd(eElement.getElementsByTagName("secnKacd").item(0).getTextContent());
                            bondSingleCompany.get(i).setBondIssuDt(eElement.getElementsByTagName("issuDt").item(0).getTextContent());
                            bondSingleCompany.get(i).setBondRedDt(eElement.getElementsByTagName("redDt").item(0).getTextContent());
                            bondSingleCompany.get(i).setBondIssuCurCd(eElement.getElementsByTagName("issuCurCd").item(0).getTextContent());
                            bondSingleCompany.get(i).setBondPayinAmt(eElement.getElementsByTagName("payinAmt").item(0).getTextContent());
                            bondSingleCompany.get(i).setBondFirstIssuAmt(eElement.getElementsByTagName("firstIssuAmt").item(0).getTextContent());
                        }
                    }
                }else {
                    bondSingleCompany.add(new BondCompanyModel(company));
                }
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bondSingleCompany;
    }
}
