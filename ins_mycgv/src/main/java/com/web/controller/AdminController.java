package com.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.dao.CgvMemberDAO;
import com.web.dao.CgvNoticeDAO;
import com.web.vo.CgvMemberVO;
import com.web.vo.CgvNoticeVO;

@Controller
public class AdminController {
	
	/** 
	 * ������ �������� ����Ʈ **/
	@RequestMapping(value="/admin/notice_list.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();
		
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
		
		ArrayList<CgvNoticeVO> list = dao.select(startCount, endCount);
		
		mv.addObject("list", list);
		mv.addObject("dbCount", dbCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("reqPage", reqPage);
		
		mv.setViewName("/admin/notice/notice_list");
		
		return mv;
	}
		
	
	/** 
	 * ������ �������� ��� ó�� **/
	@RequestMapping(value="/admin/notice_write.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_write(CgvNoticeVO vo, HttpServletRequest request) 
																	throws Exception{
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();
		String nfile="", nsfile = "";
		
		//1. ���ϰ�ü�� ���� Ȯ��
		if(!vo.getFile1().getOriginalFilename().equals("")) {
			UUID uuid = UUID.randomUUID();
			nfile = vo.getFile1().getOriginalFilename();
			nsfile = uuid+"_"+nfile;
			
			vo.setNfile(nfile);
			vo.setNsfile(nsfile);
		}

		int result = dao.insert(vo);
		
		if(result == 1) {
			if(!vo.getFile1().getOriginalFilename().equals("")) {
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "resources\\upload\\";
				
				File file = new File(path+nsfile);
				vo.getFile1().transferTo(file);
			}
			mv.setViewName("redirect:/admin/notice_list.do");
		}else {
			//����������
		}
		
		return mv;
	}
	
	/** ������ �������� ���ȭ�� **/
	@RequestMapping(value="/admin/notice_write.do", method=RequestMethod.GET)
	public String admin_notice_write() {
		return "/admin/notice/notice_write";
	}
	
	
	/** 
	 * ������ �������� ������ **/
	@RequestMapping(value="/admin/notice_content.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_content(String nid, String rno) {
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();		
		CgvNoticeVO vo = dao.select(nid);
		
		mv.addObject("vo", vo);
		mv.addObject("rno", rno);
		mv.setViewName("/admin/notice/notice_content");
		
		return mv;
	}
	
	/** ������ �������� ����ó�� **/
	@RequestMapping(value="/admin/notice_update.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_update(CgvNoticeVO vo, HttpServletRequest request) 
															throws Exception{
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();
		//String nfile, nsfile = null;  //nfile, nsfile - ��ü���·� ����
		String nfile, nsfile = "";  //nfile, nsfile - �⺻������ ����
		String oldFile = vo.getNsfile();
		
		if(!vo.getFile1().getOriginalFilename().equals("")) { //���ο� ���� ����
			UUID uuid = UUID.randomUUID();
			nfile = vo.getFile1().getOriginalFilename();
			nsfile = uuid + "_" + nfile;
			
			vo.setNfile(nfile);
			vo.setNsfile(nsfile);
		}		
		
		int result = dao.update(vo);
		
		if(result ==1) {
			if(!vo.getFile1().getOriginalFilename().equals("")) {
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "resources\\upload\\";
				
				File file = new File(path + nsfile);
				vo.getFile1().transferTo(file);
				
				//������ ���� ����
				if(oldFile != null) {
					File dfile = new File(path + oldFile);
					if(dfile.exists()) {
						dfile.delete();
					}
				}
			}
			mv.setViewName("redirect:/admin/notice_list.do");			
		}else {
			//���������� ȣ��
		}		
		
		return mv;
	}
	
	
	/** ������ �������� ����ȭ�� **/
	@RequestMapping(value="/admin/notice_update.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_update(String nid, String rno) {
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		
		mv.addObject("vo", vo);
		mv.addObject("rno", rno);
		mv.setViewName("/admin/notice/notice_update");
		
		return mv;
	}
	
	
	/** ������ �������� ����ó�� **/
	@RequestMapping(value="/admin/notice_delete.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_delete(CgvNoticeVO vo, HttpServletRequest request)
															throws Exception{
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();
		String nsfile = dao.selectNsfile(vo.getNid()); //close ���� ����
		int result = dao.delete(vo.getNid());
		
		if(result ==1) {
			//������ �ִ°�� upload�������� ������ ����
			if(nsfile != null) {
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "resources\\upload\\";
				File file = new File(path+nsfile);
				if(file.exists()) {
					file.delete();
				}
			}
			mv.setViewName("redirect:/admin/notice_list.do");			
		}
				
		return mv;
	}
	
	
	/** ������ �������� ����ȭ�� **/
	@RequestMapping(value="/admin/notice_delete.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_delete(String nid, String rno) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("nid", nid);
		mv.addObject("rno", rno);
		mv.setViewName("/admin/notice/notice_delete");
		
		return mv;
	}
	
	
	/** ������ ȸ�� ������ **/
	@RequestMapping(value="/admin/member_content.do", method=RequestMethod.GET)
	public ModelAndView admin_member_content(String id, String rno) {
		ModelAndView mv = new ModelAndView();
		CgvMemberDAO dao = new CgvMemberDAO();
		CgvMemberVO vo = dao.select(id);
		
		mv.addObject("vo", vo);
		mv.addObject("rno", rno);
		mv.setViewName("/admin/member/member_content");		
		
		return mv;
	}

	/** ������ ȸ�� ����Ʈ **/
	@RequestMapping(value="/admin/member_list.do", method=RequestMethod.GET)
	public ModelAndView admin_member_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		CgvMemberDAO dao = new CgvMemberDAO();
		
		//����¡ ó�� - startCount, endCount ���ϱ�
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//���������� �Խù� ��
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
				
		
		ArrayList<CgvMemberVO> list = dao.select(startCount, endCount);
				
		mv.addObject("list",list);
		mv.addObject("dbCount", dbCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("reqPage", reqPage);	
		
		mv.setViewName("/admin/member/member_list");		
		
		return mv;
	}
	
	
	/** ������ ���� ������ **/
	@RequestMapping(value="/admin.do", method=RequestMethod.GET)
	public String admin() {
		return "/admin/admin";
	}
}






