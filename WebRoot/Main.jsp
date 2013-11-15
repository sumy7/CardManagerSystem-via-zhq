<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head><title>联系人列表</title>
  <script>
  function deleteconfirm(userid){
  if (confirm("确实要删除该记录吗？")){
    location.href="DeleteCardAction?uid="+userid;
  }
  }
  
  </script>
  </head>
  <style>  
  th,td{
  border:2px solid gray;
  text-align:center;
  padding:2px 10px;
  }
  table{
  border-collapse:collapse;
  }
  body{
  text-align:center;
  }
  a{
  text-decoration:none
  }
  </style>
  <body>
  

<c:if test="${pageCount>0}">
<h2>所有联系人信息</h2>
共有${pageCount}页，这是第${pageNo}页。

  <c:if test="${pageNo>1}">
    <a href="FindAllAction?pageNo=1">第一页</a>
    <a href="FindAllAction?pageNo=${pageNo-1}">上一页</a>
  </c:if>
   
  <c:if test="${pageNo!=pageCount}">
    <a href="FindAllAction?pageNo=${pageNo+1}">下一页</a>
    <a href="FindAllAction?pageNo=${pageCount}">最后一页</a>
  </c:if>
  <s:form action="FindCardAction" name="fi" method="get" >
  
  <select name="searchkey">
  <option selected value="uname">姓名</option>
  <option value="uposition">职位</option>
  <option value="utel">电话</option>
  <option value="uemail">email</option>
  <option value="uaddress">住址</option>
  </select>
  <input type="text" name="keyword">
  <input type="submit" value="查询"></s:form>
  
  
  
  <br>
  <div>
<table style="margin:0 auto;text-align: left">
   <tr><th>&nbsp;</th><th>联系人ID</th><th>姓名</th><th>职位</th><th>电话</th><th>Email</th><th>地址</th><th>修改</th><th>删除至回收站</th><th>永久删除</th></tr>
   <c:forEach items="${cardlist}" var="card" varStatus="cardstatus" >
      <tr>
        <td>${cardstatus.count }</td>
        <td>${card.uid}</td>
        <td>${card.uname}</td>
        <td>${card.uposition}</td>
        <td>${card.utel}</td>
        <td>${card.uemail}</td>
        <td>${card.uaddress}</td>
         <td><a href="UpdateCardAction?uid=${card.uid}">修改</a></td>
         <td><a href="TDeleteCardAction?uid=${card.uid}">删除至回收站</a></td>
         <td><a href="javascript:deleteconfirm('${card.uid}')">删除</a></td>
      </tr>
   </c:forEach>
</table>
</div>
<br>

</c:if>
<c:if test="${pageCount==0}">
<p>目前没有记录</p>
</c:if>
<a href="AddCard.jsp">添加记录</a>
 </body>
</html>
