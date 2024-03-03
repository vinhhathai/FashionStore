/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public Account login(String user, String pass) {
        String query = "select * from Account\n"
                + "where [user] = ?\n"
                + "and pass = ?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, user);
            prepare.setString(2, pass);
            result = prepare.executeQuery();
            while (result.next()) {
                return new Account(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4),
                        result.getInt(5),
                        result.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Account";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Account(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4),
                        result.getInt(5),
                        result.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void editProfile(String username, String password, String email, int uID) {
        String query = "update Account set [user]=?,\r\n"
                + "[pass]=?,\r\n"
                + "[email]=?\r\n"
                + "where [uID] = ?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, username);
            prepare.setString(2, password);
            prepare.setString(3, email);
            prepare.setInt(4, uID);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Account checkAccountExist(String user) {
        String query = "select * from Account\n"
                + "where [user] = ?\n";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, user);
            result = prepare.executeQuery();
            while (result.next()) {
                return new Account(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4),
                        result.getInt(5),
                        result.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void singup(String user, String pass, String email) {
        String query = "insert into Account\n"
                + "values(?,?,0,0,?)";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, user);
            prepare.setString(2, pass);
            prepare.setString(3, email);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        AccountDAO test = new AccountDAO();
        System.out.println(test.login("abc", "123456"));
    }

    public void insertAccount(String user, String pass, String isSell,
            String isAdmin, String email) {
        String query = "insert Account([user], pass, isSell, isAdmin, email)\r\n"
                + "values(?,?,?,?,?)";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setString(1, user);
            prepare.setString(2, pass);
            prepare.setString(3, isSell);
            prepare.setString(4, isAdmin);
            prepare.setString(5, email);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteReviewByAccountID(String id) {
        String query = "delete from Review where [accountID] = ?";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setString(1, id);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteInvoiceByAccountId(String id) {
        String query = "delete from Invoice\n"
                + "where [accountID] = ?";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setString(1, id);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteTongChiTieuBanHangByUserID(String id) {
        String query = "delete from TongChiTieuBanHang\n"
                + "where [userID] = ?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, id);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteAccount(String id) {
        String query = "delete from Account where uID= ?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, id);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}
