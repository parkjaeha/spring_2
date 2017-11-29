package com.iu.s2;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iu.Board.BoardDTO;
import com.iu.notice.NoticeService;
import com.iu.qna.QnaService;

@Controller
@RequestMapping(value="/qna/*")
public class QnaController {

private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	@Inject
	private QnaService qnaService = null;
	
	@RequestMapping(value="qnaList")
	public String qnaList(Model model){
		
		List<BoardDTO> ar = null;
		
		try {
			ar = qnaService.selectList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("list", ar).addAttribute("board","qna");
		
		return "board/boardList";
	}
	
	
}
