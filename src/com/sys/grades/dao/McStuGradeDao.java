package com.sys.grades.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sys.manager.dao.SessionManager;

public class McStuGradeDao {
	/**
  	 * ����ѧ�Ų�ѯѧ���ĳɼ���Ϣ
  	 * 
  	 */
	public static List selectBySno(String sno)
    {
 	   Session session=SessionManager.getSession();
   	   String hql="select t.sno,t.grade,t.coursecredit,t.elective,t.date,t.teachername,t.testtype from McStuGrade t where trim(t.sno)= ?";
   	   Query query=session.createQuery(hql);
        query.setString(0,sno.trim());
        List result=null;
        result=query.list();
        session.clear();
        session.close();
        return result;    	   
    }
	/**
  	 * ����ѧ�Ų�ѯѧ���ĳɼ���Ϣ
  	 * 
  	 */
       public static List selectAllBySno(String sno)
       {
    	   Session session=SessionManager.getSession();
      	   String hql="select t.sno,t.coursenum,t.grade,t.coursecredit,t.elective,t.teachername,t.testtype from McStuGrade t where trim(t.sno)= ?";
      	   Query query=session.createQuery(hql);
           query.setString(0,sno);
           List result=null;
           result=query.list();
           session.clear();
           session.close();
           return result;    	   
       }
       /**
     	 * ��ȡ����ѧ����ѧ���б�
     	 * 
     	 */
       public static List selectAllSno()
       {
    	   Session session=SessionManager.getSession();
      	   String hql="select distinct t.sno from McStuGrade t";
      	   Query query=session.createQuery(hql);
           List result=null;
           result=query.list();
           session.clear();
           session.close();
           return result;  
       }
       /**
     	 * ����ѧԺ��רҵ���꼶��ȡѧ�������������б�
     	 * 
     	 */
       public static List selectReTakeNum(String department,String major,int grade)
       {
    	   Session session=SessionManager.getSession();
      	   String hql="select t.sno,count(t.elective) from McStuGrade t,DeptLocator d where trim(t.sno)=trim(d.sno) and t.elective=? and d.department = ? and substr(d.classno,0,2) = ? and substr(d.classno,3,4) = ? group by t.sno";
      	   Query query=session.createQuery(hql);
     	   query.setString(0,"����");
     	   query.setString(1,department);
     	   query.setString(3,major);
     	  query.setString(2, Integer.toString(grade).substring(2,4));    	
           List result=null;
           result=query.list();
           session.clear();
           session.close();
           return result;  
       }
       /**
        * ͳ��ѧ������������
        * @param sno
        * @return
        */
       public static List selectFRBySno(String sno)
       {
    	   Session session=SessionManager.getSession();
      	   String hql="select t.grade,t.elective,t.teachername,t.testtype from McStuGrade t where trim(t.sno)= ?";
      	   Query query=session.createQuery(hql);
           query.setString(0,sno.trim());
           List result=null;
           result=query.list();
           session.clear();
           session.close();
           return result;    	   
       }
       
       public static void main(String[] args)
       {
    	  List list=selectReTakeNum("�����ѧԺ","01",2012);
    	  System.out.println(list.size());
    	  Iterator it=list.iterator();
    	  while(it.hasNext())
    	  {
    		  Object[] o=(Object[]) it.next();
    		  System.out.println(o[0].toString()+" "+o[1]);
    	  }
       }
}
