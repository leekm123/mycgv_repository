package com.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.dao.CgvBoardDAO;
import com.web.vo.CgvBoardVO;

@Controller
public class BoardController {
	/**
	 * 게시글 삭제 처리
	 * @return
	 */
	@RequestMapping(value="/board_delete.do", method=RequestMethod.POST)
	public ModelAndView board_delete(CgvBoardVO vo, HttpServletRequest request)
													throws Exception{
		ModelAndView mv = new ModelAndView();
		CgvBoardDAO dao = new CgvBoardDAO();
		String bsfile = dao.selectBsfile(vo.getBid());
		int result = dao.delete(vo.getBid());
	
		if(result == 1) {
			if(bsfile != null) {
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "resources\\upload\\";
				File file = new File(path + bsfile);
				if(file.exists()) file.delete();
			}
			mv.setViewName("redirect:/board_list.do");			
		}else {
			//에러페이지 호출
		}		
		
		return mv;
	}
	
	
	/**
	 * 게시글 삭제 화면
	 * @return
	 */
	@RequestMapping(value="/board_delete.do", method=RequestMethod.GET)
	public ModelAndView board_delete(String bid, String rno) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("bid", bid);
		mv.addObject("rno", rno);
		mv.setViewName("/board/board_delete");
		
		return mv;
	}
	
	
	/**
	 * 게시글 업데이트 처리
	 * @return
	 */
	@RequestMapping(value="/board_update.do", method=RequestMethod.POST)
	public ModelAndView board_update(CgvBoardVO vo, HttpServletRequest request) 
														throws Exception{
		ModelAndView mv = new ModelAndView();
		CgvBoardDAO dao = new CgvBoardDAO();
		String bfile, bsfile = "";
		String oldFile = vo.getBsfile();
		
		//1. vo.getFile1().getOriginalFilename() 값이 널인지 체크
		// 널인 경우 --> 기존의 파일 유지, 널이 아닌 경우 --> 기존파일을 새로운 파일로 업데이트
		if(!vo.getFile1().getOriginalFilename().equals("")) {
			UUID uuid = UUID.randomUUID();
			bfile = vo.getFile1().getOriginalFilename();
			bsfile = uuid + "_" + bfile;
			
			vo.setBfile(bfile);
			vo.setBsfile(bsfile);
		}
		
		int result = dao.update(vo);
		
		if(result == 1) {
			if(!vo.getFile1().getOriginalFilename().equals("")) {
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "resources\\upload\\";
				
				File file = new File(path+bsfile);
				vo.getFile1().transferTo(file);
				
				//기존 파일이 존재하는 경우 삭제처리
				File ofile = new File(path+oldFile);
				if(ofile.exists()) {
					ofile.delete();
				}
			}
			mv.setViewName("redirect:/board_list.do");
		}else {
			//에러페이지 호출
		}
				
		return mv;
	}
	
	
	
	
	/**
	 * 게시글 업데이트 폼
	 * @return
	 */
	@RequestMapping(value="/board_update.do", method=RequestMethod.GET)
	public ModelAndView board_update(String bid, String rno) {
		ModelAndView mv = new ModelAndView();
		CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = dao.select(bid);
		
		mv.addObject("vo", vo);
		mv.addObject("rno",rno);
		mv.setViewName("/board/board_update");
		
		
		return mv;
	}
	
	/**
	 * 게시글 상세보기
	 * @return
	 */
	@RequestMapping(value="/board_content.do", method=RequestMethod.GET)
	public ModelAndView board_content(String bid, String rno) {
		ModelAndView mv = new ModelAndView();
		CgvBoardDAO dao = new CgvBoardDAO();
		dao.updateHits(bid);
		CgvBoardVO vo = dao.select(bid);
		
		mv.addObject("vo", vo);
		mv.addObject("rno", rno);
		mv.setViewName("/board/board_content");
		
		return mv;
	}
	
	/**
	 * 게시글 등록 처리
	 * @return
	 */
	@RequestMapping(value="/board_write.do", method=RequestMethod.POST)
	public String board_write(CgvBoardVO vo, HttpServletRequest request) 
													throws Exception{
		String result_page = "";		
		String bfile, bsfile = "";
		CgvBoardDAO dao = new CgvBoardDAO();		
		
		//파일업로드 데이터 생성 --> bfile, bsfile
		if(!vo.getFile1().getOriginalFilename().equals("")) { //파일존재 하는 경우
			UUID uuid = UUID.randomUUID();
			bfile = vo.getFile1().getOriginalFilename();
			bsfile = uuid + "_" + vo.getFile1().getOriginalFilename() ;
			
			vo.setBfile(bfile);
			vo.setBsfile(bsfile);			
		}	
		
		int result = dao.insert(vo);
		
		if(result == 1) {
			if(!vo.getFile1().getOriginalFilename().equals("")) {
				//파일저장 위치 확인
				String root_path = request.getSession().getServletContext().getRealPath("/");
				root_path += "resources\\upload\\";
				System.out.println(root_path);
				
				//파일저장
				File file = new File(root_path + bsfile);			
				vo.getFile1().transferTo(file);
			}					
			result_page = "redirect:/board_list.do";
			
		}else {
			//에러페이지 호출
		}		
		
		return result_page;
	}
	
	
	
	/**
	 * 게시글 등록폼
	 * @return
	 */
	@RequestMapping(value="/board_write.do", method=RequestMethod.GET)
	public String board_write() {
		return "/board/board_write";
	}

	/**
	 * 게시글 전체 리스트
	 * @return
	 */
	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
	public ModelAndView board_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		CgvBoardDAO dao = new CgvBoardDAO();		
		
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
		
		//ArrayList<CgvBoardVO> list = dao.select();  //전체 리스트를 모두 출력
		ArrayList<CgvBoardVO> list = dao.select(startCount, endCount);
		
		mv.addObject("list", list);
		mv.addObject("dbCount", dbCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("reqPage", reqPage);		
		mv.setViewName("/board/board_list");
		
		return mv;
	}
	
}//controller

















