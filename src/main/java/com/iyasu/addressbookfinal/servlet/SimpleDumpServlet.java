package com.iyasu.addressbookfinal.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@SuppressWarnings("serial")
public class SimpleDumpServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
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

    out.println("<html>");
    out.println("  <head>");
    out.println("    <title>Request Dump</Title>");
    out.println("  </head>");
    out.println("  <body>");
    out.println("    <h1>Request Dump</h1>");
    
    debugInfo(this, request, response, out);
    
    out.println("  </body>");
    out.println("</html>");

  }

  public static void debugInfo(HttpServlet servlet, HttpServletRequest request,
      HttpServletResponse response, PrintWriter pout) {
    ServletContext servletContext = servlet.getServletContext();
    try {
      pout.write("<table width=\"95%\">");
      pout.write("<tr>\n");
      pout.write("<th align=\"right\">getContentLength:&nbsp;</th>");
      pout.write("<td>" + request.getContentLength() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getContentType:&nbsp;</th>");
      pout.write("<td>" + notag(request.getContentType()) + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getContextPath:&nbsp;</th>");
      pout.write("<td>" + request.getContextPath() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getDispatcherType:&nbsp;</th>");
      pout.write("<td>" + request.getDispatcherType() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getLocale:&nbsp;</th>");
      pout.write("<td>" + request.getLocale() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getLocalName:&nbsp;</th>");
      pout.write("<td>" + request.getLocalName() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getLocalAddr:&nbsp;</th>");
      pout.write("<td>" + request.getLocalAddr() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getLocalPort:&nbsp;</th>");
      pout.write("<td>" + request.getLocalPort() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getMethod:&nbsp;</th>");
      pout.write("<td>" + notag(request.getMethod()) + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getPathInfo:&nbsp;</th>");
      pout.write("<td>" + notag(request.getPathInfo()) + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getPathTranslated:&nbsp;</th>");
      pout.write("<td>" + notag(request.getPathTranslated()) + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getProtocol:&nbsp;</th>");
      pout.write("<td>" + request.getProtocol() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getQueryString:&nbsp;</th>");
      pout.write("<td>" + notag(request.getQueryString()) + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getRemoteAddr:&nbsp;</th>");
      pout.write("<td>" + request.getRemoteAddr() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getRemoteHost:&nbsp;</th>");
      pout.write("<td>" + request.getRemoteHost() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getRemotePort:&nbsp;</th>");
      pout.write("<td>" + request.getRemotePort() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getRemoteUser:&nbsp;</th>");
      pout.write("<td>" + request.getRemoteUser() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getRequestedSessionId:&nbsp;</th>");
      pout.write("<td>" + request.getRequestedSessionId() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getRequestURI:&nbsp;</th>");
      pout.write("<td>" + notag(request.getRequestURI()) + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getRequestURL:&nbsp;</th>");
      pout.write("<td>" + notag(request.getRequestURL().toString()) + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getScheme:&nbsp;</th>");
      pout.write("<td>" + request.getScheme() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getServerName:&nbsp;</th>");
      pout.write("<td>" + notag(request.getServerName()) + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getServletPath:&nbsp;</th>");
      pout.write("<td>" + notag(request.getServletPath()) + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getServerPort:&nbsp;</th>");
      pout.write("<td>" + request.getServerPort() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">getUserPrincipal:&nbsp;</th>");
      pout.write("<td>" + request.getUserPrincipal() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">isAsyncStarted():&nbsp;</th>");
      pout.write("<td>" + request.isAsyncStarted() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">isAsyncSupported():&nbsp;</th>");
      pout.write("<td>" + request.isAsyncSupported() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">isSecure():&nbsp;</th>");
      pout.write("<td>" + request.isSecure() + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">isUserInRole(admin):&nbsp;</th>");
      pout.write("<td>" + request.isUserInRole("admin") + "</td>");
      pout.write("</tr><tr>\n");
      pout.write("<th align=\"right\">encodeRedirectURL(/foo?bar):&nbsp;</th>");
      pout.write("<td>" + response.encodeRedirectURL("/foo?bar") + "</td>");

      Enumeration<Locale> locales = request.getLocales();
      while (locales.hasMoreElements()) {
        pout.write("</tr><tr>\n");
        pout.write("<th align=\"right\">getLocales:&nbsp;</th>");
        pout.write("<td>" + locales.nextElement() + "</td>");
      }
      pout.write("</tr><tr>\n");

      pout.write("<th align=\"left\" colspan=\"2\"><big><br/>Other HTTP Headers:</big></th>");
      Enumeration<String> h = request.getHeaderNames();
      String name;
      while (h.hasMoreElements()) {
        name = h.nextElement();

        Enumeration<String> h2 = request.getHeaders(name);
        while (h2.hasMoreElements()) {
          String hv = h2.nextElement();
          pout.write("</tr><tr>\n");
          pout.write("<th align=\"right\">" + notag(name) + ":&nbsp;</th>");
          pout.write("<td>" + notag(hv) + "</td>");
        }
      }

      pout.write("</tr><tr>\n");
      pout.write("<th align=\"left\" colspan=\"2\"><big><br/>Request Parameters:</big></th>");
      h = request.getParameterNames();
      while (h.hasMoreElements()) {
        name = h.nextElement();
        pout.write("</tr><tr>\n");
        pout.write("<th align=\"right\">" + notag(name) + ":&nbsp;</th>");
        pout.write("<td>" + notag(request.getParameter(name)) + "</td>");
        String[] values = request.getParameterValues(name);
        if (values == null) {
          pout.write("</tr><tr>\n");
          pout.write("<th align=\"right\">" + notag(name) + " Values:&nbsp;</th>");
          pout.write("<td>" + "NULL!" + "</td>");
        } else if (values.length > 1) {
          for (int i = 0; i < values.length; i++) {
            pout.write("</tr><tr>\n");
            pout.write("<th align=\"right\">" + notag(name) + "[" + i + "]:&nbsp;</th>");
            pout.write("<td>" + notag(values[i]) + "</td>");
          }
        }
      }

      try {
        Collection<Part> parts = request.getParts();
        if (parts != null && !parts.isEmpty()) {
          pout.write("</tr><tr>\n");
          pout.write("<th align=\"left\" colspan=\"2\"><big><br/>Parts:</big></th>");
          for (Part p : parts) {
            pout.write("</tr><tr>\n");
            pout.write("<th align=\"right\">" + notag(p.getName()) + ":&nbsp;</th>");
            pout.write("<td>" + p + "</td>");
          }
        }
      } catch (ServletException e) {
        pout.write("</tr><tr>\n");
        pout.write("<th align=\"left\" colspan=\"2\"><big><br/>No Parts!</big></th>");
      }

      pout.write("</tr><tr>\n");
      pout.write("<th align=\"left\" colspan=\"2\"><big><br/>Cookies:</big></th>");
      Cookie[] cookies = request.getCookies();
      for (int i = 0; cookies != null && i < cookies.length; i++) {
        Cookie cookie = cookies[i];

        pout.write("</tr><tr>\n");
        pout.write("<th align=\"right\">" + notag(cookie.getName()) + ":&nbsp;</th>");
        pout.write("<td>" + notag(cookie.getValue()) + "</td>");
      }

      String contentType = request.getContentType();
      if (contentType != null && !contentType.startsWith("application/x-www-form-urlencoded")
          && !contentType.startsWith("multipart/form-data")) {
        pout.write("</tr><tr>\n");
        pout.write("<th align=\"left\" valign=\"top\" colspan=\"2\"><big><br/>Content:</big></th>");
        pout.write("</tr><tr>\n");
        pout.write("<td><pre>");
        char[] content = new char[4096];
        int len;
        try {
          Reader in = request.getReader();

          while ((len = in.read(content)) >= 0) {
            pout.write(notag(new String(content, 0, len)));
          }
        } catch (IOException e) {
          pout.write(e.toString());
        }

        pout.write("</pre></td>");
      }

      pout.write("</tr><tr>\n");
      pout.write("<th align=\"left\" colspan=\"2\"><big><br/>Request Attributes:</big></th>");
      Enumeration<String> a = request.getAttributeNames();
      while (a.hasMoreElements()) {
        name = a.nextElement();
        pout.write("</tr><tr>\n");
        pout.write(
            "<th align=\"right\" valign=\"top\">" + name + ":&nbsp;</th>");
        Object value = request.getAttribute(name);
        if (value instanceof File) {
          File file = (File) value;
          pout.write("<td>" + "<pre>" + file.getName() + " (" + file.length() + " "
              + new Date(file.lastModified()) + ")</pre>" + "</td>");
        } else
          pout.write("<td>" + "<pre>" + toString(request.getAttribute(name)) + "</pre>" + "</td>");
      }
      request.setAttribute("org.eclipse.jetty.servlet.MultiPartFilter.files", null);

      pout.write("</tr><tr>\n");
      pout.write("<th align=\"left\" colspan=\"2\"><big><br/>Servlet InitParameters:</big></th>");
      a = servlet.getInitParameterNames();
      while (a.hasMoreElements()) {
        name = a.nextElement();
        pout.write("</tr><tr>\n");
        pout.write("<th align=\"right\">" + name + ":&nbsp;</th>");
        pout.write("<td>" + toString(servlet.getInitParameter(name)) + "</td>");
      }

      pout.write("</tr><tr>\n");
      pout.write("<th align=\"left\" colspan=\"2\"><big><br/>Context InitParameters:</big></th>");
      a = servletContext.getInitParameterNames();
      while (a.hasMoreElements()) {
        name = a.nextElement();
        pout.write("</tr><tr>\n");
        pout.write(
            "<th align=\"right\" valign=\"top\">" + name + ":&nbsp;</th>");
        pout.write("<td>" + toString(servletContext.getInitParameter(name)) + "</td>");
      }

      pout.write("</tr><tr>\n");
      pout.write("<th align=\"left\" colspan=\"2\"><big><br/>Context Attributes:</big></th>");
      a = servletContext.getAttributeNames();
      while (a.hasMoreElements()) {
        name = a.nextElement();
        pout.write("</tr><tr>\n");
        pout.write(
            "<th align=\"right\" valign=\"top\">" + name + ":&nbsp;</th>");
        pout.write(
            "<td>" + "<pre>" + toString(servletContext.getAttribute(name)) + "</pre>" + "</td>");
      }

      String res = request.getParameter("resource");
      if (res != null && res.length() > 0) {
        pout.write("</tr><tr>\n");
        pout.write(
            "<th align=\"left\" colspan=\"2\"><big><br/>Get Resource: \"" + res + "\"</big></th>");

        pout.write("</tr><tr>\n");
        pout.write("<th align=\"right\">servletContext.getResource(...):&nbsp;</th>");
        try {
          pout.write("<td>" + servletContext.getResource(res) + "</td>");
        } catch (Exception e) {
          pout.write("<td>" + "" + e + "</td>");
        }

        pout.write("</tr><tr>\n");
        pout.write("<th align=\"right\">servletContext.getResourcePaths(...):&nbsp;</th>");
        try {
          pout.write("<td>" + servletContext.getResourcePaths(res) + "</td>");
        } catch (Exception e) {
          pout.write("<td>" + "" + e + "</td>");
        }

        pout.write("</tr><tr>\n");
        pout.write("<th align=\"right\">servletContext.getRealPath(...):&nbsp;</th>");
        try {
          pout.write("<td>" + servletContext.getRealPath(res) + "</td>");
        } catch (Exception e) {
          pout.write("<td>" + "" + e + "</td>");
        }

        pout.write("</tr><tr>\n");
        pout.write("<th align=\"right\">servletContext.getContext(...):&nbsp;</th>");

        ServletContext context = servletContext.getContext(res);
        pout.write("<td>" + context + "</td>");

        if (context != null) {
          pout.write("</tr><tr>\n");
          pout.write(
              "<th align=\"right\">servletContext.getContext(...).getResource(...):&nbsp;</th>");
          try {
            pout.write("<td>" + context.getResource(res) + "</td>");
          } catch (Exception e) {
            pout.write("<td>" + "" + e + "</td>");
          }

          pout.write("</tr><tr>\n");
          pout.write(
              "<th align=\"right\">servletContext.getContext(...).getResourcePaths(...):&nbsp;</th>");
          try {
            pout.write("<td>" + context.getResourcePaths(res) + "</td>");
          } catch (Exception e) {
            pout.write("<td>" + "" + e + "</td>");
          }

          String cp = context.getContextPath();
          if (cp == null || "/".equals(cp))
            cp = "";
          pout.write("</tr><tr>\n");
          pout.write(
              "<th align=\"right\">servletContext.getContext(...).getRequestDispatcher(...):&nbsp;</th>");
          pout.write("<td>" + context.getRequestDispatcher(res.substring(cp.length())) + "</td>");

          pout.write("</tr><tr>\n");
          pout.write(
              "<th align=\"right\">servletContext.getContext(...).getRealPath(...):&nbsp;</th>");
          pout.write("<td>" + context.getRealPath(res.substring(cp.length())) + "</td>");
        }

        pout.write("</tr><tr>\n");
        pout.write("<th align=\"right\">this.getClass().getResource(...):&nbsp;</th>");
        pout.write("<td>" + servlet.getClass().getResource(res) + "</td>");

        pout.write("</tr><tr>\n");
        pout.write(
            "<th align=\"right\">this.getClass().getClassLoader().getResource(...):&nbsp;</th>");
        pout.write("<td>" + servlet.getClass().getClassLoader().getResource(res) + "</td>");

        pout.write("</tr><tr>\n");
        pout.write(
            "<th align=\"right\">Thread.currentThread().getContextClassLoader().getResource(...):&nbsp;</th>");
        pout.write(
            "<td>" + Thread.currentThread().getContextClassLoader().getResource(res) + "</td>");
        pout.write("</tr><tr>\n");
        pout.write(
            "<th align=\"right\">Thread.currentThread().getContextClassLoader().getResources(...):&nbsp;</th>");
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(res);
        if (urls == null)
          pout.write("<td>null</td>");
        else
          pout.write("<td>" + Collections.list(urls) + "</td>");
      }

      pout.write("</tr></table>\n");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static String notag(String s) {
    if (s == null)
      return "null";
    s = s.replace("&", "&amp;");
    s = s.replace("<", "&lt;");
    s = s.replace(">", "&gt;");
    return s;
  }

  private static String toString(Object o) {
    if (o == null)
      return null;

    try {
      if (o.getClass().isArray()) {
        StringBuffer sb = new StringBuffer();
        if (!o.getClass().getComponentType().isPrimitive()) {
          Object[] array = (Object[]) o;
          for (int i = 0; i < array.length; i++) {
            if (i > 0)
              sb.append("\n");
            sb.append(array.getClass().getComponentType().getName());
            sb.append("[");
            sb.append(i);
            sb.append("]=");
            sb.append(toString(array[i]));
          }
          return sb.toString();
        } else {
          int length = Array.getLength(o);
          for (int i = 0; i < length; i++) {
            if (i > 0)
              sb.append("\n");
            sb.append(o.getClass().getComponentType().getName());
            sb.append("[");
            sb.append(i);
            sb.append("]=");
            sb.append(toString(Array.get(o, i)));
          }
          return sb.toString();
        }
      } else
        return o.toString();
    } catch (Exception e) {
      return e.toString();
    }
  }
}

