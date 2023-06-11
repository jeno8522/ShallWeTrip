package com.ssafy.trip.infoBoard.model.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssafy.trip.infoBoard.controller.InfoBoardRestController;
import com.ssafy.trip.infoBoard.model.dao.InfoBoardDao;
import com.ssafy.trip.infoBoard.model.dto.InfoBoard;
import com.ssafy.trip.infoBoard.model.dto.PageBean;
import com.ssafy.trip.util.PageUtility;

@Service
public class InfoBoardServiceImpl implements InfoBoardService {
	private InfoBoardDao boardDao;
//	private static InfoBoardService boardService = new InfoBoardServiceImpl(boardDao);
    private static final Logger logger = LoggerFactory.getLogger(InfoBoardService.class);
	
//	private InfoBoardServiceImpl() {
//		boardDao = InfoBoardDaoImpl.getBoardDao();
//	}
	private InfoBoardServiceImpl(InfoBoardDao boardDao) {
		this.boardDao = boardDao;
	}

//	public static InfoBoardService getBoardService() {
//		return boardService;
//	}

	@Override
	public void writeArticle(InfoBoard boardDto) throws Exception {
		boardDao.writeArticle(boardDto);
	}

//	@Override
//	public List<InfoBoard> listArticle(Map<String, String> map) throws Exception {
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
////		if("userid".equals(key))
////			key = "user_id";
//		param.put("key", key.isEmpty() ? "" : key);
//		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);
//		return boardDao.listArticle(param);
//	}
	@Override
	public List<InfoBoard> listArticleAll() throws Exception {
		List<InfoBoard>  result= boardDao.listArticleAll();
		return result;
	}
	@Override
	public List<InfoBoard> listArticle(PageBean bean) throws SQLException {

			List<InfoBoard>  first= boardDao.listArticle(bean);
			int total = boardDao.getTotalArticleCount(bean);
//			logger.debug("searchAll  bean INfo..................{}",bean);
			PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "");
			bean.setPageLink(page.getPageBar());  //paging 처리한 html 링크를 추출하기 
			List<InfoBoard>  result= boardDao.listArticle(bean);
			return first;

	}
	
//	@Override
//	public PageNavigation makePageNavigation(PageBean bean) throws Exception {
//		PageNavigation pageNavigation = new PageNavigation();
//
//		int naviSize = SizeConstant.NAVIGATION_SIZE;
//		int sizePerPage = SizeConstant.LIST_SIZE;
//		int currentPage = Integer.parseInt(map.get("pgno"));
//
//		pageNavigation.setCurrentPage(currentPage);
//		pageNavigation.setNaviSize(naviSize);
////		Map<String, Object> param = new HashMap<String, Object>();
////		String key = map.get("key");
////		if ("userid".equals(key))
////			key = "user_id";
////		param.put("key", key.isEmpty() ? "" : key);
////		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
//		int totalCount = boardDao.getTotalArticleCount(bean);
//		pageNavigation.setTotalCount(totalCount);
//		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//		pageNavigation.setTotalPageCount(totalPageCount);
//		boolean startRange = currentPage <= naviSize;
//		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//		pageNavigation.setEndRange(endRange);
//		pageNavigation.makeNavigator();
//
//		return pageNavigation;
//	}

	@Override
	public InfoBoard getArticle(int articleNo) throws Exception {
		return boardDao.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		boardDao.updateHit(articleNo);
	}

	@Override
	public void modifyArticle(InfoBoard boardDto) throws Exception {
		// TODO : BoardDaoImpl의 modifyArticle 호출
		boardDao.modifyArticle(boardDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		// TODO : BoardDaoImpl의 deleteArticle 호출
		boardDao.deleteArticle(articleNo);
	}

	@Override
	public int getTotalArticleCount(PageBean bean) throws Exception {
		int total = boardDao.getTotalArticleCount(bean);
		return total;
	}



}
