<html> 
  <head> 
    <title>Address Book : Delete</Title> 
  </head> 
  <body> 
    <h1>Address Book : Delete</h1> 
      Delete a  Person.
    <ul>
      <li><a href='list'>Back</a>
    </ul>

    <form action='delete' method='post'>
      <input type='hidden' id='id' name='id' value='${requestScope.id}'>

      Delete Person: ${requestScope.firstName} ${requestScope.lastName}.<br>
      Are you sure?<br>
      <button type='submit' id='confirm' name='confirm' value='yes'>Yes</button> 
      <button type='submit' id='confirm' name='confirm' value='no'>No</button> 
    </form>
  </body> 
</html> 
