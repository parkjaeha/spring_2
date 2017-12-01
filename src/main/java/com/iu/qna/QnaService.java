package com.iu.qna;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.iu.Board.BoardDTO;
import com.iu.Board.BoardService;
import com.iu.util.ListData;
import com.iu.util.Pager;
import com.iu.util.RowNum;
@Service
public class QnaService implements BoardService {

	@Inject
	QnaDAO qnaDAO = new QnaDAO();
	
	/*public void setQnaDAO(QnaDAO qnaDAO) {
		this.qnaDAO = qnaDAO;
	}*/

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.insert(boardDTO);
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
	public BoardDTO selectOne(int num) throws Exception {
		// TODO Auto-generated method stub
		qnaDAO.hit(num);
		return qnaDAO.selectOne(num);
	}

	@Override
	public List<BoardDTO> selectList(ListData listData, Model model) throws Exception {
		// TODO Auto-generated method stub
		RowNum rowNum = listData.makeRow();
		int totalCount = qnaDAO.getTotalCount(rowNum);
		Pager pager = listData.makePage(totalCount);
		
		model.addAttribute("pager",pager);
		model.addAttribute("list",qnaDAO.selectList(rowNum));
		
		return qnaDAO.selectList(rowNum);
	}

	
}
