package com.ezen.farmocean.admin.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.farmocean.admin.dto.Banner;
import com.ezen.farmocean.admin.dto.BuyInfo;
import com.ezen.farmocean.admin.dto.BuyListInfo;
import com.ezen.farmocean.admin.service.JsonProdService;
import com.ezen.farmocean.cs.dto.CsBoard;
import com.ezen.farmocean.cs.service.BoardService;
import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.cs.service.FileUploadService;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.service.MemberService;
import com.ezen.farmocean.prod.dto.Product;
import com.ezen.farmocean.prod.service.ProdImgService;
import com.ezen.farmocean.prod.service.ProdService;
import com.nhncorp.lucy.security.xss.XssPreventer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class AdminController {
	
	@Autowired
	CommonFunction cf;
	
	@Autowired
	JsonProdService serviceJson;
	
	@Autowired
	ProdService serviceProd;
	
	@Autowired
	ProdImgService serviceProdImg;
	
	@Autowired
	BoardService serviceBoard;
	
	@Autowired
	HttpServletRequest req;
	
	@Autowired
	MemberService serviceMember;
	
	@Autowired
	FileUploadService serviceFileUpload;
			
	// 관리자 페이지	
	@GetMapping("/admin/main")
	public String viewMain() {
		
		HttpSession session = req.getSession();
			
		LoginMember lInfo = cf.loginInfo(req);
		
		if(cf.chkNull(lInfo.getMember_id())){
			session.setAttribute("admin", 0);
		}else {		
			session.setAttribute("admin", serviceJson.chkAdmin(lInfo.getMember_id()));
		}
				
		return "redirect:/admin/mainbanner";
		
	}
	
	@GetMapping("/admin/authlist")
	public void viewAuthList() {
		
	}
	
	@GetMapping("/admin/buylist")
	public void viewBuyList() {
		
	}
	
	@PostMapping("/admin/buylist")
	public void viewBuyList(String member_id, Model model) {		
		List<BuyListInfo> buyList = serviceJson.selBuyList(member_id, 1, 10);
		for(BuyListInfo b : buyList) {
			b.setView_price(cf.viewWon(b.getProd_price()));
			b.setView_regdate(cf.viewDate(b.getReg_date()));
			b.setDec();
			b.setAddress();
		}
		
		model.addAttribute("buyList", buyList);
	}
	
	@GetMapping("/admin/selllist")
	public void viewSellList() {
		
	}
	
	@PostMapping("/admin/selllist")
	public void viewSellList(String member_id, Integer pageNum, Model model) {
		
		if(cf.chkNull(pageNum)) {
			pageNum = 1;
		}
		
		int buyCount = serviceJson.selSellCount(member_id);
		int pageSize = 10;
		
		int totalPage = buyCount % pageSize == 0 ? buyCount / pageSize : buyCount / pageSize + 1;
		
		List<BuyListInfo> sellList = serviceJson.selSellList(member_id , pageNum, pageSize);
		for(BuyListInfo b : sellList) {
			b.setView_price(cf.viewWon(b.getProd_price()));
			b.setView_regdate(cf.viewDate(b.getReg_date()));
			b.setDec();
			b.setAddress();
		}
		
		model.addAttribute("sellList", sellList);
		model.addAttribute("page", pageNum);
		model.addAttribute("pageLsit", totalPage);
	}
	
	@GetMapping("/admin/mainbanner")
	public void viewMainBanner(Model model) {
		
		model.addAttribute("maintop",  serviceJson.selMainTopBanner("maintop"));
		 
	}
	
	@GetMapping("/admin/sellsearch")
	public void viewSellSearch() {
		
	}
	
	@GetMapping("/admin/prodsearch")
	public void viewProdSearch() {
		
	}
	
	@GetMapping("/admin/adminauth")
	public void viewAdminAuth(Model model) {
		
		List<String> adminList = serviceJson.listAdmin();
		List<Member> adminInfoList = new ArrayList<>();
		
		for(String id : adminList) {
			adminInfoList.add(serviceMember.getMember(id));			
		}
		for(Member m : adminInfoList) {
			m.setDec();
			m.setMember_pw("");
		}
		
//		log.info(adminInfoList);
		
		model.addAttribute("adminList", adminInfoList);
		
	}
	
	@GetMapping("/admin/daumtest")
	public void viewDaumTest() {
		
	}
	
	
	
	
	// 구매자 팝업
	@GetMapping("/buy/prod/{prodIdx}")
	public String setBuyProd(@PathVariable Integer prodIdx, Model model) {
		
		Product product = serviceProd.getProductById(prodIdx);
		LoginMember mInfo = cf.loginInfo(req);
		if(product == null || cf.chkNull(mInfo.getMember_id())) {
			model.addAttribute("productTitle", null);
		}else {
			
			model.addAttribute("productId", prodIdx);
			model.addAttribute("productTitle",cf.cutStr(product.getProd_name(), 10));
			model.addAttribute("productPrice", cf.viewWon(product.getProd_price()));			
			model.addAttribute("productDeadline", cf.viewDate(product.getProd_sell_deadline()));
			model.addAttribute("productImg", serviceProdImg.getImgsByProdIdx(prodIdx));
		}
		
		return "product/product_buy";
	}
	
	// 메인 상단 배너 등록
	@PostMapping("/admin/setMainTopBanner")
	public String setMainTopBanner(MultipartHttpServletRequest multiReq) throws Exception {
		
		Map<String , MultipartFile> arrReq = multiReq.getFileMap();
		
		Iterator<Entry<String , MultipartFile>> itr = arrReq.entrySet().iterator();
		
		List<Banner> bannerList = new ArrayList<>();
		
		String bannerCate = "maintop";
	
		for(int i = 0, maxCnt = multiReq.getParameterValues("mainTopName").length; i < maxCnt; i++) {
			
			Integer bannerIdx = cf.chkNull(multiReq.getParameterValues("mainTopIdx")[i]) ? 0 : Integer.parseInt(multiReq.getParameterValues("mainTopIdx")[i]); 
			
			bannerList.add(new Banner(bannerIdx, bannerCate, 0, multiReq.getParameterValues("mainTopName")[i], multiReq.getParameterValues("mainTopLink")[i], multiReq.getParameterValues("mainTopFileName")[i]));
		}
		
//		log.info(bannerList);
		
		int chkNum = 0;
		
		//String savePath = "\\JavaAWS\\project-farmocean\\src\\main\\webapp\\resources\\image\\mainbanner";
		
		String uploadPath = "resources\\image\\mainbanner";
        String savePath = req.getServletContext().getRealPath("/") + uploadPath;// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
		log.info("savepath : " + savePath);
		
		while(itr.hasNext()) {
			
			Entry<String , MultipartFile> entry = itr.next();
			
			MultipartFile mFile = entry.getValue();			
			
			if(!cf.chkNull(mFile.getOriginalFilename())) {			
				
				if(!cf.chkNull(bannerList.get(chkNum).getFilename())){				
					String[] removeFile = bannerList.get(chkNum).getFilename().split("/");
					String removeFileName = removeFile[removeFile.length -1];				
					serviceFileUpload.fileDelete(serviceFileUpload.getDriver(), savePath, removeFileName);
				}
				bannerList.get(chkNum).setFilename("/resources/image/mainbanner/" + serviceFileUpload.fileUpload(mFile, savePath));								
			}
			
			chkNum++;
		}
		
		for(Banner b : bannerList) {
			if(b.getIdx() == 0) {
				serviceJson.setMainTopBanner(b);
			}else {
				serviceJson.uptMainTopBanner(b);
			}
		}
		
//		log.info(bannerList);
		return "redirect:/admin/mainbanner";
	}
	
	@GetMapping(value = "/test/test")
	public void test() {
		BuyInfo bInfo =  new BuyInfo();
		bInfo.setPost_code("010101");
		bInfo.setEnc();
//		log.info("test : " + bInfo);
	}
	
	/*
	 * 공지사항 목록(패이징)
	 */
	@GetMapping("/admin/board/{cate}/{page}")
	public String adminBoardList(Model model, @PathVariable Integer cate, @PathVariable Integer page) {
		
		int pageSize = 20;
		
		model.addAttribute("boards", serviceBoard.getBoardList(page, pageSize, cate));
		
		int totalCnt = serviceBoard.getBoardCount();
		
		int pageLsit = totalCnt % pageSize == 0 ? totalCnt / pageSize : totalCnt / pageSize + 1;
		
		model.addAttribute("page", page);
		model.addAttribute("pageLsit", pageLsit);
		
		HttpSession session = req.getSession();
		
		LoginMember lInfo = cf.loginInfo(req);
		
		if(cf.chkNull(lInfo.getMember_id())){
			session.setAttribute("admin", 0);
		}else {		
			session.setAttribute("admin", serviceJson.chkAdmin(lInfo.getMember_id()));
		}
				
		return "admin/board";
	}
	
	/**
	 * 게시글 보기
	 * @param board_idx
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/boardView/{board_idx}")
	public String adminBboardView(@PathVariable Integer board_idx, Model model, Integer page) {		
		serviceBoard.setBoardCount(board_idx);
		CsBoard board = serviceBoard.getBoardInfo(board_idx);
		board.setBoard_memo(cf.chgHtml(board.getBoard_memo()));
		
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		return "admin/boardView";
	}
	
	//공지 등록
	@GetMapping("/admin/noticeInsert")
	public void noticeInsert(Model model) {
		model.addAttribute("catagorys", serviceBoard.getGateList());
	}
	
	/**
	 * 공지사항 등록
	 * @param board
	 * @return
	 */
	@PostMapping("/admin/noticeInsert")
	public String boardInsert(CsBoard board) {
		
		LoginMember mInfo = cf.loginInfo(req);
		
		if(mInfo.getMember_id() == null) {
			return "redirect:noticeInsert";
		}
		
		board.setBoard_header(0);
		board.setBoard_writer(mInfo.getMember_id());
		
		if(cf.chkNull(board.getBoard_title()) || cf.chkNull(board.getBoard_memo())) {
			return "redirect:noticeInsert";
		}
		
		board.setBoard_title(XssPreventer.escape(board.getBoard_title()));
		
		if(serviceBoard.setBoardIns(board) > 0) {
			return "redirect:noticeInsert";
		}else {
			return "redirect:noticeInsert";
		}
	}
	
	//공지 등록
	@GetMapping("/admin/noticeInsertCopy")
	public void noticeInsertCopy(Model model) {
		model.addAttribute("catagorys", serviceBoard.getGateList());
	}
}
