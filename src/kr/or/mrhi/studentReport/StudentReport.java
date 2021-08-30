package kr.or.mrhi.studentReport;

import java.util.Objects;

public class StudentReport {
	private String studentNumber;
	private String studentName;
	private int java;
	private int android;
	private int kotlin;
	private int totalScore;
	private double average;
	private String grade;

	public StudentReport(String studentNumber, String studentName, int java, int android, int kotlin, int totalScore,
			double average, String grade) {
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.java = java;
		this.android = android;
		this.kotlin = kotlin;
		this.totalScore = totalScore;
		this.average = average;
		this.grade = grade;
	}

	public StudentReport(String studentNumber, String studentName, int java, int android, int kotlin) {
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.java = java;
		this.android = android;
		this.kotlin = kotlin;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getJava() {
		return java;
	}

	public void setJava(int java) {
		this.java = java;
	}

	public int getAndroid() {
		return android;
	}

	public void setAndroid(int android) {
		this.android = android;
	}

	public int getKotlin() {
		return kotlin;
	}

	public void setKotlin(int kotlin) {
		this.kotlin = kotlin;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StudentReport) {
			StudentReport studentReport = (StudentReport) obj;
			return this.studentNumber.equals(studentReport.getStudentNumber());
		}

		return true;
	}

}
