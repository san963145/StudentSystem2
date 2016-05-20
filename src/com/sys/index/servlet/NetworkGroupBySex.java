package com.sys.index.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sys.card.dao.MajorNameDao;
import com.sys.index.dao.NetworkDao;
import com.sys.network.bean.CategoryNameNum;

/**
 * Servlet implementation class NetworkGroupBySex
 */
@WebServlet("/NetworkGroupBySex")
public class NetworkGroupBySex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NetworkGroupBySex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String department=request.getParameter("department");     //��CardGroupByCollegeҳ��ת��
		if(department!=null)
		   department=java.net.URLDecoder.decode(department, "UTF-8");
        String major=request.getParameter("major");
        String grade=request.getParameter("grade");
        String majorNum=null;
        if(major!=null)
		{
        	majorNum=major;
			major=MajorNameDao.getNameByNum(major);			
		}
        String str="";
        String cur=null;
		Cookie[] cookies=request.getCookies();
		if(cookies!=null)
		for(Cookie c : cookies)
		{
			if(c.getName().equals("str"))
			{
				str=java.net.URLDecoder.decode(c.getValue(), "UTF-8");
			}
		}
		if(department!=null)
		{
			cur="department";
		}
		if(major!=null)
		{
			cur="major";
		}
		if(grade!=null)
		{
			cur="grade";
		}
		if(cookies!=null)
		for(Cookie c : cookies)
		{
			if(c.getName().equals("department"))
			{
				department=java.net.URLDecoder.decode(c.getValue(), "UTF-8");
			}
			if(c.getName().equals("major"))
			{
				major=java.net.URLDecoder.decode(c.getValue(), "UTF-8");
				majorNum=MajorNameDao.getNumByName(department,major);
			}
			if(c.getName().equals("grade"))
			{
				grade=java.net.URLDecoder.decode(c.getValue(), "UTF-8");
			}
		}
		if(cookies!=null)
		for(Cookie c : cookies)
		{
			c.setMaxAge(0);
			response.addCookie(c);
		}
		Map<String, ArrayList<CategoryNameNum>> networkMap=null;
		try {
			networkMap = NetworkDao.get(department, majorNum, grade, null, "sex");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("networkMap",networkMap);		
		request.setAttribute("department",department);			
		request.setAttribute("major",major);	
		request.setAttribute("grade",grade);
		request.setAttribute("str",str);
		request.setAttribute("cur",cur);
		request.getRequestDispatcher("pages/index/networkGroupBySex.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
