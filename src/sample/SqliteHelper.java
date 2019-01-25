package sample;


import sample.models.OrderModel;

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
                System.out.println(orderModel.getCu_ad_city());

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
