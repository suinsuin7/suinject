/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.78
 * Generated at: 2023-08-01 11:57:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class test_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<h1>미지의 세계</h1>\r\n");
      out.write("<form>\r\n");
      out.write("	<input type=\"text\" name=\"goodWords\" value=\"경진\"><br>\r\n");
      out.write("	<input type=\"text\" name=\"badWords\" value=\"예린\"><br>\r\n");
      out.write("	<input type=\"file\" name=\"suinFile\" value=\"\" onchange=\"fChg(this)\">\r\n");
      out.write("</form>\r\n");
      out.write("<div id=\"disp\"></div>\r\n");
      out.write("<script>    \r\n");
      out.write("const myDisp = document.querySelector(\"#disp\");\r\n");
      out.write("const myForm = document.forms[0];\r\n");
      out.write("\r\n");
      out.write("function fChg(pThis) {\r\n");
      out.write("	\r\n");
      out.write("	let formData = new FormData(myForm);\r\n");
      out.write("	\r\n");
      out.write("	//아작스로 파일보내기를 하려면 꼭 FormData를 써야 함!\r\n");
      out.write("	/*\r\n");
      out.write("	let formData = new FormData(); // 무조건 자동으로 multipart/form-data로 전송됨\r\n");
      out.write("	console.log(\"로그\",pThis.files[0]);\r\n");
      out.write("	formData.append(\"suinFile\",pThis.files[0]);\r\n");
      out.write("	*/\r\n");
      out.write("	\r\n");
      out.write("	let xhr = new XMLHttpRequest();\r\n");
      out.write("	xhr.open(\"post\",\"/zzang/mFile\",true);\r\n");
      out.write("    xhr.onreadystatechange = function() {\r\n");
      out.write("        if (xhr.readyState == 4 && xhr.status == 200) {\r\n");
      out.write("            console.log(xhr.responseText);\r\n");
      out.write("            let myImg = document.createElement(\"img\");	// 이미지 태크 만들기\r\n");
      out.write("            myImg.src = xhr.responseText;	// 이미지 경로 세팅\r\n");
      out.write("            myImg.width = 100;\r\n");
      out.write("            myImg.height = 100;\r\n");
      out.write("            myDisp.appendChild(myImg);\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("    // get방식 이외에는 보내는 데이터를 send()안에 매개변수로 보내야 함\r\n");
      out.write("    xhr.send(formData); // 꼬옥 문자열로\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}