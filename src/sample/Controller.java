package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.models.OrderModel;

import javax.swing.text.html.ListView;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class Controller {
    private SqliteHelper db;

    @FXML
    TreeView<String> orderTreeView;

    @FXML
    TextField branch ;
    @FXML
    TextField transport ;
    @FXML
    TextField name;
    @FXML
    TextField city;
    @FXML
    TextField bulding_number;
    @FXML
    TextField street;
    @FXML
    DatePicker delivery;
    @FXML
    Button addaddress;



    @FXML
    public void initialize()
    {
        db = new SqliteHelper();
        orderTreeView.setRoot(createTree(db.getOrders()));
        addaddress.setOnAction(e -> addorder());

    }

    private void addorder() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String datedeliver = delivery.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        OrderModel orderModel=new OrderModel();
        orderModel.setCu_ad_street(street.getText());
        orderModel.setCu_ad_city(city.getText());
        orderModel.setTransport_cost(transport.getText());
        orderModel.setCu_ad_building_num(Integer.parseInt(bulding_number.getText()));
        orderModel.setOrder_date(date);
        orderModel.setDelivery_date(datedeliver);
        orderModel.setCu_name(name.getText());
        orderModel.setShop_bransh_id(Integer.parseInt(branch.getText()));

        db.insertOrder(orderModel);
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
