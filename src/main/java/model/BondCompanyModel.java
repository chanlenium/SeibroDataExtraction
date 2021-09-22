package model;

public class BondCompanyModel extends GeneralCompanyModel{
	private String bondIssucoCustno;	// 채권 발행회사 고객번호
	private String bondIsin;			// 채권 종목번호
	private String bondKorSecnNm;		// 채권 종목명
	private String bondSecnKacd;		// 채권 종류([1108] 일반회사채)
	private String bondIssuDt;			// 채권 발행일자
	private String bondRedDt;			// 채권 상환일자
	private String bondIssuCurCd;		// 채권 발행통화(KRW 등)
	private String bondPayinAmt;		// 채권 발행가액
	private String bondFirstIssuAmt;	// 채권 발행금액 (발행가액 * 주식수)

	public BondCompanyModel() {
		super();
	}

	public BondCompanyModel(GeneralCompanyModel company) {
		super(company);
	}

	public int getColumnCount() {
		GeneralCompanyModel parent = new GeneralCompanyModel();
		return parent.getColumnCount() + getClass().getDeclaredFields().length;
	}

	public String getBondIssucoCustno() {
		return bondIssucoCustno;
	}

	public void setBondIssucoCustno(String bondIssucoCustno) {
		this.bondIssucoCustno = bondIssucoCustno;
	}

	public String getBondIsin() {
		return bondIsin;
	}

	public void setBondIsin(String bondIsin) {
		this.bondIsin = bondIsin;
	}

	public String getBondKorSecnNm() {
		return bondKorSecnNm;
	}

	public void setBondKorSecnNm(String bondKorSecnNm) {
		this.bondKorSecnNm = bondKorSecnNm;
	}

	public String getBondSecnKacd() {
		return bondSecnKacd;
	}

	public void setBondSecnKacd(String bondSecnKacd) {
		this.bondSecnKacd = bondSecnKacd;
	}

	public String getBondIssuDt() {
		return bondIssuDt;
	}

	public void setBondIssuDt(String bondIssuDt) {
		this.bondIssuDt = bondIssuDt;
	}

	public String getBondRedDt() {
		return bondRedDt;
	}

	public void setBondRedDt(String bondRedDt) {
		this.bondRedDt = bondRedDt;
	}

	public String getBondIssuCurCd() {
		return bondIssuCurCd;
	}

	public void setBondIssuCurCd(String bondIssuCurCd) {
		this.bondIssuCurCd = bondIssuCurCd;
	}

	public String getBondPayinAmt() {
		return bondPayinAmt;
	}

	public void setBondPayinAmt(String bondPayinAmt) {
		this.bondPayinAmt = bondPayinAmt;
	}

	public String getBondFirstIssuAmt() {
		return bondFirstIssuAmt;
	}

	public void setBondFirstIssuAmt(String bondFirstIssuAmt) {
		this.bondFirstIssuAmt = bondFirstIssuAmt;
	}
}
