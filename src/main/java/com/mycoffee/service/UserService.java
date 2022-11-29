package com.mycoffee.service;

import com.mycoffee.domain.UserDTO;
import com.mycoffee.domain.UserVO;
public interface UserService 
{
//	public void sele();
//	public void register(UserVO user);
	public UserVO getUser(String userid);
	public boolean modifyUser(UserDTO user);
	public boolean removeUser(String id);
	public void insertUser(UserDTO user);
//	public int CheckId(String userid);
//	public int CheckUser(String userid, String password);
	
	/**
	 * 로그인 체크
	 * @param userid
	 * @param password
	 * @return 0:OK, 1:미등록 아이디, 2:비밀번호 불일치
	 */
	public int loginCheck(String userid, String password);
}
