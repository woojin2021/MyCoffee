package com.mycoffee.service;

import org.springframework.stereotype.Service;

import com.mycoffee.domain.UserVO;
import com.mycoffee.mapper.UserMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	private UserMapper umapper;
	@Override
	public void register(UserVO user) {
		log.info("register : "+ user);
		umapper.insertUser(user);
	}

	@Override
	public UserVO LoginUser(String id,String passwd) {
		UserVO user = new UserVO();
		log.info("userid : "+ id);
		user=umapper.LoginUser(id,passwd);
		return user;
	}

	@Override
	public boolean modify(UserVO user) {
		log.info("modify.........."+user);
	
		return umapper.update(user)==1;
	}

	@Override
	public boolean remove(String id) {
		log.info(" remove.........."+id);
		
		return umapper.delete(id)==1;
	}

	@Override
	public void sele() {
		umapper.getList();
	}

	@Override
	public void insertUser(UserVO user) {
		umapper.insertUser(user);
	}

	@Override
	public int CheckId(String userid) {
		int count = umapper.checkid(userid);
		return count;
	}

	@Override
	public int CheckUser(String userid, String password) {
		int count = umapper.checkuser(userid,password);
		return count;
	}

}
