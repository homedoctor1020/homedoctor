<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"> 
	<title>预约医生</title>
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../bootstrap/js/bootstrap-datetimepicker.min.js"></script>
</head>
<body>

<form role="form" method="post" action="AddYyServlet" >
	<div class="form-group">
		<label for="name">姓名</label>
		<input type="text" class="form-control" name="name" 
			   placeholder="请输入患者姓名">
	</div>
	<div class="form-group">
		<label for="name">性别</label>
		<label class="checkbox-inline">
		<input type="radio" name="sex" id="sex1" value="男" checked> 男
	</label>
	<label class="checkbox-inline">
		<input type="radio" name="sex" id="sex2"  value="女"> 女
	</label>
	</div>
	<div class="form-group">
		<label for="name">年龄</label>
		<input type="text" class="form-control" name="age" id="age" 
			   placeholder="请输入患者年龄">
	</div>
	<div class="form-group">
		<label for="name">手机号码</label>
		<input type="text" class="form-control" name="phone" id="phone" 
			   placeholder="请输入患者手机号码">
	</div>
	<div class="form-group">
		<label for="name">就诊时间</label>
		<input type="datetime-local" class="form-control" name="jztime" id="jztime" 
			   placeholder="请输入患者就诊时间">
		
    </div>  
   
	
	
	
	<button type="submit" class="btn btn-default">提交</button>
</form>
	
</body>
</html>
  