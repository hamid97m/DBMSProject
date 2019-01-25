package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import sample.models.OrderModel;

import javax.swing.text.html.ListView;
import java.util.Hashtable;
import java.util.List;

public class Controller {
    private SqliteHelper db;

    @FXML
    TreeView<String> orderTreeView;


    @FXML
    public void initialize()
    {
        db = new SqliteHelper();
        orderTreeView.setRoot(createTree(db.getOrders()));

    }



    private TreeItem<String> createTree(List<OrderModel> nodes)
    {
        TreeItem<String> rootItem = new TreeItem<String> ("سفارشات");
        rootItem.setExpanded(true);
        for (int i = 0; i <nodes.size(); i++) {
            TreeItem<String> item = new TreeItem<String> (nodes.get(i).getOrder_date());
            TreeItem<String> item1=new TreeItem<>();
            TreeItem<String> item2=new TreeItem<>();
            TreeItem<String> item3=new TreeItem<>();
            TreeItem<String> item4=new TreeItem<>();
            TreeItem<String> item5=new TreeItem<>();
            for (int j = 0; j < 5; j++) {
                item1 = new TreeItem<String> ("تاریخ تحویل  ="+nodes.get(i).getDelivery_date());
                item2 = new TreeItem<String> ("شعبه  ="+nodes.get(i).getShop_bransh_id());
                item3 = new TreeItem<String> ("هزینه حمل و نقل  ="+nodes.get(i).getTransport_cost());
                item4 = new TreeItem<String> ("نام مشتری  ="+nodes.get(i).getCu_name());
                item5 = new TreeItem<String> ("آدرس  ="+nodes.get(i).getCu_ad_city()+"  "+nodes.get(i).getCu_ad_street()+"  "+nodes.get(i).getShop_bransh_id());

            }
            item.getChildren().add(item1);
            item.getChildren().add(item2);
            item.getChildren().add(item3);
            item.getChildren().add(item4);
            item.getChildren().add(item5);
            rootItem.getChildren().add(item);
            item5 = new TreeItem<String> ("آدرس  ="+nodes.get(i).getCu_ad_city()+"  "+nodes.get(i).getCu_ad_street()+"  "+nodes.get(i).getShop_bransh_id());


        }



        return rootItem;
    }

}
