package com.mycoffee.service;

import java.util.List;

import com.mycoffee.domain.BoardVO;

public interface BoardService {

	public List<BoardVO> getList();
	
	public List<BoardVO> getList(String did);
	
	
}
