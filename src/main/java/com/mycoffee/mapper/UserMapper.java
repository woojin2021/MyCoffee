package com.mycoffee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mycoffee.domain.UserVO;

public interface UserMapper {
	public List<UserVO> getList();
	public UserVO LoginUser(@Param("userid")String userid, @Param("password")String password);
	public void insertUser(UserVO user);
	public int delete(String id);
	public int update(UserVO user);
	public int checkid(@Param("userid")String userid);
	public int checkuser(@Param("userid")String userid, @Param("password")String password);
}
