<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
  </head>
  
  <body>
    ${message}
    <s:form name="RegisterAction">
    <s:textfield name="log.logid" label="用户名"></s:textfield>
    <s:textfield name="log.logpwd" label="密码"></s:textfield>
    <s:reset value="重置"></s:reset>
    <s:reset value="注册"></s:reset>
    </s:form> 
  </body>
</html>
