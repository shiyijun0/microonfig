<%@page import="com.jwk.common.utils.wx.WxUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String state = "a145x7wae5x47f65a2d8we5";
	String back_url = WxUtils.getWXLoginUrl(state);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<title></title>
</head>
<body>
	<!-- <form action="https://open.weixin.qq.com/connect/qrconnect?appid=wx99526b78e9f02358&redirect_uri=http://www.tmxskymall.com/view/site/returnweixin.jsp&response_type=code&scope=snsapi_login&state=123456#wechat_redirect" method="post">
			<br /><br /><br /><br />
			<input type="submit" value="登录"/>
		</form> -->

	<!-- <a href="https://open.weixin.qq.com/connect/qrconnect?appid=wx99526b78e9f02358&redirect_uri=http%3A%2F%2Fwww.tmxskymall.com%2Fview%2Fsite%2Freturnweixin.jsp&response_type=code&scope=snsapi_login&state=123456#wechat_redirect" >微信登录</a> -->
	<a href="<%=back_url%>">微信登录</a>
</body>
</html>
