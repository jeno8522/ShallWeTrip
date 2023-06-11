package com.ssafy.trip.attraction.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.attraction.model.dto.Attraction;
import com.ssafy.trip.attraction.model.dto.SearchOption;

public interface AttractionDao {

	Attraction search(String contentId) throws SQLException;
	List<Attraction> searchAll(SearchOption option) throws SQLException;

}
