package controllers;

import models.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.Procress6.*;
import views.html.SaveGrowth.formGrowth;
import views.html.SaveGrowth.listGrowth;
import views.html.editBreed;
import views.html.formBreed;
import views.html.form_RecipeFoods;
import views.html.plansVac.planVacs;
import views.html.showBreed;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static controllers.allController.data;
import static controllers.allController.main1;

public class saveKKFarmController extends Controller {




    //ข้อมูลบันทึกการให้อาหาร
    public static Form<SaveFoods> myForms = Form.form(SaveFoods.class);
    public static Form<Cows> cowForm = Form.form(Cows.class);
    public static Form<DetailRecip> myForm = Form.form(DetailRecip.class);
    public static SaveFoods data1;
    public static DetailRecip detailrecip;
    public static Recipe recip;
    public static Cows cow;
    public static CowandRecip cowandRecip;

    public static List<SaveFoods> SaveFoodList = new ArrayList<SaveFoods>();
    public static List<Cows> cowsList = new ArrayList<Cows>();
    public static List<Recipe> RecipeList = new ArrayList<Recipe>();
    public static List<breeds> blist = new ArrayList<breeds>();
    public static List<Inputfood> ifoodList = new ArrayList<Inputfood>();
    public static HashMap newmap = new HashMap<String,String>();
    public static HashMap newmap1 = new HashMap<String,String>();

    public static Result showsaveFood(){
        SaveFoodList = SaveFoods.list();
        myForms = Form.form(SaveFoods.class);
        cowsList = Cows.showlist();
        RecipeList = Recipe.list();
        ifoodList = Inputfood.showlist();
        return savefoodseach();
    }
    public static int amountcow=0;
    public static String cowdata;
public static Result addcowfoods(String id){
    cowdata=id;
    session("savefoods",id.toString());
    session("detail",id.toString());
    myForms = Form.form(SaveFoods.class);
    recip = Recipe.finder.byId(id);

    cowsList = Cows.showlist();
    SaveFoodList = SaveFoods.finder.where().eq("recip_name_recip",id).findList();
    data1 = SaveFoods.finder.byId(id);
        return main1(addcow.render(myForms,recip,cowsList,SaveFoodList,data1));
}
public static Result showcowfoods(){

   SaveFoodList=SaveFoods.list();
        return main1(viewSavefood.render(SaveFoodList));
}

    public static Result input() {
        cowsList=Cows.showlist();
        DynamicForm newMapping = Form.form().bindFromRequest();
        int i = 0;
        while (newMapping.field("id[" + i + "]") != null) {
            String id = newMapping.field("id[" + i + "]").value();
            String value = newMapping.field("learnId[" + i + "]").value();
            newmap.put(id, value);
            newmap1.put(i,id);

            i++;
            if (i == cowsList.size()) {
                break;
            }
        }
        int y;

        Recipe dataC = Recipe.finder.byId(session("savefoods"));
        if (dataC==null){
            flash("Flashfood","ไม่พบข้อมูล");


        }else {
            for (y = 0; y < cowsList.size(); y++) {
                String id = (String) newmap1.get(y);
                String val = (String) newmap.get(id);

//                    int idd;
//                    String sId;
//                    SaveFoodList = SaveFoods.list();
//                    if (SaveFoodList == null) {
//                        sId = "SF-000001";
//                    } else {
//                        int numlist = SaveFoodList.size();
//                        idd = numlist + 1;
//                        if (idd < 10) {
//                            sId = "SF-00000" + idd;
//                        } else if (idd < 100) {
//                            sId = "SF-0000" + idd;
//                        } else if (idd < 1000) {
//                            sId = "SF-000" + idd;
//                        } else if (idd < 10000) {
//                            sId = "SF-00" + idd;
//                        } else if (idd < 100000) {
//                            sId = "SF-0" + idd;
//                        } else {
//                            sId = "SF-" + idd;
//                        }
//                    }

                    Cows datacow= Cows.finder.byId(id);



                    if (val != null){
                        data1 = new SaveFoods("",new Date(),datacow,dataC,null,dataC.getAmountSavefood());


                        SaveFoods chk1 = SaveFoods.findFood(data1.getCowdata().getCow_id());
                       if(chk1 !=null){
                           flash("Errortoadd", "ข้อมูลมีการซ้ำกันในระบบ");
                           return main1(addcow.render(myForms,recip,cowsList,SaveFoodList,data1));
                       }else {
//CowandRecip cow = new CowandRecip();
//                           SaveFoodList = SaveFoods.list();
//                            int num;
//                           int num2=SaveFoodList.size();
//                           int num3;
//                           num = num2+1;
//                           int amount =data1.getRecip().getAmountCow();
//
//
//num3=amount;
//if(num3<=num){
//    flash("Errortoadd", "โคเต็มสูตร");
//
//    return main1(addcow.render(myForms,recip,cowsList,SaveFoodList,data1));
//
//}else{

    SaveFoods.add(data1);
    datacow.setEat_recip(dataC.getNameRecip());
//    datacow.setColor (String.valueOf(num3));
    Cows.edit(datacow);

}


                       }
                    }
//                    else{
//                        if(newMapping.field("learnId[" + i + "]").value()!=null){
//
//                        }
//                    }


            }
//        }

                return savefoodseach();
    }

