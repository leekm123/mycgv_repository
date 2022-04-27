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
	 * ����¡ ó�� - ��ü row ī��Ʈ
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
	 * �Խñ� ���� : delete(pk��) 
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
	 * �Խñ� ������Ʈ : update(pk��)
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
	 * ��ȸ�� ������Ʈ : updateHits(pk��)
	 */
	public void updateHits(String bid) {
		String sql = "update cgv_board set bhits=bhits+1 where bid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, bid); //�Ķ���� ����			
			pstmt.executeUpdate();   //���� ����
			
			//close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * �Խñ� �� ���� : select(pk��)
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
	 * �Խñ� ��ü ����Ʈ : select()
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
				
				//list�� �߰��ϱ�!!!
				list.add(vo);
			}
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	/**
	 * �Խñ� ��� : insert
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




