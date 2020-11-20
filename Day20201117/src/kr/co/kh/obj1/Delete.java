package kr.co.kh.obj1;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
	private Connection conn;
	private String tileDelete;
	private Statement stmt;
	private String sql;
	private int cnt;
	public Delete() {
		
	}
	public void boardDeleteTitle() {
		System.out.println("삭제할 제목을 입력:");
		tileDelete = Register.input.next();
	}
	public void boardDeleteSql() throws SQLException {
		conn = Register.getConnection();
		stmt = conn.createStatement();
		sql = "delete from board where title='" + tileDelete + "'";		
	}
	public void boarDeleteExecuter() throws SQLException {
		cnt = stmt.executeUpdate(sql);
		System.out.println(cnt + "건 게시글이 삭제");
	}
/*	public static void main(String[] args) {
		Delete delete = new Delete();
		delete.boardDeleteTitle();
		try {
			delete.boardDeleteSql();
			delete.boarDeleteExecuter();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}

