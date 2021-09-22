package utils;
import java.io.*;

import model.CpCompanyModel;
import org.apache.poi.xssf.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class CpWriteExcel implements Write {
    private static FileInputStream fis;
    private static FileOutputStream fos;
    private XSSFWorkbook xssfWb = null; // .xlsx
    private XSSFSheet xssfSheet = null; // .xlsx
    private XSSFRow xssfRow = null; // .xlsx
    private XSSFCell xssfCell = null;// .xlsx

    // The wildcard <?> is something different.
    // It is used to specify compatibility.
    // <?> is the syntax to avoid specification of the type
    @Override
    public void writeExcel(ArrayList<?> companies, String sheetName) throws FileNotFoundException, IOException {
        // BondCompanyModel로 강제 형변환(Cast)
        // Generic은 runtime casting을 하기 때문에 명시적으로 바꿔줌
        ArrayList<CpCompanyModel> cpCompanies = (ArrayList<CpCompanyModel>) companies;

        try {
            if(new File("./resultdata.xlsx").exists()){
                System.out.println("file exist.");
                File file = new File("./resultdata.xlsx");
                fis = new FileInputStream(file);
                xssfSheet = xssfWb.createSheet(sheetName);
                writeRow(xssfSheet, cpCompanies);
                fos = new FileOutputStream(file);
            }else{
                System.out.println("No file.");
                xssfWb = new XSSFWorkbook();
                xssfSheet = xssfWb.createSheet(sheetName);
                makeBondHeader(xssfSheet);
                writeRow(xssfSheet, cpCompanies);
                fos = new FileOutputStream("./resultdata.xlsx");
            }
            xssfWb.write(fos);
            fos.flush();
            fos.close();
        } catch(Exception e){

        }finally{

        }

    }

    private void makeBondHeader(XSSFSheet xssfSheet) {
        xssfRow = xssfSheet.createRow(0);   // 첫번째 행에 header를 만듬
        xssfCell = xssfRow.createCell((short) 0);
        xssfCell.setCellValue("사업자번호");
        xssfCell = xssfRow.createCell((short) 1);
        xssfCell.setCellValue("발행회사번호(고객번호)");
        xssfCell = xssfRow.createCell((short) 2);
        xssfCell.setCellValue("발행회사단축번호");
        xssfCell = xssfRow.createCell((short) 3);
        xssfCell.setCellValue("국문종목명");
        xssfCell = xssfRow.createCell((short) 4);
        xssfCell.setCellValue("주식종목코드(풀코드)");
        xssfCell = xssfRow.createCell((short) 5);
        xssfCell.setCellValue("시장구분코드");
        xssfCell = xssfRow.createCell((short) 6);
        xssfCell.setCellValue("시장구분코드");
        xssfCell = xssfRow.createCell((short) 7);
        xssfCell.setCellValue("시장구분명");
        xssfCell = xssfRow.createCell((short) 8);
        xssfCell.setCellValue("액면가");
        xssfCell = xssfRow.createCell((short) 9);
        xssfCell.setCellValue("총발행주식수");
        xssfCell = xssfRow.createCell((short) 10);
        xssfCell.setCellValue("전자단기사채발행잔액");
        xssfCell = xssfRow.createCell((short) 11);
        xssfCell.setCellValue("CP발행잔액");
        xssfCell = xssfRow.createCell((short) 12);
        xssfCell.setCellValue("국내 BW 발행잔액");
        xssfCell = xssfRow.createCell((short) 13);
        xssfCell.setCellValue("국내 EB 발행잔액");
        xssfCell = xssfRow.createCell((short) 14);
        xssfCell.setCellValue("국내 CB 발행잔액");
        xssfCell = xssfRow.createCell((short) 15);
        xssfCell.setCellValue("회사채 발행잔액");
        xssfCell = xssfRow.createCell((short) 16);
        xssfCell.setCellValue("단기사채 발행회사고객번호");
        xssfCell = xssfRow.createCell((short) 17);
        xssfCell.setCellValue("단기사채 발행회사명");
        xssfCell = xssfRow.createCell((short) 18);
        xssfCell.setCellValue("단기사채 종목번호");
        xssfCell = xssfRow.createCell((short) 19);
        xssfCell.setCellValue("단기사채 종목명");
        xssfCell = xssfRow.createCell((short) 20);
        xssfCell.setCellValue("단기사채 발행일자");
        xssfCell = xssfRow.createCell((short) 21);
        xssfCell.setCellValue("단기사채 말기일자");
        xssfCell = xssfRow.createCell((short) 22);
        xssfCell.setCellValue("단기사채 발행통화코드");
        xssfCell = xssfRow.createCell((short) 23);
        xssfCell.setCellValue("단기사채 발행금액");
    }

    private void writeRow(XSSFSheet xssfSheet, ArrayList<CpCompanyModel> cpCompanies) {
        for (int rowIndex = 0; rowIndex < cpCompanies.size(); rowIndex++) {
            xssfRow = xssfSheet.createRow(rowIndex++);

            /** 기업일반정보 */
            xssfCell = xssfRow.createCell(0);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getBizno(),""));		// 사업자번호
            xssfCell = xssfRow.createCell(1);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getIssucoCustno(), ""));	// 발행회사번호(고객번호)
            xssfCell = xssfRow.createCell(2);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getShortlsin(), ""));	// 발행회사단축번호
            xssfCell = xssfRow.createCell(3);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getKorSecnNm(), ""));		// 국문종목명
            xssfCell = xssfRow.createCell(4);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getIsin(), ""));				// 주식종목코드(풀코드)
            xssfCell = xssfRow.createCell(5);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getMartTpcd(), ""));	// 시장구분코드(11: 유가증권시장, 12: 코스닥시장, 13: K-OTC시장, 14: 코넥스시장, 50: 기타시장)
            xssfCell = xssfRow.createCell(6);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getCaltotMartTpcd(), ""));	// 시장구분코드
            xssfCell = xssfRow.createCell(7);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getCaltotMartTpcdNm(), ""));	// 시장구분명
            xssfCell = xssfRow.createCell(8);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getPval(), ""));		// 액면가
            xssfCell = xssfRow.createCell(9);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getTotalStkCnt(), ""));	// 총발행주식수

            /** 증권 잔액정보 */
            xssfCell = xssfRow.createCell(10);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getEctAmt(), ""));	// 전자단기사채발행잔액
            xssfCell = xssfRow.createCell(11);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getFaceAmt(), ""));	// CP발행잔액
            xssfCell = xssfRow.createCell(12);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getIssuKorRemaBw(), ""));	// 국내 BW 발행잔액
            xssfCell = xssfRow.createCell(13);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getIssuKorRemaEb(), ""));	// 국내 EB 발행잔액
            xssfCell = xssfRow.createCell(14);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getIssuKorRemaStk(), ""));	// 국내 CB 발행잔액
            xssfCell = xssfRow.createCell(15);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getIssuRema(), ""));	// 회사채 발행잔액

            /** 단기사채 발행정보 */
            xssfCell = xssfRow.createCell(16);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getCpIssucoCustno(), ""));	// 단기사채 발행회사고객번호
            xssfCell = xssfRow.createCell(17);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getCpIssucoNm(), ""));	// 단기사채 발행회사명
            xssfCell = xssfRow.createCell(18);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getCpIsin(), ""));	// 단기사채 종목번호
            xssfCell = xssfRow.createCell(19);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getCpKorSecnNm(), ""));	// 단기사채 종목명
            xssfCell = xssfRow.createCell(20);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getCpIssuDt(), ""));	// 단기사채 발행일자
            xssfCell = xssfRow.createCell(21);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getCpXpirDt(), ""));	// 단기사채 말기일자
            xssfCell = xssfRow.createCell(22);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getCpIssuCurCd(), ""));// 단기사채 발행통화코드
            xssfCell = xssfRow.createCell(23);
            xssfCell.setCellValue(Objects.toString(cpCompanies.get(rowIndex).getCpPayinAmt(), ""));	// 단기사채 발행금액
        }
    }
}