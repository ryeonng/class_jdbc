package ch02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLJdbcExample {

	public static void main(String[] args) {

		// jdbc connect 준비물 
		//
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root"; // 상용서비스에서 절대 루트 계정 사용 금지 !
		String password = "asd123";
		
		// 필요 데이터 타입
		// JDBC API 레벨(자바 개발자들이 개념화 시켜놓은 클래스들)
		Connection connection = null; // 연결
		Statement statement = null; // 문자를 기반으로 쿼리를 실행시키는 객체
		ResultSet resultSet = null;
		
		// 1. MySQL 구현체를 사용하겠다는 설정을 해야 한다.
		// JDBC 드라이버 로드 (MySQL 드라이버)
		try {
			// 1. 메모리에 사용하는 드라이버 클래스(JDBC API를 구현한 클래스)를 띄운다.
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 데이터 베이스에 연결 설정
			connection = DriverManager.getConnection(url, user, password);
			
			// 3. SQL 실행 (PreparedStatement 객체 사용해보기)
			
			// 3-1 쿼리 만들어 보기
			String query = "insert into employee values(?, ?, ?, ?, now())";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 7);
			preparedStatement.setString(2, "이순신");
			preparedStatement.setString(3, "IT");
			preparedStatement.setString(4, "5000000.00");
			
			// 실행의 호출은 executeQuery 에 사용
			int rowCount = preparedStatement.executeUpdate();
			System.out.println("rowCount : " + rowCount);
			
			// 4. 결과 처리
			
				
			
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
	} // end of main

} // end of class
