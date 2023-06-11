package com.ssafy.trip.infoBoard.model.service;

import java.util.List;

import com.ssafy.trip.infoBoard.model.dto.InfoBoard;
import com.ssafy.trip.infoBoard.model.dto.PageBean;

public interface InfoBoardService {

	void writeArticle(InfoBoard boardDto) throws Exception;
//	List<InfoBoard> listArticle(Map<String, String> map) throws Exception;
	List<InfoBoard> listArticle(PageBean bean) throws Exception;
	List<InfoBoard> listArticleAll() throws Exception;
//	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
//	PageNavigation makePageNavigation(PageBean bean) throws Exception;
	InfoBoard getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	int getTotalArticleCount(PageBean bean) throws Exception;
	void modifyArticle(InfoBoard boardDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	
}
