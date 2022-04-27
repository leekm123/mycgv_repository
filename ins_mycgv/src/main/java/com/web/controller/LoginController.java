package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.dao.CgvMemberDAO;
import com.web.vo.CgvMemberVO;

@Controller
public class LoginController {
	
	/**
	 * 로그인 처리
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(CgvMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.select(vo);
		
		//System.out.println("login--->> " + result);
		if(result == 1) {	
			mv.addObject("login_result", "succ");
			mv.setViewName("index");
		}else {
			mv.addObject("login_result", "fail");
			mv.setViewName("/login/login");
		}		
		
		return mv;
	}
	
	
	/**
	 * 로그인 화면
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "/login/login";
	}
	

}
