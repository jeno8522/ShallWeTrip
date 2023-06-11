package com.ssafy.trip.hotplace.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.hotplace.model.dto.FileInfoDto;
import com.ssafy.trip.hotplace.model.dto.HotPlace;
import com.ssafy.trip.hotplace.model.dto.HotPlacePageBean;
import com.ssafy.trip.infoBoard.model.dto.InfoBoard;

public interface HotPlaceDao {

	void writeArticle(HotPlace boardDto) throws SQLException;
//	List<InfoBoard> listArticle(Map<String, Object> param) throws SQLException;
	List<HotPlace> listArticle(HotPlacePageBean bean) throws SQLException;
//	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	int getTotalArticleCount(HotPlacePageBean bean) throws SQLException;
	HotPlace getArticle(int hotId) throws SQLException;
	void updateHit(int hotId) throws SQLException;
	void uploadFile(HotPlace boardDto) throws Exception;
	List<FileInfoDto> fileInfoList(int hotId) throws Exception;
	void modifyArticle(HotPlace boardDto) throws SQLException;
	void deleteArticle(int hotId) throws SQLException;
	void deleteImage(int hotId) throws SQLException;
	List<HotPlace> listArticleAll();
	
}
