package com.iu.notice;

import java.util.List;

import com.iu.Board.BoardDTO;
import com.iu.Board.BoardService;

public class NoticeService implements BoardService {
	private NoticeDAO noticeDAO;
	
	public NoticeService() {
		// TODO Auto-generated constructor stub
		noticeDAO = new NoticeDAO();
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
		//page maker code
		
		return noticeDAO.selectList();
	}

}
