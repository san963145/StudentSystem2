package com.sys.grades.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sys.manager.dao.SessionManager;

public class McDeptDao {
	/**
  	 * ��ȡ����ѧԺ�б�
  	 * 
  	 */
       public static List getAllDepartments()
       {
    	   Session session=SessionManager.getSession();
      	   String hql="select distinct t.collegename from McDept t";
      	   Query query=session.createQuery(hql);
           List result=null;
           result=query.list();
           session.clear();
           session.close();
           return result; 
       }
       /**
     	 * ����ѧԺ��ȡרҵ�б�
     	 * 
     	 */
       public static List getMajorByDepartment(String department)
       {
    	   Session session=SessionManager.getSession();
      	   String hql="select t.majorname from McDept t where t.collegename=?";
      	   Query query=session.createQuery(hql);
      	   query.setString(0, department);
           List result=null;
           result=query.list();
           session.clear();
           session.close();
           return result;
       }
       /**
     	 * ����ѧԺ��ȡרҵ���б�
     	 * 
     	 */
       public static List getMajorNumByDepartment(String department)
       {
    	   Session session=SessionManager.getSession();
      	   String hql="select t.majornum from McDept t where t.collegename=?";
      	   Query query=session.createQuery(hql);
      	   query.setString(0, department);
           List result=null;
           result=query.list();
           session.clear();
           session.close();
           return result;
       }
       /**
     	 * ����רҵ����ȡרҵ��
     	 * 
     	 */
       public static String getNumByMajor(String major)
       {
    	   Session session=SessionManager.getSession();
      	   String hql="select t.majornum from McDept t where t.majorname=?";
      	   Query query=session.createQuery(hql);
      	   query.setString(0, major);
           List result=null;
           result=query.list();
           session.clear();
           session.close();
           String majorNum=null;
           if(result!=null)
              majorNum=result.get(0).toString();
           return majorNum;
       }
       
       
      
}
