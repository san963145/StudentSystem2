package com.sys.grades.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sys.grades.bean.McNewestGpa;
import com.sys.manager.dao.SessionManager;

public class McNewestGpaDao {
	/**
  	 * ���һ��McNewestGpa��¼
  	 * 
  	 */
       public static void add(McNewestGpa mcNewestGpa)
       {
    	   Session session=SessionManager.getSession();
  		   Transaction transaction=session.beginTransaction();
  		   session.save(mcNewestGpa);
  		   transaction.commit();
  		 session.clear();
  		   session.close();
       }
       /**
     	 * ���McNewestGpa��
     	 * 
     	 */
       public static void clear()
       {
    	   Session session=SessionManager.getSession();
    	   Transaction transaction=session.beginTransaction();
    	   String hql="delete from McNewestGpa";
    	   Query query=session.createQuery(hql);
    	   query.executeUpdate();
    	   transaction.commit(); 
    	   session.clear();
    	   session.close();
       }
       /**
     	 * ��ʽ��McNewestGpa��
     	 * 
     	 */
       public static void formatDecimal()
       {
    	   Session session=SessionManager.getSession();
    	   Transaction transaction=session.beginTransaction();
    	   String hql="update McNewestGpa t set t.newestgpa=round(t.newestgpa,2)";
    	   Query query=session.createQuery(hql);
    	   query.executeUpdate();
    	   transaction.commit(); 
    	   session.clear();
    	   session.close(); 
       }
       /**
        * ����ѧԺ��רҵ���꼶��ȡ����ѧ���Ĺҿơ���������
        */
       public static List SelectByCMG(String collegename,String major,int grade)
      	{
      		Session session=SessionManager.getSession();
          	String hql = "select trim(s.sno),s.restudynum,s.failednum from DeptLocator t,McNewestGpa s where trim(s.sno)=trim(t.sno) and t.department = ? and substr(t.classno,0,2) = ? and substr(t.classno,3,4) = ?";
          	Query query=session.createQuery(hql);
          	query.setString(0, collegename);
          	query.setString(1, Integer.toString(grade).substring(2,4));
          	query.setString(2, major);
          	List list = query.list();
          	session.clear();
          	session.close();
          	return list;
      	}
       public static void main(String[]args)
   	  {
   		 List list=SelectByCMG("�����ѧԺ","01",2012);
   		 for(int i=0;i<list.size();i++)
   		 {
   			 Object[] obj=(Object[]) list.get(i);
   			 System.out.println(obj[0].toString()+" "+obj[1].toString()+" "+obj[2].toString());
   		 }
   	  }
}
