package com.ssafy.trip.attraction.model.service;

import java.util.List;

import com.ssafy.trip.attraction.model.dto.Attraction;
import com.ssafy.trip.attraction.model.dto.SearchOption;

public interface AttractionService {
	Attraction search(String contextId);
	List<Attraction> searchAll(SearchOption option);
}
