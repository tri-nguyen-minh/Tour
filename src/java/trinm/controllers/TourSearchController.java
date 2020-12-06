package trinm.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import trinm.daos.BookingDAO;
import trinm.daos.TourDAO;
import trinm.dtos.TourDTO;
import trinm.utils.Utilities;

/**
 *
 * @author TNM
 */
public class TourSearchController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(TourSearchController.class);
    private static final String SEARCH = "tourSearch.jsp";
    private static final String[] PRICERANGELIST = {"Not Specified", "Below 1500",
                                                    "1500 to 3000", "3000 to 5000",
                                                    "5000 to 10000", "10000 to 15000",
                                                    "15000 to 20000"};

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SEARCH;
        HttpSession session = request.getSession();
        String search = "", dateLowLimit = "", dateHighLimit = "";
        int priceLowLimit = 0, priceHighLimit = 20000, page = 1, totalPage = 0;
        List<TourDTO> result = null;
        TourDAO dao = new TourDAO();
        try {
            dao.deactivateTour();
            String action = request.getParameter("action");
            if (request.getParameter("PAGE") != null) {
                page = Integer.parseInt(request.getParameter("PAGE"));
                if (action.equals("Next Page")) {
                    page += 1;
                } else if (action.equals("Previous Page")) {
                    page -= 1;
                }
            }
            if (request.getParameter("txtNameSearch") != null) {
                search = request.getParameter("txtNameSearch");
            }
            if (request.getParameter("dateLow") != null) {
                dateLowLimit = request.getParameter("dateLow");
            }
            if (request.getParameter("dateHigh") != null) {
                dateHighLimit = request.getParameter("dateHigh");
            }
            if (!dateLowLimit.equals("") && !dateHighLimit.equals("")) {
                String[] dateRange = Utilities.manageDateLimit(dateLowLimit, dateHighLimit);
                dateLowLimit = dateRange[0];
                dateHighLimit = dateRange[1];
            }
            if (request.getParameter("priceRange") != null) {
                String priceRange = request.getParameter("priceRange");
                if (priceRange.equals("Below 1500")) {
                    priceHighLimit = 1500;
                } else if (!priceRange.equals("Not Specified") && !priceRange.equals("")) {
                    int[] rangeList = Utilities.manageNumberLimit(request.getParameter("priceRange"));
                    priceLowLimit = rangeList[0];
                    priceHighLimit = rangeList[1];
                }
            }
            result = dao.searchTour(search, dateLowLimit, dateHighLimit, priceLowLimit, priceHighLimit, page);
            for (int i = 0; i < result.size(); i++) {
                int slotsBooked = (new BookingDAO()).getBookedSlots(result.get(i).getId());
                result.get(i).setSlotAvailable(result.get(i).getQuota() - slotsBooked);
                if (slotsBooked == result.get(i).getQuota()) {
                    result.get(i).setStatus("Inactive");
                }
            }
            List<TourDTO> cart = (List<TourDTO>) session.getAttribute("CART");
            if (cart != null) {
                Utilities.manageTemporaryTourList(result, cart);
            }
            totalPage = dao.getTotalSearchPage(search, dateLowLimit, dateHighLimit, priceLowLimit, priceHighLimit);
            if (request.getParameter("priceRange") != null) {
                request.setAttribute("PRICERANGE", request.getParameter("priceRange"));
            } else {
                request.setAttribute("PRICERANGE", "Not Specified");
            }
            request.setAttribute("TOURLIST", result);
            request.setAttribute("TOTALPAGE", totalPage);
            request.setAttribute("PRICELIST", PRICERANGELIST);
            request.setAttribute("NAMESEARCH", search);
            request.setAttribute("DATELOW", dateLowLimit);
            request.setAttribute("DATEHIGH", dateHighLimit);
            request.setAttribute("PAGE", page);
        } catch (Exception e) {
            e.printStackTrace();
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
