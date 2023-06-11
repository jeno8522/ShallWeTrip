package com.ssafy.trip.infoBoard.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.infoBoard.model.dto.InfoBoard;
import com.ssafy.trip.infoBoard.model.dto.PageBean;

public interface InfoBoardDao {

	void writeArticle(InfoBoard boardDto) throws SQLException;
//	List<InfoBoard> listArticle(Map<String, Object> param) throws SQLException;
	List<InfoBoard> listArticle(PageBean bean) throws SQLException;
	List<InfoBoard> listArticleAll() throws SQLException;
//	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	int getTotalArticleCount(PageBean bean) throws SQLException;
	InfoBoard getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	
	void modifyArticle(InfoBoard boardDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;
	
}
