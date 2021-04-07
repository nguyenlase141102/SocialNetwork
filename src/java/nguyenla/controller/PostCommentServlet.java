/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyenla.interact_account.Interaction_accountDAO;
import nguyenla.interact_account.Interaction_accountDTO;
import nguyenla.interaction_article.InteractionDAO;
import nguyenla.interaction_article.InteractionDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class PostCommentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String NOTIFIPAGE = "NotificationAccountServlet";
    private String url;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            InteractionDAO interDAO = new InteractionDAO();
            Interaction_accountDAO iaDAO = new Interaction_accountDAO();
            
            String userComment = (String) session.getAttribute("saveUserName");
            String userEmail =  interDAO.getEmail(userComment);
            String contentComment = request.getParameter("txtComment").trim();
            String idArticle = request.getParameter("hiddenIdArticle");
            int idArticleInt = Integer.parseInt(idArticle);
            SimpleDateFormat simpleDate = new SimpleDateFormat("MMM-dd-YYYY HH:ss ");
            Date date = new Date();
            String currentDate = simpleDate.format(date);
            
          
            //Post comment 
            InteractionDTO interDTO = new InteractionDTO(idArticleInt,contentComment,0, 0,currentDate,userComment,userEmail);
            if(interDAO.insertInteraction(interDTO)){
                int id_interaction = interDAO.getIdInteract(contentComment);              
                Interaction_accountDTO iaDTO = new Interaction_accountDTO(id_interaction,idArticleInt, currentDate);
                if(iaDAO.insertInteractAccount(iaDTO) ){
                    url = NOTIFIPAGE;
                }
            }
            
            //Load Notification 
            

            
            
            request.setAttribute("userComment",userComment);
            request.setAttribute("dateComment",currentDate);
            request.setAttribute("typeInteract","Comment");
            request.setAttribute("contentComment",contentComment);
            request.setAttribute("idArticle",idArticleInt);
            
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
            
        }catch(Exception  e){
            System.err.println("Error messages (Post comments): "+e.getMessage());
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
