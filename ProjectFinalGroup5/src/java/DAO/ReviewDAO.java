/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Review;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinh Dev
 */
public class ReviewDAO {

    Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public List<Review> getAllReviewByProductID(String productId) {
        List<Review> list = new ArrayList<>();
        String query = "select * from Review\r\n"
                + "where [productID] =?";
        try {
            connect = new DBConnection.DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setString(1, productId);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Review(result.getInt(1),
                        result.getInt(2),
                        result.getString(3),
                        result.getDate(4)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insertReview(int accountID, int productID, String contentReview) {
        String query = "insert Review(accountID, productID, contentReview, dateReview)\r\n"
                + "values(?,?,?,?)";

        try {
            connect = new DBConnection.DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, accountID);
            prepare.setInt(2, productID);
            prepare.setString(3, contentReview);
            prepare.setDate(4, getCurrentDate());
            prepare.executeUpdate();

        } catch (Exception e) {
        }
    }

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
    
    public Review getNewReview(int accountID, int productID) {
        String query = "select top 1 * from Review\r\n"
                + "where accountID = ? and productID = ?\r\n"
                + "order by maReview desc";
        try {
          connect = new DBConnection.DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, accountID);
            prepare.setInt(2, productID);
            result = prepare.executeQuery();
            while (result.next()) {
                return new Review(result.getInt(1),
                        result.getInt(2),
                        result.getString(3),
                        result.getDate(4));
            }
        } catch (Exception e) {
        }
        return null;
    }
}
