package controllers;

import models.*;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.recip.checkBill;
import views.html.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controllers.allController.main1;

public class castController extends Controller {
    public static List<OrdersDetail> basketList = new ArrayList<OrdersDetail>();
    public static List<Inputfood> Inputfoodlist = new ArrayList<Inputfood>();

    public static Inputfood dataFood;
    public static Result basketSell(){
        List<Basket> basketList = (List<Basket>) Cache.get("basketList");
        return ok(views.html.recip.basketList.render(basketList));
    }

    public static play.mvc.Result addOrder(String id){
        List<Basket> basketList = new ArrayList<Basket>();

        boolean found = false;
        if (Cache.get("basketList") != null){
            basketList.addAll((List<Basket>) Cache.get("basketList"));
            for (int
                 i =0; i<basketList.size(); i++){
                if (basketList.get(i).getProduct().getId_ifoods().equals(id)){
                    int amount = basketList.get(i).getAmount();
                    basketList.get(i).setAmount(amount +1);
                    found = true;
                    break;
                }
            }
        }
        if (found == false){
            dataFood = Inputfood.finder.byId(id);
            basketList.add(new Basket(dataFood,1));
        }

        Cache.set("basketList",basketList);
        return redirect("/basketSell");
    }

    public static play.mvc.Result removeItem(String id){
        List<Basket> basketList = new ArrayList<Basket>();
        if (Cache.get("basketList") != null){
            basketList.addAll((List<Basket>) Cache.get("basketList"));
            for (int i = 0; i < basketList.size(); i++){
                if (basketList.get(i).getProduct().getId_ifoods().equals(id)){
                    basketList.remove(i);
                    break;
                }
            }
        }
        Cache.set("basketList",basketList);
        return redirect("/basketSell");
    }

    public static UserKK ulogin;
    public static play.mvc.Result checkBill(){
        ulogin = UserKK.finder.byId(session().get("usr"));
        List<Basket> basketList =new ArrayList<Basket>();
        if (Cache.get("basketList") != null){
            basketList = (List<Basket>) Cache.get("basketList");
        }
        return main1(checkBill.render(basketList, ulogin));
    }
    public static List<Inputfood> productList = new ArrayList<Inputfood>();
    public static  play.mvc.Result saveBill(){
        List<Basket> basketList = new ArrayList<Basket>();
        if (Cache.get("basketList") != null){
            Orders orders = new Orders();
            UserKK ulogin = UserKK.finder.byId(session().get("usr"));
            orders.setDate(new Date());
            orders.setUlogin(ulogin);
            orders.setStatus("");
            Orders.insert(orders);
            basketList = (List<Basket>) Cache.get("basketList");
            for (int i = 0; i< basketList.size(); i++){
                OrdersDetail ordersDetail = new OrdersDetail();
                ordersDetail.setOrders(orders);
                ordersDetail.setProduct(basketList.get(i).getProduct());
                ordersDetail.setAmount(basketList.get(i).getAmount());
                OrdersDetail.insert(ordersDetail);
            }
        }
        productList = Inputfood.showlist();
        Cache.remove("basketList");
        return basketSell();
    }

}
