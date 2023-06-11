package com.ssafy.trip.infoBoard.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.trip.infoBoard.model.dto.InfoBoard;
import com.ssafy.trip.infoBoard.model.dto.PageBean;
import com.ssafy.trip.infoBoard.model.service.InfoBoardService;
import com.ssafy.trip.user.model.dto.User;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/info")
public class InfoBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(InfoBoardController.class);
	private int pgno;
	private String key;
	private String word;
	private String queryStrig;
	
	@Autowired
	private InfoBoardService boardService;

//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		boardService = InfoBoardServiceImpl.getBoardService();
//	}

	
	@ApiOperation(value="책 목록 정보", notes = "검색 조건에 맞는 도서 정보를 검색한 목록 정보")
	@GetMapping("/infoList")
	public void bookList(PageBean bean, Model model) throws Exception {
		logger.debug("infoBoards....................pageBean:{}",bean);
		System.out.println("%^*(#%*^%#(^#(%*&(#*&(P*&%P(*(&*&%%#(&*");
		System.out.println(bean.toString());
		List<InfoBoard> infoBoards = boardService.listArticle(bean);
		System.out.println("--------------여기야여기-----------------");
		System.out.println(infoBoards.toString());
		logger.debug("infoBoards....................{}",infoBoards);
		model.addAttribute("infoBoards", infoBoards);
	}
	
	@GetMapping("/view")
	public void detail(int infoId, PageBean bean, Model model) throws Exception {
		logger.debug("infoBoard detail....................");
		boardService.updateHit(infoId);
		InfoBoard infoBoard = boardService.getArticle(infoId);
		logger.debug("detail....................{}",infoBoard);
		model.addAttribute("infoBoard", infoBoard);
	}
	
	@GetMapping("/write")
	public void writeArticle(HttpSession session) {
		logger.debug("infoBoard................... write 화면으로 이동");
	}
	
	@PostMapping("/write")
	public String regist(InfoBoard infoBoard, HttpSession session) throws Exception {
		logger.debug("infoBoard................... write");
		logger.debug("infoBoard................... 요청데이타 infoBoard:{}",infoBoard.toString());
		User user = (User)session.getAttribute("userInfo");
		infoBoard.setUserId(user.getUserid());
		boardService.writeArticle(infoBoard);
		return "redirect:/info/infoList";
	}
	
	@GetMapping("/delete")
	public String delete(int infoId, PageBean bean, RedirectAttributes ra) throws Exception {
		logger.debug("book................... remove isbn:{}",infoId);
		boardService.deleteArticle(infoId);
		ra.addAttribute("pageNo", bean.getPageNo());
		ra.addAttribute("key", bean.getKey());
		ra.addAttribute("word", bean.getWord());
		return "redirect:/info/infoList";
	}
	
	@GetMapping("/modify")
	public void modify(int infoId, Model model) throws Exception {
		logger.debug("infoBoard................... update isbn:{}",infoId);
		InfoBoard infoBoard = boardService.getArticle(infoId);
		logger.debug("infoBoard................... update book:{}",infoBoard);
		model.addAttribute("infoBoard", infoBoard);
	}
	
	@PostMapping("/modify")
	public String modify(InfoBoard infoBoard, PageBean bean, RedirectAttributes ra) throws Exception {
		logger.debug("infoBoard................... update infoBoard:{}",infoBoard);
		boardService.modifyArticle(infoBoard);
//		ra.addAttribute("pageNo", bean.getPageNo());
//		ra.addAttribute("key", bean.getKey());
//		ra.addAttribute("word", bean.getWord());
		
		return "redirect:/info/infoList";
	}
	
	public boolean check(HttpSession session) {
//		User  user = (User)session.getAttribute("userInfo");
//		if(user == null  || !user.getId().equals("admin")) {
//			throw new BookException("관리자 전용 페이지 입니다. 접근 권한이 없습니다.");
//		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
