package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<HashMap<String, String>> haksaList = new ArrayList<HashMap<String, String>>();
		Scanner input = new Scanner(System.in);
		// �޸� ���� �����Ҷ� �̰� ���̾�
		while (true) {
			System.out.println("R:���L:��ü���");
			String protocol = input.next();
			if (protocol.equals("R") || protocol.equals("r")) {
				HashMap<String, String> haksaHash = new HashMap<String, String>(); // �̰� �־�� �ߺ����ŵ�(HashMap�� ����������
																					// ����������)
				System.out.println("�����Է�:");
				String age = input.next();
				System.out.println("�̸��Է�:");
				String irum = input.next();
				haksaHash.put("age", age);
				haksaHash.put("irum", irum);
				haksaList.add(haksaHash); // haksaList�� haksaHash�� �ִ´�
				System.out.println("�л��̵�ϵǾ����ϴ�.");
			} else if (protocol.equals("L") || protocol.equals("l")) {
				for (int i = 0; i < haksaList.size(); i++) {
					HashMap<String, String> haksaHash = haksaList.get(i);
					System.out.println(haksaHash.get("age")); // age�� 'Ű'
					System.out.println(haksaHash.get("irum")); // irum�� 'Ű'
				}
			}
		}

	}

}
