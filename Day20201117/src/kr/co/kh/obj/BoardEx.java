
package kr.co.kh.obj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// �ν��Ͻ� �޼ҵ�� ���� -> �ݵ�� new�� ��ü ������ �ҷ�����, static�� �޸� ���� ���� ���ϴ�.
public class BoardEx {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Register register = new Register();
		Search search = new Search();
		Delete delete = new Delete();
		Update update = new Update();
		List list = new List();
		Connection conn = null; // Ŭ����(�빮�ڷν���) �ʱⰪ�� ������ null

		while (true) { // �ݺ���
			// 2.����(Connection)�����ϴ�. DriverManager.getConnection

			try {
				conn = register.getConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("===�Խ����ۼ�===");
			System.out.println("R:��� S:�˻� D:���� U:���� L:���");
			char protocol = input.next().charAt(0);
			if (protocol == 'R' || protocol == 'r') { // ���
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
				} finally { // �����Ű��
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // ���
			else if (protocol == 'S' || protocol == 's') { // �˻�
				try {
					search.setTitleSearch();
					search.boardSearchTitle();
					search.boardStmtSql();
					search.boardSearchExecuter();
					search.boardSerarchProcess();
					search.boardSerarchReadcount();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally { // ���� �����Ű��
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // �˻�
			else if (protocol == 'D' || protocol == 'd') { // ����
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
			} // ����
			else if (protocol == 'U' || protocol == 'u') { // ����
				char option = 'n';
				try {
					update.boardUpdateTitle();
					update.boardUpdateSearch();
					update.boardUpdateExecuterSql();
					update.boardUpdateOld();
					option = update.boardUpdateOption();
					if (option == 'n' || option == 'N') {
						continue; // �ݺ������� �ö�
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
			} // ����
			else if (protocol == 'L' || protocol == 'l') { // ���(��ü���)
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
