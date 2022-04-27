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
	 * �Խñ� ���� ó��
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
			//���������� ȣ��
		}		
		
		return mv;
	}
	
	
	/**
	 * �Խñ� ���� ȭ��
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
	 * �Խñ� ������Ʈ ó��
	 * @return
	 */
	@RequestMapping(value="/board_update.do", method=RequestMethod.POST)
	public ModelAndView board_update(CgvBoardVO vo, HttpServletRequest request) 
														throws Exception{
		ModelAndView mv = new ModelAndView();
		CgvBoardDAO dao = new CgvBoardDAO();
		String bfile, bsfile = "";
		String oldFile = vo.getBsfile();
		
		//1. vo.getFile1().getOriginalFilename() ���� ������ üũ
		// ���� ��� --> ������ ���� ����, ���� �ƴ� ��� --> ���������� ���ο� ���Ϸ� ������Ʈ
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
				
				//���� ������ �����ϴ� ��� ����ó��
				File ofile = new File(path+oldFile);
				if(ofile.exists()) {
					ofile.delete();
				}
			}
			mv.setViewName("redirect:/board_list.do");
		}else {
			//���������� ȣ��
		}
				
		return mv;
	}
	
	
	
	
	/**
	 * �Խñ� ������Ʈ ��
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
	 * �Խñ� �󼼺���
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
	 * �Խñ� ��� ó��
	 * @return
	 */
	@RequestMapping(value="/board_write.do", method=RequestMethod.POST)
	public String board_write(CgvBoardVO vo, HttpServletRequest request) 
													throws Exception{
		String result_page = "";		
		String bfile, bsfile = "";
		CgvBoardDAO dao = new CgvBoardDAO();		
		
		//���Ͼ��ε� ������ ���� --> bfile, bsfile
		if(!vo.getFile1().getOriginalFilename().equals("")) { //�������� �ϴ� ���
			UUID uuid = UUID.randomUUID();
			bfile = vo.getFile1().getOriginalFilename();
			bsfile = uuid + "_" + vo.getFile1().getOriginalFilename() ;
			
			vo.setBfile(bfile);
			vo.setBsfile(bsfile);			
		}	
		
		int result = dao.insert(vo);
		
		if(result == 1) {
			if(!vo.getFile1().getOriginalFilename().equals("")) {
				//�������� ��ġ Ȯ��
				String root_path = request.getSession().getServletContext().getRealPath("/");
				root_path += "resources\\upload\\";
				System.out.println(root_path);
				
				//��������
				File file = new File(root_path + bsfile);			
				vo.getFile1().transferTo(file);
			}					
			result_page = "redirect:/board_list.do";
			
		}else {
			//���������� ȣ��
		}		
		
		return result_page;
	}
	
	
	
	/**
	 * �Խñ� �����
	 * @return
	 */
	@RequestMapping(value="/board_write.do", method=RequestMethod.GET)
	public String board_write() {
		return "/board/board_write";
	}

	/**
	 * �Խñ� ��ü ����Ʈ
	 * @return
	 */
	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
	public ModelAndView board_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		CgvBoardDAO dao = new CgvBoardDAO();		
		
		//����¡ ó�� - startCount, endCount ���ϱ�
		int startCount = 0;
		int endCount = 0;
		int pageSize = 3;	//���������� �Խù� ��
		int reqPage = 1;	//��û������	
		int pageCount = 1;	//��ü ������ ��
		int dbCount = dao.execTotalCount();	//DB���� ������ ��ü ���
		
		//�� ������ �� ���
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//��û ������ ���
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}
		
		//ArrayList<CgvBoardVO> list = dao.select();  //��ü ����Ʈ�� ��� ���
		ArrayList<CgvBoardVO> list = dao.select(startCount, endCount);
		
		mv.addObject("list", list);
		mv.addObject("dbCount", dbCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("reqPage", reqPage);		
		mv.setViewName("/board/board_list");
		
		return mv;
	}
	
}//controller

















