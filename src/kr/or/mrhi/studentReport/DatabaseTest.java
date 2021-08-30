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

		// 메뉴선택
		while (!flag) {
			// 메뉴출력 및 번호선택
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
				System.out.println("숫자범위초과 다시입력요망");
				break;
			}// end of switch
		} // end of while

		System.out.println("프로그램 종료!");
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
			System.out.print("학번 입력 >>");
			studentNumber = scan.nextLine();
			if (studentNumber.length() != 10) {
				System.out.println("유효하지 않은 학번입니다. 다시 입력해주세요.");
				continue;
			}
			
			scan.nextLine();
			break;
		} // end of while

		while (true) {
			System.out.print("이름 입력 >>");
			studentName = scan.nextLine();
			if (studentName.length() < 2 || studentName.length() > 7) {
				System.out.println("이름 다시 입력해주세요");
				continue;
			} else {
				break;
			}
		} // end of while

		while (true) {
			System.out.print("Java 점수 입력 >>");
			java = scan.nextInt();
			if (java < 0 || java > 100) {
				System.out.println("유효하지 않은 성적입니다. 다시 입력해주세요.");
				continue;
			} else {
				scan.nextLine();
				break;
			}
		} // end of while

		while (true) {
			System.out.print("Android 점수 입력 >>");
			android = scan.nextInt();
			if (android < 0 || android > 100) {
				System.out.println("유효하지 않은 성적입니다. 다시 입력해주세요.");
				continue;
			} else {
				scan.nextLine();
				break;
			}
		} // end of while

		while (true) {
			System.out.print("Kotlin 점수 입력 >>");
			kotlin = scan.nextInt();
			if (kotlin < 0 || kotlin > 100) {
				System.out.println("유효하지 않은 성적입니다. 다시 입력해주세요.");
				continue;
			} else {
				scan.nextLine();
				break;
			}
		} // end of while
		
		StudentReport studentReport = new StudentReport(studentNumber, studentName, java, android, kotlin);
		
		int count = DBController.studentReportInsertTBL(studentReport);

		if (count != 0) {
			System.out.println(studentReport.getStudentName() + "님 삽입성공");
		} else {
			System.out.println(studentReport.getStudentName() + "님 삽입실패");
		}

	}

	private static void studentReportUpdate() {
		// TODO Auto-generated method stub

	}

	private static void studentReportDelete() {
		// TODO Auto-generated method stub

	}

	// 메뉴 출력 및 번호선택하기
	private static int displayMenu() {

		int selectNumber = 0;
		boolean flag = false;
		while (!flag) {
			System.out.println("┏━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃  1. 데이터 조회     2. 데이터 입력     3. 데이터 수정     4. 데이터 삭제     5. 프로그램 종료  ┃");
			System.out.println("┗━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━┛");
			System.out.print("   번호선택>>");
			// 번호선택
			try {
				selectNumber = Integer.parseInt(scan.nextLine());
			} catch (InputMismatchException e) {
				System.out.println("숫자 입력 요망");
				continue;
			} catch (Exception e) {
				System.out.println("숫자 입력 문제발생 재입력요망");
				continue;
			}
			break;
		}
		return selectNumber;
	}

}
