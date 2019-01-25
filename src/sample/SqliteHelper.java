package sample;


import sample.models.OrderModel;
import sample.models.ProductModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteHelper


{

    private static final String TBL_NAME = "RuleNode";
    private static final String COL_ID = "ID";
    private static final String COL_PARENT = "Parent";
    private static final String COL_IS_LEAF = "IsLeaf";
    private static final String COL_TITLE = "Title";
    private static final String COL_CONTENT = "Content";
    private Connection connection;

    public SqliteHelper()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:dbmsproject.db");
            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON");
            statement.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void insertOrder(OrderModel node)
    {
        try
        {
            String query = String.format("INSERT INTO sale_order (order_date, delivery_date, transport_cost, shop_branch_id, cu_name, cu_ad_city, cu_ad_street, cu_ad_bulding_num) VALUES (?,?,?,?,?,?,?,?)");

            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, node.getOrder_date());
            stmt.setString(2, node.getDelivery_date());
            stmt.setString(3, node.getTransport_cost());
            stmt.setString(4, node.getShop_bransh_id().toString());
            stmt.setString(5, node.getCu_name());
            stmt.setString(6, node.getCu_ad_city());
            stmt.setString(7, node.getCu_ad_street());
            stmt.setString(8, node.getCu_ad_building_num().toString());

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }
    }
    public void insertOrderProduct(int order_id,int product_id,int count)
    {
        try
        {
            String query = String.format("INSERT INTO order_product (order_id, product_id, count) VALUES (?,?,?)");

            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, order_id);
            stmt.setInt(2, product_id);
            stmt.setInt(3, count);


            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }
    }

    public List<OrderModel> getOrders()
    {
        List<OrderModel> result = new ArrayList<>();
        try
        {
            String query = String.format("SELECT * FROM  sale_order");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                OrderModel orderModel=new OrderModel();
                orderModel.setOrder_id(rs.getInt("order_id"));
                orderModel.setOrder_date(rs.getString("order_date"));
                orderModel.setDelivery_date(rs.getString("delivery_date"));
                orderModel.setTransport_cost(rs.getString("transport_cost"));
                orderModel.setShop_bransh_id(rs.getInt("shop_branch_id"));
                orderModel.setCu_name(rs.getString("cu_name"));
                orderModel.setCu_ad_city(rs.getString("cu_ad_city"));
                orderModel.setCu_ad_street(rs.getString("cu_ad_street"));
                orderModel.setCu_ad_building_num(rs.getInt("cu_ad_bulding_num"));

                result.add(orderModel);
            }
            rs.close();
            stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public List<ProductModel> getProducts(int order_id)
    {
        List<ProductModel> result = new ArrayList<>();
        try
        {
            String query = String.format("SELECT  product.product_id, product.order_id, product.count FROM  order_product AS product JOIN sale_order AS sale  ON product.order_id=sale.order_id  WHERE product.order_id="+order_id);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                ProductModel productModel=new ProductModel();
                productModel.setCount(rs.getInt("count"));
                productModel.setOrder_id(rs.getInt("order_id"));
                productModel.setProduct_id(rs.getInt("product_id"));


                result.add(productModel);
            }
            rs.close();
            stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public List<OrderModel> search(int id)
    {
        List<OrderModel> result = new ArrayList<>();
        try
        {
            String query = String.format("SELECT * FROM  sale_order WHERE order_id="+id);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                OrderModel orderModel=new OrderModel();
                orderModel.setOrder_id(rs.getInt("order_id"));
                orderModel.setOrder_date(rs.getString("order_date"));
                orderModel.setDelivery_date(rs.getString("delivery_date"));
                orderModel.setTransport_cost(rs.getString("transport_cost"));
                orderModel.setShop_bransh_id(rs.getInt("shop_branch_id"));
                orderModel.setCu_name(rs.getString("cu_name"));
                orderModel.setCu_ad_city(rs.getString("cu_ad_city"));
                orderModel.setCu_ad_street(rs.getString("cu_ad_street"));
                orderModel.setCu_ad_building_num(rs.getInt("cu_ad_bulding_num"));

                result.add(orderModel);
            }
            rs.close();
            stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }


}
