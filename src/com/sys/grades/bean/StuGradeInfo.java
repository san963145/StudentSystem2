package com.sys.grades.bean;

    /**
	 * ѧ��ԭʼ�ɼ���
	 * 
	 */
public class StuGradeInfo {

	//��־����
	private int id;
	// ѧ��
	private String sno;
	// ѧ��
	private int semester;
	// ѧ��
	private double coursecredit;
	// �����ɼ�
	private String grade;
	// ѡ��
	private String elective;
	//��ʦ
	private String teachername;
	//�������
	private String testtype;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public double getCoursecredit() {
		return coursecredit;
	}

	public void setCoursecredit(double coursecredit) {
		this.coursecredit = coursecredit;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getElective() {
		return elective;
	}

	public void setElective(String elective) {
		this.elective = elective;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getTesttype() {
		return testtype;
	}

	public void setTesttype(String testtype) {
		this.testtype = testtype;
	}
	
}
