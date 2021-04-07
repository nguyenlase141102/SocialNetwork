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
import javax.servlet.http.HttpSession;
import nguyenla.account.AccountDAO;
import nguyenla.account.AccountDTO;
import nguyenla.account.ConvertPassword;

/**
 *
 * @author ANH NGUYEN
 */
public class LoginAccountServlet extends HttpServlet {

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
    private final String LOADHOMEPAGE = "LoadHomePageServlet";
    private String url;
    private String typeError;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = LOGINPAGE;
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            AccountDAO accountDAO = new AccountDAO();
            AccountDTO accountDTO = new AccountDTO();
            AccountDTO user = new AccountDTO();
            String valueUserEmail = request.getParameter("txtUserEmail").trim();
            String valueUserPassword = request.getParameter("txtUserPassword").trim();
            String valuePasswordConvert = ConvertPassword.convertPassword(valueUserPassword);
            String code = "";

            accountDTO = accountDAO.checkAvailableLogin(valueUserEmail, valuePasswordConvert);

            if (accountDTO == null) {
                typeError = "This account not available !!";

            } else {
                String status = accountDTO.getStatus_user().trim();
               
                if (status.equals("New")) {
                    user = (AccountDTO) session.getAttribute("authCode");
                    code = request.getParameter("authCode").trim();
                   
                    if (!code.equals(user.getCode())) {
                        typeError = "Code invalid !!";
                        request.setAttribute("already","Yes");
                    } else {
                        url = LOADHOMEPAGE;
                        session.setAttribute("saveUserName", accountDTO.getName_user().trim());
                        request.setAttribute("welcomeUser", "You are welcome <3 :" + accountDTO.getName_user());
                        accountDAO.updateStatusUser(valueUserEmail);
                    }
                } else {
                    url = LOADHOMEPAGE;
                    session.setAttribute("saveUserName", accountDTO.getName_user().trim());
                    request.setAttribute("welcomeUser", "You are welcome <3 :" + accountDTO.getName_user());
                    accountDAO.updateStatusUser(valueUserEmail);
                }
            }

            request.setAttribute("errorLogin", typeError);
            request.setAttribute("valueSaveEmail", valueUserEmail);
            request.setAttribute("valueSavePassword", valueUserPassword);
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
        } catch (Exception e) {
            System.err.println("Error Messages (LoginAccount Servlet): " + e.getMessage());
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
