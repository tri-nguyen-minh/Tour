
package trinm.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import trinm.daos.AccountDAO;
import trinm.daos.DiscountDAO;
import trinm.dtos.AccountDTO;
import trinm.dtos.DiscountDTO;
import trinm.dtos.TourDTO;

/**
 *
 * @author TNM
 */
public class AccountController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AccountController.class);
    private static final String RETURN = "login.jsp";
    private static final String SEARCH = "TourSearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = RETURN;
        try {
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            AccountDAO dao = new AccountDAO();
            if (action.equals("Login Account")) {
                url = RETURN;
                request.setAttribute("NAMESEARCH", request.getParameter("txtNameSearch"));
                request.setAttribute("DATELOW", request.getParameter("dateLow"));
                request.setAttribute("DATEHIGH", request.getParameter("dateHigh"));
                request.setAttribute("PRICERANGE", request.getParameter("priceRange"));
                request.setAttribute("PAGE", request.getParameter("PAGE"));
            } else if (action.equals("Login")) {
                String id = request.getParameter("txtId");
                String password = request.getParameter("txtPassword");
                AccountDTO dto = dao.getAccount(id, password);
                if (dto != null) {
                    if(dto.getStatus().equals("Inactive")) {
                    request.setAttribute("NAMESEARCH", request.getParameter("txtNameSearch"));
                    request.setAttribute("DATELOW", request.getParameter("dateLow"));
                    request.setAttribute("DATEHIGH", request.getParameter("dateHigh"));
                    request.setAttribute("PRICERANGE", request.getParameter("priceRange"));
                    request.setAttribute("PAGE", request.getParameter("PAGE"));
                    request.setAttribute("ERROR", "This account is not available");
                    } else {
                    url = SEARCH + "?action=Search";
                    session.setAttribute("USER", dto);
                    if (dto.getRole().equals("user")) {
                        List<DiscountDTO> discountList = (new DiscountDAO()).getAllUserDiscount(id);
                        List<TourDTO> cart = new ArrayList<>();
                        session.setAttribute("DISCOUNT", discountList);
                        session.setAttribute("DISCOUNTCOUNT", discountList.size());
                        session.setAttribute("CART", cart);
                        session.setAttribute("CARTSIZE", 0);
                        session.setAttribute("CARTCOST", 0);
                    }
                }
                } else {
                    request.setAttribute("NAMESEARCH", request.getParameter("txtNameSearch"));
                    request.setAttribute("DATELOW", request.getParameter("dateLow"));
                    request.setAttribute("DATEHIGH", request.getParameter("dateHigh"));
                    request.setAttribute("PRICERANGE", request.getParameter("priceRange"));
                    request.setAttribute("PAGE", request.getParameter("PAGE"));
                    request.setAttribute("ERROR", "Your Username or Password is incorrect");
                }
            }
        } catch (Exception e) {
            System.out.println("AccountController");
            LOGGER.error(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
