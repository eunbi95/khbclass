package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<HashMap<String, String>> haksaList = new ArrayList<HashMap<String, String>>();
		Scanner input = new Scanner(System.in);
		// 메모리 값을 관리할때 이걸 많이씀
		while (true) {
			System.out.println("R:등록L:전체출력");
			String protocol = input.next();
			if (protocol.equals("R") || protocol.equals("r")) {
				HashMap<String, String> haksaHash = new HashMap<String, String>(); // 이걸 넣어야 중복제거됨(HashMap을 돌릴때마다
																					// 만들어줘야함)
				System.out.println("나이입력:");
				String age = input.next();
				System.out.println("이름입력:");
				String irum = input.next();
				haksaHash.put("age", age);
				haksaHash.put("irum", irum);
				haksaList.add(haksaHash); // haksaList에 haksaHash를 넣는다
				System.out.println("학생이등록되었습니다.");
			} else if (protocol.equals("L") || protocol.equals("l")) {
				for (int i = 0; i < haksaList.size(); i++) {
					HashMap<String, String> haksaHash = haksaList.get(i);
					System.out.println(haksaHash.get("age")); // age가 '키'
					System.out.println(haksaHash.get("irum")); // irum이 '키'
				}
			}
		}

	}

}
