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
	
	// 게시글 수정
	public Board updateBoard(BoardDto dto) {
		
		BoardDto temp = selectBoardOne(dto.getBoard_no());
		temp.setBoard_title(dto.getBoard_title());
		temp.setBoard_content(dto.getBoard_content());
		if(dto.getOri_thumbnail() != null
			&& "".equals(dto.getOri_thumbnail()) == false){
			temp.setOri_thumbnail(dto.getOri_thumbnail());
			temp.setNew_thumbnail(dto.getNew_thumbnail());
		}
		
		Board board = temp.toEntity();
		Board result = boardRepository.save(board);
		return result;
	}
	
	
	// 게시글 상세
	public BoardDto selectBoardOne(Long board_no) {
		Board board =boardRepository.findByboardNo(board_no);
		BoardDto dto = new BoardDto().toDto(board);
		return dto;
	}
	
	
	// 게시글 등록
	public Board createBoard(BoardDto dto) {
		Board board = dto.toEntity();
		return boardRepository.save(board);
		
	}
	

	
	public Page<BoardDto> selectBoardList(BoardDto searchDto,Pageable pageable){
		Page<Board> boardList = null;
		
//		String boardTitle = searchDto.getBoard_title();
//		if(boardTitle != null && !"".equals(boardTitle)) {
//			boardList = boardRepository.findByboardTitleContaining(boardTitle, pageable);
//		} else {
//			boardList = boardRepository.findAll(pageable);
//		}
		// 제목으로 검색, 내용으로 검색, 제목+내용으로 검색
		String searchText = searchDto.getSearch_text();
		if(searchText != null && "".equals(searchText) == false) {
			int searchType = searchDto.getSearch_type();
			switch(searchType) {
			case 1 :
				boardList = boardRepository.findByboardTitleContaining(searchText, pageable);
				break;
			case 2 :
				boardList = boardRepository.findByboardContentContaining(searchText, pageable);
				break;
			case 3 :
				boardList = boardRepository.findByboardTitleOrboardContentContaining(searchText,pageable);
				break;
			}
			
		} else {
			boardList = boardRepository.findAll(pageable);
		}
		
		
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
