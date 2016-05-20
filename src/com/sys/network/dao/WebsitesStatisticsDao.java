package com.sys.network.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sys.manager.dao.SessionManager;
import com.sys.network.bean.WebsitesStatistics;

public class WebsitesStatisticsDao {
	
	/**
	 * ��ȡ���е�NtMain
	 */
	public static void AddWebsitesStatistics(WebsitesStatistics webSitesStatistics){
		Session session = SessionManager.getSession();
		Transaction transaction = session.beginTransaction();
	    session.save(webSitesStatistics);
		transaction.commit();
		session.clear();
		session.close();
	}
	
	/**
	 * ��ѯ��ַ�ķ��ࡢ������
	 */
	public static List getByURL(String url){
		Session session=SessionManager.getSession();
    	String hql = "select t.weburl,t.type,t.subaccesscount from WebsitesStatistics t where t.weburl like ?";
    	Query query=session.createQuery(hql);
    	query.setString(0,"%"+url+"%");
    	List list = query.list();
    	session.clear();
    	session.close();
    	return list;
	}
	/**
	 * ��ȡ������ ������ַ����ϸ��Ϣ
	 */
	public static List getByType(String type){
		Session session=SessionManager.getSession();
    	String hql = "select t.weburl,t.type,t.subaccesscount from WebsitesStatistics t where t.type = ?";
    	Query query=session.createQuery(hql);
    	query.setString(0,type);
    	List list = query.list();
    	session.clear();
    	session.close();
    	return list;
	}
	/**
  	 * ���WebsitesStatistics��
  	 * 
  	 */
   public static void clear()
   {
	   Session session=SessionManager.getSession();
	   Transaction transaction=session.beginTransaction();
	   String hql="delete from WebsitesStatistics";
	   Query query=session.createQuery(hql);
	   query.executeUpdate();
	   transaction.commit(); 
	   session.clear();
	   session.close();
   }
	public static void main(String[] args){
		List list=getByURL("qq.com");
		for(int i=0;i<list.size();i++)
		{
		  Object[] obj=(Object[]) list.get(i);
		  System.out.println(obj[0]+" "+obj[1]+" "+obj[2]);
		}
	}
}
