/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinh Dev
 */
public class CategoryDAO {

    Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            connect = new DBConnection.DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Category(result.getInt(1),
                        result.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    
    
    public int getCateIDByProductID(String id) {
        String query = "select [cateID] from Product\r\n"
                + "where [id] =?";
        try {
             connect = new DBConnection.DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setString(1, id);
            result = prepare.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public static void main(String[] args) {
        CategoryDAO test = new CategoryDAO();
        for (Category category : test.getAllCategory()) {
            System.out.println(category);
        }
    }
}
