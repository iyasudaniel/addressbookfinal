package com.iyasu.addressbookfinal.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.iyasu.addressbookfinal.database.Database;
import com.iyasu.addressbookfinal.database.MySql;
import com.iyasu.addressbookfinal.page.AddEditPage;
import com.iyasu.addressbookfinal.page.DeletePage;
import com.iyasu.addressbookfinal.page.ListPage;
import com.iyasu.addressbookfinal.person.Person;
import com.iyasu.addressbookfinal.person.PersonStore;

/**
 * AddressBook Servlet.
 */
@SuppressWarnings("serial")
public class AddressBookServlet extends HttpServlet {

  private PersonStore store;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);

    try {
      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
      MySql driver =
          new MySql("//localhost/ict4300?user=ictuser&password=4300&serverTimezone=America/Denver");
      Database db = new Database(driver.connect());
      store = new PersonStore(db);
    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      throw new ServletException(e);
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String id = request.getParameter("id");
    if (id == null || id.isEmpty() || !id.matches("[0-9]+")) {
      store.create(toList(request.getParameter("firstName"), request.getParameter("lastName"),
    		  request.getParameter("street"),  request.getParameter("city"),  request.getParameter("state"), 
    		  request.getParameter("country"),  request.getParameter("postalCode")));
    } else if (request.getPathInfo().equals("/delete")) {
      String confirm = request.getParameter("confirm");
      if (confirm != null && confirm.equals("yes")) {
        store.remove(Integer.parseInt(id));
      }
    } else {
      store.update(Integer.parseInt(id),
          toList(request.getParameter("firstName"), request.getParameter("lastName"),
        		  request.getParameter("street"),  request.getParameter("city"),  request.getParameter("state"), 
        		  request.getParameter("country"),  request.getParameter("postalCode")));
    }
    response.sendRedirect("list");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String charset = request.getParameter("charset");
    if (charset == null) {
      charset = "UTF-8";
    }
    response.setCharacterEncoding(charset);
    response.setContentType("text/html");
    PrintWriter out = null;
    try {
      out = response.getWriter();
    } catch (IllegalStateException e) {
      out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), charset));
    }

    String id = request.getParameter("id");
    if (request.getPathInfo().equals("/add") || request.getPathInfo().equals("/edit")) {
      doAddEdit(request, response, out);
    } else if (request.getPathInfo().equals("/delete") 
        && id != null && !id.isEmpty() && id.matches("[0-9]+")) {
      doDelete(request, response, out);
    } else {
      doList(request, response, out);
    }
  }

  private void doAddEdit(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
      throws ServletException, IOException {

    String id = request.getParameter("id");

    String title = "Add";
    String action = "add";
    Person person = new Person(0, "", "","","","","","");

    if (id != null && !id.isEmpty() && id.matches("[0-9]+")) {
      title = "Edit";
      action = "edit";
      person = store.get(Integer.parseInt(id));
    }

    out.print(
        new AddEditPage().getPage(title, Arrays.asList(new Object[] {title, action, person})));
  }

  private void doDelete(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
      throws ServletException, IOException {

    String id = request.getParameter("id");
    Person person = store.get(Integer.parseInt(id));
    out.print(new DeletePage().getPage("Delete", Arrays.asList(new Object[] {person})));
  }

  private void doList(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
      throws ServletException, IOException {
    List<Person> people = store.getAll();
    
    List<Object> personObjects =  new ArrayList<>();
    personObjects.addAll(new ArrayList<>(people));
    out.print(new ListPage().getPage("List", personObjects));
  }

  private static List<String> toList(String... values) {
    return Arrays.asList(values);
  }
}

