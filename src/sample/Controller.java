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
            TreeItem<String> item = new TreeItem<String> (nodes.get(i).getOrder_id().toString());
            TreeItem<String> item1=new TreeItem<>();
            TreeItem<String> item2=new TreeItem<>();
            TreeItem<String> item3=new TreeItem<>();
            TreeItem<String> item4=new TreeItem<>();
            TreeItem<String> item5=new TreeItem<>();
            TreeItem<String> item6=new TreeItem<>();
            TreeItem<String> item7=new TreeItem<>();
            TreeItem<String> item8=new TreeItem<>();

                item6 = new TreeItem<String> (nodes.get(i).getOrder_date()+" = تاریخ خرید");
                item1 = new TreeItem<String> (nodes.get(i).getDelivery_date()+" = تاریخ تحویل");
                item2 = new TreeItem<String> (nodes.get(i).getShop_bransh_id()+" = شعبه");
                item3 = new TreeItem<String> (nodes.get(i).getTransport_cost()+" = هزینه حمل و نقل");
                item4 = new TreeItem<String> (nodes.get(i).getCu_name()+" = نام مشتری ");
                item5 = new TreeItem<String> (nodes.get(i).getCu_ad_city()+" = شهر");
                item7 = new TreeItem<String> (nodes.get(i).getCu_ad_building_num()+" = پلاک");
                item8 = new TreeItem<String> (nodes.get(i).getCu_ad_street()+" = خیابان");


            item.getChildren().add(item1);
            item.getChildren().add(item6);
            item.getChildren().add(item2);
            item.getChildren().add(item3);
            item.getChildren().add(item4);
            item.getChildren().add(item5);
            item.getChildren().add(item7);
            item.getChildren().add(item8);
            rootItem.getChildren().add(item);


        }



        return rootItem;
    }

}
