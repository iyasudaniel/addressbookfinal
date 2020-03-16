<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<html> 
  <head> 
    <title>Address Book : ${requestScope.title}</Title> 
  </head> 
  <body> 
    <h1>Address Book : ${requestScope.title}</h1> 
      Add a  Person.
    <ul>
      <li><a href='list'>Back</a>
    </ul>

    <form action='${requestScope.action}' method='post'>
      <table>
        <tr>
          <td>
            First name:
          </td>
          <td>
	  <c:if test="${!empty requestScope.id}">
            <input type='hidden' id='id' name='id' value='${requestScope.id}'>
          </c:if>
            <input type='text' id='firstname' name='firstname' value='${requestScope.firstname}'>
          </td>
        </tr>
        <tr>
          <td>
            Last name:
          </td>
          <td>
            <input type='text' id='lastname' name='lastname' value='${requestScope.lastname}'>
          </td>
        </tr>
        <tr>
          <td>
            <button type='submit'>${requestScope.title} Person</button>
          </td>
          <td>
            
          </td>
        </tr>
      </table>
    </form>
  </body> 
</html> 
