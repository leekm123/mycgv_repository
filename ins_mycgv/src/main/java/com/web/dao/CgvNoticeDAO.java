package com.web.dao;

import java.util.ArrayList;

import com.web.vo.CgvNoticeVO;

public class CgvNoticeDAO extends DBConn{
	
	/**
	 * nsfile 
	 */
	public String selectNsfile(String nid) {
		String result = "";
		String sql = "select nsfile from cgv_notice where nid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, nid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * 페이징 처리 - 전체 row 카운트
	 */
	public int execTotalCount() {
		int count = 0;
		String sql = "select count(*) from cgv_notice";
		getPreparedStatement(sql);
		
		try {
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
			//close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;		
	}
	
	/**
	 * 공지사항 삭제
	 */
	public int delete(String nid) {
		int result = 0;
		String sql = "delete from cgv_notice where nid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, nid);
			result = pstmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 공지사항 업데이트
	 */
	public int update(CgvNoticeVO vo) {
		int result = 0;
		String sql = "";
		
		try {
			if(vo.getNfile() != "") {
				sql = "update cgv_notice set ntitle=?, ncontent=?, nfile=?, nsfile=?  where nid=?";
				getPreparedStatement(sql);
				pstmt.setString(1, vo.getNtitle());
				pstmt.setString(2, vo.getNcontent());
				pstmt.setString(3, vo.getNfile());
				pstmt.setString(4, vo.getNsfile());
				pstmt.setString(5, vo.getNid());
			}else {
				sql = "update cgv_notice set ntitle=?, ncontent=?  where nid=?";			
				getPreparedStatement(sql);
				pstmt.setString(1, vo.getNtitle());
				pstmt.setString(2, vo.getNcontent());
				pstmt.setString(3, vo.getNid());
			}
			
			result = pstmt.executeUpdate();
			
			close();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 공지사항 조회수 업데이트
	 */
	public void updateHits(String nid) {
		String sql = "update cgv_notice set nhits=nhits+1 where nid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, nid);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 공지사항 상세 정보, 업데이트폼
	 */
	public CgvNoticeVO select(String nid) {
		CgvNoticeVO vo = new CgvNoticeVO();
		String sql = "select nid, ntitle, ncontent, nhits, to_char(ndate,'yyyy-mm-dd') ndate"
				+ " , nsfile, nfile "
				+ " from cgv_notice where nid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, nid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setNid(rs.getString(1));
				vo.setNtitle(rs.getString(2));
				vo.setNcontent(rs.getString(3));
				vo.setNhits(rs.getInt(4));
				vo.setNdate(rs.getString(5));
				vo.setNsfile(rs.getString(6));
				vo.setNfile(rs.getString(7));
			}
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
		return vo;
	}
	
	/**
	 * 공지사항 전체 리스트 
	 */
	public ArrayList<CgvNoticeVO> select(){
		ArrayList<CgvNoticeVO> list = new ArrayList<CgvNoticeVO>();
		String sql = "select rownum rno, nid, ntitle, nhits, to_char(ndate,'yyyy-mm-dd') ndate "
				+ "        from ( select nid, ntitle, nhits, ndate from cgv_notice order by ndate desc)"
				+ "       ";
		getPreparedStatement(sql);
		
		try {
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CgvNoticeVO vo = new CgvNoticeVO();
				vo.setRno(rs.getInt(1));
				vo.setNid(rs.getString(2));
				vo.setNtitle(rs.getString(3));
				vo.setNhits(rs.getInt(4));
				vo.setNdate(rs.getString(5));
				
				list.add(vo);
			}
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	/**
	 * 공지사항 전체 리스트 - 페이징처리
	 */
	public ArrayList<CgvNoticeVO> select(int startCount, int endCount){
		ArrayList<CgvNoticeVO> list = new ArrayList<CgvNoticeVO>();
		String sql = "select rno, nid, ntitle, nhits, ndate "
				+ " from (select rownum rno, nid, ntitle, nhits, to_char(ndate,'yyyy-mm-dd') ndate "
				+ "        from ( select nid, ntitle, nhits, ndate from cgv_notice order by ndate desc)"
				+ "       )  where rno between ? and ?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CgvNoticeVO vo = new CgvNoticeVO();
				vo.setRno(rs.getInt(1));
				vo.setNid(rs.getString(2));
				vo.setNtitle(rs.getString(3));
				vo.setNhits(rs.getInt(4));
				vo.setNdate(rs.getString(5));
				
				list.add(vo);
			}
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	/**
	 * 공지사항 등록
	 */
	public int insert(CgvNoticeVO vo) {
		int result = 0;		
		String sql = "insert into cgv_notice values('n_'||SEQU_CGV_NOTICE_NID.nextval,?,?,0,sysdate,?,?)";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getNtitle());
			pstmt.setString(2, vo.getNcontent());
			pstmt.setString(3, vo.getNfile());
			pstmt.setString(4, vo.getNsfile());
			
			result = pstmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
}
