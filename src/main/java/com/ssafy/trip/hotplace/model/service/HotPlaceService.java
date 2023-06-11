package com.ssafy.trip.hotplace.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.hotplace.model.dto.HotPlace;
import com.ssafy.trip.hotplace.model.dto.HotPlacePageBean;

public interface HotPlaceService {

	void writeArticle(HotPlace boardDto) throws Exception;
//	List<HotPlaceBoard> listArticle(Map<String, String> map) throws Exception;
	List<HotPlace> listArticle(HotPlacePageBean bean) throws Exception;
//	void writeArticle(HotPlace hotPlace, MultipartFile[] files) throws Exception;
//	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
//	PageNavigation makePageNavigation(HotPlacePageBean bean) throws Exception;
	HotPlace getArticle(int hotId) throws Exception;
	void updateHit(int hotId) throws Exception;
	void modifyArticle(HotPlace boardDto) throws Exception;
	void deleteArticle(int hotId) throws Exception;
	public List<HotPlace> listArticleAll() throws Exception;
}
