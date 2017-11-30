package com.iu.s2;

import static org.hamcrest.CoreMatchers.not;

import java.lang.ProcessBuilder.Redirect;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.Board.BoardDTO;
import com.iu.notice.NoticeDTO;
import com.iu.notice.NoticeService;
import com.iu.util.ListData;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Inject
	private NoticeService noticeService = null;
	
	@RequestMapping(value="noticeList", method=RequestMethod.GET)
	public String selectList(Model model, ListData listData){
		
		List<BoardDTO> ar=null;
		try {
			ar = noticeService.selectList(listData,model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("board","notice");
		
		//return "board/boardList";
		return "board/boardList";
	}
	
	@RequestMapping(value="noticeView", method=RequestMethod.GET)
	public String selectOne(Model model,@RequestParam(defaultValue="1",required=false)int num){
		BoardDTO boardDTO = null;
		try {
			boardDTO = noticeService.selectOne(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("view","notice").addAttribute("dto",boardDTO);
		return "board/boardView";
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public String insert(Model model){
		
		model.addAttribute("board","notice");
		return "board/boardWrite";
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public String insert(RedirectAttributes rd,String title,String writer,String contents){
		
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setWriter(writer);
			boardDTO.setTitle(title);
			boardDTO.setContents(contents);
			int result =0;
			try {
			result=	noticeService.insert(boardDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String message ="fail";
			
			if(result>0){
				message ="Success";
			}
		
			rd.addFlashAttribute("message", message);
		return "redirect:./noticeList";
	}
	

	
}
