package kr.co.kh.instance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 메소드 분리, 인스턴스함수와 static함수가 있다,종료간소화
public class BoardFunction {
	public void register(Scanner input, Connection conn)throws SQLException {  // (Scanner input)=외부값 
		// 01 234
		System.out.println("제목|내용:");
		String titleContent = input.next();
		int indexI = titleContent.indexOf("|"); // "|"위치는 2 -> indexI=2
		String title = titleContent.substring(0, indexI);
		String content = titleContent.substring(indexI + 1);
		System.out.println("작성자입력:");
		String author = input.next();
		System.out.println("날짜:");
		String nal = input.next();
		System.out.println("조회수:");
		int readcount = input.nextInt();
			// 3.준비(Statement) 3-1 공간을 준비
			// 3.준비 3-1 쿼리준비
			Statement stmt = conn.createStatement();
			String sql = "insert into board(title,content,author,nal,readcount) values('" + title
					+ "','" + content + "','" + author + "','" + nal + "'," + readcount + ")";
			// 4.실행 execute
			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt + "건 게시글이 등록되었습니다.");
	}

	public void search(Scanner input, Connection conn)throws SQLException {  // throws SQLException=예외 던지기
		System.out.println("찾는 게시글 제목입력:");
		String titleSearch = input.next();
		System.out.print("번호\t제목\t내용\t작성자\t날짜\t조회수\n");
		// 3.준비	
			Statement stmt = conn.createStatement();
			String sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE='" + titleSearch + "'"; // WHERE
																														// TITLE='"+titleSearch+"'"																														// ->조건문
			//int cnt = stmt.executeUpdate(sql);
			ResultSet rs = stmt.executeQuery(sql);
			int readcount = 0;

			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String author = rs.getString("author");
				String nal = rs.getString("nal");
				readcount = rs.getInt("readcount");
				// rs는 인터페이스이며 표안에있는 데이터값 가리킴
				System.out.print(
						no + "\t" + title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount + "\n");
			//	System.out.println(cnt + "건 게시글이 검색");
				readcount++;
			}
			stmt = conn.createStatement();
			sql = "UPDATE BOARD SET readcount=" + readcount + " WHERE title='" + titleSearch + "'";
			int cnt1 = stmt.executeUpdate(sql);
	}

	public void delete(Scanner input, Connection conn)throws SQLException {
		System.out.println("삭제할 제목을 입력:");
		String tileDelete = input.next();
		// 3.준비
		// 1)공간을 준비한다.
		// 2)쿼리를 준비한다.		
			Statement stmt = conn.createStatement();
			String sql = "delete from board where title='" + tileDelete + "'";
			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt + "건 게시글이 삭제");
	}

	public char update(Scanner input, Connection conn) throws SQLException {
		System.out.println("변경할 게시글 제목 입력");
		String titleSearch = input.next();
		Statement stmt = null; // null값 꼭 지정
		String sql = null;
		stmt = conn.createStatement();
		sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE='" + titleSearch + "'";

		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("===변경하기 전 게시글입니다===");
		while (rs.next()) {
			String title = rs.getString("title");
			String content = rs.getString("content");
			String author = rs.getString("author");
			String nal = rs.getString("nal");
			int readcount = rs.getInt("readcount");
			System.out.print(title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount + "\n");
		}
		
		System.out.println("정말로 변경하시겠습니까? y/n");
		char option = input.next().charAt(0);
		if (option == 'Y' || option == 'y') {
			System.out.println("제목|내용:");
			String titleContent = input.next();
			int indexI = titleContent.indexOf("|"); // "|"위치는 2 -> indexI=2
			String titleUpdate = titleContent.substring(0, indexI);
			String contentUpdate = titleContent.substring(indexI + 1);
			System.out.println("작성자입력:");
			String authorUpdate = input.next();
			System.out.println("날짜:");
			String nalUpdate = input.next();
			System.out.println("조회수:");
			int readcountUpdate = input.nextInt();

			stmt = conn.createStatement();
			sql = "UPDATE board SET TITLE='" + titleUpdate + "',CONTENT='" + contentUpdate + "',AUTHOR='" + authorUpdate
					+ "',NAL='" + nalUpdate + "',READCOUNT='" + readcountUpdate + "' WHERE TITLE='" + titleSearch + "'";
			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt + "긴 게시글이 수정되었습니다.");
		}
		return option;
	}

	public void list(Connection conn)throws SQLException {
		System.out.println("===게시판 전체출력===");
		System.out.print("번호\t제목\t내용\t작성자\t날짜\t조회수\n");
		// 3.1 공간준비
		// 3.2 쿼리준비	
			Statement stmt = conn.createStatement();
			String sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String author = rs.getString("author");
				String nal = rs.getString("nal");
				int readcount = rs.getInt("readcount");
				System.out.print(
						no + "\t" + title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount + "\n");
			}	
	}
}
