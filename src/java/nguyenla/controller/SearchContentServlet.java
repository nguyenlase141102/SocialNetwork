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
import nguyenla.interaction_article.InteractionDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class SearchContentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String SEARCHPAGE = "Search-article.jsp";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = SEARCHPAGE;
            /* TODO output your page here. You may use following sample code. */
            ArticleDAO articleDAO = new ArticleDAO();
            HttpSession session = request.getSession();
            String valueSearch = request.getParameter("contentSearch").trim();
            List<ArticleDTO> listSearch = new ArrayList<>();
            List<InteractionDTO> listNumberComments = new ArrayList<>();
            String indexClick = request.getParameter("index");

            int index;
            if (indexClick == null) {
                index = 1;
            } else {
                index = Integer.parseInt(indexClick);
            }

            int pageSize = 5;
            int countArticleContent = articleDAO.countArticleByContent(valueSearch);
            int endPage = countArticleContent / pageSize;

            if (countArticleContent % pageSize != 0) {
                endPage++;
            }

            
            listSearch = articleDAO.getArticleByContent(index,valueSearch);
            listNumberComments = (List<InteractionDTO>) session.getAttribute("listNumberComment");
            if(listSearch.size() == 0){
                request.setAttribute("errorSearch","Can't found any article !!");
            }
            
            session.setAttribute("listNumberComment",listNumberComments);
            request.setAttribute("endPage",endPage);
            request.setAttribute("listSearchContent",listSearch);
            request.setAttribute("saveValueSearch",valueSearch);
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);

        } catch (Exception e) {
            System.err.println("Error messages (Search content: ) " + e.getMessage());
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
