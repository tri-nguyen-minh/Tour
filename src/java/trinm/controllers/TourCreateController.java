package trinm.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import trinm.daos.TourDAO;
import trinm.dtos.TourDTO;
import trinm.dtos.TourInvalidObject;
import trinm.utils.Utilities;

/**
 *
 * @author TNM
 */
public class TourCreateController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(TourCreateController.class);
    private static final String SEARCH = "TourSearchController";
    private static final String INVALID = "tourCreate.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = INVALID;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = INVALID;
                request.setAttribute("IMAGELIST", Utilities.getImageList());
            } else if (action.equals("Add Tour")) {
                boolean tourValid = true;
                String tourName = request.getParameter("txtName");
                String destination = request.getParameter("txtDestination");
                String quotaString = request.getParameter("txtQuota");
                int quota = 0;
                String priceString = request.getParameter("txtPrice");
                int price = 0;
                String dateDepart = request.getParameter("dateDepart");
                String dateFinish = request.getParameter("dateFinish");
                String imageLink = "img/tourIMG/" + request.getParameter("txtImage");
                TourInvalidObject obj = new TourInvalidObject();
                if(tourName.equals("")) {
                    tourValid = false;
                    obj.setNameInvalid("Please enter Tour's Name!");
                }
                if(destination.equals("")) {
                    tourValid = false;
                    obj.setDestinationInvalid("Please enter Tour's Destination!");
                }
                if(!quotaString.matches("\\d{1,3}")) {
                    tourValid = false;
                    obj.setQuotaInvalid("Invalid Tour's Quota! Integer between 10 and 500.");
                } else {
                    quota = Integer.parseInt(quotaString);
                    if(quota < 10 || quota > 500) {
                    tourValid = false;
                    obj.setQuotaInvalid("Tour's Quota is preferably between 10 and 500!");
                    }
                }
                if(!priceString.matches("\\d{1,5}")) {
                    tourValid = false;
                    obj.setPriceInvalid("Invalid Tour Price! Integer between 100 and 20000.");
                } else {
                    price = Integer.parseInt(priceString);
                    if(price < 100 || price > 20000) {
                    tourValid = false;
                    obj.setPriceInvalid("Tour's Price is preferably between 100 and 20000!");
                    }
                }
                if(dateDepart.equals("")) {
                    tourValid = false;
                    obj.setDateDepartInvalid("Please select Departing Time!");
                }
                if(dateFinish.equals("")) {
                    tourValid = false;
                    obj.setDateFinishInvalid("Please select Finishing Time!");
                }
                if(!dateFinish.equals("") && !dateDepart.equals("")) {
                    if(Utilities.compareDate(dateDepart, dateFinish)) {
                    tourValid = false;
                    obj.setDateDepartInvalid("Departing Time cannot be after Finishing Time");
                    obj.setDateFinishInvalid("Finishing Time cannot be before Departing Time!");
                    }
                }
                if (tourValid) {
                    TourDAO dao = new TourDAO();
                    String id = "T" + (dao.countAllTour()+1);
                    TourDTO dto = new TourDTO(id, tourName, destination, dateDepart, dateFinish,
                            "", price, quota, imageLink, "");
                    if(dao.createTour(dto)) {
                        url = SEARCH;
                        request.setAttribute("NAMESEARCH", destination);
                    }
                } else {
                    request.setAttribute("IMAGE", request.getParameter("txtImage"));
                    request.setAttribute("INVALID", obj);
                    request.setAttribute("IMAGELIST", Utilities.getImageList());
                }
            }
        } catch (Exception e) {
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
