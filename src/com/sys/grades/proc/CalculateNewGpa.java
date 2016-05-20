package com.sys.grades.proc;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.sys.grades.bean.McNewestGpa;
import com.sys.grades.dao.McNewestGpaDao;
import com.sys.grades.dao.McStuGradeDao;
import com.sys.grades.servlet.CalculateGrades;

public class CalculateNewGpa {
	
	
	  //obj[0]ѧ�� ��obj[1]�γ̺ţ�obj[2]�ɼ���obj[3]ѧ��,obj[4]���ޣ�obj[5]��ʦ��obj[6]����
	/**
   	 * ��������GPA
   	 * 
   	 */
      public static void calculateNewGpa()
      {
    	  McNewestGpaDao.clear();
    	  List snoList=McStuGradeDao.selectAllSno();
    	  Iterator snoIt=snoList.iterator();
    	  List grades=null;
    	  HashSet<String>courseNoSet=new HashSet<String>();  //�洢�ҿƿγ�
    	  while(snoIt.hasNext())
    	  {
    		  String sno=(String) snoIt.next();
    		  grades=McStuGradeDao.selectAllBySno(sno.trim());
    		  Iterator it=grades.iterator();
    		  double a=0;   //GPA����
    		  double b=0;   //GPA��ĸ
    		  int failedNum=0;         //�ҿ�����
    		  int restudyNum=0;         //��������
    		  double g;     //���Ƽ���
    		  courseNoSet.clear();
    		  while(it.hasNext())
    		  {
    			  Object[] obj=(Object[]) it.next();
    			  String courseNo=obj[1].toString(); 
    			  String grade=obj[2].toString();   			  
    			  double courseCredit=Double.parseDouble(obj[3].toString());
    			  String elective=obj[4].toString(); 
    			  String teacherName=obj[5].toString();
    			  String testType=obj[6].toString();
    			  
    			  if(teacherName.equals("�����ļ�")||teacherName.equals("��������")||elective.equals("��ר"))
    				  continue;
    			  if(grade.matches("\\d+"))
    			  {
    				  if(Integer.parseInt(grade)<60)
    					  g=0;
    				  else
    				  {
    					  double _grade=Double.parseDouble(grade);
    					  if(_grade>90)
    						  _grade=90;
    				      g=(_grade-50.0)/10.0;
    				  }
    			  }
    			  else
    			  {
    				  
    				  if(grade.equals("��"))
    				  {
    					 g=4.0;
    				  }
    				  else if(grade.equals("��")||grade.equals("�ϸ�"))
    				  {
    					  g=3.5;
    				  }
    				  else if(grade.equals("��"))
    				  {
    					  g=2.5;
    				  }
    				  else if(grade.equals("��"))
    				  {
    					  g=1.5;
    				  }
    				  else
    				  {
    					  g=0;
    				  }
    			  }
    			  
    			  if(g==0&&elective.equals("����")&&testType.equals("����"))
    			  {
    				  failedNum++;
    			  }
    			  if(elective.equals("����"))
    			  {
    				  restudyNum++;
    			  }
    			  
    			  if(elective.equals("����")&&testType.equals("����"))
    			  {
    				  if(g>0)
    				  {
    					  b+=courseCredit;
    	    			  a+=(g*courseCredit); 
    				  }
    				  else
    				  {
    					  courseNoSet.add(courseNo);
    					  b+=courseCredit;
    				  }
    			  }
    			  if(elective.equals("����")||testType.equals("����"))
    			  {
    				  if(g>0)
    				  {
    					  if(courseNoSet.contains(courseNo))
    					  {
    	    			   a+=(1.0*courseCredit); 
    	    			   courseNoSet.remove(courseNo);
    					  }
    				  }
    			  }    			  
    		  }
    		  double gpa=a/b;
    		  McNewestGpa mcNewestGpa=new McNewestGpa();    		  
    		  mcNewestGpa.setSno(sno);
    		  mcNewestGpa.setNewestgpa(gpa);
    		  mcNewestGpa.setFailednum(failedNum);
    		  mcNewestGpa.setRestudynum(restudyNum);
    		  //System.out.println(sno+" "+gpa+" "+failedNum+" "+restudyNum);
    		  McNewestGpaDao.add(mcNewestGpa);
    	  }
    	  McNewestGpaDao.formatDecimal();
    	  CalculateGrades.setFlag();
      }
      
      public static void main(String[]args)
      {
    	  calculateNewGpa();
      }
}
