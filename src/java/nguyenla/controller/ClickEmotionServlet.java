/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyenla.interaction_article.InteractionDAO;
import nguyenla.interaction_article.InteractionDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class ClickEmotionServlet extends HttpServlet {

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
    private String typeEmotions;
    List<String> listId = new ArrayList<>();
    List<InteractionDTO> listInter = new ArrayList<>();
    Map<String, String> mapInter = new HashMap<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = NOTIFIPAGE;
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            
            InteractionDAO interDAO = new InteractionDAO();

            String typeEmotions = request.getParameter("type").trim();
            String idArticle = request.getParameter("idArticle").trim();
            String idPreArticle = request.getParameter("idPreArticle").trim();
            String changeEmotions = request.getParameter("emotions").trim();
            SimpleDateFormat simpleDate = new SimpleDateFormat("MMM-dd-YYYY HH:ss ");
            Date date = new Date();
            String currentDate = simpleDate.format(date);
            String change = "";
            if (changeEmotions.length() == 0) {
                change = "not change";
            } else {
                change = "change";
            }
            String numberLikeString;
            int numberLikeInt;
            String numberDisLikeString;
            int numberDisLikeInt;
            boolean test = false;
            int idArticleInt = Integer.parseInt(idArticle);

            
            
            if (idPreArticle.length() == 0 || idArticle.equals(idPreArticle) || listId == null) {
                listId.add(idArticle);

                if (change.equals("not change")) {

                    if (typeEmotions.equals("like")) {
                        numberLikeString = request.getParameter("currentLike");
                        typeEmotions = "like";
                        numberLikeInt = Integer.parseInt(numberLikeString) + 1;
                        interDAO.updateLike(numberLikeInt, idArticleInt);
                    } else {

                        numberDisLikeString = request.getParameter("currentDisLike");
                        typeEmotions = "dislike";
                        numberDisLikeInt = Integer.parseInt(numberDisLikeString) + 1;
                        interDAO.updateDisLike(numberDisLikeInt, idArticleInt);
                    }
                } else {
                    if (typeEmotions.equals(changeEmotions)) {
                        url = NOTIFIPAGE;
                    } else {
                        if (typeEmotions.equals("like")) {
                            numberLikeString = request.getParameter("currentLike");
                            typeEmotions = "like";
                            numberLikeInt = Integer.parseInt(numberLikeString) + 1;
                            interDAO.updateLike(numberLikeInt, idArticleInt);
                            numberDisLikeString = request.getParameter("currentDisLike");
                            numberDisLikeInt = Integer.parseInt(numberDisLikeString) - 1;
                            interDAO.updateDisLike(numberDisLikeInt, idArticleInt);
                        } else {
                            numberDisLikeString = request.getParameter("currentDisLike");
                            typeEmotions = "dislike";
                            numberDisLikeInt = Integer.parseInt(numberDisLikeString) + 1;
                            interDAO.updateDisLike(numberDisLikeInt, idArticleInt);

                            numberLikeString = request.getParameter("currentLike");
                            numberLikeInt = Integer.parseInt(numberLikeString) - 1;
                            interDAO.updateLike(numberLikeInt, idArticleInt);
                        }
                    }

//                     InteractionDTO interDTO = new InteractionDTO(idArticleInt,typeEmotions);                    
//                     listInter.add(interDTO);
                }
                if (mapInter.size() == 0) {
                    mapInter.put(idArticle, typeEmotions);
                } else {
                    for (Map.Entry<String, String> entry : mapInter.entrySet()) {
                        String id = entry.getKey().trim();
                        String value = entry.getValue();
                        if (id.equals(idArticle)) {
                            mapInter.replace(idArticle, typeEmotions);
                        }
                    }
                }
            } else {

                for (int i = 0; i < listId.size(); i++) {
                    String id = listId.get(i).trim();
                    if (id.equals(idArticle)) {
                        test = true;
                        break;
                    }
                }

                if (test == false) {
                    mapInter.put(idArticle, typeEmotions);
                    if (!idArticle.equals(idPreArticle)) {
                        change = "not change";
                    }

                    if (change.equals("not change")) {

                        if (typeEmotions.equals("like")) {
                            numberLikeString = request.getParameter("currentLike");
                            typeEmotions = "like";
                            numberLikeInt = Integer.parseInt(numberLikeString) + 1;
                            interDAO.updateLike(numberLikeInt, idArticleInt);
                        } else {

                            numberDisLikeString = request.getParameter("currentDisLike");
                            typeEmotions = "dislike";
                            numberDisLikeInt = Integer.parseInt(numberDisLikeString) + 1;
                            interDAO.updateDisLike(numberDisLikeInt, idArticleInt);
                        }
                    } else {
                        if (typeEmotions.equals(changeEmotions)) {
                            url = NOTIFIPAGE;
                        } else {
                            if (typeEmotions.equals("like")) {
                                numberLikeString = request.getParameter("currentLike");
                                typeEmotions = "like";
                                numberLikeInt = Integer.parseInt(numberLikeString) + 1;
                                interDAO.updateLike(numberLikeInt, idArticleInt);

                                numberDisLikeString = request.getParameter("currentDisLike");

                                numberDisLikeInt = Integer.parseInt(numberDisLikeString) - 1;
                                interDAO.updateDisLike(numberDisLikeInt, idArticleInt);
                            } else {
                                numberDisLikeString = request.getParameter("currentDisLike");
                                typeEmotions = "dislike";
                                numberDisLikeInt = Integer.parseInt(numberDisLikeString) + 1;
                                interDAO.updateDisLike(numberDisLikeInt, idArticleInt);

                                numberLikeString = request.getParameter("currentLike");
                                numberLikeInt = Integer.parseInt(numberLikeString) - 1;
                                interDAO.updateLike(numberLikeInt, idArticleInt);
                            }
                        }
                    }
                    listId.add(idArticle);
                } else {
                    for (Map.Entry<String, String> entry : mapInter.entrySet()) {
                        String id = entry.getKey();
                        String value = entry.getValue().trim();
                        if (id.equals(idArticle)) {
                            if (typeEmotions.equals(value)) {
                                url = NOTIFIPAGE;
                            } else {
                                if (typeEmotions.equals("like")) {
                                    numberLikeString = request.getParameter("currentLike");
                                    typeEmotions = "like";
                                    numberLikeInt = Integer.parseInt(numberLikeString) + 1;
                                    interDAO.updateLike(numberLikeInt, idArticleInt);

                                    numberDisLikeString = request.getParameter("currentDisLike");

                                    numberDisLikeInt = Integer.parseInt(numberDisLikeString) - 1;
                                    interDAO.updateDisLike(numberDisLikeInt, idArticleInt);
                                } else {
                                    numberDisLikeString = request.getParameter("currentDisLike");
                                    typeEmotions = "dislike";
                                    numberDisLikeInt = Integer.parseInt(numberDisLikeString) + 1;
                                    interDAO.updateDisLike(numberDisLikeInt, idArticleInt);

                                    numberLikeString = request.getParameter("currentLike");
                                    numberLikeInt = Integer.parseInt(numberLikeString) - 1;
                                    interDAO.updateLike(numberLikeInt, idArticleInt);
                                }
                            }
                        }
                    }
                }

            }
            String color;
            if (typeEmotions.equals("like")) {
                color = "blue";
            } else {
                color = "red";
            }
            
            
            String poster = (String) session.getAttribute("saveUserName");
            interDAO.updateColor(idArticleInt, color, poster);

            System.out.println("map size: " + mapInter.size());

            request.setAttribute("dateEmotions",currentDate);
            request.setAttribute("userEmotions",poster);
            request.setAttribute("typeInteract", "Emotions");
            request.setAttribute("idSaveArticle", idArticleInt);
            request.setAttribute("typeEmotions", typeEmotions);
           
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
        } catch (Exception e) {
            System.err.println("Error Messages (Click Emotions)  : " + e.getMessage());
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
