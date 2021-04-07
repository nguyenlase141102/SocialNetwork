/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nguyenla.account.AccountDAO;
import nguyenla.notification.Notification_accountDAO;
import nguyenla.notification.Notification_accountDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class NotificationAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String LOADHOMEPAGE = "LoadHomePageServlet";
    private String url;
    private String type;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            String typeNotifi = (String) request.getAttribute("typeInteract");
            AccountDAO ac = new AccountDAO();
            Notification_accountDAO noDAO = new Notification_accountDAO();
            System.out.println("type: " + typeNotifi);
            if (typeNotifi.equals("Comment")) {
                String userNotifi = (String) request.getAttribute("userComment");
                String dateNotifi = (String) request.getAttribute("dateComment");
                String contentNotifi = (String) request.getAttribute("contentComment");
                String contentSplit = contentNotifi.substring(0, 4) + "....";
                int idArticle = (int) request.getAttribute("idArticle");
                String email = ac.getEmail(userNotifi);
                type = "Comments";
                Notification_accountDTO noDTO = new Notification_accountDTO(userNotifi, dateNotifi, typeNotifi, contentSplit, idArticle, email);
                if (noDAO.insertInteraction(noDTO)) {
                    url = LOADHOMEPAGE;
                }
            } else {

                String userNotifi = (String) request.getAttribute("userEmotions");
                String dateNotifi = (String) request.getAttribute("dateEmotions");
                String typeEmotions = (String) request.getAttribute("typeEmotions");
                String contenNotifi = userNotifi + " " + typeEmotions + " this post";
                int idArticle = (int) request.getAttribute("idSaveArticle");
                String email = ac.getEmail(userNotifi);

                type = "Emotions";

                Notification_accountDTO noDTO = new Notification_accountDTO(userNotifi, dateNotifi, typeEmotions, contenNotifi, idArticle, email);
                if (noDAO.insertInteraction(noDTO)) {
                    url = LOADHOMEPAGE;
                   
                }
            }

            request.setAttribute("type", type);
            
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);

        } catch (Exception e) {
            System.err.println("Error messages (Notification user): " + e.getMessage());
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
