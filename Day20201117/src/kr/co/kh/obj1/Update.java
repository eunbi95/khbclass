package kr.co.kh.obj1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
	private String titleSearch;
	private Statement stmt;
	private String sql;
	private Connection conn;
	private ResultSet rs;
	private String title;
	private String content;
	private String author;
	private String nal;
	private int readcount;
	private char option;
	private String titleContent;
	private int indexI;
	private String titleUpdate;
	private String contentUpdate;
	private String authorUpdate;
	private String nalUpdate;
	private int readcountUpdate;
	private int cnt;
	
	public Update() {		
	}
	public void boardUpdateTitle() {
		System.out.println("변경할 게시글 제목 입력");
		titleSearch = Register.input.next();
	}
	public void boardUpdateSearch() throws SQLException {
		conn = Register.getConnection();
		stmt = conn.createStatement();
		sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE='" + titleSearch + "'";
	}
	public void boardUpdateExecuterSql() throws SQLException {
		rs = stmt.executeQuery(sql);
	}
	public void boardUpdateOld() throws SQLException {
		System.out.println("===변경하기 전 게시글입니다===");
		while (rs.next()) {
			title = rs.getString("title");
			content = rs.getString("content");
			author = rs.getString("author");
			nal = rs.getString("nal");
			readcount = rs.getInt("readcount");
			System.out.print(title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount + "\n");
		}		
	}
	public char boardUpdateOption() {
		System.out.println("정말로 변경하시겠습니까? y/n");
		option = Register.input.next().charAt(0);
		return option;
	}
	public void boardUpdateConfirm() {
		if (option == 'Y' || option == 'y') {
			System.out.println("제목|내용:");
			titleContent = Register.input.next();
			indexI = titleContent.indexOf("|"); // "|"위치는 2 -> indexI=2
			titleUpdate = titleContent.substring(0, indexI);
			contentUpdate = titleContent.substring(indexI + 1);
			System.out.println("작성자입력:");
			authorUpdate = Register.input.next();
			System.out.println("날짜:");
			nalUpdate = Register.input.next();  // 키보드 선언을 Register에서함.
			System.out.println("조회수:");
			readcountUpdate = Register.input.nextInt();		
	}
}
	public void boardUpdateFinal() throws SQLException {
		stmt = conn.createStatement();
		sql = "UPDATE board SET TITLE='" + titleUpdate + "',CONTENT='" + contentUpdate + "',AUTHOR='" + authorUpdate+ "',NAL='" + nalUpdate + "',READCOUNT='" + readcountUpdate + "' WHERE TITLE='" + titleSearch + "'";
		cnt = stmt.executeUpdate(sql);
		System.out.println(cnt + "긴 게시글이 수정되었습니다.");
	}
/*	public static void main(String[] args) {
		Update update = new Update();
		update.boardUpdateTitle();
		try {
			update.boardUpdateSearch();
			update.boardUpdateExecuterSql();
			update.boardUpdateOld();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.boardUpdateOption();
		update.boardUpdateConfirm();
		try {
			update.boardUpdateFinal();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	*/
}