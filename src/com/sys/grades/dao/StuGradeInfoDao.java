package com.sys.grades.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sys.grades.bean.StuGradeInfo;
import com.sys.manager.dao.SessionManager;

/**
 * �ݴ�ѧ���ɼ�����
 * 
 * @author Administrator
 * 
 */
public class StuGradeInfoDao {

	/**
	 * ��� ѧ���ݴ���Ϣ
	 * 
	 * @param stugradeinfo
	 */
	public static void addStuGradeInfo(StuGradeInfo stugradeinfo) {
		Session session = SessionManager.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(stugradeinfo);
		session.flush(); 
		transaction.commit();
		session.clear();
		session.close();
	}

	/**
	 * ��ѯ������ѧ��ѧ��
	 * 
	 * @return
	 */
	public static List selectAllSno() {
		Session session = SessionManager.getSession();
		String hql = "select distinct t.sno from StuGradeInfo t";
		Query query = session.createQuery(hql);
		List result = null;
		result = query.list();
		session.clear();
		session.close();
		return result;
	}

	/**
	 * ��ѯ������ѧ����Ŀ
	 * 
	 * @return
	 */
	public static List selectAllSemester() {
		Session session = SessionManager.getSession();
		String hql = "select distinct t.semester from StuGradeInfo t";
		Query query = session.createQuery(hql);
		List result = null;
		result = query.list();
		session.clear();
		session.close();
		return result;
	}

	/**
	 * ����ѧ�Ŷ���ѧ�ڲ�ѯѧ����Ϣ
	 */
	public static List selectBySnoSemester(String sno,int semester) {
		Session session = SessionManager.getSession();
		String hql = "select t.sno,t.semester,t.grade,t.coursecredit,t.elective,t.teachername,t.testtype from StuGradeInfo t where trim(sno) = ? and semester = ?";
		Query query=session.createQuery(hql);
        query.setString(0,sno.trim());
        query.setInteger(1, semester);
        List result=null;
        result=query.list();
        session.clear();
        session.close();
        return result;    
	}

	
	/**
	 * ����ѧ�Ų�����е�ѧ��
	 * @param args
	 */
	public static List seletSemestersBySno(String sno){
		Session session = SessionManager.getSession();
		String hql = "select distinct t.semester from StuGradeInfo t where trim(sno) = ? and t.semester<6";
		Query query=session.createQuery(hql);
		query.setString(0,sno);
		List result = null;
		result = query.list();
		session.clear();
		session.close();
		return result;
	}
	/**
	 * ����ѧ�Ų�����е�ѧ��
	 * @param args
	 */
	public static List seletSemester_1(){
		Session session = SessionManager.getSession();
		String hql = "select distinct t.semester from StuGradeInfo t where t.semester<6 order by t.semester asc";
		Query query=session.createQuery(hql);
		List result = null;
		result = query.list();
		session.clear();
		session.close();
		return result;
	}
	/**
	 * �����ǰ���е�ѧ��
	 * @param args
	 */
	public static List seletSemesters(){
		Session session = SessionManager.getSession();
		String hql = "select distinct t.semester from StuGradeInfo t order by t.semester asc";
		Query query=session.createQuery(hql);
		List result = null;
		result = query.list();
		if(result.size()>0)
		{
			result.remove(result.size()-1);
		}
		session.clear();
		session.close();
		return result;
	}
	/**
	 * ����ѧ�Ŷ���ѧ�ڲ�ѯѧ����Ϣ
	 */
	public static List selectInfoBySnoSemester(String sno,int semester) {
		Session session = SessionManager.getSession();
		String hql = "select t.sno,t.semester,t.grade,t.coursecredit,t.elective,t.teachername,t.testtype from StuGradeInfo t where trim(sno) = ? and semester = ?";
		Query query=session.createQuery(hql);
        query.setString(0,sno.trim());
        query.setInteger(1, semester);
        List result=null;
        result=query.list();
        session.clear();
        session.close();
        return result;    
	}
	/**
	 * ����ѧ�ż���ѧ��ÿѧ�ڹҿ�����
	 * @param args
	 */
	public static List GetFailBySno(String sno){
		List stufailList = new ArrayList<>();
		List semestersList = StuGradeInfoDao.seletSemestersBySno(sno);
		//List semestersList = StuGradeInfoDao.seletSemesters();      
		Object[] obj1=semestersList.toArray();
		Arrays.sort(obj1);
		semestersList=Arrays.asList(obj1);
		if(semestersList.size()!=0){
			Iterator semIt = semestersList.iterator();
			while(semIt.hasNext()){
				int failnum = 0;
				int semester = (int) semIt.next();
				List list = StuGradeInfoDao.selectBySnoSemester(sno, semester);
				Iterator it = list.iterator();
				while(it.hasNext()){
					Object[] obj = (Object[])it.next();
					String grade = obj[2].toString();
					String elective = obj[4].toString();
					String teachername = obj[5].toString();
					String testtype = obj[6].toString();
					if("�����ļ�".equals(teachername) || "��������".equals(teachername) || "��ר".equals(elective) || "����".equals(elective)){
						continue;
					}else{
						if("����".equals(testtype)){
							continue;
						}else{
							double g ;
							if(grade.matches("\\d+"))
			  			  {
			  				  if(Integer.parseInt(grade)<60)
			  					  g=0;
			  				  else
			  				      g=(Double.parseDouble(grade)-50.0)/10.0;
			  			  }
			  			  else
			  			  {
			  				  if(grade.equals("��"))
			  				  {
			  					 g=4.0;
			  				  }
			  				  else if(grade.equals("��")||grade.equals("�ϸ�"))
			  				  {
			  					  g=3.5;
			  				  }
			  				  else if(grade.equals("��"))
			  				  {
			  					  g=2.5;
			  				  }
			  				  else if(grade.equals("��"))
			  				  {
			  					  g=1.5;
			  				  }
			  				  else
			  				  {
			  					  g=0;
			  				  }
			  			  }
							if(g==0){
								failnum++;
							}
						}
					}
				}
				stufailList.add(failnum);
			}
		}
		
		return stufailList;
	}

	 /**
   	 * ����ѧ�ţ�ѧ�ڲ�ѯѡ��������grade
   	 * @param sno
   	 * @param semester
   	 * @return
   	 */
	public static String SelectFailBySnoSemester(String sno,int semester,String teachername){
   		Session session = SessionManager.getSession();
   		String hql = "select t.grade from StuGradeInfo t where t.testtype = '����' and trim(sno) = ? and semester = ? and teachername = ?";
   		Query query=session.createQuery(hql);
           query.setString(0,sno.trim());
           query.setInteger(1, semester);
           query.setString(2, teachername);
           String result=null;
           if(query.list().size()>0){
               result=(String) query.list().get(0);
           }
           session.clear();
           session.close();
           return result;    
   	}
	/**
  	 * ���StuGradeInfo��
  	 * 
  	 */
   public static void clear()
   {
	   Session session=SessionManager.getSession();
	   Transaction transaction=session.beginTransaction();
	   String hql="delete from StuGradeInfo";
	   Query query=session.createQuery(hql);
	   query.executeUpdate();
	   transaction.commit(); 
	   session.clear();
	   session.close();
   }
	public static void main(String[] args){
		List list=seletSemesters();
		for(int i=0;i<list.size();i++)
		{
			System.out.println((Integer)list.get(i));
		}
	}
}
