package com.sys.grades.bean;

    /**
	 * ѧ���ɼ�Ԥ���� ��
	 * 
	 */
public class StuGradePrediction {

	//��־����
	private int id;
	//ѧ��
	private String sno;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//����
	private String sname;
	//����GPA
	private double gpa;
	//�Ƿ���ڹҿƷ���
	private String risk;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	
}
