<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>审核笑话</title>
  </head>
  
  <body>
  	<script language="JavaScript">  
	  var state;  
	  //初始化  
	  
	  function playerinit(){ 
	  	alert("当前播放"+player.Filename);  
	  }  
	   
	  //播放  
	  function  play()  
	  {  
	  	if (player.controls.isavailable('play'))  
	  	{  
	  		player.controls.play();  
	  		state=setInterval("updatetime()",1000);  
	  	}  
	  }  
	</script> 
  	
		<object classid="clsid:05589FA1-C356-11CE-BF01-00AA0055595A"
			id="player" width="239" height="250">
			<param name="Appearance" value="0">
			<param name="AutoStart" value="-1">
			<param name="AllowChangeDisplayMode" value="-1">
			<param name="AllowHideDisplay" value="0">
			<param name="AllowHideControls" value="-1">
			<param name="AutoRewind" value="-1">
			<param name="Balance" value="0">
			<param name="CurrentPosition" value="0">
			<param name="DisplayBackColor" value="0">
			<param name="DisplayForeColor" value="16777215">
			<param name="DisplayMode" value="0">
			<param name="Enabled" value="-1">
			<param name="EnableContextMenu" value="-1">
			<param name="EnablePositionControls" value="-1">
			<param name="EnableSelectionControls" value="0">
			<param name="EnableTracker" value="-1">
			<param name="Filename" value="<s:property value='selectedJoke.location'/>" valuetype="ref">
			<param name="FullScreenMode" value="0">
			<param name="MovieWindowSize" value="0">
			<param name="PlayCount" value="1">
			<param name="Rate" value="1">
			<param name="SelectionStart" value="-1">
			<param name="SelectionEnd" value="-1">
			<param name="ShowControls" value="-1">
			<param name="ShowDisplay" value="-1">
			<param name="ShowPositionControls" value="0">
			<param name="ShowTracker" value="-1">
			<param name="Volume" value="-480">
		</object>
		
		<s:form action="VerifyJoke">
	<table class="eTable">		
		<tbody id="tab">
			<tr>
				<td width="30%">标题</td>
				<td width="70%">
					<s:textfield name="selectedJoke.title"/>
				</td>
			</tr>
			<tr>
				<td>关键字</td>
				<td>
					<s:textfield name="selectedJoke.keyWord" />
				</td>
			</tr>
			<tr>
				<td>分类</td>
				<td>
								
				</td>
			</tr>
			<tr>
				<td>作者</td>
				<td>					
					<s:property value="selectedJoke.author" />
				</td>
			</tr>
			<tr>
				<td>上传时间</td>
				<td>
					<s:property value="selectedJoke.uploadTime" />
				</td>
			</tr>
			
			
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<s:submit value="确定" cssClass="bt_register"/>												
					<s:reset value="重置" cssClass="bt_login"/>						
				</td>
			</tr>
		</tfoot>
	</table>
	</s:form>
  </body>
</html>
