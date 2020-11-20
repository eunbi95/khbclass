
package kr.co.kh.obj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 인스턴스 메소드로 변경 -> 반드시 new로 객체 생성후 불러야함, static은 메모리 공간 낭비가 심하다.
public class BoardEx {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Register register = new Register();
		Search search = new Search();
		Delete delete = new Delete();
		Update update = new Update();
		List list = new List();
		Connection conn = null; // 클래스(대문자로시작) 초기값은 무조건 null

		while (true) { // 반복문
			// 2.연결(Connection)연결하다. DriverManager.getConnection

			try {
				conn = register.getConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("===게시판작성===");
			System.out.println("R:등록 S:검색 D:삭제 U:수정 L:목록");
			char protocol = input.next().charAt(0);
			if (protocol == 'R' || protocol == 'r') { // 등록
				try {
					register.setTitleContent();
					register.titleContentProcess();
					register.setAuthor();
					register.setNal();
					register.setReadCount();
					register.boardQuery();
					register.boardExecuter();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally { // 종료시키기
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
					search.setTitleSearch();
					search.boardSearchTitle();
					search.boardStmtSql();
					search.boardSearchExecuter();
					search.boardSerarchProcess();
					search.boardSerarchReadcount();
				} catch (SQLException e) {
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
					delete.boardDeleteTitle();
					delete.boardDeleteSql();
					delete.boarDeleteExecuter();
				} catch (SQLException e) {
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
				char option = 'n';
				try {
					update.boardUpdateTitle();
					update.boardUpdateSearch();
					update.boardUpdateExecuterSql();
					update.boardUpdateOld();
					option = update.boardUpdateOption();
					if (option == 'n' || option == 'N') {
						continue; // 반복문으로 올라감
					}
					update.boardUpdateConfirm();
					update.boardUpdateFinal();
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
			} // 수정
			else if (protocol == 'L' || protocol == 'l') { // 목록(전체출력)
				try {
					list.listBoardTitle();
					list.listBoardSql();
					list.listBoardExecuter();
					list.listBoardProcess();
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
