package com.sys.grades.servlet;

import java.io.IOException;
import java.io.PrintWriter;










import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;















import com.sys.grades.dao.GradeSearchByGroupDao;
import com.sys.grades.dao.McDecisionMatrixDao;
import com.sys.grades.dao.McHistoryGpaDao;
import com.sys.grades.dao.McNewestGpaDao;
import com.sys.grades.dao.StuGradeInfoDao;
import com.sys.grades.dao.StuGradePredictionDao;
import com.sys.grades.proc.CalculateByGroup;
import com.sys.grades.proc.CalculateHistoryGpa;
import com.sys.grades.proc.CalculateNewGpa;
import com.sys.grades.proc.CalculatePrediction;



/**
 * �ܵļ������
 */
@WebServlet("/CalculateGrades")
public class CalculateGrades extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean flag=false;  
	private static boolean initflag1=false;
	private static boolean initflag2=false;
	private static boolean initflag3=false;
	private static boolean initflag4=false;
	private static boolean initflag5=false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculateGrades() {
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
		response.setContentType("text/html");
		ServletContext application=(ServletContext) request.getServletContext();
		application.setAttribute("flagGrade","1");
		String step=request.getParameter("step");
		PrintWriter out=response.getWriter();
		if(step.equals("0"))
		{		  
		  out.println("��ʼ����...");
		  out.close();
		}
		else if(step.equals("1"))
		{			
			if(flag)
			{
			  flag=false;
		      out.println("ѧ����ʷGPA�������...");				      
			}
			else
			{
				out.print("0");
			}
		    out.close();
		    if(!initflag1)
			{
			 initflag1=true;
			 new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					CalculateHistoryGpa.calculateHistoryGpa();
		            CalculateHistoryGpa.AddStuGradeInfo();
				
				}
				 
			 }).start();
	         
			}
		}
		else if(step.equals("2"))
		{			
			if(flag)
			{			  
			  flag=false;
			  out.println("ѧ������GPA�������...");	
			}
			else
			{
				out.print("0");
			}
		    out.close();
		    if(!initflag2)
			{
			 initflag2=true;
			 new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						CalculateNewGpa.calculateNewGpa();
						
					}
					 
			 }).start();
			 
			}
		}	
		else if(step.equals("3"))
		{			
			if(flag)
			{			  	 
			  flag=false;
			  out.println("��ѧԺ��ѯ�����ݼ������...");
			}
			else
			{
				out.print("0");
			}
		          //���� ��ѧԺ��ѯ������		
		    out.close();
		    if(!initflag3)
			{
			 initflag3=true;
			 new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						CalculateByGroup.calculate();
						
					}
					 
			 }).start();
			 
			}
		}
		else if(step.equals("4"))
		{			
			if(flag)
			{			    
			  flag=false;
			  out.println("Ԥ�����������...");	
			}
			else
			{
				out.print("0");
			}
		    out.close();
		    if(!initflag4)
			{
			 initflag4=true;
			 new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						CalculatePrediction.calculateMatrix();
						
					}
					 
			 }).start();
			 
			}
		}
		else if(step.equals("5"))
		{			
			if(flag)
			{		  
			  flag=false;
			  out.println("�ɼ�Ԥ�����ݼ������...");	
			}
			else
			{
				out.print("0");
			}
		    out.close();
		    if(!initflag5)
			{
			 initflag5=true;
			 new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						CalculatePrediction.calculate();
					
					}
					 
			 }).start();
			 
			}
		}
		else if(step.equals("6"))
		{
	      out.println("������̽���!");
		  out.close();
		  initflag1=initflag2=initflag3=initflag4=initflag5=false;
		}
		application.removeAttribute("flagGrade");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public static void setFlag()
	{
		flag=true;
	}
    public static void main(String[] args)
    {
    	StuGradeInfoDao.clear();
    	McHistoryGpaDao.clear();
    	McNewestGpaDao.clear();
    	GradeSearchByGroupDao.clear();
    	McDecisionMatrixDao.clear();
    	StuGradePredictionDao.clear();
    }
}
