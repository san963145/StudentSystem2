package com.sys.card.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sys.manager.dao.SessionManager;

public class MajorNameDao {
	
	/**
 	 * ����רҵ����ȡרҵ��
 	 * 
 	 */
   public static String getNumByName(String majorName)
   {
	   Session session=SessionManager.getSession();
  	   String hql="select t.majorNum from MajorName t where t.majorName=?";
  	   Query query=session.createQuery(hql);
  	   query.setString(0, majorName);
       List result=null;
       result=query.list();
       session.clear();
       session.close();
       
       String majorNum=null;
       if(result!=null)
       {
    	   if(result.size()>0)
             majorNum=result.get(0).toString();
       }
       return majorNum;
   }
   /**
	 * ����ѧԺ��רҵ����ȡרҵ��
	 * 
	 */
  public static String getNumByName(String department,String majorName)
  {
	   Session session=SessionManager.getSession();
 	   String hql="select t.majorNum from MajorName t,DeptLocator d where t.majorName=? and substr(d.classno,3,4)=t.majorNum";
 	   Query query=session.createQuery(hql);
 	   query.setString(0, majorName);
      List result=null;
      result=query.list();
      session.clear();
      session.close();
     
      String majorNum=null;
      if(result!=null)
      {
   	   if(result.size()>0)
            majorNum=result.get(0).toString();
      }
      return majorNum;
  }
   /**
	 * ����רҵ�Ż�ȡרҵ��
	 * 
	 */
  public static String getNameByNum(String majorNum)
  {
	   Session session=SessionManager.getSession();
 	   String hql="select t.majorName from MajorName t where t.majorNum=?";
 	   Query query=session.createQuery(hql);
 	   query.setString(0, majorNum);
       List result=null;
       result=query.list();
       session.clear();
       session.close();
       
       String majorName=null;
       if(result!=null)
       {
   	    if(result.size()>0)
   		  majorName=result.get(0).toString();
       }
       return majorName;
  }
  public static void main(String[] args)
  {
	  String s=getNumByName("���պ���ѧԺ","������ѧ");
	  System.out.println(s);
  }
}
