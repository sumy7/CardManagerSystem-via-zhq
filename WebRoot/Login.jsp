<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
  </head>
  
  <body>
    <s:form action="LogAction">
    <s:textfield name="log.logid" label="用户名"></s:textfield>
    <s:textfield name="log.logpwd" label="密码"></s:textfield>
    <s:reset value="重置"></s:reset>
    <s:submit value="登录"></s:submit>
    </s:form>
  </body>
</html>
