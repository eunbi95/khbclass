package kr.co.kh.obj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Search {
	private String titleSearch;
	private Connection conn;
	private Statement stmt;
	private String sql;
	private ResultSet rs;
	private int readcount;
	private int cnt1;
	
	public Search() {
		
	}
	public void setTitleSearch() {
		System.out.println("ã�� �Խñ� �����Է�:"); 
		titleSearch = Register.input.next();
	
	}
	public void boardSearchTitle() {
		System.out.print("��ȣ\t����\t����\t�ۼ���\t��¥\t��ȸ��\n");
	}
	public void boardStmtSql()throws SQLException {	
		 conn=Register.getConnection();
		 stmt= conn.createStatement();
		 sql="SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE='" +titleSearch + "'"; 
	}
	public void boardSearchExecuter()throws SQLException{
		 rs = stmt.executeQuery(sql);
	}
	public void boardSerarchProcess() {
		 try {
			while (rs.next()) { 
			 int no = rs.getInt("no"); 
			 String title = rs.getString("title"); 
			 String content = rs.getString("content"); 
			 String author = rs.getString("author"); 
			 String nal = rs.getString("nal"); 
			 readcount= rs.getInt("readcount"); // rs�� �������̽��̸� ǥ�ȿ��ִ� �����Ͱ� ����Ŵ 
			 System.out.print(no + "\t" + title + "\t" + content + "\t" + author + "\t" + nal + "\t" +readcount + "\n");
			 readcount++;
}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void boardSerarchReadcount()throws SQLException {
		stmt = conn.createStatement(); sql = "UPDATE BOARD SET readcount=" +readcount + " WHERE title='" + titleSearch + "'"; 
		cnt1 =stmt.executeUpdate(sql); 
	}
/*	public static void main(String[] args) {
		Search search = new Search();
		search.setTitleSearch();
		search.boardSearchTitle();
		try {
			search.boardStmtSql();
			search.boardSearchExecuter();
			search.boardSerarchProcess();
			search.boardSerarchReadcount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

}