    public static Result edit(String id) {


        play.data.Form<SaveFoods> newformpro = myForms.bindFromRequest();
        data1 = SaveFoods.finder.byId(id);
        if (data1 == null) {
            return showsaveFood();
        } else {
            myForms = play.data.Form.form(SaveFoods.class).fill(data1);
            return main1(editsavefood.render(myForms,cowsList));
        }
    }

    public static Result update() {
        play.data.Form<SaveFoods> formupdate = myForms.bindFromRequest();
        if (formupdate.hasErrors()) {
            return main1(editsavefood.render(formupdate,cowsList));
        } else {
            data1=formupdate.get();

            SaveFoods.edit(data1);
            return showsaveFood();
        }
    }

    //ลบ
    public static Result delete(String id) {
        play.data.Form<SaveFoods> newformpro = myForms.bindFromRequest();
        data1 = SaveFoods.finder.byId(id);
        if (data1 != null) {
            data1.delete();
        }
        return showsaveFood();

    }



    public static Result delectClear(String id) {
        data1 = SaveFoods.finder.byId(id);
//        cow = Cows.finder.byId(id);
        if (data1 != null) {
            data1.delete();



        }
        return showsaveFood();
//        return ok(editStatus.render(cowForm,blist));
    }

    public static String cowIdd;
    public static Result editClear(String id){
        cowIdd=id;
        Form<Cows> newformpro = cowForm.bindFromRequest();
        cow = Cows.finder.byId(id);

        if (cow == null) {
            return updateClear();
        } else {
           blist = breeds.showlist();
            cowForm = Form.form(Cows.class).fill(cow);
            return  updateClear();

        }

    }
    public static Result updateClear() {
        Form<Cows> myFormupdate = cowForm.bindFromRequest();

        if (myForm.hasErrors()) {
            return showsaveFood();
        } else {
            cow = myFormupdate.get();
            cow.setCow_id(cowIdd);
            cow.setEat_recip("1");
            Cows.edit(cow);
            return delectClear(cowIdd);
        }
    }

    public static Result savefoodseach(){
        SaveFoodList = SaveFoods.finder.where().orderBy().desc("datesavefood").findList();//title คือชื่อฟิลด์ที่ต้องการค้นหา
        myForms = Form.form(SaveFoods.class);
        cowsList = Cows.showlist();
        RecipeList = Recipe.list();
        ifoodList = Inputfood.showlist();

        return main1(saveFoods.render(SaveFoodList,myForms,cowsList,RecipeList,ifoodList));
    }
    public static Result detailsSavefood(String id) {

        recip =Recipe.finder.byId(id);

        SaveFoodList = SaveFoods.finder.where().eq("recip_name_recip",id).findList();
        return ok(detailSavefood.render(recip,SaveFoodList));
    }


    //บันทึกการเจริญเติบโต
    public static Form<Cows>growthForm=Form.form(Cows.class);
    public static List<Cows>cowwList=new ArrayList<Cows>();
    public static List<breeds>breedList=new ArrayList<breeds>();
    public static Cows cowgrowth;
public static Result listgrowth(){
    cowwList=Cows.showlist();
    return main1(listGrowth.render(cowwList));
}
    public static Result saveGrowth(){
        growthForm=Form.form(Cows.class);

        cowwList=Cows.showlist();
        breedList=breeds.showlist();

        return main1(formGrowth.render(growthForm,cowgrowth,cowwList,breedList));
    }
    public static String cowId;
    public static Result editstutas(String id){
        cowId=id;
        Form<Cows> newformpro = growthForm.bindFromRequest();
        cowgrowth = Cows.finder.byId(id);

        if (cowgrowth == null) {
            return listgrowth();
        } else {
            breedList = breeds.showlist();
            growthForm = Form.form(Cows.class).fill(cowgrowth);
            return saveGrowth();
        }

    }

 public static Result inputGrowth() {

     Form<Cows> myForm = growthForm.bindFromRequest();

     if (myForm.hasErrors()) {
         return saveGrowth();
     } else {
         cowgrowth = myForm.get();
//         cowgrowth.setStatus("ป่วย");
         cowgrowth.setCow_id(cowId);
         Cows.edit(cowgrowth);


         return saveGrowth();
     }

 }
}

