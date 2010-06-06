<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>上传漫画</title>
	</head>
	<body>
		<script language="javascript">
			function addComponent()
			{
				var uploadHTML = document.createElement("<input type='file' name='cartoon'/>");
				document.getElementById("files").appendChild(uploadHTML);
			}
		</script>
	
		<div align="center">

			<s:form action="CartoonFileUploadAction" method="POST"
				enctype="multipart/form-data">
				<table class="eTable">
					<tbody id="tab">
						
						<tr>
							<td width="30%">
								<s:label value="标题" cssClass="gray"></s:label>
							</td>
							<td>
								<s:textfield name="title" label="标题" size="14" />
							</td>
						</tr>						
						
						<tr>
						
							<td>
								<s:label value="文件位置" cssClass="gray"></s:label>
							</td>
							<td>
								<span id="files">
									<input type="button" onclick="addComponent();" value="添加文件"/>									
									<input type='file' name='cartoon'/>																		
								</span>	
							</td>
							
						</tr>
						
							
						
					</tbody>
					<tfoot>					
						<tr>
							<td colspan="2">
								<s:submit value="上传" cssClass="bt_register" />
								<s:reset value="重置" cssClass="bt_login" />							
							</td>
						</tr>
					</tfoot>	

				</table>
			</s:form>
		</div>
	</body>
</html>