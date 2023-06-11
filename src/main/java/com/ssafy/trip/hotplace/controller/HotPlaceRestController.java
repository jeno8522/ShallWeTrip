package com.ssafy.trip.hotplace.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.hotplace.model.dto.HotPlace;
import com.ssafy.trip.hotplace.model.service.HotPlaceServiceImpl;
import com.ssafy.trip.infoBoard.model.dto.PageBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/trip/swagger-ui.html
//@RequestMapping("/rest")
@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)

@RequestMapping("/hotplace")
@Api(value = "Book REST-ful위한 API", description = "SAFFY 도서 정보 ")
public class HotPlaceRestController {
	private static final Logger logger = LoggerFactory.getLogger(HotPlaceRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

//	@Value("${file.path}")
//	private String realPath;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private HotPlaceServiceImpl boardService;

//    @GetMapping("/infoList")
	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<HotPlace>> hotList() throws Exception {
		List<HotPlace> hotPlaces = boardService.listArticleAll();
		logger.debug("hotPlaces....................{}", hotPlaces);
		return new ResponseEntity<>(hotPlaces, HttpStatus.OK);
	}
//    public ResponseEntity<List<HotPlace>> infoList(PageBean bean) throws Exception {
//        logger.debug("infoBoards....................pageBean:{}", bean);
////        bean.setPageNo(2);
//        List<HotPlace> infoBoards = boardService.listArticle(bean);
//        logger.debug("infoBoards....................{}", infoBoards);
//        return new ResponseEntity<>(infoBoards, HttpStatus.OK);
//    }
//
//	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
//	@GetMapping("/page={pageNo}")
//	public ResponseEntity<List<HotPlace>> infoList(PageBean bean, @PathVariable("pageNo") int pageNo)
//			throws Exception {
////		logger.debug("infoBoards....................pageBean:{}", bean);
//		bean.setPageNo(pageNo);
//		List<HotPlace> infoBoards = boardService.listArticle(bean);
////		logger.debug("page처리된 infoboardsS....................{}", infoBoards);
//		return new ResponseEntity<>(infoBoards, HttpStatus.OK);
//	}

	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = HotPlace.class)
	@GetMapping("/{hotId}")
	public ResponseEntity<HotPlace> view(
			@PathVariable("hotId") @ApiParam(value = "얻어올 글의 글번호.", required = true) int hotId) throws Exception {
//		logger.debug("hotPlace detail....................{}" );
		boardService.updateHit(hotId);
		HotPlace hotPlace = boardService.getArticle(hotId);
		logger.debug("hotPlace detail....................{}", hotPlace);
		return new ResponseEntity<>(hotPlace, HttpStatus.OK);
	}

////    @PostMapping("/write")
//	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@PostMapping
//	public ResponseEntity<String> write(@RequestBody HotPlace hotPlace) throws Exception {
////		logger.debug("hotPlace write 호출..............");
////        User user = (User) session.getAttribute("userInfo");
////        hotPlace.setUserId(user.getId());
//		boardService.writeArticle(hotPlace);
//		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//	}
//    @PostMapping("/write")
	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeArticle(@ModelAttribute HotPlace hotplace) throws Exception {
		logger.debug("hotPlace write 호출..............");
//        User user = (User) session.getAttribute("userInfo");
//        hotPlace.setUserId(user.getId());
		logger.debug("upload한 파일...............{}", hotplace);
		boardService.writeArticle(hotplace);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

//	@PutMapping("/modify")
	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modify(
			@RequestBody @ApiParam(value = "수정할 글정보.", required = true) HotPlace hotPlace, PageBean bean)
			throws Exception {
//		logger.debug("hotPlace................... update hotPlace:{}", hotPlace);
		boardService.modifyArticle(hotPlace);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{hotId}")
	public ResponseEntity<String> delete(
			@PathVariable("hotId") @ApiParam(value = "삭제할 글의 글번호.", required = true) int hotId, PageBean bean)
			throws Exception {
//		logger.debug("deleteArticle - 호출");
//		logger.debug("params로 넘어온 infoId......{}", infoId);
		boardService.deleteArticle(hotId);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
