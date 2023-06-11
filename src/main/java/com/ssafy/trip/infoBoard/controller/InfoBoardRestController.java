package com.ssafy.trip.infoBoard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.infoBoard.model.dto.InfoBoard;
import com.ssafy.trip.infoBoard.model.dto.PageBean;
import com.ssafy.trip.infoBoard.model.service.InfoBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/trip/swagger-ui.html
//@RequestMapping("/rest")
@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)

@RequestMapping("/board")
@Api(value = "Book REST-ful위한 API", description = "SAFFY 도서 정보 ")
public class InfoBoardRestController {
	private static final Logger logger = LoggerFactory.getLogger(InfoBoardRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private InfoBoardService boardService;

//    @GetMapping("/infoList")
	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<InfoBoard>> infoList() throws Exception {
		List<InfoBoard> infoBoards = boardService.listArticleAll();
//		logger.debug("infoBoards....................{}", infoBoards);
		return new ResponseEntity<>(infoBoards, HttpStatus.OK);
	}
//    public ResponseEntity<List<InfoBoard>> infoList(PageBean bean) throws Exception {
//        logger.debug("infoBoards....................pageBean:{}", bean);
////        bean.setPageNo(2);
//        List<InfoBoard> infoBoards = boardService.listArticle(bean);
//        logger.debug("infoBoards....................{}", infoBoards);
//        return new ResponseEntity<>(infoBoards, HttpStatus.OK);
//    }

	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/page={pageNo}")
	public ResponseEntity<List<InfoBoard>> infoList(PageBean bean, @PathVariable("pageNo") int pageNo)
			throws Exception {
//		logger.debug("infoBoards....................pageBean:{}", bean);
		bean.setPageNo(pageNo);
		List<InfoBoard> infoBoards = boardService.listArticle(bean);
//		logger.debug("page처리된 infoboardsS....................{}", infoBoards);
		return new ResponseEntity<>(infoBoards, HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = InfoBoard.class)
	@GetMapping("/{infoId}")
	public ResponseEntity<InfoBoard> view(
			@PathVariable("infoId") @ApiParam(value = "얻어올 글의 글번호.", required = true) int infoId) throws Exception {
//		logger.debug("infoBoard detail....................");
		boardService.updateHit(infoId);
		InfoBoard infoBoard = boardService.getArticle(infoId);
//		logger.debug("detail....................{}", infoBoard);
		return new ResponseEntity<>(infoBoard, HttpStatus.OK);
	}

//    @PostMapping("/write")
	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> write(@RequestBody InfoBoard infoBoard) throws Exception {
//		logger.debug("infoBoard write 호출..............");
//        User user = (User) session.getAttribute("userInfo");
//        infoBoard.setUserId(user.getId());
		boardService.writeArticle(infoBoard);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

//	@PutMapping("/modify")
	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modify(
			@RequestBody @ApiParam(value = "수정할 글정보.", required = true) InfoBoard infoBoard, PageBean bean)
			throws Exception {
//		logger.debug("infoBoard................... update infoBoard:{}", infoBoard);
		boardService.modifyArticle(infoBoard);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{infoId}")
	public ResponseEntity<String> delete(
			@PathVariable("infoId") @ApiParam(value = "삭제할 글의 글번호.", required = true) int infoId, PageBean bean)
			throws Exception {
//		logger.debug("deleteArticle - 호출");
//		logger.debug("params로 넘어온 infoId......{}", infoId);
		boardService.deleteArticle(infoId);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
