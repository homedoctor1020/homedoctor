<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<title>文件上传</title>

	<link href="<%=basePath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
</head>

<body>
<div class="container">
	<form action="uploadServlet?openid=${openid}" method="post"
		enctype="multipart/form-data" onsubmit="beginUpload()" target="_self">
		<div class="form-group">
			<label class="col-sm-2 control-label col-lg-offset-2">病历类别</label>
			<div class="col-sm-5 col-lg-offset-1">
				<select name="bingli_type">
					<option value="">请选择</option>
					<option title="类别1" value="1">类别1</option>
					<option title="类别2" value="2">类别2</option>
					<option title="类别3" value="3">类别3</option>

				</select> <br /> 
				<br /> 
				<input type="file" name="formFile"
					style="width:300px; height:30px; font-size: 14px"> 
					<br />
				<br /> 
				上传进度： 
				<br /> 
				<br />
				<div id="progress_bar"
					style="width: 400px; height: 20px; display: none; border: 1px solid black;">
					<div id="progress"
						style="background-color: red; height: 20px; width: 0px;"></div>
				</div>
				<br /> 
				<br /> 
				<input type="submit" value="Upload"
					style="width:100px; height:30px; font-size: 20px">
				<div id="msg"></div>
			</div>
		</div>
	</form>
	</div>
	<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">提示</h4>
      </div>
      <div class="modal-body">
        <p>请先在患者维护界面选择患者后再进行上传，谢谢</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>

<script>
	function beginUpload() {
		$("#progress_bar").show();
		setInterval("getUploadMeter()", 500);
	};
	function getUploadMeter() {
		$.post("servlet/getUploadPercent", function(data) {
			var json = eval("(" + data + ")");
			jQuery("#progress").css("width", json.percentage / 100 * 200 + "px");
			jQuery("#msg").css("padding", "1px").css("width", "400px").css("height", "20px").html("Finishing: " + json.percentage + "%");
		});
	};
	$(document).ready(function(){
	var openid = '${openid}';
	if(openid==null||openid==""){
	$('#myModal').modal('show');
	}
	});
	
	
</script>