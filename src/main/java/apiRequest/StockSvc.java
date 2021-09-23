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
import java.util.ArrayList;

public class StockSvc {
    public void getShotnByMart(String martTpcd, ArrayList<GeneralCompanyModel> companies) throws IOException {
        Boolean isEnd = false;
        int pageNo = 1;	// 페이지 번호
        int numOfRows = 10;	// 페이지당 데이터 수
        String className = this.getClass().getSimpleName();

        while(!isEnd) {
            StringBuilder urlBuilder = new StringBuilder("http://api.seibro.or.kr/openapi/service/" + className + "/getShotnByMartN1"); /*URL*/
            try {
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + Servicekey.getServicekey()); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(Integer.toString(pageNo), "UTF-8")); /*페이지번호*/
                urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(Integer.toString(numOfRows), "UTF-8")); /*한 페이지 결과 수*/
                urlBuilder.append("&" + URLEncoder.encode("martTpcd","UTF-8") + "=" + URLEncoder.encode(martTpcd, "UTF-8")); /*11.유가증권시장, 12.코스닥, 13.K-OTC, 14.코넥스, 50.기타시장*/

                URLrequest urlRequestObj = new URLrequest();
                StringBuilder xmlString = urlRequestObj.urlRequest(urlBuilder.toString());
                //System.out.println(xmlString.toString());

                if(xmlString != null) {
                    ConvertXmlstrToDocument convertXmlstrToDocumentObj = new ConvertXmlstrToDocument();
                    Document document = convertXmlstrToDocumentObj.convertXmlstrToDocument(xmlString.toString());

                    document.getDocumentElement().normalize();
                    //System.out.println("Root element :" + document.getDocumentElement().getNodeName());

                    // Tag이름으로 element를 찾음 <item></item>에 기업정보가 있으므로 "item"으로 검색
                    NodeList nList = document.getElementsByTagName("item");
                    int totalCount = Integer.parseInt(document.getElementsByTagName("totalCount").item(0).getTextContent());
                    System.out.println("totalCount : " + totalCount);
                    for (int i = 0; i < nList.getLength(); i++) {
                        Node nNode = nList.item(i);
                        //System.out.println("\nCurrent Element : " + nNode.getNodeName() + ((pageNo * numOfRows) + i + 1));
                        if (nNode.getNodeType() == Node.ELEMENT_NODE && ((pageNo * numOfRows) + i + 1) < totalCount) {
                            Element eElement = (Element) nNode;
                            GeneralCompanyModel company = new GeneralCompanyModel();
                            company.setKorSecnNm(eElement.getElementsByTagName("korSecnNm").item(0).getTextContent());
                            company.setShortlsin(eElement.getElementsByTagName("shotnIsin").item(0).getTextContent());
                            company.setMartTpcd(martTpcd);

                            // 주식종목코드(isin), 발행회사번호(issucoCustno) 조회
                            getStkIsinByShortIsin(company);

                            // 기업기본정보 기업개요 조회
                            CorpSvc corpSvcObj = new CorpSvc();
                            corpSvcObj.getIssucoBasicInfo(company);

                            // 채권/CP발행현황 조회(잔액)
                            corpSvcObj.getIssucoBondIssuStatus(company);

                            companies.add(company);
                            //isEnd = true;
                        }else {
                            isEnd = true;
                        }
                    }
                }


                if(pageNo > 5){
                    isEnd = true;
                }else{
                    pageNo++;
                }
                //pageNo++;
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public void getStkIsinByShortIsin(GeneralCompanyModel company) throws IOException{
        String className = this.getClass().getSimpleName();

        StringBuilder urlBuilder = new StringBuilder("http://api.seibro.or.kr/openapi/service/" + className + "/getStkIsinByShortIsinN1"); /*URL*/
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
