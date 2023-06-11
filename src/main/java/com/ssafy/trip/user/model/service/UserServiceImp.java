package com.ssafy.trip.user.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.ssafy.trip.user.model.dao.UserDao;
import com.ssafy.trip.user.model.dto.User;
import com.ssafy.trip.user.model.dto.UserException;


@Service
public class UserServiceImp implements UserService {
	private UserDao dao;

	private UserServiceImp(UserDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public User find(String id, String name, String email) {
		try {
			User user = dao.search(id);
			if (user == null)
				throw new UserException("등록되지 않은 아이디입니다.");
			if (!name.equals(user.getName()) || !email.equals(user.getEmail())) {
				throw new UserException("회원 정보를 잘못 입력했습니다.");
			}
			return user;
		} catch (Exception e) {
			throw new UserException("비밀번호 찾기 처리 중 오류 발생");
		}
	}

	@Override
	public User search(String id, String pass) {
		try {
			User user = dao.search(id);
			System.out.println(user);

			if (user == null)
				throw new UserException("등록되지 않은 아이디입니다.");

			if (!BCrypt.checkpw(pass, user.getUserpwd())) {
				throw new UserException(" 비밀번호를 잘못 입력했습니다.");
			}
			return user;
		} catch (SQLException e) {
			throw new UserException("로그인 처리 중 오류 발생");
		}
	}

//	@Override
//	public User search(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void regist(User user) {
	    try {
	        User find = dao.search(user.getUserid());
	        if (find != null) {
	            throw new UserException("이미 등록된 아이디 입니다.");
	        }
	        String pass = user.getUserpwd();
	        String hashpass = BCrypt.hashpw(pass, BCrypt.gensalt());
	        user.setUserpwd(hashpass);
	        dao.regist(user);
	    } catch (SQLException e) {
	        throw new UserException("회원 정보 처리 중 오류 발생");
	    }
	}

	@Override
	public void update(User user) {
		try {
			if (user.getUserpwd().equals("")) {
				dao.updateWithoutPassword(user);
			} else {
		        String pass = user.getUserpwd();
		        String hashpass = BCrypt.hashpw(pass, BCrypt.gensalt());
		        user.setUserpwd(hashpass);
				dao.update(user);
			}

		} catch (Exception e) {
			throw new UserException("회원 정보 수정 중 오류 발생");
		}

	}

	@Override
	public void remove(String id) {
		try {
			dao.remove(id);
		} catch (Exception e) {
			throw new UserException("회원 정보 제거 중 오류 발생");
		}
	}

	@Override
	public int idCheck(String userId) {
		try {
			return dao.idCheck(userId);
		} catch (Exception e) {
			throw new UserException("회원 정보 제거 중 오류 발생");
		}

	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		dao.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return dao.getRefreshToken(userid);
	}

	@Override
	public void deleteRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		dao.deleteRefreshToken(map);
	}

	@Override
	public User userInfo(String userid) {
		try {
			User user = dao.search(userid);
			System.out.println(user);

			if (user == null)
				throw new UserException("등록되지 않은 아이디입니다.");

			return user;
		} catch (SQLException e) {
			throw new UserException("로그인 처리 중 오류 발생");
		}
	}

	@Override
	public boolean check(String userid, String pass) {
		try {
			User user = dao.search(userid);
	        if(BCrypt.checkpw(pass,user.getUserpwd())) return true;
	        else return false;
			
		} catch (Exception e) {
			throw new UserException("비밀번호 확인 중 오류 발생");
		}
		
	}


}
