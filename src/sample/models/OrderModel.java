package sample.models;

public class OrderModel
{
   private Integer order_id;
   private String order_date;
   private String delivery_date;
   private Integer shop_bransh_id;
   private String transport_cost;
   private String cu_name;
   private String cu_ad_city;
   private String cu_ad_street;
   private Integer cu_ad_building_num;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Integer getShop_bransh_id() {
        return shop_bransh_id;
    }

    public void setShop_bransh_id(Integer shop_bransh_id) {
        this.shop_bransh_id = shop_bransh_id;
    }

    public String getTransport_cost() {
        return transport_cost;
    }

    public void setTransport_cost(String transport_cost) {
        this.transport_cost = transport_cost;
    }

    public String getCu_name() {
        return cu_name;
    }

    public void setCu_name(String cu_name) {
        this.cu_name = cu_name;
    }

    public String getCu_ad_city() {
        return cu_ad_city;
    }

    public void setCu_ad_city(String cu_ad_city) {
        this.cu_ad_city = cu_ad_city;
    }

    public String getCu_ad_street() {
        return cu_ad_street;
    }

    public void setCu_ad_street(String cu_ad_street) {
        this.cu_ad_street = cu_ad_street;
    }

    public Integer getCu_ad_building_num() {
        return cu_ad_building_num;
    }

    public void setCu_ad_building_num(Integer cu_ad_building_num) {
        this.cu_ad_building_num = cu_ad_building_num;
    }
}