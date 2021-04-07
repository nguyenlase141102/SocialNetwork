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
import nguyenla.account.SendEmail;
import nguyenla.checkvalid.Validation;

/**
 *
 * @author ANH NGUYEN
 */
public class RegisterAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String REGISTERPAGE = "Register-account.jsp";
    private final String LOGINPAGE = "Login-account.jsp";
    private String url;
    private String typeError;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            url = REGISTERPAGE;
            AccountDAO accountDAO = new AccountDAO();
            String valueUserEmail = request.getParameter("txtUserEmail").trim();
            String valueUserName = request.getParameter("txtUserName");
            String valueUserPassword = request.getParameter("txtUserPassword");
            String valueUserRole = "member";
            String valueUserStatus = "New";
            String valuePasswordConvert = ConvertPassword.convertPassword(valueUserPassword);

            SendEmail sm = new SendEmail();
            HttpSession session = request.getSession();
            String code = sm.getRandom();

            AccountDTO account = new AccountDTO(valueUserEmail, valueUserName, valueUserRole, code);
            boolean checkEmail = sm.sendEmail(account);
            if (checkEmail) {
                session.setAttribute("authCode", account);
            } else {
                typeError = "Invalid account email !!";
            }

            if (checkEmail) {
                if (!Validation.checkCharacters(valueUserName)) {
                    typeError = "User Name doesn't contain special characters !!";
                } else if (!Validation.checkCharacters(valueUserPassword)) {
                    typeError = "User Password doesn't contain special characters !!";
                } else if (accountDAO.checkAvailableLogin(valueUserEmail, valuePasswordConvert) != null) {
                    typeError = "This email was already !!";
                } else {

                    AccountDTO newAccount = new AccountDTO(valueUserEmail, valueUserName, valuePasswordConvert, valueUserRole, valueUserStatus);
                    if (accountDAO.insertAccount(newAccount)) {
                        url = LOGINPAGE;
                    } else {
                        typeError = "Register account failed !!";
                    }

                }
            }

            request.setAttribute("already","Yes");
            request.setAttribute("messagesRegister", "Register account success ");
            request.setAttribute("valueSaveEmail", valueUserEmail);
            request.setAttribute("valueSaveName", valueUserName);
            request.setAttribute("valueSavePassword", valueUserPassword);
            request.setAttribute("errorRegister", typeError);
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
        } catch (Exception e) {
            System.err.println("Error Messages (Register Servlet): " + e.getMessage());
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
