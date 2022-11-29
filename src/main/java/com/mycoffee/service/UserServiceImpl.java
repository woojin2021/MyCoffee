package com.mycoffee.service;

import org.springframework.stereotype.Service;

import com.mycoffee.domain.UserDTO;
import com.mycoffee.domain.UserVO;
import com.mycoffee.mapper.UserMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private UserMapper umapper;
//	@Override
//	public void register(UserVO user) {
//		log.info("register : "+ user);
//		umapper.insertUser(user);
//	}

	@Override
	public UserVO getUser(String id) {
		UserVO user = new UserVO();
		log.info("userid : " + id);
		user = umapper.selectUser(id);
		return user;
	}

	@Override
	public boolean modifyUser(UserDTO user) {
		log.info("modify.........." + user);

		return umapper.updateUser(user) == 1;
	}

	@Override
	public boolean removeUser(String id) {
		log.info(" remove.........." + id);

		return umapper.deleteUser(id) == 1;
	}

//	@Override
//	public void sele() {
//		umapper.getList();
//	}

	@Override
	public void insertUser(UserDTO user) {
		umapper.insertUser(user);
	}

//	@Override
//	public int CheckId(String userid) {
//		int count = umapper.checkid(userid);
//		return count;
//	}

//	@Override
//	public int CheckUser(String userid, String password) {
//		int count = umapper.checkuser(userid, password);
//		return count;
//	}

	@Override
	public int loginCheck(String userid, String password) {
		UserVO user = getUser(userid);
		log.info(user);
		if (user == null) {
			// 아이디마저 없을때
			return 1;
		} else if (user.getPassword().equals(password) == false) {
			// 아이디가 있을때 비밀번호가 없을때
			return 2;
		} else {
			return 0;
		}
	}

}
