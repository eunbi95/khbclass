import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BoardEx {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		// 1.로드(적재=자바에게 내가 데이터베이스를 뭘쓰겠다.)

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

				try {
					// 3.준비(Statement) 3-1 공간을 준비
					// 3.준비 3-1 쿼리준비
					Statement stmt = conn.createStatement();
					String sql = "insert into board(no,title,content,author,nal,readcount) values(board_no.nextval,'"
							+ title + "','" + content + "','" + author + "','" + nal + "'," + readcount + ")";
					// 4.실행 execute
					int cnt = stmt.executeUpdate(sql);
					System.out.println(cnt + "건 게시글이 등록되었습니다.");
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // 등록
			else if (protocol == 'S' || protocol == 's') { // 검색
				System.out.println("찾는 게시글 제목입력:");
				String titleSearch = input.next();
				System.out.print("번호\t제목\t내용\t작성자\t날짜\t조회수\n");
				// 3.준비
				try {
					Statement stmt = conn.createStatement();
					String sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE='" + titleSearch
							+ "'"; // WHERE TITLE='"+titleSearch+"'" ->조건문
					int cnt=stmt.executeUpdate(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					int readcount=0;

					while (rs.next()) {
						int no = rs.getInt("no");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String author = rs.getString("author");
						String nal = rs.getString("nal");
						readcount = rs.getInt("readcount");
						// rs는 인터페이스이며 표안에있는 데이터값 가리킴

						System.out.print(no + "\t" + title + "\t" + content + "\t" + author + "\t" + nal + "\t"
								+ readcount + "\n");
						System.out.println(cnt+"건 게시글이 검색");
						readcount++;

					}
					stmt=conn.createStatement();
					sql="UPDATE BOARD SET readcount="+readcount+" WHERE title='"+titleSearch+"'";
					int cnt1=stmt.executeUpdate(sql);
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} // 검색
			else if (protocol == 'D' || protocol == 'd') { // 삭제
				System.out.println("삭제할 제목을 입력:");
				String tileDelete = input.next();
				// 3.준비
				// 1)공간을 준비한다.
				// 2)쿼리를 준비한다.
				try {
					Statement stmt = conn.createStatement();
					String sql = "delete from board where title='" + tileDelete + "'";
					int cnt = stmt.executeUpdate(sql);
					System.out.println(cnt + "건 게시글이 삭제");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} // 삭제
			else if (protocol == 'U' || protocol == 'u') { // 수정
				System.out.println("변경할 게시글 제목 입력");
				String titleSearch = input.next();
				Statement stmt = null; // null값 꼭 지정
				String sql=null;
				try {
					stmt = conn.createStatement();
					sql = "SELECT TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE='"+titleSearch+"'";

					ResultSet rs=stmt.executeQuery(sql);
					System.out.println("===변경하기 전 게시글입니다===");
					while(rs.next()) {
						String title = rs.getString("title");
						String content = rs.getString("content");
						String author = rs.getString("author");
						String nal = rs.getString("nal");
						int readcount = rs.getInt("readcount");
						System.out.print(title + "\t" + content + "\t" + author + "\t" + nal + "\t"+ readcount + "\n");						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("정말로 변경하시겠습니까? y/n");
				char option= input.next().charAt(0);
				if(option=='Y'||option=='y') {
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
					
					try {
						stmt = conn.createStatement();
						sql = "UPDATE board SET TITLE='"+titleUpdate+"',CONTENT='"+contentUpdate+"',AUTHOR='"+authorUpdate+"',NAL='"+nalUpdate+"',READCOUNT='"+readcountUpdate+"' WHERE TITLE='"+titleSearch+"'";
						int cnt=stmt.executeUpdate(sql);
						System.out.println(cnt+"긴 게시글이 수정되었습니다.");
						stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else{
					try {
						stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					continue;  // 반복문으로 올라감
				}
								
			} // 수정
			else if (protocol == 'L' || protocol == 'l') { // 목록(전체출력)
				System.out.println("===게시판 전체출력===");
				System.out.print("번호\t제목\t내용\t작성자\t날짜\t조회수\n");
				// 3.1 공간준비
				// 3.2 쿼리준비
				try {
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
						System.out.print(no + "\t" + title + "\t" + content + "\t" + author + "\t" + nal + "\t"
								+ readcount + "\n");
					}

					stmt.close();
					conn.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} // 목록

		} // 반복문

	}

}
