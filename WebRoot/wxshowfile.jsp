<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
  <meta name="viewport" content="initial-scale=1.0, user-scalable=yes" />
  <title>查看病历</title>
  <link href="jquery/jquery.touchPDF.css" rel="stylesheet" media="screen" />
	<style>
		body , html{
			background-color: #404040;
			height: 100%;
			padding: 0;
			margin: 0;
		}
	</style>
</head>  
<body>  
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript">
    $(function(){
        $("#displayPdfIframe").attr("src",'<c:url value="/pdf/web/viewer.html" />?file=' + encodeURIComponent('<c:url value="/displayPDF?openid=${openid}&filename=${filename}"/>'));
    });
</script>
<div class="ctrlDiv">
    <div class="eleContainer elePaddingBtm">
        <iframe id="displayPdfIframe" width="100%" height="100%"></iframe>
    </div>
</div> 

</body>  
</html>  