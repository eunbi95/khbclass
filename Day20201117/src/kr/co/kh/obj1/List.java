package kr.co.kh.obj1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class List {
	private Connection conn;
	private Statement stmt;
	private String sql;
	private ResultSet rs;
	private int no;
	private String title;
	private String content;
	private String author;
	private String nal;
	private int readcount;
	
	public List() {
		
	}
	public void listBoardTitle() {
		System.out.println("===�Խ��� ��ü���===");
		System.out.print("��ȣ\t����\t����\t�ۼ���\t��¥\t��ȸ��\n");
	}
	public void listBoardSql() throws SQLException {
		conn=Register.getConnection();
		stmt = conn.createStatement();
		sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD ORDER BY NO ASC"; //ASC:�������� DSC:��������
	}
	public void listBoardExecuter() throws SQLException {
		rs = stmt.executeQuery(sql);
	}
	public void listBoardProcess() throws SQLException {
		while (rs.next()) {
			no = rs.getInt("no");
			title = rs.getString("title");
			content = rs.getString("content");
			author = rs.getString("author");
			nal = rs.getString("nal");
			readcount = rs.getInt("readcount");
			System.out.print(
					no + "\t" + title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount + "\n");
		}			
	}
/*	public static void main(String[] args) {
		List list = new List();
		list.listBoardTitle();
		try {
			list.listBoardSql();
			list.listBoardExecuter();
			list.listBoardProcess();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}

