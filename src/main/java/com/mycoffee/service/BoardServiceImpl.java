package com.mycoffee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycoffee.domain.BoardVO;
import com.mycoffee.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getList() {
		log.info("getList..............");
		return mapper.getList();
	}

	@Override
	public List<BoardVO> getList(String did) {
		log.info("getList..............");
		return mapper.getList();
	}


}
