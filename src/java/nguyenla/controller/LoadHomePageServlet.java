/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyenla.article.ArticleDAO;
import nguyenla.article.ArticleDTO;
import nguyenla.interaction_article.InteractionDAO;
import nguyenla.interaction_article.InteractionDTO;
import nguyenla.notification.Notification_accountDAO;
import nguyenla.notification.Notification_accountDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class LoadHomePageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String WINKUPAGE = "Winku.jsp";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            url = WINKUPAGE;
          
            HttpSession session = request.getSession();
            String nameUser = (String) session.getAttribute("saveUserName");
            ArticleDTO articleDTO = new ArticleDTO();
            ArticleDAO articleDAO = new ArticleDAO();
            InteractionDAO interDAO = new InteractionDAO();
            Notification_accountDAO noDAO = new Notification_accountDAO();
            InteractionDTO interCommentDTO = new InteractionDTO();
            InteractionDTO interLikesDTO = new InteractionDTO();

            List<ArticleDTO> listArticle = new ArrayList<>();
            List<InteractionDTO> listInteraction = new ArrayList<>();
            List<InteractionDTO> listNumberComments = new ArrayList<>();
            List<InteractionDTO> listNumber = new ArrayList<>();
            List<ArticleDTO> listManage = new ArrayList<>();
            List<Notification_accountDTO> listNotification = new ArrayList<>();
            List<Notification_accountDTO> listOtherUser = new ArrayList<>();
            List<ArticleDTO> listIdArticle = new ArrayList<>();
            String indexClick = request.getParameter("index");

            int index;
            if (indexClick == null) {
                index = 1;
            } else {
                index = Integer.parseInt(indexClick);
            }

            //Count All Article
            int pageSize = 5;
            int countAllArticle = articleDAO.countAllArticle();
            int endPage = countAllArticle / pageSize;

            if (countAllArticle % pageSize != 0) {
                endPage++;
            }

           
            //Load  all list car
            listArticle = articleDAO.getAllArticle(index);
           
            //Load Interaction
            listInteraction = interDAO.getAllListInteract();
          
            //Load Number comments
            for (int i = 0; i < listArticle.size(); i++) {
                int idArticle = listArticle.get(i).getId_article();
                interCommentDTO = interDAO.countComments(idArticle);
                interLikesDTO = interDAO.countLikesAndDislikes(idArticle);
                listNumberComments.add(interCommentDTO);
                listNumber.add(interLikesDTO);

            }
            int numberNotification = 0;
            //load list admin
            listManage = articleDAO.getAllArticleNoIndex();           
            // count number notification
            int numberNotifi = noDAO.countAllNotification();

            //Load Other User notification
            listOtherUser = noDAO.getNameNotifi(nameUser);
            listIdArticle = noDAO.getIdArticle(nameUser);

            for (int i = 0; i < listIdArticle.size(); i++) {
                int idArticle = listIdArticle.get(i).getId_article();
                for (int j = 0; j < listOtherUser.size(); j++) {
                    int idArticleNotifi = listOtherUser.get(j).getId_article();
                    if (idArticle == idArticleNotifi) {
                        numberNotification += 1;
                    }
                }
            }
            //Load notification details
            listNotification = noDAO.getAllNotifi(nameUser);
            
            request.setAttribute("numberNo", numberNotification);
            session.setAttribute("listNotification", listNotification);
            session.setAttribute("listManage", listManage);
            session.setAttribute("listNumber", listNumber);
            session.setAttribute("listNumberComment", listNumberComments);
            session.setAttribute("listAllInter", listInteraction);
            session.setAttribute("listAllArticle", listArticle);
            request.setAttribute("endPage", endPage);
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
        } catch (Exception e) {
            System.err.println("Error Messages (Load Home Page): " + e.getMessage());
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
