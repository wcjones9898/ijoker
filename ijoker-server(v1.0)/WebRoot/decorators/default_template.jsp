<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<link href="../styles/style.css" type="text/css" rel="stylesheet" >
		<LINK href="../styles/extra.css" type="text/css" rel="stylesheet">
		<title>iJoker——后台管理系统</title>
		<decorator:head />
	</head>

	<body bgcolor="#E5E5E5" text="#000000" link="#000000" vlink="#666666"
		alink="#666666" leftmargin="1" topmargin="1" marginwidth="1"
		marginheight="1">
		<div align="center">
			<table width="750" height="950" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="2" width="750" height="243">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
							codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0"
							width="750" height="243">
							<param name="movie" value="../images/header.swf">
							<param name="quality" value="high">
							<param name="LOOP" value="false">
							<param name="menu" value="false">
						</object>
					</td>
				</tr>
				<tr>
					<td width="197" height="608">
						<table border="0" cellspacing="0" cellpadding="0" width="197"
							height="608" background="../images/sidebar.gif">
							<tr>
								<td width="75%" height="100">&nbsp;</td>
								<td width="25%">&nbsp;</td>
							</tr>							
							<tr>
								<td height="100" class="bod">
									<h6 class="subol">
										笑话审核
									</h6>
									<ol>
										<li><a href="UnverifyListAction.action">未审核列表</a></li>	
									</ol>
								</td>
								<td>&nbsp;</td>
							</tr>
							
							<tr>
								<td height="100" class="bod">
									<h6 class="subol">
										笑话管理
									</h6>
									<ol>
										<li><a href="CatalogManageAction.action">分类管理</a></li>
										<li><a href="joke_upload.jsp">上传笑话</a></li>
										<li><a href="JokeListAction.action">笑话列表</a></li>										
									</ol>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="100" class="bod">
									<h6 class="subol">
										漫画管理
									</h6>
									<ol>
										<li><a href="cartoon_upload.jsp">上传漫画</a></li>
										<li><a href="CartoonListAction.action">漫画列表</a></li>
									</ol>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="100" class="bod">
									<h6 class="subol">
										用户管理
									</h6>
									<ol>
										<li><a href="UserListAction.action">用户列表</a></li>
										<li><a href="LoadLockedUser.action">已冻结用户</a></li>
									</ol>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="128" class="bod">									
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
							</tr>

						</table>
					</td>
					<td width="553" height="608" background="../images/content.jpg">
						<table width="500" height="608">							
							<tr>
								<td height="35" align="left" valign="bottom">
									<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您所在的位置：</strong>
									<decorator:title />
								</td>
							</tr>
							<tr valign="top">
								<td height="20" align="left">
									<s:actionmessage cssClass="error_message"/>
								</td>
							</tr>
							<tr>
								<td valign="top">									
									<decorator:body />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" width="750" height="69">
						<table width="750" height="69" border="0" cellpadding="0"
							cellspacing="0" background="../images/footer.jpg">
							<tr>
								<td height="40">
									<p>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="../login.jsp" class="nav">User Login</a>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;::
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="../Logout.action" class="nav">Log Out</a>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;::
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="#" class="nav">About Us </a>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;::
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="#" class="nav">Contact</a>
									</p>
								</td>
							</tr>
							<tr height="29" align="center">
								<td>
									<p>
										&copy;2009 All Right Reserved By CreditES Develop Team
										<br>
									</p>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<script type="text/javascript">
		
		var Ptr=document.getElementById("tab").getElementsByTagName("tr");
		function $() {
		    for (i=1;i<Ptr.length+1;i++) { 
		    Ptr[i-1].className = (i%2>0)?"t1":"t2"; 
		    }
		}
		window.onload=$;
		for(var i=0;i<Ptr.length;i++) {
		    Ptr[i].onmouseover=function(){
		    this.tmpClass=this.className;
		    this.className = "t3";
		    
		    };
		    Ptr[i].onmouseout=function(){
		    this.className=this.tmpClass;
		    };
		}
		
		</script>
	</body>
</html>