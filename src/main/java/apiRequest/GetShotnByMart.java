package apiRequest;

import java.net.URLEncoder;
import java.util.ArrayList;

//'org.w3c.dom.Document' : It represents the entire XML DOM. Conceptually, it is the root of the document tree, and provides the access to the document’s data further down into the tree.
import org.w3c.dom.*;
import java.io.*;

import model.GeneralCompanyModel;
import model.Servicekey;
import utils.ConvertXmlstrToDocument;
import utils.URLrequest;

/** GetShotnByMart
 *  시장구분을 기준으로 단축종목번호와 종목명 조회
 *  시장구분코드 martTpcd : [11]유가증권시장, [12]코스닥시장, [13]K-OTC시장, [14]코넥스시장, [50]기타시장
 * */

public class GetShotnByMart {
	public void getShotnByMart(String martTpcd, ArrayList<GeneralCompanyModel> companies) throws IOException{
		Boolean isEnd = false;
		int pageNo = 1;	// 페이지 번호
		int numOfRows = 5;	// 페이지당 데이터 수

		while(!isEnd) {
			StringBuilder urlBuilder = new StringBuilder("http://api.seibro.or.kr/openapi/service/StockSvc/getShotnByMartN1"); /*URL*/
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
							GetStkIsinByShortIsin stkIsinByShortIsinObj = new GetStkIsinByShortIsin();
							stkIsinByShortIsinObj.getStkIsinByShortIsin(company);

							// 기업기본정보 기업개요 조회
							GetIssucoBasicInfo issucoBasicInfoObj = new GetIssucoBasicInfo();
							issucoBasicInfoObj.getIssucoBasicInfo(company);

							// 채권/CP발행현황 조회(잔액)
							GetIssucoBondIssuStatus issucoBondIssuStatusObj = new GetIssucoBondIssuStatus();
							issucoBondIssuStatusObj.getIssucoBondIssuStatus(company);

							companies.add(company);
							isEnd = true;
						}else {
							isEnd = true;
						}
					}
				}
				isEnd = true;
				pageNo++;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
