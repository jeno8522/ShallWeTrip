package com.ssafy.trip.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.infoBoard.model.dto.InfoBoard;
import com.ssafy.trip.infoBoard.model.dto.PageBean;
import com.ssafy.trip.user.model.dto.User;
import com.ssafy.trip.user.model.service.JwtServiceImpl;
import com.ssafy.trip.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api("사용자 컨트롤러  API V1")
@CrossOrigin("/*")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserService userService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = userService.search(user.getUserid(), user.getUserpwd());
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getUserid());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserid());// key, data
				userService.saveRefreshToken(user.getUserid(), refreshToken);
//				logger.debug("로그인 accessToken 정보 : {}", accessToken);
//				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
//			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

//	 @ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
//	    @PostMapping("/login")
//	    public ResponseEntity<Map<String, Object>> login(
//	            @RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User user) {
//	        logger.debug("login 호출!!");
//	        logger.debug("userid.................{}", user.getUserid());
//	        logger.debug(user.toString());
//	        Map<String, Object> resultMap = new HashMap<>();
//	        HttpStatus status = null;
//	        try {
//	            User loginUser = userService.search(user.getUserid(), user.getUserpwd());
//	            if (loginUser != null) {
//	                String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
//	                logger.debug("로그인 토큰정보 : {}", token);
//	                resultMap.put("access-token", token);
//	                resultMap.put("message", SUCCESS);
//	                status = HttpStatus.ACCEPTED;
//	            } else {
//	                resultMap.put("message", FAIL);
//	                status = HttpStatus.ACCEPTED;
//	            }
//	        } catch (Exception e) {
//	            logger.error("로그인 실패 : {}", e);
//	            resultMap.put("message", e.getMessage());
//	            status = HttpStatus.INTERNAL_SERVER_ERROR;
//	        }
//	        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	    }

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
//			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User user = userService.userInfo(userid);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
//				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
//			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleteRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
//			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "사원 정보 등록", notes = "사원 정보를 등록")
	@PostMapping("/regist") // PathVariable로 GetMapping을 여러 개를 구별하므로 반드시 전달돼야 한다.
	public ResponseEntity<String> regist(@RequestBody @ApiParam(value = "회원가입 시 필요한 회원정보", required = true) User user) {
//		logger.debug("user regist 호출!!!!!!!!!!!");
//		logger.debug("user.regist...........user:{}", user);
		try {
			userService.regist(user);			
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);			// TODO: handle exception
		}

	}

	@ApiOperation(value = "비밀번호 확인", notes = "비밀번호 확인")
	@GetMapping("/check/{userid}/{password}") // PathVariable로 GetMapping을 여러 개를 구별하므로 반드시 전달돼야 한다.
	public ResponseEntity<String> check(
			@PathVariable @ApiParam(value = "회원가입 시 필요한 회원정보", required = true) String userid,
			@PathVariable String password) {
//		logger.debug("user check password 호출!!!!!!!!!!!");
		if (userService.check(userid, password)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}

	}

	@ApiOperation(value = "회원 정보 수정", notes = "수정할 회원 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/update")
	public ResponseEntity<String> modify(@ApiParam(value = "수정할 글정보.", required = true) @RequestBody User user)
			throws Exception {
//		logger.debug("modify:user................... update user:{}", user);
		userService.update(user);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody User user, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
//		logger.debug("token : {}, user : {}", token, user);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(user.getUserid()))) {
				String accessToken = jwtService.createAccessToken("userid", user.getUserid());
//				logger.debug("token : {}", accessToken);
//				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
//			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@DeleteMapping("/{userid}")
	public ResponseEntity<String> delete(@PathVariable("userid") String userid) throws Exception {
//		logger.debug("deleteArticle - 호출");
//		logger.debug("params로 넘어온 infoId......{}", userid);
		userService.remove(userid);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

}
