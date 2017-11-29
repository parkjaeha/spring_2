package com.iu.s2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iu.Board.BoardDTO;
import com.iu.notice.NoticeService;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private NoticeService noticeService = null;
	public NoticeController() {
		noticeService = new NoticeService();
	}
	
/*	@RequestMapping(value="./noticeList", method=RequestMethod.GET)
	public ModelAndView selectList(ModelAndView mv){
		
		List<BoardDTO> ar=null;
		try {
			ar = noticeService.selectList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject("list", ar);
		mv.setViewName("notice/noticeList");
		//return "board/boardList";
		return mv;
	}
	*/
	
	@RequestMapping(value="noticeList", method=RequestMethod.GET)
	public String selectList(Model model){
		
		List<BoardDTO> ar=null;
		try {
			ar = noticeService.selectList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("list", ar).addAttribute("board","notice");
		
		//return "board/boardList";
		return "board/boardList";
	}
	
	

	
}
