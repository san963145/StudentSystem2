package com.sys.grades.bean;

import java.io.Serializable;

/**
 * ѧ����ʷ�ĳɼ�
 * @author Administrator
 *
 */
public class McHistoryGpa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1737308691465248212L;
	//ѧ��
	private String sno;
	//ѧ��
	private int semester;
	//��ѧ�ڵ�GPA
	private double  gpasemester;
	//��ѧ�ڵ�ƽ����
	private double averscoresmester;
	//ÿѧ�ڵ�ѡ�η�
	private double selectcreditsemester;
	//ÿѧ�ڻ�õ�ѧ��
	private double hadcreditsemester;
	//ÿѧ�ڵ�ѧ�ֻ����
	private double hadcreditratesemester;
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
	public double getGpasemester() {
		return gpasemester;
	}
	public void setGpasemester(double gpasemester) {
		this.gpasemester = gpasemester;
	}
	public double getAverscoresmester() {
		return averscoresmester;
	}
	public void setAverscoresmester(double averscoresmester) {
		this.averscoresmester = averscoresmester;
	}
	public double getSelectcreditsemester() {
		return selectcreditsemester;
	}
	public void setSelectcreditsemester(double selectcreditsemester) {
		this.selectcreditsemester = selectcreditsemester;
	}
	public double getHadcreditsemester() {
		return hadcreditsemester;
	}
	public void setHadcreditsemester(double hadcreditsemester) {
		this.hadcreditsemester = hadcreditsemester;
	}
	public double getHadcreditratesemester() {
		return hadcreditratesemester;
	}
	public void setHadcreditratesemester(double hadcreditratesemester) {
		this.hadcreditratesemester = hadcreditratesemester;
	}
	
	
}
