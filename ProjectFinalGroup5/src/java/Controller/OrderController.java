package Controller;

import DAO.CartDAO;
import DAO.DAO;
import DAO.InvoiceDAO;
import DAO.ProductDAO;
import Model.Account;
import Model.Cart;
import Model.Email;
import Model.EmailUtils;
import Model.Product;
import Model.SoLuongDaBan;
import Model.TongChiTieuBanHang;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            response.sendRedirect("login");
            return;
        }
        int accountID = a.getId();
        CartDAO cart_dao = new CartDAO();
        ProductDAO product = new ProductDAO();
        List<Cart> list = cart_dao.getCartByAccountID(accountID);
        List<Product> list2 = product.getAllProduct();
        double totalMoney = 0;
        for (Cart c : list) {
            for (Product p : list2) {
                if (c.getProductID() == p.getId()) {
                    totalMoney = totalMoney + (p.getPrice() * c.getAmount());
                }
            }
        }
        double totalMoneyVAT = totalMoney + totalMoney * 0.1;

        double tongTienBanHangThem = 0;
        int sell_ID;
        for (Cart c : list) {
            for (Product p : list2) {
                if (c.getProductID() == p.getId()) {
                    tongTienBanHangThem = 0;
                    sell_ID = product.getSellIDByProductID(p.getId());
                    tongTienBanHangThem = tongTienBanHangThem + (p.getPrice() * c.getAmount());
                    TongChiTieuBanHang t2 = product.checkTongChiTieuBanHangExist(sell_ID);
                    if (t2 == null) {
                        product.insertTongChiTieuBanHang(sell_ID, 0, tongTienBanHangThem);
                    } else {
                        product.editTongBanHang(sell_ID, tongTienBanHangThem);
                    }
                }
            }
        }

        for (Cart c : list) {
            for (Product p : list2) {
                if (c.getProductID() == p.getId()) {
                    SoLuongDaBan s = product.checkSoLuongDaBanExist(p.getId());
                    if (s == null) {
                        product.insertSoLuongDaBan(p.getId(), c.getAmount());
                    } else {
                        product.editSoLuongDaBan(p.getId(), c.getAmount());
                    }
                }
            }
        }
        InvoiceDAO incoice = new InvoiceDAO();
        incoice.insertInvoice(accountID, totalMoneyVAT);
        TongChiTieuBanHang t = product.checkTongChiTieuBanHangExist(accountID);
        if (t == null) {
            product.insertTongChiTieuBanHang(accountID, totalMoneyVAT, 0);
        } else {
            product.editTongChiTieu(accountID, totalMoneyVAT);
        }

        request.getRequestDispatcher("DatHang.jsp").forward(request, response);
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
        try {
            String emailAddress = request.getParameter("email");
            String name = request.getParameter("name");
            String phoneNumber = request.getParameter("phoneNumber");
            String deliveryAddress = request.getParameter("deliveryAddress");

            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("acc");
            if (a == null) {
                response.sendRedirect("login");
                return;
            }
            int accountID = a.getId();
            DAO dao = new DAO();
            List<Cart> list = dao.getCartByAccountID(accountID);
            List<Product> list2 = dao.getAllProduct();

            double totalMoney = 0;
            for (Cart c : list) {
                for (Product p : list2) {
                    if (c.getProductID() == p.getId()) {
                        totalMoney = totalMoney + (p.getPrice() * c.getAmount());
                    }
                }
            }
            double totalMoneyVAT = totalMoney + totalMoney * 0.1;

            //old code
            Email email = new Email();
            email.setFrom("todohongy@gmail.com"); //chinh lai email quan tri tai day [chu y dung email con hoat dong]
            email.setFromPassword("uewh gqjz deby sxnz"); //mat khau email tren
            email.setTo(emailAddress);
            email.setSubject("You have just placed an order from Fashion Store. ");
            StringBuilder sb = new StringBuilder();
            sb.append("Dear ").append(name).append("<br>");
            sb.append("You have just placed an order from Fashion Store. <br> ");
            sb.append("Your delivery address is: <b>").append(deliveryAddress).append(" </b> <br>");
            sb.append("The phone number when receiving the product is: <b>").append(phoneNumber).append(" </b> <br>");
            sb.append("The products you have ordered include: <br>");
            for (Cart c : list) {
                for (Product p : list2) {
                    if (c.getProductID() == p.getId()) {
                        sb.append(p.getName()).append(" | ").append("Price:").append(p.getPrice()).append("$").append(" | ").append("Amount:").append(c.getAmount()).append(" | ").append("Size:").append(c.getSize()).append("<br>");
                    }
                }
            }
            sb.append("Tong Tien: ").append(String.format("%.02f", totalMoneyVAT)).append("$").append("<br>");
            sb.append("Thank you for ordering at Fashion Store<br>");
            sb.append("Fashion Store");

            email.setContent(sb.toString());
            EmailUtils.send(email);
            request.setAttribute("mess", "Order placed successfully!");

            dao.deleteCartByAccountID(accountID);

//            new code
            request.setAttribute("email", emailAddress);
            request.getRequestDispatcher("ThongTinDatHang.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Ordering is a failure!");
            e.printStackTrace();
        }

        request.getRequestDispatcher("DatHang.jsp").forward(request, response);
    }
}
