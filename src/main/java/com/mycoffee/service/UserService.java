package com.mycoffee.service;

import org.apache.ibatis.annotations.Param;

import com.mycoffee.domain.UserVO;
public interface UserService 
{
	public void sele();
	public void register(UserVO user);
	public UserVO LoginUser(@Param("userid")String userid, @Param("password")String password);
	public boolean modify(UserVO user);
	public boolean remove(String id);
	public void insertUser(UserVO user);
	public int CheckId(@Param("userid")String userid);
	public int CheckUser(@Param("userid")String userid, @Param("password")String password);
}
