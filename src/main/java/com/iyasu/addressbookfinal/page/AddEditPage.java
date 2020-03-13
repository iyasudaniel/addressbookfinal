
package com.iyasu.addressbookfinal.page;

import java.util.List;
import com.iyasu.addressbookfinal.person.Person;

public class AddEditPage extends Page {

  private static final String HIDDEN =
      "            <input type='hidden' id='id' name='id' value='%s'> %n";

  private static final String TEXT = 
      "      %s a  Person. %n" // title - data.get(0)
      + "    <ul> %n"
      + "      <li><a href='list'>Back</a> %n"
      + "    </ul> %n"
      + "%n"
      + "    <form action='%s' method='post'> %n" // action - data.get(1)
      + "      <table> %n"
      + "        <tr> %n"
      + "          <td> %n"
      + "            First name: %n"
      + "          </td> %n"
      + "          <td> %n"
      + "            %s %n" // hidden
      + "            <input type='text' id='firstname' name='firstname' value='%s'> %n" // firstname
      + "          </td> %n"
      + "        </tr> %n"
      + "        <tr> %n"
      + "          <td> %n"
      + "            Last name: %n"
      + "          </td> %n"
      + "          <td> %n"
      + "            <input type='text' id='lastname' name='lastname' value='%s'> %n" // lastname
      + "          </td> %n"
      + "        </tr> %n"
      + "        <tr> %n"
      + "          <td> %n"
      + "            <button type='submit'>%s Person</button> %n" // title - data.get(0)
      + "          </td> %n"
      + "          <td> %n"
      + "             %n"
      + "          </td> %n"
      + "        </tr> %n"
      + "      </table> %n"
      + "    </form> %n";

  @Override
  public String getPageBody(List<Object> data) {
    Person p = (Person) data.get(2);
    String hidden = "";
    if (p.getId() > 0) {
      hidden = String.format(HIDDEN, p.getId());
    }
    return String.format(
        TEXT, data.get(0), data.get(1), hidden, p.getFirstName(), p.getLastName(), data.get(0));
  }

}
