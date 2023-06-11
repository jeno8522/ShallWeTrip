package com.ssafy.trip.user.model.service;

import java.sql.SQLException;

import com.ssafy.trip.user.model.dto.User;

public interface UserService {
//	User login(String id, String pass) ;
	User search(String id, String pass) ;
	void regist(User user) ;
	void update(User user) ;
	void remove(String id) ;
	int idCheck(String userId);
	boolean check(String userid, String pass);
	User find(String id, String name, String email) ;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleteRefreshToken(String userid) throws Exception;
	User userInfo(String userid);
}
