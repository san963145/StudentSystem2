package com.sys.grades.bean;

import java.io.Serializable;

/**
 * ѧ�����µĳɼ�
 * @author Administrator
 *
 */
public class McNewestGpa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8621706462389311470L;
	//ѧ��
	private String sno;
	//����GPA
	private double newestgpa;
	//�ҿ�����
	private int failednum;
	//��������
	private int restudynum;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public double getNewestgpa() {
		return newestgpa;
	}
	public void setNewestgpa(double newestgpa) {
		this.newestgpa = newestgpa;
	}
	public int getFailednum() {
		return failednum;
	}
	public void setFailednum(int failednum) {
		this.failednum = failednum;
	}
	public int getRestudynum() {
		return restudynum;
	}
	public void setRestudynum(int restudynum) {
		this.restudynum = restudynum;
	}
	
	
}
