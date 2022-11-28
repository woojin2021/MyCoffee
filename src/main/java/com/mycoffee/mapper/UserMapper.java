package com.mycoffee.mapper;

import java.util.List;

import com.mycoffee.domain.UserDTO;
import com.mycoffee.domain.UserVO;

public interface UserMapper {
	public List<UserVO> selectUserList();
//	public UserVO LoginUser(@Param("userid")String userid, @Param("password")String password);
	public UserVO selectUser(String userid);
	public void insertUser(UserDTO user);
	public int deleteUser(String id);
	public int updateUser(UserDTO user);
//	public int checkid(@Param("userid")String userid);
//	public int checkuser(@Param("userid")String userid, @Param("password")String password);
}
