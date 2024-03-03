/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Vinh Dev
 */
public class InvoiceDAO {

    Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
    
    public void insertInvoice(int accountID, double tongGia) {
        String query = "insert Invoice(accountID,tongGia,ngayXuat)\r\n"
                + "values(?,?,?)";

        try {
            connect = new DBConnection.DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, accountID);
            prepare.setDouble(2, tongGia);
            prepare.setDate(3, getCurrentDate());
            prepare.executeUpdate();

        } catch (Exception e) {

        }
    }
}
