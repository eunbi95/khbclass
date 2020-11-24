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
	// **로그인처리 --> session
	public static String session;
	static {
		session = null;
	}// 로그인처리

	public static void main(String[] args) {
		// 첫글자가 대문자면 무조건 null
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

		while (true) { //반복문
			System.out.println("====회원가입 로그인 프로젝트====");
			System.out.println("R:회원가입 L:회원목록 S:ID찾기 D:회원탈퇴 E:회원수정 I:로그인 O:로그아웃");
			try {
				protocol = input.readLine();
				if (protocol.equals("R") || protocol.equals("r")) { // ---------가입
					try {
						stmt = conn.createStatement();
						sql = "select id from member";
						rs = stmt.executeQuery(sql);
						while(rs.next()) {
							idlogin=rs.getString("id");
						} // **로그인이 되어있는데 회원가입할수없음**
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			/*		if(idlogin.equals(session)) {
						System.out.println("로그인중입니다.");
						continue;
					}*/
					System.out.println("아이디입력:");
					String id = input.readLine();
					System.out.println("패스워드입력:");
					String pw = input.readLine();
					System.out.println("주소입력:");
					String addr = input.readLine();
					System.out.println("전화번호입력:");
					String tel = input.readLine();
					int cnt=0;
					try {
						stmt = conn.createStatement();
						sql = "insert into member(id,pw,addr,tel) values('"+id+"','"+pw+"','"+addr+"','"+tel+"')";
						cnt = stmt.executeUpdate(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println(cnt + "건 회원이 등록되었습니다.");
					
				} // 가입끝
				else if (protocol.equals("S") || protocol.equals("s")) {
				} else if (protocol.equals("D") || protocol.equals("d")) {
				} else if (protocol.equals("E") || protocol.equals("e")) {
				} else if (protocol.equals("I") || protocol.equals("i")) {
					System.out.println("아이디입력:");
					String loginId = input.readLine();
					System.out.println("패스워드입력:");
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
						System.out.println("아이디가틀렸습니다.");
						continue;
					}
					if(!(loginId.equals("pw"))) {
						System.out.println("패스워드가틀렸습니다.");
						continue;
					}
					System.out.println("환영합니다.로그인되었습니다.");
					session=loginId;



					
				} else if (protocol.equals("O") || protocol.equals("o")) {
				}

				else if (protocol.equals("L") || protocol.equals("l")) { // ---------목록(전체출력)
			
				} // 목록끝

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
