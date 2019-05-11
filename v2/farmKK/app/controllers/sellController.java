package controllers;

import models.Cows;
import models.SellCows;
import models.breeds;
import models.cooperatives;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.sellCows.formSellcows;
import views.html.sellCows.listSellcows;
import views.html.sellCows.showSellCowsSuccess;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controllers.allController.main1;

public class sellController extends Controller {
    public static Form<SellCows> myForms = Form.form(SellCows.class);
    public static Form<Cows> myFormscow = Form.form(Cows.class);
    public static SellCows sellcow;
    public static Cows cow;
    public static breeds breed;
    public static List<SellCows> sellcowList = new ArrayList<SellCows>();
    public static List<cooperatives> cooList = new ArrayList<cooperatives>();
    public static List<Cows> cowList = new ArrayList<Cows>();

    public static play.mvc.Result listSellcows() {
        sellcowList = SellCows.showlist();
        cowList = Cows.showlist();
        return main1(listSellcows.render(sellcowList, cowList));
    }

    public static play.mvc.Result saveSellcows() {
        myForms = Form.form(SellCows.class);

        sellcowList = SellCows.showlist();
        cowList = Cows.showlist();
        cooList = cooperatives.showlist();


        return main1(formSellcows.render(myForms, sellcowList, cowList,cooList));
    }

    public static String cowId;
    public static String cowName;

    public static play.mvc.Result editsellcow(String id) {
        cowId = id;
        cowName=cowId;
        Form<Cows> newformpro = myFormscow.bindFromRequest();
//        sellcow = SellCows.finder.byId(id);
        cow = Cows.finder.byId(id);
        breed = breeds.finder.byId(id);

        if (cow == null) {
            return saveSellcows();
        } else {

            cowList = Cows.showlist();
            myFormscow = Form.form(Cows.class).fill(cow);
            return saveSellcows();
        }

    }

    public static play.mvc.Result inputsellcows() {

        Form<SellCows> myForm = myForms.bindFromRequest();
        Form<Cows> myformcow = myFormscow.bindFromRequest();

        if (myForm.hasErrors()) {
            return saveSellcows();
        } else {
            sellcow = myForm.get();
            cow = myformcow.get();
//         cowgrowth.setStatus("ป่วย");

            cow.setCow_id(cowId);
            cow.setStatus("ขายแล้ว");
            sellcow.setStatus("ขายแล้ว");
            sellcow.setDate(new Date());
            sellcow.setName(cowName);
            Cows.edit(cow);
            SellCows.add(sellcow);


            return deletesellcows(cowId);
        }

    }

    public static play.mvc.Result deletesellcows(String id) {
        cow = Cows.finder.byId(id);
        if (cow != null) {
            File temp = new File("public/images/Piccow/" + cow.getPicture());
            temp.delete();

            cow.delete();
        }
//        return detailSellcows(cowId);
        return listSellcows();
    }



    public static Result detailSellcows(String id) {
        int i;
        for (i = 0; i < sellcowList.size(); i++) {
            if (sellcowList.get(i).getId().equals(id)) {
                break;
            }
        }
        return ok(showSellCowsSuccess.render(sellcowList.get(i)));
    }

}