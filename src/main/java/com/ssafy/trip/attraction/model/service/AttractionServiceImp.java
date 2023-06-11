package com.ssafy.trip.attraction.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.attraction.model.dao.AttractionDao;
import com.ssafy.trip.attraction.model.dto.Attraction;
import com.ssafy.trip.attraction.model.dto.SearchOption;

@Service
public class AttractionServiceImp implements AttractionService {
	private AttractionDao dao;
	public AttractionServiceImp(AttractionDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public Attraction search(String contextId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attraction> searchAll(SearchOption option) {
		try {
			return dao.searchAll(option);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
