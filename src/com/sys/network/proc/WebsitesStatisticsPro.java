package com.sys.network.proc;

import java.util.Iterator;
import java.util.List;

import com.sys.network.bean.WebsitesStatistics;
import com.sys.network.dao.NtRecordCountDao;
import com.sys.network.dao.NtSubCategoryDao;
import com.sys.network.dao.NtWebsitesDao;
import com.sys.network.dao.WebsitesStatisticsDao;
import com.sys.network.servlet.Calculate;

public class WebsitesStatisticsPro {

	/**
	 * �����ݿ������WebsitesStatistics
	 * 1.�Ȳ�ѯ�������������Ϣ
	 * 2.����ӽ����ݿ�
	 */
	public static void addWebsitesStatistics(){
		WebsitesStatisticsDao.clear();
		List list = NtSubCategoryDao.GetAllSubCategories();
		Iterator it = list.iterator();
		while(it.hasNext()){
			Object[] obj = (Object[])it.next();
			int sid = Integer.parseInt(obj[0].toString());
			//����subid��ѯ������������
			List fullnameList = NtSubCategoryDao.GetNameBySid(sid);
			Object[] obj1 = (Object[])fullnameList.get(0);
			String fullname = obj1[0]+"-"+obj1[1]+"-"+obj1[2];
			//�ٸ���sid�������е�weburl
			List urlList = NtWebsitesDao.GetNtWebsitesSub(sid);
			//����sid��ѯ������
			int accesscount = NtRecordCountDao.GetAccessCountBySubId(sid);
			Iterator urlit = urlList.iterator();
			while(urlit.hasNext()){
				String url = (String) urlit.next();
				WebsitesStatistics ws = new WebsitesStatistics(url, fullname, accesscount,sid);
				WebsitesStatisticsDao.AddWebsitesStatistics(ws);
			}
		}
		Calculate.setFlag();
	}
	
	public static void main(String[] args){
		addWebsitesStatistics();
	}
}
