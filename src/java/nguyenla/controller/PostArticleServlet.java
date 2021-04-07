/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import nguyenla.article.ArticleDAO;
import nguyenla.article.ArticleDTO;
import nguyenla.interact_account.Interaction_accountDAO;
import nguyenla.interact_account.Interaction_accountDTO;
import nguyenla.interaction_article.InteractionDAO;
import nguyenla.interaction_article.InteractionDTO;

/**
 *
 * @author ANH NGUYEN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class PostArticleServlet extends HttpServlet {

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

    public String dbFileName = "";
    public static final String UPLOAD_DIR = "uploads";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = LOADHOMEPAGE;
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String poster = (String) session.getAttribute("saveUserName");
            ArticleDAO articleDAO = new ArticleDAO();
            String valueUserWrite = request.getParameter("txtUserWrite");
            String valueUserTitle = request.getParameter("txtTitle");
            String txtFile = request.getParameter("file");
            System.out.println("txtFile: "+txtFile);
            //CurrentDate
            SimpleDateFormat simpleDate = new SimpleDateFormat("MMM-dd-YYYY");
            Date date = new Date();
            String currentDate = simpleDate.format(date);
            //Load Image 
            Part part = request.getPart("file");
            String fileName = extractFileName(part);

            if (txtFile != null) {
                System.out.println("o day");
                String applicationPath = getServletContext().getRealPath("");
                String uploadPath = applicationPath + File.separator + UPLOAD_DIR;

                File fileUploadDirectory = new File(uploadPath);
                if (!fileUploadDirectory.exists()) {
                    fileUploadDirectory.mkdirs();
                }
                String savePath = uploadPath + File.separator + fileName;
                String sRootPath = new File(savePath).getAbsolutePath();
//            part.write(savePath + File.separator);
                File fileSaveDir1 = new File(savePath);
                part.write(savePath + File.separator);
                /*if you may have more than one files with same name then you can calculate some random characters
         and append that characters in fileName so that it will  make your each image name identical.*/
                dbFileName = UPLOAD_DIR + File.separator + fileName;
                part.write(savePath + File.separator);
                //out.println(id+" "+firstName+" "+lastName+" "+fileName+" "+savePath);
                /*
         You need this loop if you submitted more than one file
         for (Part part : request.getParts()) {
         String fileName = extractFileName(part);
         part.write(savePath + File.separator + fileName);
         }*/
                System.out.println("savePath : "+savePath);
                ArticleDTO newArticle = new ArticleDTO(valueUserTitle, valueUserWrite, fileName, currentDate, savePath, poster, "Active");
                if (articleDAO.postArticle(newArticle)) {
                    request.setAttribute("messagesArticle", "Post Article Success");
                } else {
                    request.setAttribute("errorPostArticle", "Post Article Fail");
                }
            } else {
                
                ArticleDTO newArticle = new ArticleDTO(valueUserTitle, valueUserWrite, fileName, currentDate,"", poster, "Active");
                if (articleDAO.postArticle(newArticle)) {
                    request.setAttribute("messagesArticle", "Post Article Success");
                } else {
                    request.setAttribute("errorPostArticle", "Post Article Fail");
                }
            }

           

            InteractionDTO interLikesDTO = new InteractionDTO();
            InteractionDAO interDAO = new InteractionDAO();
            Interaction_accountDAO interAccountDAO = new Interaction_accountDAO();
            List<ArticleDTO> listArticle = new ArrayList<>();

            listArticle = articleDAO.getAllArticleNoIndex();
            for (int i = 0; i < listArticle.size(); i++) {
                int id_article = listArticle.get(i).getId_article();
                interLikesDTO = interDAO.countLikesAndDislikes(id_article);
                if (interLikesDTO == null) {
                    InteractionDTO interDTO = new InteractionDTO(id_article, null, 0, 0, currentDate, poster, null, "Null");
                    interDAO.insertInteraction(interDTO);
                    int lastId = interDAO.getLastId();
                    Interaction_accountDTO interAccount = new Interaction_accountDTO(lastId, id_article, currentDate);
                    interAccountDAO.insertInteractAccount(interAccount);
                }
            }

            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);

        } catch (Exception e) {
            System.err.println("Error messages (Post Article Servlet): " + e.getMessage());
        }
    }

    private String extractFileName(Part part) {//This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
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
