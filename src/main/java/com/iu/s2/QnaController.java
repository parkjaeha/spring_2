package com.iu.s2;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iu.Board.BoardDTO;
import com.iu.notice.NoticeService;
import com.iu.qna.QnaDAO;
import com.iu.qna.QnaService;
import com.iu.util.ListData;

@Controller
@RequestMapping(value="/qna/*")
public class QnaController {

private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	@Inject
	private QnaService qnaService = null;
	
	@RequestMapping(value="qnaList")
	public String qnaList(Model model, ListData listData){
		
		List<BoardDTO> ar = null;
		
		try {
			ar = qnaService.selectList(listData,model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("board","qna");
		
		return "board/boardList";
	}
	
	@RequestMapping(value="qnaView", method=RequestMethod.GET)
	public String qnaView(Model model,@RequestParam(defaultValue="1", required=false)int num){
		
		BoardDTO boardDTO = null;
		try {
			boardDTO = qnaService.selectOne(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("view","qna").addAttribute("dto",boardDTO);
		return "board/boardView";
	}
	
	
	@RequestMapping(value="qnaWrite", method=RequestMethod.GET)
	public String qnaWrite(Model model){
		model.addAttribute("board","qna");
		return "board/boardWrite";
	}
	
	@RequestMapping(value="qnaWrite", method=RequestMethod.POST)
	public String qnaWrite(Model model,String title,String writer,String contents){
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setTitle(title);
		boardDTO.setWriter(writer);
		boardDTO.setContents(contents);
	
		try {
			qnaService.insert(boardDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("board","qna");
		return "redirect:qnaList";
		
	}
	
	
}
