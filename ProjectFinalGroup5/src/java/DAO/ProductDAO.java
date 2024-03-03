/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.Product;
import Model.SoLuongDaBan;
import Model.TongChiTieuBanHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinh Dev
 */
public class ProductDAO {

    Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void editTongChiTieu(int accountID, double totalMoneyVAT) {
        String query = "exec dbo.proc_CapNhatTongChiTieu ?,?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, accountID);
            prepare.setDouble(2, totalMoneyVAT);
            prepare.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void insertTongChiTieuBanHang(int userID, double tongChiTieu, double tongBanHang) {
        String query = "insert TongChiTieuBanHang(userID,TongChiTieu,TongBanHang)\r\n"
                + "values(?,?,?)";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, userID);
            prepare.setDouble(2, tongChiTieu);
            prepare.setDouble(3, tongBanHang);

            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public TongChiTieuBanHang checkTongChiTieuBanHangExist(int userID) {

        String query = "select * from TongChiTieuBanHang where [userID]=?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, userID);

            result = prepare.executeQuery();
            while (result.next()) {
                return new TongChiTieuBanHang(result.getInt(1),
                        result.getDouble(2),
                        result.getDouble(3)
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public SoLuongDaBan checkSoLuongDaBanExist(int productID) {

        String query = "select * from SoLuongDaBan where productID = ?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, productID);

            result = prepare.executeQuery();
            while (result.next()) {
                return new SoLuongDaBan(result.getInt(1),
                        result.getInt(2)
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void insertSoLuongDaBan(int productID, int soLuongDaBan) {
        String query = "insert SoLuongDaBan(productID,soLuongDaBan)\r\n"
                + "values(?,?)";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, productID);
            prepare.setInt(2, soLuongDaBan);

            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editSoLuongDaBan(int productID, int soLuongBanThem) {
        String query = "exec dbo.proc_CapNhatSoLuongDaBan ?,?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, productID);
            prepare.setInt(2, soLuongBanThem);

            prepare.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void editTongBanHang(int sell_ID, double tongTienBanHangThem) {
        String query = "exec dbo.proc_CapNhatTongBanHang ?,?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, sell_ID);
            prepare.setDouble(2, tongTienBanHangThem);

            prepare.executeUpdate();

        } catch (Exception e) {

        }
    }

    public int getSellIDByProductID(int productID) {
        String query = "select sell_ID\r\n"
                + "from Product\r\n"
                + "where [id]=?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, productID);
            result = prepare.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Product> getLatestProduct() {

        List<Product> listLatestProduct = new ArrayList<>();
        String query = "SELECT top 8 * FROM Product\n"
                + "ORDER BY [name] DESC\n"
                + "";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                listLatestProduct.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4), result.getString(5)));
            }
        } catch (Exception e) {
            String error = "Error happended while getting latest products!!!";
            System.out.println(error);
        }

        return listLatestProduct;

    }

    public List<Product> getLastestNike() {

        List<Product> listLatestProduct = new ArrayList<>();
        String query = "select top 4 * from Product\n"
                + "where cateID = 2\n"
                + "order by id desc"
                + "";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                listLatestProduct.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4), result.getString(5)));
            }
        } catch (Exception e) {
            String error = "Error happended while getting latest products!!!";
            System.out.println(error);
        }

        return listLatestProduct;

    }

    public List<Product> getNext4NikeProduct(int amount) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\r\n"
                + "where cateID=2\r\n"
                + "order by id desc\r\n"
                + "offset ? rows\r\n"
                + "fetch next 4 rows only";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, amount);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(9),
                        result.getString(10),
                        result.getString(11),
                        result.getString(12),
                        result.getString(13),
                        result.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getLastestAdidas() {

        List<Product> listLatestProduct = new ArrayList<>();
        String query = "select top 4 * from Product\n"
                + "where cateID = 1\n"
                + "order by id desc"
                + "";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                listLatestProduct.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4), result.getString(5)));
            }
        } catch (Exception e) {
            String error = "Error happended while getting latest products!!!";
            System.out.println(error);
        }

        return listLatestProduct;

    }

    public List<Product> getNext4AdidasProduct(int amount) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\r\n"
                + "where cateID=1\r\n"
                + "order by id desc\r\n"
                + "offset ? rows\r\n"
                + "fetch next 4 rows only";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, amount);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(9),
                        result.getString(10),
                        result.getString(11),
                        result.getString(12),
                        result.getString(13),
                        result.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductByIndex(int indexPage) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product \r\n"
                + "order by [id]\r\n"
                + "offset ? rows\r\n"
                + "fetch next 9 rows only";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, (indexPage - 1) * 9);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(9),
                        result.getString(10),
                        result.getString(11),
                        result.getString(12),
                        result.getString(13),
                        result.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
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

    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\n"
                + "where cateID = ?";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setString(1, cid);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(9),
                        result.getString(10),
                        result.getString(11),
                        result.getString(12),
                        result.getString(13),
                        result.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\n"
                + "where [name] like ?";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setString(1, "%" + txtSearch + "%");
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(9),
                        result.getString(10),
                        result.getString(11),
                        result.getString(12),
                        result.getString(13),
                        result.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getLast() {
        String query = "select top 1 * from Product\n"
                + "order by id desc";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                return new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7),
                        result.getString(8),
                        result.getString(9),
                        result.getString(10),
                        result.getString(11),
                        result.getString(12));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> searchByPriceMinToMax(String priceMin, String priceMax) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\r\n"
                + "where [price] >= ? and [price]<=?";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setString(1, priceMin);
            prepare.setString(2, priceMax);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(9),
                        result.getString(10),
                        result.getString(11),
                        result.getString(12),
                        result.getString(13),
                        result.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductByID(String id) {
        String query = "select * from Product\n"
                + "where id = ?";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setString(1, id);
            result = prepare.executeQuery();
            while (result.next()) {
                return new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(9),
                        result.getString(10),
                        result.getString(11),
                        result.getString(12),
                        result.getString(13),
                        result.getString(14));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> getRelatedProduct(int cateIDProductDetail) {
        List<Product> list = new ArrayList<>();
        String query = "select top 4 * from product\r\n"
                + "where [cateID] =?\r\n"
                + "order by id desc";
        try {
            connect = new DBConnection().getConnect();//mo ket noi voi sql
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, cateIDProductDetail);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(9),
                        result.getString(10),
                        result.getString(11),
                        result.getString(12),
                        result.getString(13),
                        result.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(9),
                        result.getString(10),
                        result.getString(11),
                        result.getString(12),
                        result.getString(13),
                        result.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
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

    public List<Product> getProductBySellIDAndIndex(int id, int indexPage) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product \r\n"
                + "where sell_ID = ?\r\n"
                + "order by [id]\r\n"
                + "offset ? rows\r\n"
                + "fetch next 5 rows only";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, id);
            prepare.setInt(2, (indexPage - 1) * 5);
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(9),
                        result.getString(10),
                        result.getString(11),
                        result.getString(12),
                        result.getString(13),
                        result.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int countAllProductBySellID(int sell_ID) {
        String query = "select count(*) from Product where sell_ID=?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, sell_ID);
            result = prepare.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public static void main(String[] args) {
        ProductDAO test = new ProductDAO();
        for (Product arg : test.searchByName("Adidas")) {
            System.out.println(arg);
        }
    }

    public void insertProduct(String name, String image, String price,
            String title, String description, String category, int sid, String model, String color, String delivery, String image2, String image3, String image4) {
        String query = "insert Product([name],[image],[price],[title],\r\n"
                + "[description],[cateID],[sell_ID],[model],\r\n"
                + "[color],[delivery],[image2],[image3],[image4])\r\n"
                + "values(N'" + name + "','" + image + "','" + price + "',N'" + title + "',N'" + description + "','" + category + "','" + sid + "',N'" + model + "',N'" + color + "',N'" + delivery + "','" + image2 + "','" + image3 + "','" + image4 + "')";
        try {

            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            /*
			 * ps.setString(1, name); ps.setString(2, image); ps.setString(3, price);
			 * ps.setString(4, title); ps.setString(5, description); ps.setString(6,
			 * category); ps.setInt(7, sid); ps.setString(8, model); ps.setString(9, color);
			 * ps.setString(10, delivery); ps.setString(11, image2); ps.setString(12,
			 * image3); ps.setString(13, image4);
             */
            prepare.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void editProduct(String pname, String pimage, String pprice, String ptitle, String pdescription, String pcategory,
            String pmodel, String pcolor,
            String pdelivery, String pimage2, String pimage3, String pimage4, String pid) {
        String query = "update Product\r\n"
                + "set [name] = ?,\r\n"
                + "[image] = ?,\r\n"
                + "price = ?,\r\n"
                + "title = ?,\r\n"
                + "[description] = ?,\r\n"
                + "cateID = ?,\r\n"
                + "model= ?,\r\n"
                + "color= ?,\r\n"
                + "delivery=?,\r\n"
                + "image2=?,\r\n"
                + "image3=?,\r\n"
                + "image4=?\r\n"
                + "where [id] = ?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, pname);
            prepare.setString(2, pimage);
            prepare.setString(3, pprice);
            prepare.setString(4, ptitle);
            prepare.setString(5, pdescription);
            prepare.setString(6, pcategory);
            prepare.setString(7, pmodel);
            prepare.setString(8, pcolor);
            prepare.setString(9, pdelivery);
            prepare.setString(10, pimage2);
            prepare.setString(11, pimage3);
            prepare.setString(12, pimage4);
            prepare.setString(13, pid);
            prepare.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void deleteCartByProductID(String productID) {
        String query = "delete from Cart where [productID]=?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, productID);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteReviewByProductID(String productID) {
        String query = "delete from Review where [productID] = ?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, productID);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteSoLuongDaBanByProductID(String productID) {
        String query = "delete from SoLuongDaBan where [productID]=?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, productID);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteProduct(String pid) {
        String query = "delete from Product\n"
                + "where [id] = ?";
        try {
            connect = new DBConnection().getConnect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, pid);
            prepare.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteProductBySellID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
