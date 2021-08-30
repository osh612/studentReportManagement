package kr.or.mrhi.studentReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//데이터베이스에 관련된 내용을 여기서 주관한다.(실행)
public class DBController {

	// 전화번호부 입력하기
	public static int studentReportInsertTBL(StudentReport studentReport) {
		// 데이타베이스에 연결
		Connection con = DBUtility.getConnection();
		PreparedStatement preparedStatement = null;
		int count = 0;
		String insertQuery = "CALL student_report_data.myCalculator(?,?,?,?,?)";

		try {
			preparedStatement = con.prepareStatement(insertQuery);

			preparedStatement.setString(1, studentReport.getStudentNumber());
			preparedStatement.setString(2, studentReport.getStudentName());
			preparedStatement.setInt(3, studentReport.getJava());
			preparedStatement.setInt(4, studentReport.getAndroid());
			preparedStatement.setInt(5, studentReport.getKotlin());

			count = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}

			} catch (SQLException e) {
			}
		}
		return count;
	}

}
