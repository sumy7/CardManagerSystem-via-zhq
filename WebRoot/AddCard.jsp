<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
  </head>
  <script type="text/javascript">
  function check(){
  var uid=form1.card.uid.value;
  var uname=form1.card.uname.value;
  var utel=form1.card.utel.value;
  if(uid==""||uname==""||utel==""){
  alert("联系人ID、姓名、电话都不能为空！");
  return false;
  }
  }
  </script>
  
  <body>
    <h2>添加联系人</h2>
    <s:form name="form1" action="AddAction" onsubmit="return check()">
    <s:textfield name="card.uid" lable="联系人ID"></s:textfield>
    <s:textfield name="card.uname" lable="姓名"></s:textfield>
    <s:textfield name="card.uposition" lable="职位"></s:textfield>
    <s:textfield name="card.utel" lable="电话"></s:textfield>
    <s:textfield name="card.uemail" lable="Email"></s:textfield>
    <s:textfield name="card.uadress" lable="地址"></s:textfield>
    <s:reset value="重置"></s:reset>
    <s:submit value="提交"></s:submit>
    </s:form>
  </body>
</html>
