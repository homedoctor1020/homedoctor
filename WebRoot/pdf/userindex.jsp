<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>    
    <title>My JSP 'userindex.jsp' starting page</title>
    
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#tabs" ).tabs({
      event: "mouseover"
    });
  } );
  
  </script>
</head>
<body>
 
<div id="tabs">
  <ul>
    <li><a href="#tabs-1">检查报告</a></li>
    <li><a href="#tabs-2">健康方案</a></li>
    <li><a href="#tabs-3">就诊记录</a></li>
   
  </ul>
  <div id="tabs-1">
<p><a href="2.pdf" target="_blank">检查报告1</a></p>
<p><a href="2.pdf" target="_blank">检查报告2</a></p>
  </div>
  <div id="tabs-2">
 <p><a href="2.pdf" target="_blank">健康方案1</a></p>
 <p><a href="2.pdf" target="_blank">健康方案2</a></p>
    </div>
  <div id="tabs-3">
 <p><a href="2.pdf" target="_blank">就诊记录1</a></p>
  <p><a href="2.pdf" target="_blank">就诊记录2</a></p>
    </div>
</div>

 
</body>
</html>
 