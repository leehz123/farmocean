package com.ezen.farmocean.prod.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class fillDBService {
	
	static HikariConfig config = new HikariConfig();
	static {
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@3.39.84.37:1521:XE"); // 원격 DB니까 ip주소 거기 걸로
		config.setUsername("project");
		config.setPassword("project!@");
	}
	static HikariDataSource ds = new HikariDataSource(config);
	
	
	
	static String sql1 = "select member_nickname from members where member_nickname = ?";	
	static String sql2 = "insert into members(member_id, member_pw, member_name, member_nickname, member_point, member_email, member_phonenum, member_accountnum, member_address, member_account_status, member_type, member_image) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	static String sql3 = "INSERT INTO prod_detail(prod_idx, member_id, prod_name, prod_info, prod_price, prod_sell, prod_Sell_deadline, cate_idx, prod_stock, prod_delete, prod_heartnum) VALUES( prod_idx_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";	
	static String sql4 = "SELECT prod_idx FROM prod_detail WHERE prod_name = ?";
	static String sql5 = "INSERT INTO prod_img(img_idx, prod_idx, img_url) VALUES(img_idx_seq.nextval, ?, ?)";


	static List<String> cateList = getHttpHTMLList("https://www.esingsing.co.kr");
	static public List<String> imgList = new ArrayList<>();
	static public Map<String, String> pInfo = new HashMap<>();
	static public HashSet<String> cateListSub = new HashSet<>();
	
		
	static String[] cates = {"사과, 사과즙, 참외", "카테 9는 카테 8이랑 중복되는 듯" , "귤, 유자, 곶감, 감", "토마토, 복숭아", "포도, 망고, 무화과", "멜론, 수박, 배", "딸기, 키위, 다래", "매실, 살구", "블루베리, 아로니아", "오미자, 구지뽕, 오디", "대추, 복분자", "자두, 탱자", "기타 과일", "과일 가공식품", 
								"감자, 고구마", "마늘, 양파, 생강, 파", "고추, 건고추, 태양초", "배추, 절임배추, 무, 열무", "호박, 깻잎, 상추", "비트, 콜라비, 파프리카", "당근, 오이, 가지", "여주, 오크라, 수세미", "기타 채소", "채소 가공식품",
								"시금치, 명이, 냉이", "고사리, 취나물", "곤드레, 시래기", "기타 나물", "나물 가공식품", "표고, 능이, 느타리", "상황, 송이, 새송이", "목이, 영지", "기타 버섯", "버섯 가공식품", 
								"옥수수", "참깨, 들깨", "쌀, 보리, 흑미, 찹쌀", "팥, 현미, 귀리", "콩, 서리태", "백태, 흑태, 녹두", "기타 곡물", "곡물 가공식품", "밤, 은행, 잣", "땅콩, 호두", "기타 견과", "견과 가공식품",
								"마, 하늘마, 칡", "돼지감자, 토란", "도라지, 더덕", "인삼, 홍삼, 산삼", "기타 뿌리", "뿌리 가공 식품", 
								"꿀, 로얄젤리, 프로폴", "효소, 진액, 건강즙", "분말, 열매, 건강차", "기타 약초",
								"전복, 굴, 조개류", "게, 새우, 바닷가재", "문어, 오징어, 낙지, 꼴뚜기", "다슬기, 소라, 멍게", "미역, 김, 다시마", "멸치, 천일염", "기타 해산물", "해산물 가공식품", "삼치, 임연수, 갈치", "홍어, 고등어, 민어", "장어, 광어, 우럭, 돔", "굴비, 조기, 옥돔", "과메기, 꽁치, 전어", "아귀, 도루묵", "기타 생선", "생선 가공식품"};
	
	static String[] address = {"강원도 영월", "경북 의성", "전남 해남", "강원도 홍천","충남 서산", "청도군 운문면", "제주", "전북 남원", "전남 광양", "경북 영덕", "충남 보령 대천항"};
	

	static String[] names = {"김인석", "마강식", "김수광", "박남식", "조대순", "심석희", "이강희", "도민준", "도민호", "박수창", "훈민정", "민정음", "남동일", "이신자", "김가희", 
							"이미나", "최만식", "박창호", "황중석", "정길호", "문병춘", "임무황", "장중광", "손라희", "정예지", "류호식", "김청림", "이은호", "하윤성", "고하림", "하성희", "이동일"
							, "이도하", "최용훈", "표민호", "강형준", "공민화", "용문희", "박순철", "황제희", "최철희", "노구하", "곽창두", "천문식", "봉무용", "곽수용", "안수미", "김두식", "성남식", "지창희"}; 
	
	
	static int phoneNumBase = 10000000;
	static int accountNumBase1 = 100;
	static int accountNumBase2 = 100000;
	static long idPwNum = 51;
	static int cateCnt = 0;
	
	
	
	public static void getHttpHTML(String urlToRead) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		boolean startB = false;
		boolean priceB = false;
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			while ((line = rd.readLine()) != null) {
				if(line.contains("id=\"at-main\"")) {
					startB = true;
				}
				
				if(line.contains("<div class=\"print-hide view-icon view-padding\">")) {
					startB = false;
				}
				
				if(startB) {
					
					String chkValue = "<img alt=";
					
					if(line.contains(chkValue)) {
						imgList.add(line.substring(line.indexOf(" src=\"") + 6, line.indexOf("\" data-im")));
						imgList.add(line.substring(line.indexOf("mage=\"") + 6, line.indexOf("\" data-desc")));						
					}
					
					chkValue = "<i class=\"fa fa-user-circle\"></i>";
					
					if(line.contains(chkValue)) {
						String insertValue = line.replace(chkValue, "");
						insertValue = insertValue.replace("</div>", "");
						pInfo.put("sellMember", insertValue.trim());
					}
					
					chkValue = "<h1 itemprop=\"headline\" content=\"";
					if(line.contains(chkValue)) {
						String insertValue = line.substring(chkValue.length() + 5, line.indexOf("\">"));
						pInfo.put("title", insertValue);
					}
					
					chkValue = "<b>문의 :</b>";
					if(line.contains(chkValue)) {
						String insertValue = "";
						if(line.contains("<a href=\"")) {						
							insertValue = line.substring(chkValue.length() + 5, line.indexOf("<a href=\""));
						}else if(line.contains("<del>")) {
							insertValue = line.substring(line.indexOf("<del>") + 5, line.indexOf("</del>"));
						}
						
						
						pInfo.put("phone", insertValue.trim());
					}
					
					chkValue = "<b>입금 :</b>";
					if(line.contains(chkValue)) {
						String insertValue = "";
						if(line.contains("</div>")) {						
							insertValue = line.substring(chkValue.length() + 5, line.indexOf("</div>"));
						}else if(line.contains("<del>")) {
							insertValue = line.substring(line.indexOf("<del>") + 5, line.indexOf("</del>"));
						}
						pInfo.put("account", insertValue.trim());
					}
					
					chkValue = "<div style=\"margin-bottom:10px\">";
					if(line.contains(chkValue)) {						
						pInfo.put("detail", line);
					}
					
					if(priceB) {
						pInfo.put("price", line.replace("</div>","").trim());
						priceB = false;
					}
					
					if(line.contains("<div class=\"col-sm-12 mitem-title\">")) {
						priceB = true;
					}
				}
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getHttpHTMLList(String urlToRead) {
		List<String> cateList = new ArrayList<>();
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String getUrl = "";
		String conVal = "https://www.esingsing.co.kr/bbs/board.php?bo_table=";
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			while ((line = rd.readLine()) != null) {
				
				if(line.contains(conVal)) {
					getUrl = line.trim();
					getUrl = getUrl.substring(getUrl.indexOf("https://"), getUrl.length());
					if(getUrl.substring(conVal.length() + 2, conVal.length() + 3).equals("\"")) {
						getUrl = getUrl.substring(0, conVal.length() + 2);
						cateList.add(getUrl);
					}else if(getUrl.substring(conVal.length() + 3, conVal.length() + 4).equals("\"")) {
						getUrl = getUrl.substring(0, conVal.length() + 3);
						cateList.add(getUrl);
					}
				}
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cateList;
	}
	
	
	public static List<String> getHttpHTMLListSub(String urlToRead) {
		List<String> cateList = new ArrayList<>();
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String getUrl = "";
		String conVal = urlToRead + "&amp;wr_id";
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			while ((line = rd.readLine()) != null) {
				
				if(line.contains(conVal)) {
					getUrl = line.replace("&amp;", "&");					
					getUrl = getUrl.replace("'", "");
					getUrl = getUrl.substring(getUrl.indexOf("https://"), getUrl.length());
					getUrl = getUrl.substring(0, getUrl.indexOf("\""));
					
					cateListSub.add(getUrl);
				}
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cateList;
	}
	
	
	
	public static String randomSell() {
		int ran = (int)(Math.random() * 2);
		
		if(ran == 0) {
			return "판매중";
		} else {
			return "판매종료";
		}
	}

	public static Timestamp getRandomDeadLine(String sell) {

		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		Integer ran = (int)(Math.random() * 185);
		
		if(sell.equals("판매중")) {			
			cal.add(Calendar.DATE, ran);
			String randomFutureDL = null; 
			randomFutureDL = formatter.format(cal.getTime());
			Timestamp ts = Timestamp.valueOf(randomFutureDL);
			return ts;
			
		} else if(sell.equals("판매종료")) {
			cal.add(Calendar.DATE, ran * -1);
			String randomPastDL = null; 
			randomPastDL = formatter.format(cal.getTime());
			Timestamp ts = Timestamp.valueOf(randomPastDL);			
			return ts;	
		}
		
		return null;
	}

	

	
	
	public static String getPhoneNum() {		
		String frontPN = "010-";
		
		if(++phoneNumBase > 99999998) {
			phoneNumBase = 10000000;
		}
		String base = "" + phoneNumBase;	
		String fullPN = frontPN + base.substring(0, 4) + "-" + base.substring(4);
		return fullPN;
	}
	
	public static String getAccountNum() {	
		String frontAN = "110-";
		if(++accountNumBase1 > 998) { 
			accountNumBase1 = 100;
		}
		if(++accountNumBase2 > 999998) {
			accountNumBase2 = 100000;
		}
		return frontAN + accountNumBase1 + "-" + accountNumBase2;
	}
	
	
	public static String getIdPw() {
		String sample = "sample";
		++idPwNum;
		return sample + idPwNum;
	}
	
	
	public static String randomAddress() {
		int ranIndex = (int)(Math.random() * address.length);
		return address[ranIndex];
	}
	
	public static String getRandomName() {
		int ranIndex = (int)(Math.random() * names.length);
		return names[ranIndex];
	}
	
	
	public static Integer randomStock() {
		return (int)(Math.random() * 1001);
	};

	public static Integer randomDelete() {
		return (int)(Math.random() * 2);
	};
	
	public static int fillDB(int i) {
		++cateCnt;
		int storedProdNum = 0;
		
		List<String> cateList = getHttpHTMLList("https://www.esingsing.co.kr");		
		int cnt = 0;
		boolean loop = true;
		for(String s : cateList) {
			
			if(cnt > i) {	//	8번째 부터 농수산물 상품 정보임 그전거는 가격불러오는 부분이라 건너뜀
				if(loop) {	// 테스트여서 카테고리 하나만 가져오게 함(여기 풀면 메인 페이지에서 불러올수 있는 상품 정보 다 불러옴)
					getHttpHTMLListSub(s);
					loop = false;
				}
			}else {
				cnt++;
			}
		}
		
		
		int prodCnt = 0;
		for(String url : cateListSub) {
			
			if(true) {
			//if(prodCnt < 5) { //상품 몇 개 넣을 지 조절 가능 ★★★★★★★★★
				System.out.println(++prodCnt + "번째 prod 처리ing");
				Timestamp randomDeadline = null; //getRandomDeadLine("판매종료");
				
				imgList = new ArrayList<>();
				pInfo = new HashMap<>();
				
				getHttpHTML(url);
				String seller = pInfo.get("sellMember"); //상호명
				String name = pInfo.get("title");
				String content = pInfo.get("detail");
				String priceStr = pInfo.get("price").replaceAll("\\,", "");
				Integer price = Integer.parseInt(priceStr.substring(0, priceStr.indexOf('원')));
				String sell = randomSell();
				randomDeadline = getRandomDeadLine(sell);		
				String IdPw = getIdPw();
				String randomName = getRandomName();
																		
				
				try (
						Connection conn = ds.getConnection();
					
						PreparedStatement pstmt1 = conn.prepareStatement(sql1);
						PreparedStatement pstmt2 = conn.prepareStatement(sql2);
						PreparedStatement pstmt3 = conn.prepareStatement(sql3);				
						PreparedStatement pstmt4 = conn.prepareStatement(sql4);
						PreparedStatement pstmt5 = conn.prepareStatement(sql5);
				){
					
					conn.setAutoCommit(false);

					// sell_name 중복 거르기
					pstmt1.setString(1, seller);
					ResultSet rs1 = pstmt1.executeQuery();
					if(!rs1.next()) {
							// members 테이블 채우는 코드 (얘를 먼저 해야 prod_detail에서 sell_id 참조 가능)	
							pstmt2.setString(1, IdPw);
							pstmt2.setString(2, IdPw);
							pstmt2.setString(3, randomName);
							pstmt2.setString(4, seller);
							pstmt2.setInt(5, 1000); //포인트
							pstmt2.setString(6, IdPw + "@agri.com");
							pstmt2.setString(7, getPhoneNum());
							pstmt2.setString(8, getAccountNum() + " " + randomName);
							pstmt2.setString(9, randomAddress());
							pstmt2.setInt(10, 1); //계정 상태
							pstmt2.setString(11, "S");
							pstmt2.setString(12, "sample_img.jpg");
							pstmt2.executeUpdate();
							conn.commit();						
					}
								
					
					// prod_detail 테이블 채우는 코드
					pstmt3.setString(1, IdPw);// member_id
					pstmt3.setString(2, name);
					pstmt3.setNString(3, content);
					pstmt3.setInt(4, price);
					pstmt3.setString(5, sell);
					pstmt3.setTimestamp(6, randomDeadline);
					pstmt3.setInt(7, cateCnt);
					pstmt3.setInt(8, randomStock());
					pstmt3.setInt(9, randomDelete());
					pstmt3.executeUpdate();
					conn.commit();

					
					
					// 상품 이름에 해당하는 prod_idx 가져오는 코드 (prod_img 테이블에 넣어야 됨)
					pstmt4.setString(1, name);
					ResultSet rs2 = pstmt4.executeQuery();
					rs2.next();
					Integer p_idx = rs2.getInt("prod_idx");
					//System.out.println(p_idx);
					
					
					// prod_img 테이블 채우는 코드
					int imgCnt = 0;
					for(String s : imgList) {
						
						if(!s.contains("200x0.")) {
							++imgCnt;
							if(true) {
							//if(imgCnt < 4) { // 이미지 몇 개 넣을 지 조절 가능 ★★★★★★
								System.out.println(s);
								pstmt5.setInt(1, p_idx);
								pstmt5.setString(2, s);
								pstmt5.executeUpdate();
								conn.commit();		
							}
						}						
					}
					++storedProdNum;					
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
			}	
			cateListSub = new HashSet<>();
		}
		//return "DB(prod_detail, sell_member, prod_img) 채우기 끝";
		System.out.println("DB(prod_detail, sell_member, prod_img) 채우기 끝");
		return storedProdNum;
	}
	
	
	

	
	public static String fillCate() {
		
		String sql = "INSERT INTO cate(cate_idx, cate_name) VALUES(?, ?)";
		int cnt = 0;
		try(
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			
			conn.setAutoCommit(false);
	
			for(int i = 0; i < cates.length; ++i) {
				pstmt.setInt(1, i+1);
				pstmt.setString(2, cates[i]);
				++cnt;
				pstmt.executeUpdate();
				conn.commit();
			}
						
			return "카테 채우기 완료";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
//	public static String fillCate(int cateIdx, int storedProdNum) {
//		
//		String sql = "update prod_detail set prod_cate = ? where prod_cate is null";
//		String cate = cates[cateIdx];
//		System.out.println(cate);
//		
//		try(
//				Connection conn = ds.getConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//		) {
//			conn.setAutoCommit(false);
//			pstmt.setString(1, cate);
//			pstmt.executeUpdate();
//			conn.commit();			
//		} catch (SQLException e) {
//			System.out.println("카테 채우기 실패 >>> " + e.getMessage());
//			//e.printStackTrace();
//		} 
//		return "카테 채우기 완료";
//	}
	
	// 220831 폰넘, 어카넘 잘못된 거 발견. 수정 완료
	public static String updateMembers() {
		//member_name, member_phonenum, member_accountnum, 
		String sql = "update members set member_name = ?, member_phonenum = ?, member_accountnum = ? where member_id = ?";
		try(
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			
			conn.setAutoCommit(false);
			
			for(int i = 0; i < 100; ++i) {
				String randomName = getRandomName();
				pstmt.setString(1, randomName);
				pstmt.setString(2, getPhoneNum());
				pstmt.setString(3, getAccountNum() + " " + randomName);
				pstmt.setString(4, "sample"+ i);
				pstmt.executeUpdate();
				conn.commit();
			}
			return "이름, 폰넘, 어카넘 수정 끝...";	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "이름, 폰넘, 어카넘 수정 뭔가 잘못 됨";
	}
	
	
	
	public static void main(String[] args) {

		
		//System.out.println(fillCate()); //cate테이블 채우기
		
		//DB에 상품정보, 이미지 집어넣을 때. cnt>i 니까 카테8(cnt=7)부터 뽑으려면 i = 6부터. 총 카테 수는 85개(cnt=84)
//		for(int i = 6; i < 7; ++i) { //6, 16이었음
//			int cateNum = (i+2);
//			
//			System.out.println(cateNum + "번째 카테고리 DB 저장 작업 시작");
//			
//			int storedProdNum = fillDB(i);
//			System.out.println(cateNum + "번 쨰 카테고리 / 상품 " + storedProdNum + "개 DB에 저장 됨");			
//		}

		
		//sell_id 에 넣을 아디 셋 뽑을 때(클래스 밖에선 fillDBService. 붙이고 fillDBService.getIdSet()으로 해시셋 얻어야 됨)
//		for(int i = 6; i <9; ++i) { //샘플로 카테8(cnt=7) 부터 카테10(cnt=9)까지만 판매자 name 중복 없이 뽑아서 해시셋에 저장하게 함. 카테 뽑는 조건이 if(cnt>i). 
//			getSellIdSet(i);	
//		}
//		System.out.println(idSet);

		
//		sell_member 테이블 채우는 메서드들 사용 예시
//		for(int i = 0; i < 105; ++i) {
//			System.out.println(getAccountNum() +  " " + getRandomName());
//			System.out.println(getPhoneNum());
//			
//			String idPw = getIdPw();
//			System.out.println(idPw);
//			System.out.println(getIdPw() + "@agri.com");
//			System.out.println(randomAddress());
//			System.out.println("_________________________");
//		}

		System.out.println(updateMembers());;
		
		
		
		
	}

	
	
	
}
