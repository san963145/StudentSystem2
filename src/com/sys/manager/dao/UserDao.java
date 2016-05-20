package com.sys.manager.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sys.manager.bean.User;

/**
 *  用户数据访问接口
 */
public class UserDao {
	
	
     
 	/**
 	 *  按账户名查询
 	 */
     public static User select(String userId)
     {
    	 Session session=SessionManager.getSession();
    	 String hql="from User where userId=?";
    	 Query query=session.createQuery(hql);
         query.setString(0,userId);
         List<User>result=null;
         result=query.list();
         User user=null;
         if(result!=null)
         {
        	 if(result.size()!=0)
        	   user=result.get(0);
         }
         session.clear();
         session.close();
         
         return user;
     }
     public static void addUser(User user)
     {
		 Session session=SessionManager.getSession();
		 Transaction transaction=session.beginTransaction();
		 session.save(user);
		 transaction.commit();    	 
		 session.clear();
         session.close();
         
     }
     public static void updatePoint(String id,String department,String aut)
     {
    	 Session session=SessionManager.getSession();
		 Transaction transaction=session.beginTransaction();
    	 String hql="update User s set s.department=?,s.authority=? where trim(s.userId)=?";
    	 Query query=session.createQuery(hql);
    	 query.setString(0,department);
    	 query.setString(1,aut);
    	 query.setString(2,id);
    	 query.executeUpdate();
		 transaction.commit();    
		 session.clear();
		 session.close();
     } 

     public static void main(String[] args)
     {
    	 UserDao.updatePoint("06587421", "计算机学院", "Dean");
    	 User user=UserDao.select("06587421");
    	 System.out.println(user.getAuthority());
     }
}
