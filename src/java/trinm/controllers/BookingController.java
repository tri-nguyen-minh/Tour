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
import trinm.daos.BookingDAO;
import trinm.daos.DiscountDAO;
import trinm.daos.TourDAO;
import trinm.dtos.AccountDTO;
import trinm.dtos.DiscountDTO;
import trinm.dtos.TourDTO;
import trinm.utils.Utilities;

/**
 *
 * @author TNM
 */
public class BookingController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BookingController.class);
    private static final String SEARCH = "TourSearchController";
    private static final String CART = "cartPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = CART, discountCode = "";
        HttpSession session = request.getSession();
        List<TourDTO> cart = null;
        double cartCost = 0;
        int cartSize = 0, discountValue = 0;
        try {
            String action = request.getParameter("action");
            if (action.equals("Book")) {
                String tourId = request.getParameter("tourId");
                TourDTO tourBooked = (new TourDAO()).findBookingTour(tourId);
                int slotBooked = (new BookingDAO()).getBookedSlots(tourId);
                tourBooked.setSlotAvailable(tourBooked.getQuota() - slotBooked);
                cart = (List<TourDTO>) session.getAttribute("CART");
                int slotBooking = Integer.parseInt(request.getParameter("slot" + tourId));
                tourBooked.setQuota(slotBooking);
                cartSize = Utilities.manageCart(cart, tourBooked);
                url = SEARCH + "?action=Search";
            } else if (action.equals("Update")) {
                cart = (List<TourDTO>) session.getAttribute("CART");
                for (int i = 0; i < cart.size(); i++) {
                    String tourId = cart.get(i).getId();
                    int newAmount = Integer.parseInt(request.getParameter(tourId + "Amount"));
                    cart.get(i).setQuota(newAmount);
                }
                cartSize = (int) session.getAttribute("CARTSIZE");
                url = CART;
            } else if (action.equals("Delete")) {
                System.out.println("in delete");
                cart = (List<TourDTO>) session.getAttribute("CART");
                cartSize = (int) session.getAttribute("CARTSIZE");
                for (int i = 0; i < cart.size(); i++) {
                    String tourForDeletion = cart.get(i).getId() + "ToDelete";
                    if (request.getParameter(tourForDeletion) != null) {
                        System.out.println("request: "+request.getParameter(tourForDeletion));
                        cart.remove(i);
                        i--;
                        cartSize--;
                    }
                }
                url = CART;
            } else if (action.equals("Use Discount")) {
                discountCode = request.getParameter("txtDiscount");
                List<DiscountDTO> discountList = (List<DiscountDTO>) session.getAttribute("DISCOUNT");
                cart = (List<TourDTO>) session.getAttribute("CART");
                if (!discountCode.equals("")) {
                    for (int i = 0; i < discountList.size(); i++) {
                        if (discountList.get(i).getDiscountCode().equals(discountCode)) {
                            discountValue = discountList.get(i).getValue();
                        }
                    }
                } else {
                    discountValue = -1;
                }
                cartSize = (int) session.getAttribute("CARTSIZE");
                url = CART;
            } else if (action.equals("Confirm")) {
                String errorMSG = "";
                AccountDTO account = (AccountDTO) session.getAttribute("USER");
                DiscountDTO discountDTO = new DiscountDTO();
                discountDTO.setId("NONE");
                discountDTO.setValue(0);
                List<DiscountDTO> discountList = (List<DiscountDTO>) session.getAttribute("DISCOUNT");
                for (int i = 0; i < discountList.size(); i++) {
                    if (discountList.get(i).getDiscountCode().equals(discountCode)) {
                        discountDTO = discountList.get(i);
                    }
                }
                cart = (List<TourDTO>) session.getAttribute("CART");
                for (int i = 0; i < cart.size(); i++) {
                    int slotBooked = (new BookingDAO()).getBookedSlots(cart.get(i).getId());
                    TourDTO tour = (new TourDAO()).findBookingTour(cart.get(i).getId());
                    if (cart.get(i).getQuota() > (tour.getQuota() - slotBooked)) {
                        errorMSG += tour.getTourName() + "<br>";
                        cart.remove(i);
                        i--;
                    }
                }
                if (errorMSG.equals("")) {
                    (new BookingDAO()).insertBooking(cart, account.getId(), discountDTO);
                    url = SEARCH;
                    (new TourDAO()).manageBookedTour(cart);
                    discountList = (new DiscountDAO()).getAllUserDiscount(account.getId());
                    cart = new ArrayList<>();
                    cartSize = 0;
                    session.setAttribute("DISCOUNT", discountList);
                    session.setAttribute("DISCOUNTCOUNT", discountList.size());
                } else {
                    cartSize = cart.size();
                    errorMSG = "The following tours are no longer available:<br>" + errorMSG;
                    url = CART;
                    request.setAttribute("CONFIRMERROR", errorMSG);
                }
            } else if (action.equals("Reload")) {
                cartSize = (int) session.getAttribute("CARTSIZE");
                cart = (List<TourDTO>) session.getAttribute("CART");
            }
            if (discountValue > 0) {
                cartCost = Utilities.getTotalCartPrice(cart, discountValue);
            } else {
                cartCost = Utilities.getTotalCartPrice(cart, 0);
            }
            request.setAttribute("DISCOUNTCODE", discountCode);
            request.setAttribute("DISCOUNTVALUE", discountValue);
            session.setAttribute("CARTSIZE", cartSize);
            session.setAttribute("CART", cart);
            session.setAttribute("CARTCOST", cartCost);
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
