package model;

public class CpCompanyModel extends GeneralCompanyModel{
	private String cpIssucoCustno;	// 단기사채 발행회사고객번호
	private String cpIssucoNm;		// 단기사채 발행회사명
	private String cpIsin;			// 단기사채 종목번호	
	private String cpKorSecnNm;		// 단기사채 종목명
	private String cpIssuDt;		// 단기사채 발행일자
	private String cpXpirDt;		// 단기사채 말기일자
	private String cpIssuCurCd;		// 단기사채 발행통화코드
	private String cpPayinAmt;		// 단기사채 발행금액

	public CpCompanyModel() {
		super();
	}

	public CpCompanyModel(GeneralCompanyModel company) {
		super(company);
	}

	public int getColumnCount() {
		GeneralCompanyModel parent = new GeneralCompanyModel();
		return parent.getColumnCount() + getClass().getDeclaredFields().length;
	}

	public String getCpIssucoCustno() {
		return cpIssucoCustno;
	}

	public void setCpIssucoCustno(String cpIssucoCustno) {
		this.cpIssucoCustno = cpIssucoCustno;
	}

	public String getCpIssucoNm() {
		return cpIssucoNm;
	}

	public void setCpIssucoNm(String cpIssucoNm) {
		this.cpIssucoNm = cpIssucoNm;
	}

	public String getCpIsin() {
		return cpIsin;
	}

	public void setCpIsin(String cpIsin) {
		this.cpIsin = cpIsin;
	}

	public String getCpKorSecnNm() {
		return cpKorSecnNm;
	}

	public void setCpKorSecnNm(String cpKorSecnNm) {
		this.cpKorSecnNm = cpKorSecnNm;
	}

	public String getCpIssuDt() {
		return cpIssuDt;
	}

	public void setCpIssuDt(String cpIssuDt) {
		this.cpIssuDt = cpIssuDt;
	}

	public String getCpXpirDt() {
		return cpXpirDt;
	}

	public void setCpXpirDt(String cpXpirDt) {
		this.cpXpirDt = cpXpirDt;
	}

	public String getCpIssuCurCd() {
		return cpIssuCurCd;
	}

	public void setCpIssuCurCd(String cpIssuCurCd) {
		this.cpIssuCurCd = cpIssuCurCd;
	}

	public String getCpPayinAmt() {
		return cpPayinAmt;
	}

	public void setCpPayinAmt(String cpPayinAmt) {
		this.cpPayinAmt = cpPayinAmt;
	}
}
