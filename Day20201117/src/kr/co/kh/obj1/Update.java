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
		System.out.println("������ �Խñ� ���� �Է�");
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
		System.out.println("===�����ϱ� �� �Խñ��Դϴ�===");
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
		System.out.println("������ �����Ͻðڽ��ϱ�? y/n");
		option = Register.input.next().charAt(0);
		return option;
	}
	public void boardUpdateConfirm() {
		if (option == 'Y' || option == 'y') {
			System.out.println("����|����:");
			titleContent = Register.input.next();
			indexI = titleContent.indexOf("|"); // "|"��ġ�� 2 -> indexI=2
			titleUpdate = titleContent.substring(0, indexI);
			contentUpdate = titleContent.substring(indexI + 1);
			System.out.println("�ۼ����Է�:");
			authorUpdate = Register.input.next();
			System.out.println("��¥:");
			nalUpdate = Register.input.next();  // Ű���� ������ Register������.
			System.out.println("��ȸ��:");
			readcountUpdate = Register.input.nextInt();		
	}
}
	public void boardUpdateFinal() throws SQLException {
		stmt = conn.createStatement();
		sql = "UPDATE board SET TITLE='" + titleUpdate + "',CONTENT='" + contentUpdate + "',AUTHOR='" + authorUpdate+ "',NAL='" + nalUpdate + "',READCOUNT='" + readcountUpdate + "' WHERE TITLE='" + titleSearch + "'";
		cnt = stmt.executeUpdate(sql);
		System.out.println(cnt + "�� �Խñ��� �����Ǿ����ϴ�.");
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