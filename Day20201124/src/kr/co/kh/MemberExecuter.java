package kr.co.kh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberExecuter {
	// **�α���ó�� --> session
	public static String session;
	static {
		session = null;
	}// �α���ó��

	public static void main(String[] args) {
		// ù���ڰ� �빮�ڸ� ������ null
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		Connection conn = null;
		String protocol = null;
		Statement stmt = null;
		String sql = null;
		String idlogin=null;
		ResultSet rs =null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		while (true) { //�ݺ���
			System.out.println("====ȸ������ �α��� ������Ʈ====");
			System.out.println("R:ȸ������ L:ȸ����� S:IDã�� D:ȸ��Ż�� E:ȸ������ I:�α��� O:�α׾ƿ�");
			try {
				protocol = input.readLine();
				if (protocol.equals("R") || protocol.equals("r")) { // ---------����
					try {
						stmt = conn.createStatement();
						sql = "select id from member";
						rs = stmt.executeQuery(sql);
						while(rs.next()) {
							idlogin=rs.getString("id");
						} // **�α����� �Ǿ��ִµ� ȸ�������Ҽ�����**
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			/*		if(idlogin.equals(session)) {
						System.out.println("�α������Դϴ�.");
						continue;
					}*/
					System.out.println("���̵��Է�:");
					String id = input.readLine();
					System.out.println("�н������Է�:");
					String pw = input.readLine();
					System.out.println("�ּ��Է�:");
					String addr = input.readLine();
					System.out.println("��ȭ��ȣ�Է�:");
					String tel = input.readLine();
					int cnt=0;
					try {
						stmt = conn.createStatement();
						sql = "insert into member(id,pw,addr,tel) values('"+id+"','"+pw+"','"+addr+"','"+tel+"')";
						cnt = stmt.executeUpdate(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println(cnt + "�� ȸ���� ��ϵǾ����ϴ�.");
					
				} // ���Գ�
				else if (protocol.equals("S") || protocol.equals("s")) {
				} else if (protocol.equals("D") || protocol.equals("d")) {
				} else if (protocol.equals("E") || protocol.equals("e")) {
				} else if (protocol.equals("I") || protocol.equals("i")) {
					System.out.println("���̵��Է�:");
					String loginId = input.readLine();
					System.out.println("�н������Է�:");
					String loginPw = input.readLine();
					String id = null;
					String pw = null;
					try {
						stmt = conn.createStatement();
						sql = "select id,pw from member";
						rs = stmt.executeQuery(sql);
						while(rs.next()) {
							id=rs.getString("id");
							pw=rs.getString("pw");									
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(!(loginId.equals("id"))) {
						System.out.println("���̵�Ʋ�Ƚ��ϴ�.");
						continue;
					}
					if(!(loginId.equals("pw"))) {
						System.out.println("�н����尡Ʋ�Ƚ��ϴ�.");
						continue;
					}
					System.out.println("ȯ���մϴ�.�α��εǾ����ϴ�.");
					session=loginId;



					
				} else if (protocol.equals("O") || protocol.equals("o")) {
				}

				else if (protocol.equals("L") || protocol.equals("l")) { // ---------���(��ü���)
			
				} // ��ϳ�

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
