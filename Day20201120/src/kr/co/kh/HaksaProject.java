package kr.co.kh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HaksaProject {

	public static void main(String[] args) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("====�л����====");
			System.out.println("1.���");
			System.out.println("2.�˻�");
			System.out.println("3.����");
			System.out.println("4.��ü���");
			System.out.println("=============");
			System.out.println("0.����");
			System.out.println("��ȣ�� ������ �ּ���..");
			String protocol = null;
			Connection conn = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","khbclass","dkdlxl");
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				protocol = input.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (protocol.equals("1")) {
				System.out.println("====�޴�����====");
				System.out.println("1.�л�");
				System.out.println("2.����");
				System.out.println("3.������");
				System.out.println("4.�����޴�");
				System.out.println("��ȣ�� ������ �ּ���..");
				try {
					protocol = input.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if (protocol.equals("1")) {  //--------------�л�
					System.out.println("�л����");
					try {
						System.out.println("����:");
						String age1 = input.readLine();
						System.out.println("�̸�:");
						String irum = input.readLine();
						System.out.println("�й�:");
						String hakbun1 = input.readLine();
						int cnt=0;
						try {
							Statement stmt=conn.createStatement();  //���ڿ� ���ڷιٲٱ�
							int age=Integer.parseInt(age1);
							int hakbun=Integer.parseInt(hakbun1);
							String sql = "insert into student(no,age,irum,hakbun) values(student_no.nextval,"+age+",'"+irum+"',"+hakbun+")";
							cnt = stmt.executeUpdate(sql);
							System.out.println(cnt+"�� �л��� ��ϵǾ����ϴ�.");
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} else if (protocol.equals("2")) {  //--------------����
					System.out.println("�������");
					try {
						System.out.println("����:");
						String age1 = input.readLine();
						System.out.println("�̸�:");
						String irum = input.readLine();
						System.out.println("����:");
						String subject = input.readLine();
						int cnt=0;
						try {
							Statement stmt = conn.createStatement();
							int age=Integer.parseInt(age1);
							String sql ="insert into professor(no,age,irum,subject) values(professor_no.nextval,"+age+",'"+irum+"','"+subject+"')";
							cnt=stmt.executeUpdate(sql);
							System.out.println(cnt+"�� �������� ��ϵǾ����ϴ�.");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}	
					
				} else if (protocol.equals("3")) { //--------------������
					System.out.println("�����ڵ��");
					try {
						System.out.println("����:");
						String age1 = input.readLine();
						System.out.println("�̸�:");
						String irum = input.readLine();
						System.out.println("�μ�:");
						String part = input.readLine();
						int cnt=0;
						try {
							Statement stmt = conn.createStatement();
							int age=Integer.parseInt(age1);
						String sql= "insert into manager(no,age,irum,part) values(manager_no.nextval,"+age+",'"+irum+"','"+part+"')";
						cnt=stmt.executeUpdate(sql);
						System.out.println(cnt+"�� �����ڰ� ��ϵǾ����ϴ�.");
					} catch (SQLException e) {
						e.printStackTrace();
					} 
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} else if (protocol.equals("4")) { //--------------�����޴�
					continue;
				} 
				
				//1�ϸ���0�ϸ鳡
				System.out.println("����Ͻ÷��� 1, �����Ͻ÷���0�� �Է����ּ���..");
				try {
					String cnt = input.readLine();
					if(cnt.equals("1")) {
						continue;
					} else {
						System.exit(0);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
								
			}else if(protocol.equals("2")) {
				System.out.println("ã��");
			}else if(protocol.equals("3")) {
				System.out.println("����");
			}else if(protocol.equals("4")) {
				System.out.println("��ü���");
				try {
					Statement stmt =conn.createStatement();
					String sql = "select no,age,irum,hakbun from student";
					ResultSet rs=stmt.executeQuery(sql);  //rs�� ���̺� ����Ŵ
					while(rs.next()) {
						int no=rs.getInt("no");
						int age=rs.getInt("age");
						String irum=rs.getString("irum");
						int hakbun=rs.getInt("hakbun");
						System.out.print("�̸�:");  //sysout
						System.out.print(irum+"\t");
						System.out.print("����:");
						System.out.print(age+"\t");
						System.out.print("�й�:");
						System.out.print(hakbun+"\n");					
					}
					
					stmt = conn.createStatement(); //������ �����ϱ� ���ص���, ������ �Ҷ����� ���� ��������
					sql = "select no,age,irum,subject from professor";
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						int no=rs.getInt("no");
						int age=rs.getInt("age");
						String irum=rs.getString("irum");
						String subject = rs.getString("subject");
						System.out.print("�̸�:");  //no�� �Ƚᵵ��
						System.out.print(irum+"\t");
						System.out.print("����:");
						System.out.print(age+"\t");
						System.out.print("����:");
						System.out.print(subject+"\n");					
					}
					
					stmt = conn.createStatement();
					sql = "select no,age,irum,part from manager";
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						int no=rs.getInt("no");
						int age=rs.getInt("age");
						String irum=rs.getString("irum");
						String part = rs.getString("part");
						System.out.print("�̸�:");  //no�� �Ƚᵵ��
						System.out.print(irum+"\t");
						System.out.print("����:");
						System.out.print(age+"\t");
						System.out.print("�μ�:");
						System.out.print(part+"\n");			
					}							
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}else if(protocol.equals("0")) {				
				System.out.println("�л����α׷��� �����մϴ�.");
				System.exit(0);
			}
		} // �ݺ���
	}
}
