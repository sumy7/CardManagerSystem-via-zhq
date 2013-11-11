<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询结果</title>
    
  </head>
  
  <body>
    <s:form action="FindCardAction" method="post">
    	<s:textfield name="searchword" type="text"></s:textfield>
    	<s:submit>查询</s:submit>
    </s:form>
    <table>
                <tr>
                        <td>#</td>
                        <td>姓名</td>
                        <td>职务</td>
                        <td>电话</td>
                        <td>电子邮箱</td>
                        <td>住址</td>

                </tr>
                 <s:iterator value="cardlist" id="iter" status="st">
                        <tr>
                                <td><s:property value="#st.getIndex()+1" /></td>
                                <td><s:property value="#iter.uname" /></td>
                                <td><s:property value="#iter.uposition" /></td>
                                <td><s:property value="#iter.utel" /></td>
                                <td><s:property value="#iter.uemail" /></td>
                                <td><s:property value="#iter.uaddress" /></td>
                        </tr>
                </s:iterator>
    </table>
  </body>
</html>
