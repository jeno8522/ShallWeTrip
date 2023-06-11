package com.ssafy.trip.user.model.dao;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.trip.user.model.dto.User;


public interface UserDao {
	/**
	 * 입력된 id/pass 기반으로 User를 조회해서 반환한다.
	 * @param id
	 * @param pass
	 * @return
	 * @throws SQLException
	 */
	User search(String id) throws SQLException;
	void regist(User user) throws SQLException;
	void update(User user) throws SQLException;
	void remove(String id) throws SQLException;
//	User find(String id) throws SQLException;
	int idCheck(String userId) throws SQLException;
	void updateWithoutPassword(User user) throws SQLException;
	void saveRefreshToken(Map<String, String> map);
	Object getRefreshToken(String userid);
	void deleteRefreshToken(Map<String, String> map);
}
