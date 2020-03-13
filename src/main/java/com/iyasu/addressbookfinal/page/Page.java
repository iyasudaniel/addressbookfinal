package com.iyasu.addressbookfinal.page;

import java.util.List;

public abstract class Page {
  
  private static final String TEXT_TOP = 
      "<html> %n"
      + "  <head> %n"
      + "    <title>Address Book : %s</Title> %n" // name
      + "  </head> %n"
      + "  <body> %n"
      + "    <h1>Address Book : %s</h1> %n";       // name
  private static final String TEXT_BOTTOM = 
      "  </body> %n"
      + "</html> %n";
  
  protected abstract String getPageBody(List<Object> data);
  
  public String getPage(String name, List<Object> data) {
    return String.format(TEXT_TOP + getPageBody(data) + TEXT_BOTTOM, name, name);
  }
  
}
