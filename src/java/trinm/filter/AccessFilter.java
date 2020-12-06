/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trinm.dtos.AccountDTO;
import trinm.utils.Utilities;

/**
 *
 * @author TNM
 */
public class AccessFilter implements Filter {

    private static final boolean debug = true;
    
    private static final String LOGIN = "login.jsp";
    private static final String SEARCH = "TourSearchController";
    private static final String CREATE = "TourCreateController";
    private static final String BOOKING = "BookingController";
    private static final String ACCOUNT = "AccountController";
    private static final String LOGOUT = "LogoutController";
    private static final String DISCOUNT = "discountPage.jsp";
    private static final String CART = "cartPage.jsp";
    private static final String TBA = "TBA.jpg";
    private static final String CSS = "style.css";
    private static final String LOGIN_LOGO = "Loginlogo.jpg";
    private static final String HEAD_BACKGROUND = "top-bg.gif";
    private final List<String> ACCESSFORADMIN;
    private final List<String> ACCESSFORUSER;
    private final List<String> ACCESSFORGUESS;

    private FilterConfig filterConfig = null;

    public AccessFilter() {
        ACCESSFORADMIN = new ArrayList<>();
        ACCESSFORADMIN.add(SEARCH);
        ACCESSFORADMIN.add(CREATE);
        ACCESSFORADMIN.add(LOGOUT);
        ACCESSFORUSER = new ArrayList<>();
        ACCESSFORUSER.add(SEARCH);
        ACCESSFORUSER.add(BOOKING);
        ACCESSFORUSER.add(LOGOUT);
        ACCESSFORUSER.add(CART);
        ACCESSFORUSER.add(DISCOUNT);
        ACCESSFORGUESS = new ArrayList<>();
        ACCESSFORGUESS.add(SEARCH);
        ACCESSFORGUESS.add(LOGIN);
        ACCESSFORGUESS.add(ACCOUNT);
        List<String> imageList = Utilities.getImageList();
        for (int i = 0; i < imageList.size(); i++) {
            ACCESSFORADMIN.add(imageList.get(i));
            ACCESSFORUSER.add(imageList.get(i));
            ACCESSFORGUESS.add(imageList.get(i));
        }
        ACCESSFORADMIN.add(TBA);
        ACCESSFORUSER.add(TBA);
        ACCESSFORGUESS.add(TBA);
        ACCESSFORADMIN.add(HEAD_BACKGROUND);
        ACCESSFORUSER.add(HEAD_BACKGROUND);
        ACCESSFORGUESS.add(HEAD_BACKGROUND);
        ACCESSFORADMIN.add(CSS);
        ACCESSFORUSER.add(CSS);
        ACCESSFORGUESS.add(CSS);
        ACCESSFORGUESS.add(LOGIN_LOGO);
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AccessFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AccessFilter:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String uri = req.getRequestURI();
        String resource = uri.substring(uri.lastIndexOf("/") + 1);
        boolean pathRedirect = false;
        if (session != null) {
            if (session.getAttribute("USER") != null) {
                AccountDTO dto = (AccountDTO) session.getAttribute("USER");
                if (dto.getRole().equals("admin") && !ACCESSFORADMIN.contains(resource)) {
                    pathRedirect = true;
                } else if (!dto.getRole().equals("admin") && !ACCESSFORUSER.contains(resource)) {
                    pathRedirect = true;
                }
            } else {
                if (!ACCESSFORGUESS.contains(resource)) {
                    pathRedirect = true;
                }
            }
        } else {
            if (!ACCESSFORGUESS.contains(resource)) {
                pathRedirect = true;
            }
        }
        if (pathRedirect) {
            res.sendRedirect(SEARCH);
        } else {
            chain.doFilter(request, response);
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AccessFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AccessFilter()");
        }
        StringBuffer sb = new StringBuffer("AccessFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
