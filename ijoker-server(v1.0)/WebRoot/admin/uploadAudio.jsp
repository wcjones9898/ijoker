<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户登录</title>	
		<link href="styles/extra.css" rel="stylesheet" type="text/css">
		<link href="styles/slide.css" rel="stylesheet" type="text/css">		

	</head>
	<body bgcolor="#E5E5E5" text="#000000" link="#000000" vlink="#666666"
		alink="#666666" leftmargin="1" topmargin="1" marginwidth="1"
		marginheight="1">
		<script type="text/javascript">
			function changeValidateCode(obj) {
				//获取当前的时间作为参数，无具体意义   
				var timenow = new Date().getTime();
				//每次请求需要一个不同的参数，否则可能会返回同样的验证码   
				//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
				obj.src = "RandomAction.action?d=" + timenow;
			}
		</script>
		
		<div align="center">
			<table width="750" height="798" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td>
						<table width="750" height="243" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td valign="top">
									<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
										codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0"
										width="750" height="243">
										<param name="movie" value="images/header.swf">
										<param name="quality" value="high">
										<param name="LOOP" value="false">
										<param name="menu" value="false">
									</object>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border="0" background="images/login.jpg" width="750"
							height="476">
							<tr>
								<td colspan="3" height="36" />
							</td>
							<tr>
								<td width="2">
									&nbsp;
								</td>
								<td valign="top">
									<div align="center">							
										
										<s:form theme="simple" action="UploadJokeFileAction" method="POST" name="uploadJokeFileForm" enctype="multipart/form-data" >
										<table width="30%" height="30%">
											<tr>
												<td>
													<s:label value="文件位置" cssClass="gray"></s:label>
												</td>
												<td>
												<s:file id="jokeFile" name="jokeFile" size="14"></s:file>
													
												</td>
											</tr>
											<tr>
												<td>
													<s:label value="关键词" cssClass="gray"></s:label>
												</td>
												<td>
													<s:textfield name="keyWord"   label="关键词" size="14"/>
												</td>
											</tr>
											<tr>
												<td>
													<s:label value="标题" cssClass="gray"></s:label>
												</td>
												<td>
													<s:textfield name="title"   label="标题" size="14"/>
												</td>
											</tr>
											<tr>
												<td>
													<s:label value="验证码" cssClass="gray"></s:label>
												</td>
											
											</tr>
											<tr>
												<td colspan="2">
													<s:submit value="登录" cssClass="bt_register"/>												
													<s:reset value="重置" cssClass="bt_login"/><br>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													 <s:a href="TurnToRegister.action" cssClass="lost-pwd">注册新用户</s:a>
												</td>
											</tr>
											
										</table>
										</s:form>

									</div>
								</td>
								<td width="40">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="750" height="69" border="0" cellpadding="0"
							cellspacing="0" background="images/footer.jpg">
							<tr>
								<td height="40">
									<div align="center">
										<p>									
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="login.jsp" class="nav">User Login</a>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;::
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="Logout.action" class="nav">Log Out</a>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;::
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#" class="nav">About Us </a>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;::
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#" class="nav">Contact</a>
										</p>
									</div>
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
	function loginsubmit() {
		if (document.login.username.value.length < 1) {
			alert("请输入用户名！");
			login.username.focus();
			return false;
		}
		if (document.login.password.value.length < 1) {
			alert("请输入密码！");
			login.password.focus();
			return false;
		}
		if (document.login.rand.value.length < 1) {
			alert("请输入验证码！");
			login.rand.focus();
			return false;
		}
		return true;
	}
</script>

  <s:form action="UploadJokeFileAction" method="POST" enctype="multipart/form-data">
  
					<span class="STYLE2">文件位置：<span style="color: red">*</span>								</span>						
					<s:file id="jokeFile" name="jokeFile" cssClass="field"></s:file>
				
				
				<span class="STYLE2">名称：</span>					
				 <s:textfield name="title" cssClass="field"></s:textfield>
				
					<span class="STYLE2">描述：</span>							
				 <s:textarea name="keyWord" rows="8" cols="30"></s:textarea>
					
			      <s:submit value="上传"></s:submit>
			      
		</s:form>
  </body>
</html>
