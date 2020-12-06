package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class tourCreate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Tour Creation Page</title>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <link type=\"text/css\" rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header class=\"header\">\n");
      out.write("            <div class=\"top\">\n");
      out.write("                <div class=\"welcome\">Welcome, ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.USER.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("!</div>\n");
      out.write("                <div class=\"logout\">\n");
      out.write("                    <form action=\"LogoutController\" method=\"POST\">\n");
      out.write("                        <input type=\"submit\" name=\"action\" value=\"Logout\">\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"head\">\n");
      out.write("            </div>\n");
      out.write("            <nav>\n");
      out.write("                <ul>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"TourSearchController\" class=\"active\">Main Page</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"TourCreateController\">Create a new Tour</a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </nav>\n");
      out.write("        </header>\n");
      out.write("        <div class=\"main\">\n");
      out.write("            <section class=\"user-main\">\n");
      out.write("                <div class=\"info\">\n");
      out.write("                    <h1 id=\"header\">Create a new Tour!</h1>\n");
      out.write("                    <form action=\"TourCreateController\" method=\"POST\">\n");
      out.write("                        <div class=\"create-left\">\n");
      out.write("                            <h1 class='label'>Tour's Name</h1><input type=\"text\" name=\"txtName\" placeholder=\"Tour's Name\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.txtName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/></br>\n");
      out.write("                            <font color='red' font-size='10' style=\"margin-left: 5px\">\n");
      out.write("                            ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.INVALID.nameInvalid}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                            </font></br>\n");
      out.write("                            <h1 class='label'>Destination</h1><input type=\"text\" name=\"txtDestination\" placeholder=\"Tour's Destination\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.txtDestination}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/></br>\n");
      out.write("                            <font color='red' font-size='10' style=\"margin-left: 5px\">\n");
      out.write("                            ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.INVALID.destinationInvalid}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                            </font></br>\n");
      out.write("                            <h1 class='label'>Quota</h1><input type=\"text\" name=\"txtQuota\" placeholder=\"Tour's Quota\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.txtQuota}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/></br>\n");
      out.write("                            <font color='red' font-size='10' style=\"margin-left: 5px\">\n");
      out.write("                            ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.INVALID.quotaInvalid}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                            </font></br>\n");
      out.write("                            <h1 class='label'>Price</h1><input type=\"text\" name=\"txtPrice\" placeholder=\"Tour's Price\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.txtPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/></br>\n");
      out.write("                            <font color='red' font-size='10' style=\"margin-left: 5px\">\n");
      out.write("                            ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.INVALID.priceInvalid}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                            </font></br>\n");
      out.write("                            <input type=\"submit\" name=\"action\" value=\"Add Tour\" style=\"width: 150px;margin-left: 155px; margin-top: 35px\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"create-right\">\n");
      out.write("                            <h1 class='label'>Departing Date</h1><input id=\"date-input\" type=\"date\" name=\"dateDepart\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.dateDepart}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/></br>\n");
      out.write("                            <font color='red' font-size='10'>\n");
      out.write("                            ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.INVALID.dateDepartInvalid}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                            </font></br>\n");
      out.write("                            <h1 class='label'>Finishing Date</h1><input id=\"date-input\" type=\"date\" name=\"dateFinish\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.dateFinish}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/></br>\n");
      out.write("                            <font color='red' font-size='10'>\n");
      out.write("                            ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.INVALID.dateFinishInvalid}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                            </font></br>\n");
      out.write("                            <h1 class='label'>Image Link</h1>\n");
      out.write("                            <select class=\"comboBox\" id=\"txtImage\" name=\"txtImage\" onclick=\"testImage()\">\n");
      out.write("                                <option value=\"TBA.jpg\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.IMAGE eq 'TBA.jpg' ? 'selected=\"selected\"' : ''}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(">TBA.jpg</option>\n");
      out.write("                                ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                            <font color='red' font-size='10'>\n");
      out.write("                            Images from tourIMG folder!\n");
      out.write("                            </font></br></br>\n");
      out.write("                            <img src=\"img/tourIMG/TBA.jpg\" id=\"imageFrame\" width=\"300\" height=\"168\" style=\"margin-left: 5px;border: 2px solid\">\n");
      out.write("                        </div>\n");
      out.write("                    </form>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </section>\n");
      out.write("        </div>\n");
      out.write("        <script>\n");
      out.write("            function testImage() {\n");
      out.write("                var image = document.getElementById(\"txtImage\").value;\n");
      out.write("                var name = 'img/tourIMG/' + image;\n");
      out.write("                document.getElementById(\"imageFrame\").src = name;\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.IMAGELIST}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("image");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                    <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${image}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write('"');
          out.write(' ');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.IMAGE eq image ? 'selected=\"selected\"' : ''}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${image}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</option>\n");
          out.write("                                ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
