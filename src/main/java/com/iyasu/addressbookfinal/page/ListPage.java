package com.iyasu.addressbookfinal.page;

import java.util.List;
import com.iyasu.addressbookfinal.person.Person;

public class ListPage extends Page {
  
  private static final String TEXT_TOP = 
      "      Welcome to the Address Book. %n"
      + "    <ul> %n"
      + "      <li><a href='add'>Add a Person</a> %n"
      + "    </ul> %n"
      + " %n"
      + "    <table> %n";
  private static final String TABLE_ROW =
      "      <tr> %n"
      + "       <td> %n"
      + "         <a href='edit?id=%s'>Edit</a> %n"     // id
      + "         <a href='delete?id=%s'>Delete</a> %n" // id
      + "       </td> %n"
      + "       <td> %n"
      + "         %s %s %s %s %s %s %s %n"  // firstname lastname street city state country zip
      + "</td> %n"
      + "      </tr> %n";
  private static final String TEXT_BOTTOM = "    </table> %n";

  @Override
  public String getPageBody(List<Object> data) {
    String text = String.format(TEXT_TOP);
    for (Object obj : data) {
      Person p = (Person) obj;
      text += String.format(TABLE_ROW, p.getId(),p.getId(), p.getFirstName(), p.getLastName(),
    		  p.getStreet(),
    	        p.getCity(),p.getState(),p.getCountry(),p.getPostalCode());
    }
    text += String.format(TEXT_BOTTOM);
    return text;
  }

}
