/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.56
 * Generated at: 2020-07-06 08:10:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class idDupCheck_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
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
      out.write("<title>아이디 중복 검사</title>\r\n");
      out.write("</head>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\">\r\n");
      out.write("\r\n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.5.1.min.js\"></script>\r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\" integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("\tbody>*{\r\n");
      out.write("\t\tmargin-left: 75px;\r\n");
      out.write("\t}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- onload를 이용해 idValue() 실행 \r\n");
      out.write("\t== body 로딩 완료 후 idValue() 함수 실행\r\n");
      out.write("-->\r\n");
      out.write("<body onload=\"idValue();\">\r\n");
      out.write("\t<h4 class=\"mt-3\">아이디 중복 검사</h4>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<form action=\"");
      out.print( request.getContextPath() );
      out.write("/member/idDupCheck.do\" id=\"idChekcForm\" method=\"post\">\r\n");
      out.write("\t\t<input type=\"text\" id=\"id\" name=\"id\">\r\n");
      out.write("\t\t<input type=\"submit\" value=\"중복확인\">\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 사용 가능 여부 메세지 출력 -->\r\n");
      out.write("\t<span>\r\n");
      out.write("\t");

		if(request.getAttribute("result") !=null){
			int result = (int)request.getAttribute("result");
			//숫자만 가져올 수 있음(db를 다녀와서 오류가 없었다면 0아니면1)
			
			if(result > 0){
	
      out.write("\r\n");
      out.write("\t\t\t이미 사용중인 아이디 입니다.\r\n");
      out.write("\t");
  } else { 
      out.write("\r\n");
      out.write("\t\t\t사용 가능한 아이디 입니다. \r\n");
      out.write("\t");
 	
		
		}
			
		}
		
      out.write("\t\r\n");
      out.write("\t</span>\r\n");
      out.write("\t\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<input type=\"button\" id=\"cancel\" value=\"취소\" onclick=\"window.close();\">\r\n");
      out.write("\t\t<input type=\"button\" id=\"confirmId\" value=\"확인\" onclick=\"confirmId();\">\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tvar id;\r\n");
      out.write("\t\tvar result;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 팝업창이 오픈 완료 된 후 자동으로 실행되는 함수\r\n");
      out.write("\t\t// -> 팝업창 최초 오픈 시 if문이 동작되고\r\n");
      out.write("\t\t//    중복 체크 버튼으로 인한 팝업창 재 요청 시 else문이 실행됨. \r\n");
      out.write("\t\tfunction idValue(){\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tid = \"");
      out.print( request.getParameter("id"));
      out.write("\";\r\n");
      out.write("\t\t\tresult = ");
      out.print( request.getAttribute("result") );
      out.write(";\r\n");
      out.write("\t\t\t// result = 1; 랑 같은 모양 (\"\"쌍따옴표 안붙임)\r\n");
      out.write("\r\n");
      out.write("\t\t\tif(id == \"null\"){\r\n");
      out.write("\t\t\t\tid = opener.document.signUpForm.id.value;\r\n");
      out.write("\t\t\t\t// opener : 팝업창을 열은 부모창 \r\n");
      out.write("\t\t\t\t// document : 문서 \r\n");
      out.write("\t\t\t\t// signUpForm : 부모창 회원가입 form 태그의 name 속성\r\n");
      out.write("\t\t\t\t// id : 부모창 form 태그 내부에 name 속성이 id인 태그 선택\r\n");
      out.write("\t\t\t\t// value : 해당 input 태그의 값 \r\n");
      out.write("\t\t\t\t// == 부모창 id input 태그에 입력된 값 얻어오기 \r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\t\tdocument.getElementById(\"id\").value = id;\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//확인버튼을 눌렀을 경우 부모창에 전달할 값 제어\r\n");
      out.write("\t\tfunction confirmId(){ // 중복체크 확인 시 회원가입창 아이디 부분 비활성화\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t// 중복 체크 결과 result가 0일 경우 (중복 X)\r\n");
      out.write("\t\tif(result == 0){\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t// 부모창에 있는 아이디 input 태그에 중복 검사가 완료된 아이디를 전달\r\n");
      out.write("\t\t\topener.document.signUpForm.id.value \r\n");
      out.write("\t\t\t\t= document.getElementById(\"id\").value;\r\n");
      out.write("\t\t\t // 부모창에 현재 입력되어있는 아이디 전달하겠다\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t // 부모창에 hidden 타입 요소의 value값을 true로 변경 \r\n");
      out.write("\t\t\t opener.document.signUpForm.idDup.value = true;\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t     opener.document.signUpForm.idDup.value = false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif(opener != null){ \r\n");
      out.write("\t\t\topener.checkForm = null;\r\n");
      out.write("\t\t\tself.close(); // 현재 창 닫기\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#idChekcForm\").submit(function(){\r\n");
      out.write("\t\t\tvar regExp = /^[a-z][a-zA-z\\d]{5,11}$/;\r\n");
      out.write("\t\t\tif(!regExp.test($(\"#id\").val())){\r\n");
      out.write("\t\t\t\talert(\"유효하지 않은 아이디 형식 입니다.\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("      }\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t</script>\r\n");
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
