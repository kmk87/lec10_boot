package com.gn.spring.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gn.spring.board.domain.BoardDto;
import com.gn.spring.board.service.BoardService;

@Controller
public class BoardViewController {
	
	private final BoardService boardService;
	private static final Logger LOGGER 
		= LoggerFactory.getLogger(BoardViewController.class);
	
	@Autowired
	public BoardViewController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/board")
	public String selectBoardList() {
		List<BoardDto> resultList = boardService.selectBoardList();
		LOGGER.debug(resultList.toString());
		return "board/list";
	}
	
	
}
