package com.iu.Board;

import java.util.List;

import com.iu.util.ListData;
import com.iu.util.RowNum;

public interface BoardDAO {
	
	public int getTotalCount(RowNum rowNum) throws Exception;
	public int insert(BoardDTO boardDTO) throws Exception;
	public int update(BoardDTO boardDTO) throws Exception;
	public int delete(BoardDTO boardDTO) throws Exception;
	public List<BoardDTO> selectList(RowNum rowNum) throws Exception;
	public BoardDTO selectOne(int num) throws Exception;
	public int hit(int num) throws Exception;
	
	
}
