package kr.co.kh.instance1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.co.kh.instance.BoardFunction;

public class BoardEx {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		BoardFunction boardObj = new  BoardFunction();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection conn = null;

		while (true) { // 반복문
			// 2.연결(Connection)연결하다. DriverManager.getConnection

			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("===게시판작성===");
			System.out.println("R:등록 S:검색 D:삭제 U:수정 L:목록");
			char protocol = input.next().charAt(0);
			if (protocol == 'R' || protocol == 'r') { // 등록
				try {
					boardObj.register(input, conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {  // 종료시키기
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // 등록
			else if (protocol == 'S' || protocol == 's') { // 검색
				try {
					boardObj.search(input, conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally { // 연결 종료시키기
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // 검색
			else if (protocol == 'D' || protocol == 'd') { // 삭제
				try {
					boardObj.delete(input, conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} // 삭제
			else if (protocol == 'U' || protocol == 'u') { // 수정
				char option='n';
				try {
					option = boardObj.update(input, conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(option=='n'||option=='N') {
					continue;  // 반복문으로 올라감
				}								
			} // 수정
			else if (protocol == 'L' || protocol == 'l') { // 목록(전체출력)
				try {
					boardObj.list(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}

}
}
