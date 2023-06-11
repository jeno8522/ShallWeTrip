package com.ssafy.trip.attraction.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.trip.attraction.model.dto.Attraction;
import com.ssafy.trip.attraction.model.dto.SearchOption;
import com.ssafy.trip.attraction.model.service.AttractionService;
import com.ssafy.trip.attraction.model.service.AttractionServiceImp;
import com.ssafy.trip.user.controller.UserController;
import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;

@Controller
@RequestMapping("/attraction")
public class AttractionController extends HttpServlet {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AttractionService service;
	
	@ExceptionHandler(Exception.class)
	public String handler(Exception ex, Model model) {
		model.addAttribute("msg", ex.getMessage());
		return "/error/error";
	}
	
	@GetMapping("/tripInfo")
	public void tripInfo() {
		logger.debug("attraction............................ 화면 이동");
	}
	
	@PostMapping("/tripInfo")
	public String tripInfo(SearchOption option, Model model) {
		logger.debug("attraction............................ search");
		List<Attraction> attractions = service.searchAll(option);
		System.out.println("_____________________여기부터다------------------");
		System.out.println(attractions);
		//sort 알고리즘 구현
		//Collections.sort(attractions);
		
		model.addAttribute("attractions",attractions);
		
		return "attraction/tripInfo";
	}

}











