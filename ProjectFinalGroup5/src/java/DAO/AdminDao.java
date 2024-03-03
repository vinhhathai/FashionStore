/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.Account;
import Model.Invoice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Doan Thanh Phuc - CE170580
 */
public class AdminDao {

    Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public double totalMoneyDay(int day) {
        String query = "select \r\n"
                + "	SUM(tongGia) \r\n"
                + "from Invoice\r\n"
                + "where DATEPART(dw,[ngayXuat]) = ?\r\n"
                + "Group by ngayXuat ";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, day);
            result = prepare.executeQuery();
            while (result.next()) {
                return result.getDouble(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public double totalMoneyMonth(int month) {
        String query = "select SUM(tongGia) from Invoice\r\n"
                + "where MONTH(ngayXuat)=?\r\n"
                + "Group by MONTH(ngayXuat)";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, month);
            result = prepare.executeQuery();
            while (result.next()) {
                return result.getDouble(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int checkAccountAdmin(int userID) {

        String query = "select isAdmin from Account where [uID]=?";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, userID);

            result = prepare.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int countAllReview() {
        String query = "select count(*) from Review";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countAllProduct() {
        String query = "select count(*) from Product";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public double sumAllInvoice() {
        String query = "select SUM(tongGia) from Invoice";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                return result.getDouble(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Invoice> getAllInvoice() {
        List<Invoice> list = new ArrayList<>();
        String query = "select * from Invoice";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Invoice(result.getInt(1),
                        result.getInt(2),
                        result.getDouble(3),
                        result.getDate(4)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Account";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
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
    
}
