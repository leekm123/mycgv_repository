package com.web.dao;

import java.util.ArrayList;

import com.web.vo.CgvBoardVO;

public class CgvBoardDAO extends DBConn{
	/**
	 * bsfile
	 */
	public String selectBsfile(String bid) {
		String result = "";
		String sql = "select bsfile from cgv_board where bid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, bid);
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
		String sql = "select count(*) from cgv_board";
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
	 * 게시글 삭제 : delete(pk값) 
	 */
	public int delete(String bid) {
		int result = 0;
		String sql = "delete from cgv_board where bid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, bid);
			result = pstmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
		
	
	/**
	 * 게시글 업데이트 : update(pk값)
	 */
	public int update(CgvBoardVO vo) {
		int result = 0;
		String sql = "";
				
		try {
			if(vo.getBfile() != "") {
				sql = "update cgv_board set btitle=?, bcontent=?, bfile=?, bsfile=? where bid=?";
				getPreparedStatement(sql);
				pstmt.setString(1, vo.getBtitle());
				pstmt.setString(2, vo.getBcontent());
				pstmt.setString(3, vo.getBfile());
				pstmt.setString(4, vo.getBsfile());
				pstmt.setString(5, vo.getBid());
			}else {
				sql = "update cgv_board set btitle=?, bcontent=? where bid=?";
				getPreparedStatement(sql);
				pstmt.setString(1, vo.getBtitle());
				pstmt.setString(2, vo.getBcontent());
				pstmt.setString(3, vo.getBid());
			}
						
			result = pstmt.executeUpdate();
			close();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return result;
	}
		
	
	/**
	 * 조회수 업데이트 : updateHits(pk값)
	 */
	public void updateHits(String bid) {
		String sql = "update cgv_board set bhits=bhits+1 where bid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, bid); //파라미터 매핑			
			pstmt.executeUpdate();   //쿼리 실행
			
			//close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 게시글 상세 보기 : select(pk값)
	 */
	public CgvBoardVO select(String bid) {
		CgvBoardVO vo = new CgvBoardVO();
		String sql = " select bid,btitle,bcontent, bhits, bdate, bsfile, bfile from cgv_board" + 
				" where bid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, bid);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setBid(rs.getString(1));
				vo.setBtitle(rs.getString(2));
				vo.setBcontent(rs.getString(3));
				vo.setBhits(rs.getInt(4));
				vo.setBdate(rs.getString(5));
				vo.setBsfile(rs.getString(6));
				vo.setBfile(rs.getString(7));
			}
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return vo;
	}
	
	
	/**
	 * 게시글 전체 리스트 : select()
	 */
	public ArrayList<CgvBoardVO> select(int startCount, int endCount){
		ArrayList<CgvBoardVO> list = new ArrayList<CgvBoardVO>();
		String sql = " select rno, bid, btitle, bhits, bdate from "
				+ " (select rownum rno, bid, btitle, bhits, "
				+ " to_char(bdate,'yyyy/mm/dd') bdate "  
				+ " from (select bid, btitle, bhits, bdate from cgv_board order by bdate desc) "
				+ " ) where rno between ? and ?";
				
		getPreparedStatement(sql);
		
		try {
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CgvBoardVO vo = new CgvBoardVO();
				vo.setRno(rs.getInt(1));
				vo.setBid(rs.getString(2));
				vo.setBtitle(rs.getString(3));
				vo.setBhits(rs.getInt(4));
				vo.setBdate(rs.getString(5));
				
				//list에 추가하기!!!
				list.add(vo);
			}
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	/**
	 * 게시글 등록 : insert
	 */
	public int insert(CgvBoardVO vo) {
		int result = 0;
		String sql = "insert into cgv_board values('b_'||sequ_cgv_board_bid.nextval,?,?,0,sysdate,?,?)";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setString(3, vo.getBfile());
			pstmt.setString(4, vo.getBsfile());
			
			result = pstmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}




