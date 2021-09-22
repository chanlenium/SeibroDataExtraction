package model;

public class GeneralCompanyModel {
	private String bizno; 			// 사업자번호
	private String caltotMartTpcd;	// 시장구분코드(11: 유가증권시장, 12: 코스닥시장, 13: K-OTC시장, 14: 코넥스시장, 50: 기타시장)
	private String caltotMartTpcdNm;	// 시장구분명
	private String issucoCustno;		// 발행회사번호(고객번호)
	private String pval; 			// 액면가
	private String totalStkCnt;		// 총발행주식수
	
	private String shortlsin;		// 발행회사 단축번호
	private String korSecnNm; 		// 국문 종목명
	private String martTpcd;			// 시장구분코드(11: 유가증권시장, 12: 코스닥시장, 13: K-OTC시장, 14: 코넥스시장, 50: 기타시장)
	private String isin;				// 주식종목코드(풀코드)
	
	// 증권정보(잔액)
	private String ectAmt;			// 전자단기사채발행잔액
	private String faceAmt;			// CP발행잔액
	private String issuKorRemaBw;	// 국내 BW 발행잔액
	private String issuKorRemaEb;	// 국내 EB 발행잔액
	private String issuKorRemaStk;	// 국내 CB 발행잔액
	private String issuRema;			// 회사채 발행잔액
	
	public GeneralCompanyModel() {
		// TODO Auto-generated constructor stub
	}

	public GeneralCompanyModel(GeneralCompanyModel company) {
		super();
		this.bizno = company.bizno;
		this.caltotMartTpcd = company.caltotMartTpcd;
		this.caltotMartTpcdNm = company.caltotMartTpcdNm;
		this.issucoCustno = company.issucoCustno;
		this.pval = company.pval;
		this.totalStkCnt = company.totalStkCnt;
		this.shortlsin = company.shortlsin;
		this.korSecnNm = company.korSecnNm;
		this.martTpcd = company.martTpcd;
		this.isin = company.isin;
		this.ectAmt = company.ectAmt;
		this.faceAmt = company.faceAmt;
		this.issuKorRemaBw = company.issuKorRemaBw;
		this.issuKorRemaEb = company.issuKorRemaEb;
		this.issuKorRemaStk = company.issuKorRemaStk;
		this.issuRema = company.issuRema;
	}

	public int getColumnCount() {
		return getClass().getDeclaredFields().length;
	}

	public String getBizno() {
		return bizno;
	}

	public String getCaltotMartTpcd() {
		return caltotMartTpcd;
	}

	public String getCaltotMartTpcdNm() {
		return caltotMartTpcdNm;
	}

	public String getIssucoCustno() {
		return issucoCustno;
	}

	public String getPval() {
		return pval;
	}

	public String getTotalStkCnt() {
		return totalStkCnt;
	}

	public String getShortlsin() {
		return shortlsin;
	}

	public String getKorSecnNm() {
		return korSecnNm;
	}

	public String getMartTpcd() {
		return martTpcd;
	}

	public String getIsin() {
		return isin;
	}

	public String getEctAmt() {
		return ectAmt;
	}

	public String getFaceAmt() {
		return faceAmt;
	}

	public String getIssuKorRemaBw() {
		return issuKorRemaBw;
	}

	public String getIssuKorRemaEb() {
		return issuKorRemaEb;
	}

	public String getIssuKorRemaStk() {
		return issuKorRemaStk;
	}

	public String getIssuRema() {
		return issuRema;
	}

	public void setBizno(String bizno) {
		this.bizno = bizno;
	}

	public void setCaltotMartTpcd(String caltotMartTpcd) {
		this.caltotMartTpcd = caltotMartTpcd;
	}

	public void setCaltotMartTpcdNm(String caltotMartTpcdNm) {
		this.caltotMartTpcdNm = caltotMartTpcdNm;
	}

	public void setIssucoCustno(String issucoCustno) {
		this.issucoCustno = issucoCustno;
	}

	public void setPval(String pval) {
		this.pval = pval;
	}

	public void setTotalStkCnt(String totalStkCnt) {
		this.totalStkCnt = totalStkCnt;
	}

	public void setShortlsin(String shortlsin) {
		this.shortlsin = shortlsin;
	}

	public void setKorSecnNm(String korSecnNm) {
		this.korSecnNm = korSecnNm;
	}

	public void setMartTpcd(String martTpcd) {
		this.martTpcd = martTpcd;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public void setEctAmt(String ectAmt) {
		this.ectAmt = ectAmt;
	}

	public void setFaceAmt(String faceAmt) {
		this.faceAmt = faceAmt;
	}

	public void setIssuKorRemaBw(String issuKorRemaBw) {
		this.issuKorRemaBw = issuKorRemaBw;
	}

	public void setIssuKorRemaEb(String issuKorRemaEb) {
		this.issuKorRemaEb = issuKorRemaEb;
	}

	public void setIssuKorRemaStk(String issuKorRemaStk) {
		this.issuKorRemaStk = issuKorRemaStk;
	}

	public void setIssuRema(String issuRema) {
		this.issuRema = issuRema;
	}

//	// 채권발행정보
//	public String secnKacd;	// 채권종류(일반회사채: 1108)
//	public String issuDt;	// 채권 발행일자
//	public String redDt;	// 채권 상환일자
//	public String issuCurCd;	// 발행통화코드
//	public String payinAmt;	// 발행가액
//	public String firstIssuAmt;	// 발행금액 (발행가액*주식수=발행금액)
//	
//	// 단기사채발행정보
//	public String cpIssuDt;	// cp발행일자
//	public String cpXpirDt;	// cp만기일자
//	public String cpIssuCurCd;	// cp발행통화코드
//	public String cpPayinAmt;	// cp발행금액	
}