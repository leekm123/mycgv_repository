package com.web.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.dao.CgvNoticeDAO;
import com.web.vo.CgvNoticeVO;

@Controller
public class NoticeController {

	@RequestMapping(value="/notice_content.do", method=RequestMethod.GET)
	public ModelAndView notice_content(String nid, String rno) {
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();
		dao.updateHits(nid);
		CgvNoticeVO vo = dao.select(nid);
		
		mv.addObject("vo",vo);
		mv.addObject("rno",rno);
		mv.setViewName("/notice/notice_content");
		
		return mv;
	}
	
	@RequestMapping(value="/notice_list.do", method=RequestMethod.GET)
	public ModelAndView notice_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();
				
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 3;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = dao.execTotalCount();	//DB에서 가져온 전체 행수
		
		//총 페이지 수 계산
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//요청 페이지 계산
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}
				
		ArrayList<CgvNoticeVO> list = dao.select(startCount, endCount); //start,end
		
		mv.addObject("list", list);
		mv.addObject("dbCount", dbCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("reqPage", reqPage);	
		
		mv.setViewName("/notice/notice_list");
		
		return mv;
	}
}














