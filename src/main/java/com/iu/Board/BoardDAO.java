package com.iu.Board;

import java.util.List;

public interface BoardDAO {

	public int insert(BoardDTO boardDTO) throws Exception;
	public int update(BoardDTO boardDTO) throws Exception;
	public int delete(BoardDTO boardDTO) throws Exception;
	public List<BoardDTO> selectList() throws Exception;
	public BoardDTO selectOne() throws Exception;
	public int hit(int num) throws Exception;
	
	
}
