package com.sys.manager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *  Hibernate�Ự����
 */
public class SessionManager {
	private static SessionFactory factory;
	
	/**
	 *  ����һ��Session
	 */
	@SuppressWarnings("deprecation")
	public static Session getSession()
	{
		if(factory==null)
		{
			Configuration config=new Configuration().configure();  
	        factory=config.buildSessionFactory();  
		}
		
        return factory.openSession();
	}

}
