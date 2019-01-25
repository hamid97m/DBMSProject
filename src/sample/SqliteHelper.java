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

//    public void insertNode(RuleNode node)
//    {
//        try
//        {
//            String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?);",
//                    TBL_NAME, COL_PARENT, COL_TITLE, COL_CONTENT, COL_IS_LEAF);
//
//            PreparedStatement stmt = connection.prepareStatement(query);
//            if (node.getParentId() == 0)
//            {
//                stmt.setNull(1, Types.INTEGER);
//            }
//            else
//            {
//                stmt.setInt(1, node.getParentId());
//            }
//            stmt.setString(2, node.getTitle());
//            stmt.setString(3, node.getContent());
//            stmt.setBoolean(4, node.isLeaf());
//            stmt.executeUpdate();
//            stmt.close();
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//
//        }
//    }
//
//    public void deleteNode(int nodeId)
//    {
//        try
//        {
//            String query = String.format("DELETE FROM %s WHERE %s = ?;", TBL_NAME, COL_ID);
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setInt(1, nodeId);
//            stmt.executeUpdate();
//            stmt.close();
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateNode(String title, int nodeId)
//    {
//        try
//        {
//            String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?", TBL_NAME, COL_TITLE, COL_ID);
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setString(1, title);
//            stmt.setInt(2, nodeId);
//            stmt.executeUpdate();
//            stmt.close();
//
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
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
