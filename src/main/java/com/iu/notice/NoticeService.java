package com.iu.notice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.iu.Board.BoardDTO;
import com.iu.Board.BoardService;
import com.iu.util.ListData;
import com.iu.util.Pager;
import com.iu.util.RowNum;

@Service
public class NoticeService implements BoardService {
	
	@Inject
	private NoticeDAO noticeDAO;	
	
	
	//setter
	/*public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}*/
	
	//생성자
	/*public NoticeService() {
		// TODO Auto-generated constructor stub
		noticeDAO = new NoticeDAO();
	}*/
	
	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.insert(boardDTO);
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
		noticeDAO.hit(num);
		return noticeDAO.selectOne(num);
	}

	@Override
	public List<BoardDTO> selectList(ListData listData,Model model) throws Exception {
		//page maker code
		RowNum rowNum = listData.makeRow();
		int totalCount = noticeDAO.getTotalCount(rowNum);
		Pager pager = listData.makePage(totalCount);
	  /*ArrayList<Object> ar = new ArrayList<Object>();
		ar.add(pager);
		ar.add(noticeDAO.selectList(rowNum));
		return ar;*/
		model.addAttribute("pager",pager);
		model.addAttribute("list",noticeDAO.selectList(rowNum));
		return noticeDAO.selectList(rowNum);
	}

}
