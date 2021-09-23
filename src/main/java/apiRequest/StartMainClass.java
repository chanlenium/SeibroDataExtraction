package apiRequest;

import java.io.IOException;
import java.util.ArrayList;

import model.BondCompanyModel;
import model.CpCompanyModel;
import model.GeneralCompanyModel;
import utils.BondWriteExcel;
import utils.CpWriteExcel;

public class StartMainClass {
    static String ServiceKey = model.Servicekey.getServicekey();

    public static void main(String[] args) throws IOException {
        utils.SSLTrust.sslTrustAllCerts();    // 보안정책 우회
        String martTpcd = "50"; // 시장구분코드
        ArrayList<GeneralCompanyModel> companies = new ArrayList<GeneralCompanyModel>();
        ArrayList<BondCompanyModel> bondCompanies = new ArrayList<BondCompanyModel>();
        ArrayList<CpCompanyModel> cpCompanies = new ArrayList<CpCompanyModel>();

        StockSvc stockSvcObj = new StockSvc();
        stockSvcObj.getShotnByMart(martTpcd, companies);

        System.out.println("companies : " + companies.size());
        for(GeneralCompanyModel company : companies) {
            // 발행인별 채권발행내역 조회
            BondSvc bondSvcObj = new BondSvc();
            ArrayList<BondCompanyModel> bondCompany = bondSvcObj.getIssurBondIssuDetailsInfo(company);
            for(int j = 0; j < bondCompany.size(); j++) {
                bondCompanies.add(bondCompany.get(j));
            }


            // 발행인별 단기금융상품(CP)발행내역 조회
            ShortmSvc shortmSvcObj = new ShortmSvc();
            ArrayList<CpCompanyModel> cpCompany = shortmSvcObj.getSTBInfo(company);
            //System.out.println("cpCompany.size() : " + cpCompany.size());
            for(int j = 0; j < cpCompany.size(); j++) {
                cpCompanies.add(cpCompany.get(j));
            }
        }
        BondWriteExcel bondWriteExcelObj = new BondWriteExcel();
        bondWriteExcelObj.writeExcel( bondCompanies, "발행인별 채권발행내역");

        CpWriteExcel cpWriteExcelObj = new CpWriteExcel();
        cpWriteExcelObj.writeExcel( cpCompanies, "발행인별 단기금융상품(CP)발행내역");
    }
}
