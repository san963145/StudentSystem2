package com.sys.card.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sys.manager.dao.SessionManager;

public class StudentInfoDao {
	/**
     *  ����ѧ�Ż�ȡѧԺ
     */
	 public static String selectDepartmentBySno(String sno)
	 {
		 Session session=SessionManager.getSession();
   	     String hql="select s.department from StudentInfo s where trim(s.sno)=?";
   	     Query query=session.createQuery(hql);
        query.setString(0,sno);
        List list=query.list();
        String department=null;
        if(list!=null)
        {
       	 if(list.size()!=0)
       	 {
       		 department=(String) list.get(0);
       	 }
        }
        session.clear();
        session.close();
        
        return department;
	 }  
	 /**
      *  ����ѧ�Ż�ȡרҵ
      */
	 public static String selectMajorBySno(String sno)
	 {
		 Session session=SessionManager.getSession();
    	 String hql="select substr(s.classNo,3,4) from StudentInfo s where trim(s.sno)=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,sno);
         List list=query.list();
         String major=null;
         if(list!=null)
         {
        	 if(list.size()!=0)
        	 {
        		 major=(String) list.get(0);
        	 }
         }
         session.clear();
         session.close();
         return major;
	 }
	 /**
      *  ����ѧ�Ż�ȡ�꼶���༶��
      */
	 public static List selectGMBySno(String sno)
	 {
		 Session session=SessionManager.getSession();
    	 String hql="select s.grade,s.classNo from StudentInfo s where trim(s.sno)=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,sno);
         List list=query.list();
         session.clear();
         session.close();
         return list;
	 }
	 
	 public static void main(String[]args)
	 {
		 String s=selectMajorBySno("20124932");
		 System.out.println(s);
	 }
}
