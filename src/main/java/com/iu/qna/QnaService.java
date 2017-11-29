package com.iu.qna;

import java.util.List;

import com.iu.Board.BoardDTO;
import com.iu.Board.BoardService;

public class QnaService implements BoardService {

	QnaDAO qnaDAO = new QnaDAO();
	
	public void setQnaDAO(QnaDAO qnaDAO) {
		this.qnaDAO = qnaDAO;
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardDTO selectOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardDTO> selectList() throws Exception {
		// TODO Auto-generated method stub
		
		return qnaDAO.selectList();
	}

	
}
