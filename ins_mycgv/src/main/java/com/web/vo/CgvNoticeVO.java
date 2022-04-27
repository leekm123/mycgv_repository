package com.web.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CgvNoticeVO {
	int nhits, rno;
	String nid, ntitle, ncontent, ndate, nfile, nsfile;
	CommonsMultipartFile file1;
	
	public String getNfile() {
		return nfile;
	}
	public void setNfile(String nfile) {
		this.nfile = nfile;
	}
	public String getNsfile() {
		return nsfile;
	}
	public void setNsfile(String nsfile) {
		this.nsfile = nsfile;
	}
	public CommonsMultipartFile getFile1() {
		return file1;
	}
	public void setFile1(CommonsMultipartFile file1) {
		this.file1 = file1;
	}
	public int getNhits() {
		return nhits;
	}
	public void setNhits(int nhits) {
		this.nhits = nhits;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNdate() {
		return ndate;
	}
	public void setNdate(String ndate) {
		this.ndate = ndate;
	}
	
	
}
