package kr.co.kh;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BoardEx {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		// 1.�ε�(����=�ڹٿ��� ���� �����ͺ��̽��� �����ڴ�.)

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection conn = null;

		while (true) { // �ݺ���
			// 2.����(Connection)�����ϴ�. DriverManager.getConnection

			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("===�Խ����ۼ�===");
			System.out.println("R:��� S:�˻� D:���� U:���� L:���");
			char protocol = input.next().charAt(0);
			if (protocol == 'R' || protocol == 'r') { // ���
				// 01 234
				System.out.println("����|����:");
				String titleContent = input.next();
				int indexI = titleContent.indexOf("|"); // "|"��ġ�� 2 -> indexI=2
				String title = titleContent.substring(0, indexI);
				String content = titleContent.substring(indexI + 1);
				System.out.println("�ۼ����Է�:");
				String author = input.next();
				System.out.println("��¥:");
				String nal = input.next();
				System.out.println("��ȸ��:");
				int readcount = input.nextInt();

				try {
					// 3.�غ�(Statement) 3-1 ������ �غ�
					// 3.�غ� 3-1 �����غ�
					Statement stmt = conn.createStatement();
					String sql = "insert into board(no,title,content,author,nal,readcount) values(board_no.nextval,'"
							+ title + "','" + content + "','" + author + "','" + nal + "'," + readcount + ")";
					// 4.���� execute
					int cnt = stmt.executeUpdate(sql);
					System.out.println(cnt + "�� �Խñ��� ��ϵǾ����ϴ�.");
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // ���
			else if (protocol == 'S' || protocol == 's') { // �˻�
				System.out.println("ã�� �Խñ� �����Է�:");
				String titleSearch = input.next();
				System.out.print("��ȣ\t����\t����\t�ۼ���\t��¥\t��ȸ��\n");
				// 3.�غ�
				try {
					Statement stmt = conn.createStatement();
					String sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE='" + titleSearch
							+ "'"; // WHERE TITLE='"+titleSearch+"'" ->���ǹ�
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
						// rs�� �������̽��̸� ǥ�ȿ��ִ� �����Ͱ� ����Ŵ

						System.out.print(no + "\t" + title + "\t" + content + "\t" + author + "\t" + nal + "\t"
								+ readcount + "\n");
						System.out.println(cnt+"�� �Խñ��� �˻�");
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

			} // �˻�
			else if (protocol == 'D' || protocol == 'd') { // ����
				System.out.println("������ ������ �Է�:");
				String tileDelete = input.next();
				// 3.�غ�
				// 1)������ �غ��Ѵ�.
				// 2)������ �غ��Ѵ�.
				try {
					Statement stmt = conn.createStatement();
					String sql = "delete from board where title='" + tileDelete + "'";
					int cnt = stmt.executeUpdate(sql);
					System.out.println(cnt + "�� �Խñ��� ����");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} // ����
			else if (protocol == 'U' || protocol == 'u') { // ����
				System.out.println("������ �Խñ� ���� �Է�");
				String titleSearch = input.next();
				Statement stmt = null; // null�� �� ����
				String sql=null;
				try {
					stmt = conn.createStatement();
					sql = "SELECT TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE='"+titleSearch+"'";

					ResultSet rs=stmt.executeQuery(sql);
					System.out.println("===�����ϱ� �� �Խñ��Դϴ�===");
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
				System.out.println("������ �����Ͻðڽ��ϱ�? y/n");
				char option= input.next().charAt(0);
				if(option=='Y'||option=='y') {
					System.out.println("����|����:");
					String titleContent = input.next();
					int indexI = titleContent.indexOf("|"); // "|"��ġ�� 2 -> indexI=2
					String titleUpdate = titleContent.substring(0, indexI);
					String contentUpdate = titleContent.substring(indexI + 1);
					System.out.println("�ۼ����Է�:");
					String authorUpdate = input.next();
					System.out.println("��¥:");
					String nalUpdate = input.next();
					System.out.println("��ȸ��:");
					int readcountUpdate = input.nextInt();
					
					try {
						stmt = conn.createStatement();
						sql = "UPDATE board SET TITLE='"+titleUpdate+"',CONTENT='"+contentUpdate+"',AUTHOR='"+authorUpdate+"',NAL='"+nalUpdate+"',READCOUNT='"+readcountUpdate+"' WHERE TITLE='"+titleSearch+"'";
						int cnt=stmt.executeUpdate(sql);
						System.out.println(cnt+"�� �Խñ��� �����Ǿ����ϴ�.");
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
					continue;  // �ݺ������� �ö�
				}
								
			} // ����
			else if (protocol == 'L' || protocol == 'l') { // ���(��ü���)
				System.out.println("===�Խ��� ��ü���===");
				System.out.print("��ȣ\t����\t����\t�ۼ���\t��¥\t��ȸ��\n");
				// 3.1 �����غ�
				// 3.2 �����غ�
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

			} // ���

		} // �ݺ���

	}

}