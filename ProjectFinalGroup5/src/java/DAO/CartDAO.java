/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.Cart;
import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinh Dev
 */
public class CartDAO {

    Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public List<Cart> getCartByAccountID(int accountID) {
        List<Cart> list = new ArrayList<>();
        String query = "select * from Cart where accountID = ?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, accountID);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Cart(result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Cart checkCartExist(int accountID, int productID) {

        String query = "select * from Cart\r\n"
                + "where [accountID] = ? and [productID] = ?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, accountID);
            prepare.setInt(2, productID);
            result = prepare.executeQuery();
            while (result.next()) {
                return new Cart(result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getString(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void editAmountAndSizeCart(int accountID, int productID, int amount, String size) {
        String query = "update Cart set [amount]=?,\r\n"
                + "[size]=?\r\n"
                + "where [accountID]=? and [productID]=?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, amount);
            prepare.setString(2, size);
            prepare.setInt(3, accountID);
            prepare.setInt(4, productID);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertCart(int accountID, int productID, int amount, String size) {
        String query = "insert Cart(accountID, productID, amount,size)\r\n"
                + "values(?,?,?,?)";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, accountID);
            prepare.setInt(2, productID);
            prepare.setInt(3, amount);
            prepare.setString(4, size);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteCart(int productID) {
        String query = "delete from Cart where productID = ?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, productID);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editAmountCart(int accountID, int productID, int amount) {
        String query = "update Cart set [amount]=?\r\n"
                + "where [accountID]=? and [productID]=?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, amount);
            prepare.setInt(2, accountID);
            prepare.setInt(3, productID);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteCartByAccountID(int accountID) {
        String query = "delete from Cart \r\n"
                + "where [accountID]=?";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, accountID);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Cart> getAll() {
        List<Cart> list = new ArrayList<>();
        String query = "SELECT [accountID]\n"
                + "      ,[productID]\n"
                + "      ,[amount]\n"
                + "      ,[maCart]\n"
                + "      ,[size]\n"
                + "  FROM [dbo].[Cart]";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Cart(result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getString(5)));
                       
            }
        } catch (Exception e) {
        }
        return list;
    }

}
