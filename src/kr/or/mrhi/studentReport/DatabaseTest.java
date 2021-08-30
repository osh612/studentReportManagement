package kr.or.mrhi.studentReport;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DatabaseTest {
	public static final Scanner scan = new Scanner(System.in);
	public static final int INQUIRY = 1, INSERT = 2, UPDATE = 3, DELETE = 4, EXIT = 5;

	public static void main(String[] args) throws IOException {
		boolean flag = false;
		int selectNumber = 0;

		// �޴�����
		while (!flag) {
			// �޴���� �� ��ȣ����
			selectNumber = displayMenu();

			switch (selectNumber) {
			case INQUIRY:
				studentReportInquiry(); // Inquire student report data
				break;
			case INSERT:
				studentReportInsert(); // Insert student report data
				break;
			case UPDATE:
				studentReportUpdate(); // Update student report data
				break;
			case DELETE:
				studentReportDelete(); // Delete student report data
				break;
			case EXIT:
				flag = true;
				break;
			default:
				System.out.println("���ڹ����ʰ� �ٽ��Է¿��");
				break;
			}// end of switch
		} // end of while

		System.out.println("���α׷� ����!");
	}// end of main

	private static void studentReportInquiry() {

	}

	private static void studentReportInsert() {
		final int SUBJECT = 3;
		String studentNumber = null;
		String studentName = null;
		int java = 0;
		int android = 0;
		int kotlin = 0;
		int totalScore = 0;
		double average = 0.0;
		String grade = null;

		while (true) {
			System.out.print("�й� �Է� >>");
			studentNumber = scan.nextLine();
			if (studentNumber.length() != 10) {
				System.out.println("��ȿ���� ���� �й��Դϴ�. �ٽ� �Է����ּ���.");
				continue;
			}
			
			scan.nextLine();
			break;
		} // end of while

		while (true) {
			System.out.print("�̸� �Է� >>");
			studentName = scan.nextLine();
			if (studentName.length() < 2 || studentName.length() > 7) {
				System.out.println("�̸� �ٽ� �Է����ּ���");
				continue;
			} else {
				break;
			}
		} // end of while

		while (true) {
			System.out.print("Java ���� �Է� >>");
			java = scan.nextInt();
			if (java < 0 || java > 100) {
				System.out.println("��ȿ���� ���� �����Դϴ�. �ٽ� �Է����ּ���.");
				continue;
			} else {
				scan.nextLine();
				break;
			}
		} // end of while

		while (true) {
			System.out.print("Android ���� �Է� >>");
			android = scan.nextInt();
			if (android < 0 || android > 100) {
				System.out.println("��ȿ���� ���� �����Դϴ�. �ٽ� �Է����ּ���.");
				continue;
			} else {
				scan.nextLine();
				break;
			}
		} // end of while

		while (true) {
			System.out.print("Kotlin ���� �Է� >>");
			kotlin = scan.nextInt();
			if (kotlin < 0 || kotlin > 100) {
				System.out.println("��ȿ���� ���� �����Դϴ�. �ٽ� �Է����ּ���.");
				continue;
			} else {
				scan.nextLine();
				break;
			}
		} // end of while
		
		StudentReport studentReport = new StudentReport(studentNumber, studentName, java, android, kotlin);
		
		int count = DBController.studentReportInsertTBL(studentReport);

		if (count != 0) {
			System.out.println(studentReport.getStudentName() + "�� ���Լ���");
		} else {
			System.out.println(studentReport.getStudentName() + "�� ���Խ���");
		}

	}

	private static void studentReportUpdate() {
		// TODO Auto-generated method stub

	}

	private static void studentReportDelete() {
		// TODO Auto-generated method stub

	}

	// �޴� ��� �� ��ȣ�����ϱ�
	private static int displayMenu() {

		int selectNumber = 0;
		boolean flag = false;
		while (!flag) {
			System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
			System.out.println("��  1. ������ ��ȸ     2. ������ �Է�     3. ������ ����     4. ������ ����     5. ���α׷� ����  ��");
			System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
			System.out.print("   ��ȣ����>>");
			// ��ȣ����
			try {
				selectNumber = Integer.parseInt(scan.nextLine());
			} catch (InputMismatchException e) {
				System.out.println("���� �Է� ���");
				continue;
			} catch (Exception e) {
				System.out.println("���� �Է� �����߻� ���Է¿��");
				continue;
			}
			break;
		}
		return selectNumber;
	}

}