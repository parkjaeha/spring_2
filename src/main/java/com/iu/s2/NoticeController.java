package com.iu.s2;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.Board.BoardDTO;
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
		System.out.println("notice GET");
		model.addAttribute("board","notice");
		return "board/boardWrite";
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public String insert(RedirectAttributes rd, String title,String writer,String contents, @RequestParam(value="f1") MultipartFile [] files, HttpSession session){
		
		System.out.println("notice POST");
		
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

			String filepath = session.getServletContext().getRealPath("upload");
			for(MultipartFile file: files){
				System.out.println("file: " +file.getOriginalFilename());
				System.out.println("file name: " +file.getName());
			
				System.out.println("filepath : " + filepath);
				
				File f= new File(filepath);
				
				if(!f.exists()){
					f.mkdirs();
				}
				String fileName = file.getOriginalFilename();
				String name = UUID.randomUUID().toString();
				fileName = fileName.substring(fileName.length()-4,fileName.length());
				fileName = name+fileName;
				System.out.println("longname: " +fileName);
				
				//2times menu
				f= new File(filepath,fileName);
			
				try {
					file.transferTo(f);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			
			
			
			
			rd.addFlashAttribute("message", message);
		return "redirect:./noticeList";
	}
	

	
}
