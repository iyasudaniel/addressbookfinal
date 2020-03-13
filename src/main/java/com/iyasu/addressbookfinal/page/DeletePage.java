package com.iyasu.addressbookfinal.page;

import java.util.List;
import com.iyasu.addressbookfinal.person.Person;

public class DeletePage extends Page {

  private static final String TEXT = 
      "      Delete a  Person. %n"
      + "    <ul> %n"
      + "      <li><a href='list'>Back</a> %n"
      + "    </ul> %n"
      + "%n"
      + "    <form action='delete' method='post'> %n"
      + "      <input type='hidden' id='id' name='id' value='%s'> %n" // id
      + "%n"
      + "      Delete Person: %s %s.<br> %n" // firstname lastname
      + "      Are you sure?<br> %n"
      + "      <button type='submit' id='confirm' name='confirm' value='yes'>Yes</button> %n"
      + "      <button type='submit' id='confirm' name='confirm' value='no'>No</button> %n"
      + "    </form> %n";

  @Override
  public String getPageBody(List<Object> data) {
    Person p = (Person) data.get(0);
    return String.format(TEXT, p.getId(), p.getFirstName(), p.getLastName());
  }

}
