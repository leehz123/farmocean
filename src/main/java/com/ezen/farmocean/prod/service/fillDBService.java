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
	
	
	static public List<String> imgList = new ArrayList<>();
	static public Map<String, String> pInfo = new HashMap<>();
	static public HashSet<String> cateListSub = new HashSet<>();
	private static Set<String> nameSet = new HashSet<>();
	
	static HikariConfig config = new HikariConfig();
	static {
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@3.39.84.37:1521:XE"); // 원격 DB니까 ip주소 거기 걸로
		config.setUsername("project");
		config.setPassword("project!@");
	}
	static HikariDataSource ds = new HikariDataSource(config);
	static List<String> cateList = getHttpHTMLList("https://www.esingsing.co.kr");

	
	static String[] cates = {"사과, 사과즙, 참외", "귤, 유자, 곶감, 감", "토마토, 복숭아", "포도, 망고, 무화과", "멜론, 수박, 배", "딸기, 키위, 다래", "매실, 살구", "블루베리, 아로니아", "오미자, 구지뽕, 오디", "대추, 복분자", "자두, 탱자", "기타 과일", "과일 가공식품", 
								"감자, 고구마", "마늘, 양파, 생강, 파", "고추, 건고추, 태양초", "배추, 절임배추, 무, 열무", "호박, 깻잎, 상추", "비트, 콜라비, 파프리카", "당근, 오이, 가지", "여주, 오크라, 수세미", "기타 채소", "채소 가공식품",
								"시금치, 명이, 냉이", "고사리, 취나물", "곤드레, 시래기", "기타 나물", "나물 가공식품", "표고, 능이, 느타리", "상황, 송이, 새송이", "목이, 영지", "기타 버섯", "버섯 가공식품", 
								"옥수수", "참깨, 들깨", "쌀, 보리, 흑미, 찹쌀", "팥, 현미, 귀리", "콩, 서리태", "백태, 흑태, 녹두", "기타 곡물", "곡물 가공식품", "밤, 은행, 잣", "땅콩, 호두", "기타 견과", "견과 가공식품",
								"마, 하늘마, 칡", "돼지감자, 토란", "도라지, 더덕", "인삼, 홍삼, 산삼", "기타 뿌리", "뿌리 가공 식품", 
								"꿀, 로얄젤리, 프로폴", "효소, 진액, 건강즙", "분말, 열매, 건강차", "기타 약초",
								"전복, 굴, 조개류", "게, 새우, 바닷가재", "문어, 오징어, 낙지, 꼴뚜기", "다슬기, 소라, 멍게", "미역, 김, 다시마", "멸치, 천일염", "기타 해산물", "해산물 가공식품", "삼치, 임연수, 갈치", "홍어, 고등어, 민어", "장어, 광어, 우럭, 돔", "굴비, 조기, 옥돔", "과메기, 꽁치, 전어", "아귀, 도루묵", "기타 생선", "생선 가공식품"};
	
	
	public static Set<String> getNameSet() {
		return nameSet;
	}

	public static void setNameSet(Set<String> idSet) {
		fillDBService.nameSet = nameSet;
	}

	
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

	
	public static void getSellNameSet(int i) {		
		int cnt = 0;
		boolean loop = true;
		for(String s : cateList) {
			
			if(cnt > i) {	//	8번째 부터 농수산물 상품 정보임 그전거는 가격불러오는 부분이라 건너뜀
				if(loop) {	// 테스트여서 카테고리 하나만 가져오게 함(여기 풀면 메인 페이지에서 불러올수 있는 상품 정보 다 불러옴)
					System.out.println((cnt+1) + "번째 카테고리 저장 중..");
					getHttpHTMLListSub(s);
					loop = false;
				}
			}else {
				cnt++;
			}
		}
		
		int prodCnt = 0;
		for(String url : cateListSub) {
			
			if(prodCnt < 5) {
				System.out.println(++prodCnt + "번째 상품 처리 중");

				imgList = new ArrayList<>();
				pInfo = new HashMap<>();			
				getHttpHTML(url);

				
				String seller = pInfo.get("sellMember");//얘는 나중에 sell_id로 대체해줄 거
				System.out.println(seller);
				nameSet.add(seller);
			}
			cateListSub = new HashSet<>();
		}
		//return idSet;
	}
	
	
	
	
	
	public static String fillDB(int i) {
		
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
			
			if(prodCnt < 5) { //상품 몇 개 넣을 지 조절 가능 ★★★★★★★★★
				System.out.println(++prodCnt + "번째 prod 처리ing");
				Timestamp randomDeadline = null; //getRandomDeadLine("판매종료");
				
				imgList = new ArrayList<>();
				pInfo = new HashMap<>();
				
				getHttpHTML(url);
				String seller = pInfo.get("sellMember");//얘는 나중에 sell_id로 대체해줄 거
				String name = pInfo.get("title");
				String content = pInfo.get("detail");
				String priceStr = pInfo.get("price").replaceAll("\\,", "");
				Integer price = Integer.parseInt(priceStr.substring(0, priceStr.indexOf('원')));
				String sell = randomSell();
				randomDeadline = getRandomDeadLine(sell);		
						
				String sql1 = "INSERT INTO prod_detail(prod_idx, sell_id, prod_name, prod_info, prod_price, prod_sell, prod_sell_deadline)"
								+ "	VALUES( prod_idx_seq.nextval, ?, ?, ?, ?, ?, ?)";
				
				String sql2 = "SELECT prod_idx FROM prod_detail WHERE prod_name = ?";
	
				String sql3 = "INSERT INTO prod_img(img_idx, prod_idx, img_url) VALUES(img_idx_seq.nextval, ?, ?)";
	
				try (
						Connection conn = ds.getConnection();
						Connection conn2 = ds.getConnection();
						Connection conn3 = ds.getConnection();
						
						PreparedStatement pstmt = conn.prepareStatement(sql1);				
						PreparedStatement pstmt2 = conn2.prepareStatement(sql2);
						PreparedStatement pstmt3 = conn3.prepareStatement(sql3);
				){
					
					conn.setAutoCommit(false);
					conn3.setAutoCommit(false);
					
					pstmt.setString(1, seller);
					pstmt.setString(2, name);
					pstmt.setNString(3, content);
					pstmt.setInt(4, price);
					pstmt.setString(5, sell);
					pstmt.setTimestamp(6, randomDeadline);
					
					pstmt.executeUpdate();
					conn.commit();
				
					pstmt2.setString(1, name);
					ResultSet rs = pstmt2.executeQuery();
					rs.next();
					Integer p_idx = rs.getInt("prod_idx");
					//System.out.println(p_idx);
					
					int imgCnt = 0;
					for(String s : imgList) {
						++imgCnt;
						if(imgCnt < 4) { // 이미지 몇 개 넣을 지 조절 가능 ★★★★★★
							System.out.println(s);
							pstmt3.setInt(1, p_idx);
							pstmt3.setString(2, s);
							pstmt3.executeUpdate();
							conn3.commit();		
						}
										}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			cateListSub = new HashSet<>();
		}
		
		return "prod DB 채우기 끝";
	}
	
	public static void main(String[] args) {

		//DB에 상품정보, 이미지 집어넣을 때. cnt>i 니까 카테8(cnt=7)부터 뽑으려면 i = 6부터. 총 카테 수는 85개(cnt=84)
//		for(int i = 6; i < 85; ++i) {
//			System.out.println((i+1) + "번째 페이지 DB 저장 작업 시작");
//			System.out.println(fillDB(i));			
//		}

		
		//sell_id 에 넣을 아디 셋 뽑을 때(클래스 밖에선 fillDBService. 붙이고 fillDBService.getIdSet()으로 해시셋 얻어야 됨)
//		for(int i = 6; i <9; ++i) { //샘플로 카테8(cnt=7) 부터 카테10(cnt=9)까지만 판매자 name 중복 없이 뽑아서 해시셋에 저장하게 함. 카테 뽑는 조건이 if(cnt>i). 
//			getSellIdSet(i);	
//		}
//		System.out.println(idSet);
		

	}

	
}
