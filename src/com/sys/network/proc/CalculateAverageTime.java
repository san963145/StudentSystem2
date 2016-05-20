package com.sys.network.proc;

import java.util.ArrayList;

import com.sys.network.bean.CategoryNameNum;
import com.sys.network.dao.NtGameTimeDao;
import com.sys.network.dao.NtRecordCountDao;
import com.sys.network.dao.NtVdeoTimeDao;

public class CalculateAverageTime {

	/**
	 * ����ѧԺ��רҵ���꼶���Ա��޶��£�������ƽ������ʱ��
	 */
	public static Double CalculateAverageOnlineTime(String collegename,
			String major, String grade, String sex) {
		Double avg = null;
		try {
			avg = NtRecordCountDao.GetAOTByCMGS(collegename, major, grade, sex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return avg;
	}
	/**
	 * ����ȫУƽ������ʱ��
	 */
	public static Double CalculateAOT(){
		try {
			return NtRecordCountDao.GetAOT();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ����ѧԺ��רҵ���꼶���Ա��޶��£�������ƽ��������Ϸʱ��
	 */
	public static Double CalculateAverageGameTime(String collegename,
			String major, String grade, String sex) {
		Double avg = null;
		try {
			avg = NtGameTimeDao.GetAGTByCMGS(collegename, major, grade, sex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return avg;
	}
	/**
	 * ����ȫУƽ����Ϸʱ��
	 */
	public static Double CalculateAGT(){
		try {
			return NtGameTimeDao.GetAGT();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ����ѧԺ��רҵ���꼶���Ա��޶��£�������ƽ��������Ƶʱ��
	 */
	public static Double CalculateAverageVideoTime(String collegename,
			String major, String grade, String sex) {
		Double avg = null;
		try {
			avg = NtVdeoTimeDao.GetAGVByCMGS(collegename, major, grade, sex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return avg;
	}
	/**
	 * ����ȫУƽ����Ƶʱ��
	 */
	public static Double CalculateAVT(){
	   try {
		return NtVdeoTimeDao.GetAVT();
	} catch (Exception e) {
		e.printStackTrace();
	}
	   return null;
	}
	/**
     * ����ѧԺ��רҵ���꼶���Ա��޶��£�������ǰʮ���������
     */
	public static ArrayList<CategoryNameNum> CalculateTop10sub(String collegename,
			String major, String grade, String sex){
		try {
			return NtRecordCountDao.getTop10Category(collegename, major, grade, sex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<CategoryNameNum>();
	}
	
}
