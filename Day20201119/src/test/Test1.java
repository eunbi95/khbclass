package test;

import java.util.ArrayList;
import java.util.Scanner;

// 중요함 get,set메소드로, @Override:유지보수 ->다형성(사람은같은데 행동은다른것)
public class Test1 {

	public static void main(String[] args) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println("R:등록L:전체출력");
			String protocol = input.next();
			if (protocol.equals("r")) {
				Student Student = new Student(); // 이건 꼭 여기 들어가야함,중복제거(객체생성)
				System.out.println("나이");
				String nai = input.next();
				System.out.println("이름");
				String irum = input.next();
				Student.setNai(nai);
				Student.setIrum(irum);
				studentList.add(Student);
				System.out.println(irum +" 학생이등록되었습니다.");
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
