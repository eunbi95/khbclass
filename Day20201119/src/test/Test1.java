package test;

import java.util.ArrayList;
import java.util.Scanner;

// �߿��� get,set�޼ҵ��, @Override:�������� ->������(����������� �ൿ���ٸ���)
public class Test1 {

	public static void main(String[] args) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println("R:���L:��ü���");
			String protocol = input.next();
			if (protocol.equals("r")) {
				Student Student = new Student(); // �̰� �� ���� ������,�ߺ�����(��ü����)
				System.out.println("����");
				String nai = input.next();
				System.out.println("�̸�");
				String irum = input.next();
				Student.setNai(nai);
				Student.setIrum(irum);
				studentList.add(Student);
				System.out.println(irum +" �л��̵�ϵǾ����ϴ�.");
				System.out.println();
			} else if (protocol.equals("l")) {
				for (int i = 0; i < studentList.size(); i++) {
					Student student = studentList.get(i);
//					System.out.println(student);	
					System.out.println(student.getNai());
					System.out.println(student.getIrum());
					
			}
		}
	}
	}
}
