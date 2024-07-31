package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
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
			
			// 3. SQL 실행
			statement = connection.createStatement();
			// 2가지 쿼리를 실행시키는 메서드를 기억하자.
			resultSet = statement.executeQuery("SELECT * FROM employee"); // select 실행 시 사용
			// statement.executeUpdate(password); --> Insert, Update, Delete 사용
			
			// 구문 분석 -- 파싱
			
			// 4. 결과 처리
			while(resultSet.next()) { // next() : 데이터가 있다면, true 반환
				
				System.out.println("USER ID : " + resultSet.getInt("id"));
				System.out.println("USER NAME : " + resultSet.getString("name"));
				System.out.println("department : " + resultSet.getString("department"));
				System.out.println("-------------------------------------");
				
			}
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
	} // end of main

} // end of class
