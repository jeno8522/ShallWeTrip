package com.ssafy.trip.attraction.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.attraction.model.dto.Attraction;
import com.ssafy.trip.attraction.model.dto.SearchOption;
import com.ssafy.trip.attraction.model.service.AttractionService;
import com.ssafy.trip.attraction.model.service.AttractionServiceImp;
import com.ssafy.trip.user.controller.UserController;
import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController	
@CrossOrigin(origins= {"*"}, maxAge=6000)
@RequestMapping("/attraction")
@Api(value="Attraction Rest-ful위한 API",description = "trip 관광지 정보")
public class AttractionRestController extends HttpServlet {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	
	@Autowired
	private AttractionService service;
	
	@ApiOperation(value="관광지 조회", notes = "검색 조건에 맞는 관광지 정보를 검색한 목록 정보")
//	@GetMapping("/attraction/{sido}/contentId")
	@PostMapping
	public ResponseEntity <List<Attraction>> tripInfo(@RequestBody SearchOption option) throws Exception{
		logger.debug("attraction............................ search");
//		option.setSido("4");
//		option.setGugun("2");
		System.out.println(option.toString());
		List<Attraction> attractions = service.searchAll(option);
//		System.out.println("attractions 출력한다 나"+attractions);
		
		return new ResponseEntity<>(attractions,HttpStatus.OK);
	}

}











