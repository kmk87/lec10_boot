package com.gn.spring.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gn.spring.board.domain.Board;
import com.gn.spring.board.domain.BoardDto;
import com.gn.spring.board.repository.BoardRepository;

@Service
public class BoardService {
	
	// 의존성 주입
	private final BoardRepository boardRepository;
	
	// 생성자 주입
	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public Page<BoardDto> selectBoardList(Pageable pageable){
		
		Page<Board> boardList = boardRepository.findAll(pageable);
		
		List<BoardDto> boardDtoList = new ArrayList<BoardDto>();
		for(Board b : boardList) {
			BoardDto dto = new BoardDto().toDto(b);
			boardDtoList.add(dto);
		}
		
		
		return new PageImpl<>(boardDtoList,pageable,boardList.getTotalElements());
		
		
		
		
//		List<Board> boardList = boardRepository.findAll();
//		
//		List<BoardDto> boardDtoList = new ArrayList<BoardDto>();
//		// Board(Entity)를 BoardDto로 바꿔주기
//		for(Board board : boardList) {
//			BoardDto boardDto = new BoardDto().toDto(board);
//			boardDtoList.add(boardDto);
//		}
//		
//		return boardDtoList;
		
		
		
		
	}
}
