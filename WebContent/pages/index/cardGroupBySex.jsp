<%@page import="com.sys.manager.bean.*" import="com.sys.manager.dao.*" import="com.sys.card.dao.*" import="com.wiscom.is.*, java.net.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" import="java.util.Iterator" import="java.util.ArrayList"%>
<%
String flag=(String)application.getAttribute("flag");
String flagCard=(String)application.getAttribute("flagCard");
String flagGrade=(String)application.getAttribute("flagGrade");
String flagNet=(String)application.getAttribute("flagNet");
if(flag!=null||flagCard!=null||flagGrade!=null||flagNet!=null){
%>
<jsp:forward page="../../error.jsp"/>	
<%} %>

<%
	String is_config = this.getServletContext().getRealPath("/client.properties");
    Cookie all_cookies[] = request.getCookies();
    Cookie myCookie;
    String decodedCookieValue = null;
    if (all_cookies != null) {
        for(int i=0; i< all_cookies.length; i++) {
            myCookie = all_cookies[i];
            if( myCookie.getName().equals("iPlanetDirectoryPro") ) {
                decodedCookieValue = URLDecoder.decode(myCookie.getValue(),"GB2312");
            }
        }
    }

	IdentityFactory factory = IdentityFactory.createFactory( is_config );
	IdentityManager im = factory.getIdentityManager();
	
	String curUser = "";
	String authority="";
	if (decodedCookieValue != null ) {
    	curUser = im.getCurrentUser(decodedCookieValue);
    }
	if(curUser.length()==0){
		String gotoURL = request.getRequestURL().toString();
		String loginURL = im.getLoginURL() +"?goto=" +java.net.URLEncoder.encode(gotoURL,"UTF-8");
%>
<script>
  location="<%=loginURL%>"
</script>
<% 
	}   // if(curUser.length()==0)
		
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	User user=null;
  if(curUser.length()!=0)
  {
	user=(User)session.getAttribute("user");
	if(user==null)
	{
		user=UserDao.select(curUser);
		if(user==null)
		{
%>
			<script>
			  location="http://i.cqu.edu.cn/deny.html";
			</script>
<% 
		}
		else
		{
		   session.setAttribute("user",user);
		   authority=user.getAuthority();
		}
	}
	else
	{
		authority=user.getAuthority();
	}
  }
%>
 
