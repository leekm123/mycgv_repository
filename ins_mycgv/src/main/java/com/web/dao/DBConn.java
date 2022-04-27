package com.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConn {
	//Field
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "scott";
	String pass = "tiger";
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//Constructor
	public DBConn() {
		try {
			//1 단계 
			Class.forName(driver);
			
			//2. Connection 생성
			conn = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//3단계 : Statement 생성
	public void getStatement() {
		try {
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//3단계 : PreparedStatement 생성
	public void getPreparedStatement(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	//6단계
	public void close() {
		try {
			//6. Close - 객체 종료
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}//class





