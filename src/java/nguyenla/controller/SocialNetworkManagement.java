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

/**
 *
 * @author ANH NGUYEN
 */
public class SocialNetworkManagement extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String LOGINPAGE = "Login-account.jsp";
    private final String LOGINSERVLET = "LoginAccountServlet";
    private final String REGISTERPAGE = "Register-account.jsp";
    private final String REGISTERSERVLET = "RegisterAccountServlet";
    private final String LOGOUTSERVLET = "LogOutAccountServlet";
    private final String POSTARTICLESERVLET = "PostArticleServlet";
    private final String LOADHOMESERVLET = "LoadHomePageServlet";
    private final String SEARCHSERVLET = "SearchContentServlet";
    private final String POSTCOMMENTSERVLET = "PostCommentServlet";
    private final String DELETECOMMENTSERVLET  ="DeleteCommentServlet";
    private final String DELETEPOSTSERVLET = "DeletePostServlet";
    private final String ADMINREMOVESERVLET = "AdminRemoveServlet";
    private final String SHOWNOTIFISERVLET = "ShowNotificationServlet";
    private final String EMOTIONSERVLET = "ClickEmotionServlet";
    private final String CHECKCLICK = "CheckClick";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("btAction");
            if (action == null) {
                url = LOGINPAGE;
            } else if (action.equals("Register")) {
                url = REGISTERSERVLET;
            } else if (action.equals("RegisterPage")) {
                url = REGISTERPAGE;
            } else if (action.equals("Login")) {
                url = LOGINSERVLET;
            } else if (action.equals("logOut")) {
                url = LOGOUTSERVLET;
            } else if(action.equals("Post")){
                url = POSTARTICLESERVLET;
            }else if(action.equals("LoadHome")){
                url = LOADHOMESERVLET;
            }else if(action.equals("search")){
                url = SEARCHSERVLET;
            }else if(action.equals("Post Comment")){
                url = POSTCOMMENTSERVLET;
            }else if(action.equals("deleteComment")){
                url = DELETECOMMENTSERVLET;
            }else if(action.equals("Delete Post")){
                url = DELETEPOSTSERVLET;
            }else if(action.equals("adminRemove")){
                url = ADMINREMOVESERVLET;
            }else if(action.equals("showNotification")){
                url = SHOWNOTIFISERVLET;
            }else if(action.equals("clickInteract")){
                url = EMOTIONSERVLET;
            }else if(action.equals("clickTest")){
                url = CHECKCLICK;
            }
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
        } catch (Exception e) {
            System.err.println("Error Messages (Social Management) : " + e.getMessage());
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
