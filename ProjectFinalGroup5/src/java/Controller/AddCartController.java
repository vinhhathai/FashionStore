/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CartDAO;
import Model.Account;
import Model.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int productID = Integer.parseInt(request.getParameter("pid"));

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");

        if (a == null) {
            response.sendRedirect("login");
            return;
        }
        int accountID = a.getId();
        int amount = Integer.parseInt(request.getParameter("quantity"));

        String size = request.getParameter("size");
        CartDAO cart = new CartDAO();
        Cart cartExisted = cart.checkCartExist(accountID, productID);
        int amountExisted;
        String sizeExisted;
        if (cartExisted != null) {
            amountExisted = cartExisted.getAmount();
            cart.editAmountAndSizeCart(accountID, productID, (amountExisted + amount), size);
            request.setAttribute("mess", "Increased number of products!");
            request.getRequestDispatcher("managerCart").forward(request, response);
        } else {
            cart.insertCart(accountID, productID, amount, size);
            request.setAttribute("mess", "Product added to cart!");
            request.getRequestDispatcher("managerCart").forward(request, response);
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
