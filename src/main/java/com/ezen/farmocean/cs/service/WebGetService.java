package com.ezen.farmocean.cs.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class WebGetService {
	
	static public List<String> imgList = new ArrayList<>();
	static public Map<String, String> pInfo = new HashMap<>();
	
	static public HashSet<String> cateListSub = new HashSet<>();
	
	public static void main(String[] args) {
		
		List<String> cateList = getHttpHTMLList("https://www.esingsing.co.kr");		
		int cnt = 0;
		boolean loop = true;
		for(String s : cateList) {
			
			if(cnt > 6) {	//	8��° ���� ����깰 ��ǰ ������ �����Ŵ� ���ݺҷ����� �κ��̶� �ǳʶ�
				if(loop) {	// �׽�Ʈ���� ī�װ� �ϳ��� �������� ��(���� Ǯ�� ���� ���������� �ҷ��ü� �ִ� ��ǰ ���� �� �ҷ���)
					getHttpHTMLListSub(s);
					loop = false;
				}
			}else {
				cnt++;
			}
		}
		
		for(String url : cateListSub) {
			
			imgList = new ArrayList<>();
			pInfo = new HashMap<>();
			
			getHttpHTML(url);
				
			for(String s : imgList) {		// ��ǰ �̹��� ����
				System.out.println(s);
			}			
			System.out.println(pInfo);		// ��ǰ ����
			System.out.println("----------------------------------");
		}
		
		
		
		
			
		
		
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
					
					chkValue = "<b>���� :</b>";
					if(line.contains(chkValue)) {
						String insertValue = "";
						if(line.contains("<a href=\"")) {						
							insertValue = line.substring(chkValue.length() + 5, line.indexOf("<a href=\""));
						}else if(line.contains("<del>")) {
							insertValue = line.substring(line.indexOf("<del>") + 5, line.indexOf("</del>"));
						}
						
						
						pInfo.put("phone", insertValue.trim());
					}
					
					chkValue = "<b>�Ա� :</b>";
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

}