<% if(curUser.length()!=0){%>  
<%
   List cardList=(List)request.getAttribute("cardList");
   String department=(String)request.getAttribute("department");
   String major=(String)request.getAttribute("major");
   String grade=(String)request.getAttribute("grade");  
   String str=(String)request.getAttribute("str");
   String cur=(String)request.getAttribute("cur");
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>学生行为分析平台</title>
	 <!-- 禁缓存-->
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
    <!-- 响应屏幕宽度	-->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<!-- 添加的css-->
    <link rel="stylesheet" href="dist/css/my.css">
    <!-- Bootstrap 3.3.4 框架-->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- FontAwesome 4.3.0 图标-->
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <!-- Theme style 网站构造-->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css"> 
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
    
    <script src="pages/js/index.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="skin-purple-light sidebar-mini">
    <div class="wrapper">

      <header class=" main-header" id="my-menu">
        <!-- Logo -->
		<a class="logo" href="#systems" data-toggle="collapse" data-parent="#my-menu">
			<b>学生行为分析平台&nbsp;&nbsp;<img src="img/arrow.gif" height="10" width="10" /></b>
		</a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle visible-xs" data-toggle="offcanvas" role="button"></a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
			  
               <!-- User info -->
              <li>
			    <a href="pages/manager/info.jsp"><%=curUser %></a>
			  </li>
			  <li>
			    <a href="logout.jsp"><i class="fa fa-mail-forward"></i></a>
			  </li>
        
            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <div id="systems" class="collapse in">
			<div class="box-div bg-aqua">
			  <a href="pages/cardSystem/cardSystem.jsp">
				<!-- small box -->
				  <div class="small-box bg-aqua">
					<div class="box-div">
					  <h3>消费</h3>
					  <p>一卡通消费分析系统</p>
					</div>
					<div class="icon">
					  <i class="glyphicon glyphicon-shopping-cart"></i>
					</div>
					<a href="pages/cardSystem/cardSystem.jsp" class="small-box-footer">进入系统<i class="fa fa-arrow-circle-right"></i></a>
				  </div>
			  </a>
			</div>
			<div class="box-div bg-green">
			  <a href="pages/network/network.jsp">
				<!-- small box -->
				  <div class="small-box bg-green">
					<div class="box-div">
					  <h3>网络</h3>
					  <p>网络日志分析系统</p>
					</div>
					<div class="icon">
					  <i class="glyphicon glyphicon-globe"></i>
					</div>
					<a href="pages/network/network.jsp" class="small-box-footer">进入系统<i class="fa fa-arrow-circle-right"></i></a>
				  </div>
			  </a>
			</div>
			<div class="box-div bg-yellow">
			  <a href="pages/gradesSystem/gradesSystem.jsp">
				 <!-- small box -->
				  <div class="small-box bg-yellow">
					<div class="box-div">
					  <h3>成绩</h3>
					  <p>成绩预测系统</p>
					</div>
					<div class="icon">
					  <i class="glyphicon glyphicon-pencil"></i>
					</div>
					<a href="pages/gradesSystem/gradesSystem.jsp" class="small-box-footer">进入系统 <i class="fa fa-arrow-circle-right"></i></a>
				  </div>
			  </a>
			</div>
			<div class="box-div bg-red">
			  <a href="pages/manager/info.jsp">
				 <!-- small box -->
				  <div class="small-box bg-red">
					<div class="box-div">
					  <h3>用户</h3>
					  <p>用户及系统管理</p>
					</div>
					<div class="icon">
					  <i class="glyphicon glyphicon-user"></i>
					</div>
					<a href="#" class="small-box-footer">进入系统<i class="fa fa-arrow-circle-right"></i></a>
				  </div>
			  </a>
			</div>
		</div>
      </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
        	<h1>一卡通按性别细分</h1>
			<ol class="breadcrumb">
				<li class="active"><a href="index.jsp"><i class="fa fa-dashboard"></i> 主页</a></li>
				<li>一卡通</li>
			     <%if(!"".equals(str)){ %>
				<%
				   String[] s=str.split("#");
				   for(int i=0;i<s.length;i++)
				   {
					   String item=(String)request.getAttribute(s[i]);
			    %>
			          <li><a class="str" id="<%=s[i]%>" href="javascript:cardGet(<%=i%>,'<%=s[i]%>')"><%=item%></a></li>
			    <% 
				   }
				%>
				<%} %>
				<%if(cur!=null){ 
					String[] s=str.split("#");
				%>
				<li><a class="str" id="<%=cur%>" href="javascript:cardGet(<%=s.length%>,'<%=cur%>')"><%=(String)request.getAttribute(cur)%></a></li>
				<%} %>			
			    <li class="active">按性别划分</li>
			</ol>
		</section>
		<script>
         function getByDepartment(sex)
         {        	 
        	 var grade="";
        	 if(document.getElementById("grade"))
        		 grade=document.getElementById("grade").innerHTML;
        	 var list=document.getElementsByClassName("str");
        	 var str="";
        	 for(var i=0;i<list.length;i++)
        	 {
        		 if(list[i].id)
        		  str+=(list[i].id+'#');
        	 }        	 
        	 document.cookie='grade='+encodeURI(grade); 
        	 document.cookie='str='+encodeURI(str);       	 
        	 location="CardGroupByCollege?sex="+sex;
         }
         function getByMajor(sex)
         {
        	 var grade="";
        	 if(document.getElementById("grade"))
        		 grade=document.getElementById("grade").innerHTML; 
        	 var department="";
        	 if(document.getElementById("department"))
        		 department=document.getElementById("department").innerHTML; 
        	 
        	 var list=document.getElementsByClassName("str");
        	 var str="";
        	 for(var i=0;i<list.length;i++)
        	 {
        		 if(list[i].id)
        		  str+=(list[i].id+'#');
        	 }   
        	 document.cookie='department='+encodeURI(department); 
        	 document.cookie='grade='+encodeURI(grade); 
        	 document.cookie='str='+encodeURI(str); 
        	 location="CardGroupByMajor?sex="+sex;
         }
         function getByGrade(sex)
         {
        	 var department="";
        	 if(document.getElementById("department"))
        		 department=document.getElementById("department").innerHTML;
        	 var major="";
        	 if(document.getElementById("major"))
        		 major=document.getElementById("major").innerHTML;
        	 var list=document.getElementsByClassName("str");
        	 var str="";
        	 for(var i=0;i<list.length;i++)
        	 {
        		 if(list[i].id)
        		  str+=(list[i].id+'#');
        	 }        	
        	 document.cookie='department='+encodeURI(department); 
        	 document.cookie='major='+encodeURI(major); 
        	 document.cookie='str='+encodeURI(str); 
        	 location="CardGroupByGrade?sex="+sex;
         }
        </script>
        <div class="row">
           <%if(cardList!=null){if(cardList.size()>0){ %>
           <%for(int i=0;i<cardList.size();i++){ %>
           <%
              Object[] obj=(Object[])cardList.get(i);
              String sexName="";
              if(obj[0].toString().equals("1"))
              {
            	  sexName="男";
              }
              else
              {
            	  sexName="女";
              }
           %>
        	<div class="col-md-12">	
        		<section class="content-header">
		          <h4><%=sexName %></h4>
		        </section>

		        <section class="content">
					<div class="col-md-6 col-md-offset-3">	
						<div class="box box-info">
				            <div class="box-header">
				              <h3 class="box-title"><small>学院学生消费均值（单位：元）</small></h3>
				            </div>
				            <!-- /.box-header -->
				            <div class="box-body">
				            	<canvas id="barChart<%=i%>"></canvas>
				            </div>
				            <div class="box-footer">
				            	<div class="pull-right">
				            	<%if(department==null){ %>
				            	<a class="btn btn-info btn-sm" onclick="getByDepartment('<%=obj[0].toString()%>')"><small>按学院细分</small></a>
				            	<%} %>
				            	<%if(department!=null&&major==null){ %>
				            	<a class="btn btn-info btn-sm" onclick="getByMajor('<%=obj[0].toString()%>')"><small>按专业细分</small></a>
				            	<%} %>
				            	<%if(grade==null){ %>
				                <a class="btn btn-info btn-sm" onclick="getByGrade('<%=obj[0].toString()%>')"><small>按年级细分</small></a>
				                <%} %>
				            	</div>
				             </div>
			            </div>
			            <!-- /.box -->
					</div>
		        </section><!-- /.content -->
		    </div>
           <%} %>
           <%} } %>


        </div>
      </div><!-- /.content-wrapper -->



      <footer class="main-footer">
        <div class="pull-right hidden-xs">
          <b>Version</b> 1.0
        </div>
		<p class="text-center">
        <strong>重庆大学学工部</strong>
		</p>
      </footer>
    </div><!-- ./wrapper -->

    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.4 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- FastClick 
    从点击屏幕上的元素到触发元素的 click 事件，移动浏览器会有大约 300 毫秒的等待时间。
    它想看看你是不是要进行双击（double tap）操作。-->
    <script src="plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="dist/js/demo.js"></script>


    <!-- 2015年12月1日 添加的地方-->
	<!-- ChartJS 1.0.1 -->
    <script src="plugins/chartjs/Chart.min.js"></script>
    <%if(cardList!=null){if(cardList.size()>0){ %>
    <!-- Page script -->
    <script>
    	//一卡通数据概况
    	var barOptions = {
          responsive: true
        };
    	<%for(int i=0;i<cardList.size();i++){ %>
    	<%
    	  Object[] obj=(Object[])cardList.get(i);
    	%>
    	//学院1
		var bardata<%=i%>={
			labels : ["午餐均值","晚餐均值","总消费均值"],
			datasets : [
				{
					fillColor : "#0099CC",
					strokeColor : "#0099CC",
					data : [<%=obj[1].toString()%>,<%=obj[2].toString()%>,<%=obj[3].toString()%>]
				},
			]
		};
        var barChartCanvas<%=i%> = document.getElementById("barChart<%=i%>").getContext("2d");
        var barChart<%=i%> = new Chart(barChartCanvas<%=i%>).Bar(bardata<%=i%>,barOptions);
        <%} %>

    </script>
	<!-- /2015年12月1日 添加的地方-->
	<%}} %>
  </body>
</html>
<%}%>