package com.web.dao;

import java.util.ArrayList;

import com.web.vo.CgvMemberVO;

public class CgvMemberDAO extends DBConn{
	
	/**
	 * 페이징 처리 - 전체 row 카운트
	 */
	public int execTotalCount() {
		int count = 0;
		String sql = "select count(*) from cgv_member";
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
	 * 회원탈퇴 신청/취소
	 */
	public int updateJoinStatus(String id, String status) {
		int result = 0;
		String sql = "";
		int value = Integer.parseInt(status);
		if(value == 0) {
			//신청
			sql = "update cgv_member set join_status=1 where id=?";
		}else {
			//취소
			sql = "update cgv_member set join_status=0 where id=?";
		}				
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return result;
	}
	
	/**
	 * 회원 상세 정보
	 */
	public CgvMemberVO select(String id) {
		CgvMemberVO vo = new CgvMemberVO();
		String sql = "select id, name, gender, hobbylist, hp, join_status from cgv_member where id=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setGender(rs.getString(3));
				vo.setHobbylist(rs.getString(4));
				vo.setHp(rs.getString(5));
				vo.setJoin_status(rs.getInt(6));
			}
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	/**
	 * 회원 전체 리스트 - 페이징 처리
	 */
	public ArrayList<CgvMemberVO> select(int startCount, int endCount){
		ArrayList<CgvMemberVO> list = new ArrayList<CgvMemberVO>();
		String sql = " select rno, id, name, hp, gender, mdate, join_status "
				+ " from (select rownum rno, id,name,hp,gender,to_char(mdate,'yyyy-mm-dd') mdate, join_status"
				+ " 		from (select id, name, hp, gender, mdate, join_status from cgv_member "
				+ "      			order by mdate desc)"
				+ " 	 ) where rno between ? and ? ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CgvMemberVO vo = new CgvMemberVO();
				vo.setRno(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setHp(rs.getString(4));
				vo.setGender(rs.getString(5));
				vo.setMdate(rs.getString(6));
				vo.setJoin_status(rs.getInt(7));
				
				list.add(vo);
			}
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	/**
	 * 회원 전체 리스트
	 */
	public ArrayList<CgvMemberVO> select(){
		ArrayList<CgvMemberVO> list = new ArrayList<CgvMemberVO>();
		String sql = " select rownum rno, id,name,hp,gender,to_char(mdate,'yyyy-mm-dd') mdate, join_status"
				+ " from (select id, name, hp, gender, mdate, join_status from cgv_member "
				+ "      order by mdate desc)";
		getPreparedStatement(sql);
		
		try {
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CgvMemberVO vo = new CgvMemberVO();
				vo.setRno(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setHp(rs.getString(4));
				vo.setGender(rs.getString(5));
				vo.setMdate(rs.getString(6));
				vo.setJoin_status(rs.getInt(7));
				
				list.add(vo);
			}
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	/**
	 * 회원가입 시 아이디 중복체크 : idCheck(String id)
	 */
	public int idCheck(String id) {
		int result = 0;
		String sql = "select count(*) from cgv_member where id=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return result;
	}
	
	/**
	 * 로그인 - select(CgvMemberVO vo)
	 */
	public int select(CgvMemberVO vo) {
		int result = 0;
		String sql = "select count(*) from cgv_member where id=? and pass=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return result;
	}
	

	/**
	 * 회원가입 - insert(CgvMemberVO vo)
	 */
	public int insert(CgvMemberVO vo) {
		int result = 0;
		String sql = "insert into cgv_member values(?,?,?,?,?,?,?,?,sysdate,0)";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getHp());
			pstmt.setString(7, vo.getHobbylist());
			pstmt.setString(8, vo.getIntro());
			
			result = pstmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
}







