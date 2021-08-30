package kr.or.mrhi.studentReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import mirha.DBUtility;
import mirha.PhoneBook;

public class StudentManager {
	public static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Vector<StudentMemberTBL> list = new Vector<>();
		boolean flag = false;
		int select = 0;

		while (!flag) {

			disPlayMenu();

			try {
				select = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				System.out.println("���ڸ� �Է����ּ���");
				continue;
			}

			switch (select) {
			case 1:
				insertStudentMember();
				break;
			case 2:
				updateStudent();
				break;
			case 3:
				int selectNumber = 0;
				String studentName = null;
				while (true) {
					System.out.print("�˻��� �л� >>");
					try {
						studentName = scan.nextLine();
						if (studentName.length() <= 1 || studentName.length() >= 5) {
							System.out.println("�߸��Է��ϼ̽��ϴ�.");
							break;
						} else {
							break;
						}
					} catch (Exception e) {
						System.out.println("�̸��� �Է����ּ���");
						return;
					}
				}

				Connection con = StudentManagerServer.getConnection();
				PreparedStatement preparedStatement = null;
				String searchQuery = "select * from studentMemberTBL where studentName = ?";
				ResultSet resultSet = null;

				try {
					Vector<StudentMemberTBL> searchlist = new Vector<>();
					preparedStatement = con.prepareStatement(searchQuery);
					preparedStatement.setString(1, studentName);
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						String Name = resultSet.getString(1);
						int math = resultSet.getInt(2);
						int english = resultSet.getInt(3);
						int korean = resultSet.getInt(4);
						int totalScore = resultSet.getInt(5);
						double average = resultSet.getDouble(6);

						StudentMemberTBL studentMemberTBL = new StudentMemberTBL(Name, math, english, korean,
								totalScore, average);
						searchlist.add(studentMemberTBL);
					}
					System.out.println("=============================================");
					System.out.println("�̸�\t����\t����\t����\t����\t��� ");
					System.out.println("=============================================");
					for (StudentMemberTBL smt : searchlist) {
						System.out.println(smt.toString());
					}
				} catch (Exception e) {
					System.out.println("�˻� �����Դϴ�.");
					return;
				}

				break;
			case 4:
				studentTotalScore();
				break;
			case 5:
				deleteStudent();
				break;
			case 6:
				System.out.println("�����մϴ�");
				flag = true;
				break;
			default:
				System.out.println("���ڸ� �������ּ���.");
				break;

			}// end of switch
		} // end of while
	}// end of main

	private static void deleteStudent() {
		System.out.print("������ ����� �̸� >>");
		String deleteName = scan.nextLine();

		Connection con = StudentManagerServer.getConnection();
		String deletQuery = "delete from studentMemberTBL where studentName = ? ";
		PreparedStatement preparedStatement = null;
		int count = 0;

		try {
			preparedStatement = con.prepareStatement(deletQuery);
			preparedStatement.setString(1, deleteName);
			count = preparedStatement.executeUpdate();
			if (count != 0) {
				System.out.println(deleteName + "�� ������ �Ϸ�Ǿ����ϴ�.");
			} else {
				System.out.println(deleteName + "���� �������� �ʰų� �߸��Է��ϼ̽��ϴ�.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void updateStudent() {
		String studentName = null;
		int selectNumber = 0;
		String updateQuery = null;
		int math = 0;
		int english = 0;
		int korean = 0;
		int totalScore = 0;
		double average = 0.0;
		Connection con = null;
		PreparedStatement prepareStatement = null;

		while (true) {
			System.out.print("������ �����Ͻðڽ��ϱ�? >>");
			studentName = scan.nextLine();
			if (studentName.length() >= 5 || studentName.length() <= 1) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�");
				return;
			} else {
				break;
			}
		}
		System.out.println("===============");
		System.out.println("1.�̸� 2.���� ");
		System.out.println("===============");
		System.out.print("���� >>");
		try {
			selectNumber = Integer.parseInt(scan.nextLine());
		} catch (Exception e) {
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
		}

		switch (selectNumber) {
		case 1:
			System.out.print("������ �̸� >> ");
			String afterName = scan.nextLine();

			con = StudentManagerServer.getConnection();
			updateQuery = "UPDATE studentMemberTBL set studentName = ? where studentName = ? ";
			prepareStatement = null;

			try {
				prepareStatement = con.prepareStatement(updateQuery);
				prepareStatement.setString(1, afterName);
				prepareStatement.setString(2, studentName);
				int count = prepareStatement.executeUpdate();
				if (count != 0) {
					System.out.println("����Ϸ�");
					return;
				} else {
					System.out.println("�������");
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		case 2:
			while (true) {
				System.out.print("�������� >> ");
				try {
					math = Integer.parseInt(scan.nextLine());
					if (math >= 0 && math <= 100) {
						break;
					} else {
						continue;
					}
				} catch (Exception e) {
					System.out.println("�߸��Է��ϼ̽��ϴ�.");
					return;
				}
			}
			while (true) {
				System.out.print("�������� >> ");
				try {
					english = Integer.parseInt(scan.nextLine());
					if (english >= 0 && english <= 100) {
						break;
					} else {
						continue;
					}
				} catch (Exception e) {
					System.out.println("�߸��Է��ϼ̽��ϴ�.");
					return;
				}
			}
			while (true) {
				System.out.print("�������� >> ");
				try {
					korean = Integer.parseInt(scan.nextLine());
					if (korean >= 0 && korean <= 100) {
						break;
					} else {
						continue;
					}
				} catch (Exception e) {
					System.out.println("�߸��Է��ϼ̽��ϴ�.");
					return;
				}
			}

			con = StudentManagerServer.getConnection();
			updateQuery = "UPDATE studentMemberTBL set math = ? ,english = ?, korean = ?, totalScore =? , average = ? where studentName = ?";

			totalScore = math + english + korean;

			average = totalScore / 3.0;

			try {
				prepareStatement = con.prepareStatement(updateQuery);
				prepareStatement.setInt(1, math);
				prepareStatement.setInt(2, english);
				prepareStatement.setInt(3, korean);
				prepareStatement.setInt(4, totalScore);
				prepareStatement.setDouble(5, average);
				prepareStatement.setString(6, studentName);
				int count = prepareStatement.executeUpdate();
				if (count != 0) {
					System.out.println("����Ϸ�");
				} else {
					System.out.println("�������");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void studentTotalScore() {
		Vector<StudentMemberTBL> list = new Vector<>();
		Connection con = StudentManagerServer.getConnection();
		String insertQuery = "select * from studentMemberTBL order by totalScore desc";
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {
			prepareStatement = con.prepareStatement(insertQuery);
			resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {

				String studentName = resultSet.getString(1);
				int math = resultSet.getInt(2);
				int english = resultSet.getInt(3);
				int korean = resultSet.getInt(4);
				int totalScore = resultSet.getInt(5);
				double average = resultSet.getDouble(6);

				list.add(new StudentMemberTBL(studentName, math, english, korean, totalScore, average));
			}
			if (list.size() <= 0) {
				System.out.println("����� �����Ͱ� ���ų� �˻����� ����");
				return;
			}
			System.out.println("=============================================");
			System.out.println("�̸�\t����\t����\t����\t����\t��� ");
			System.out.println("=============================================");
			for (StudentMemberTBL smt : list) {
				System.out.println(smt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void insertStudentMember() {
		Vector<StudentMemberTBL> list = new Vector<>();
		String studentName = null;
		int math = 0;
		int english = 0;
		int korean = 0;
		int totalScore = 0;
		double average = 0.0;
		int rank = 0;

		while (true) {
			System.out.print("�л��� �̸��� �Է����ּ��� >> ");
			studentName = scan.nextLine();
			if (studentName.length() < 2 || studentName.length() > 7) {
				System.out.println("�̸��� �ٽ� �Է����ּ���");
				continue;
			} else {
				break;
			}
		}

		while (true) {
			System.out.print("�������� >>");
			try {
				math = Integer.parseInt(scan.nextLine());
				if (math > 100) {
					System.out.println("�ٽ� �Է����ּ���");
					continue;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				continue;
			}
		}
		while (true) {
			System.out.print("�������� >> ");
			try {
				english = Integer.parseInt(scan.nextLine());
				if (english > 100) {
					System.out.println("�ٽ� �Է����ּ���");
					continue;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				continue;
			}
		}
		while (true) {
			System.out.print("�ѱ������� >> ");
			try {
				korean = Integer.parseInt(scan.nextLine());
				if (korean > 100) {
					System.out.println("�ٽ� �Է����ּ���");
					continue;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				continue;
			}
		}

		totalScore = (math + english + korean);

		average = (totalScore / (double) 3.0);

		StudentMemberTBL studentMemberTBL = new StudentMemberTBL(studentName, math, english, korean, totalScore,
				average);
		Connection con = StudentManagerServer.getConnection();
		String insertQuery = "insert into studentMemberTBL(studentName, math, english, korean, totalScore,average) values(?,?,?,?,?,?)";
		PreparedStatement prepareStatement = null;

		try {

			prepareStatement = con.prepareStatement(insertQuery);
			prepareStatement.setString(1, studentMemberTBL.getStudentName());
			prepareStatement.setInt(2, studentMemberTBL.getMath());
			prepareStatement.setInt(3, studentMemberTBL.getEnglish());
			prepareStatement.setInt(4, studentMemberTBL.getKorean());
			prepareStatement.setInt(5, studentMemberTBL.getTotalScore());
			prepareStatement.setDouble(6, studentMemberTBL.getAverage());
			int count = prepareStatement.executeUpdate();
			if (count != 0) {
				System.out.println(studentMemberTBL.getStudentName() + "�� ���Լ���");
			} else {
				System.out.println(studentMemberTBL.getStudentName() + "�� ���Խ���");
				return;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void disPlayMenu() {
		System.out.println("\n\n�����������������������������������������������������");
		System.out.println("��� 1.�Է� |  2.���� | 3.�˻� | 4.��ü | 5.���� | 6.���� ���");
		System.out.println("�����������������������������������������������������");
		System.out.print("��ȣ���� >> ");
	}

}