package ch05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchPractice {

	public static void main(String[] args) {

		// 경로
		String url = "jdbc:mysql://localhost:3306/demo3?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";
		Connection conn = null;
		
		try {
			// 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 자바 표준 API
			conn = DriverManager.getConnection(url, user, password);
			
			// 트랜잭션
			conn.setAutoCommit(false);
			
			// 배치처리
			String sql = " INSERT INTO frogs(name, color) VALUES (?, ?) ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 1번 사용자
			pstmt.setString(1, "청개구리");
			pstmt.setString(2, "초록색");
			pstmt.addBatch();
			
			// 2번 사용자
			pstmt.setString(1, "황소개구리");
			pstmt.setString(2, "황토색");
			pstmt.addBatch();

			// 3번 사용자
			pstmt.setString(1, "독개구리");
			pstmt.setString(2, "화려한색");
			pstmt.addBatch();

			int[] rowCounts = pstmt.executeBatch();
			
			conn.commit();
			System.out.println("배치처리 완료 : " + rowCounts.length);
			
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	} // end of main

}
