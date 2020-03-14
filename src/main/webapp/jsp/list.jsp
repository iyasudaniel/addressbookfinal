<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<html>
  <head>
 
  <style><%@include file="/WEB-INF/style.css"%></style>
    <title>Address Book : List</Title>
  </head>
  <body>
  <header>
      <div class="left">
        <ul>
          <li><a href="add">Add Address</a></li>
          <li><a href="list">List Addresses</a></li>
        </ul>
      </div>
      <div class="right">
        <ul>
          <li><a href="list"> Edit Address</a></li>
          <li><a href="list">Delete Address</a></li>
        </ul>
      </div>
      <div id="logo">
        <img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100"/>
        <h5>Address Book Application</h5>
      </div>
    </header>
  
  
  


    <table>
    <c:forEach var="p" items="${requestScope.people}">
      <tr>
       <td>
         <a href="edit?id=${p.id}">Edit</a>
         <a href="delete?id=${p.id}">Delete</a>
       </td>
       <td>
         ${p.firstName} ${p.lastName} 
       </td>
      </tr>
    </c:forEach>
	
    </table>
</body>
</html>
