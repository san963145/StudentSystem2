package com.sys.grades.bean;

import java.io.Serializable;

/**
 * ѧԺ-רҵ��ϵ��
 * @author Administrator
 *
 */
public class McDept implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8025777120100942912L;
	//ѧԺ��
	private String collegename;
	//רҵ����
	private String majorname;
	//רҵ��
	private String majornum;
	public String getCollegename() {
		return collegename;
	}
	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}
	public String getMajorname() {
		return majorname;
	}
	public void setMajorname(String majorname) {
		this.majorname = majorname;
	}
	public String getMajornum() {
		return majornum;
	}
	public void setMajornum(String majornum) {
		this.majornum = majornum;
	}
	
	
}
