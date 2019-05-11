package controllers;

import models.*;
import play.cache.Cache;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import views.html.Recipe.check;
import views.html.Recipe.form;
import views.html.Recipe.show;
import views.html.form_RecipeFoods;
import views.html.oop;
import views.html.show_Recipe;
import views.html.testValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static controllers.allController.main1;

public class RecipController extends Controller {

    //Join Table
    public static List<Inputfood> foodsAnimalList = new ArrayList<Inputfood>();
    public static List<Recipe> RecipeList = new ArrayList<Recipe>();
    public static List<Foods> foodList = new ArrayList<Foods>();
    public static List<DetailRecip> dList = new ArrayList<DetailRecip>();
    public static Form<Recipe> picture = Form.form(Recipe.class);
    public static Form<Recipe> recipeForm = Form.form(Recipe.class);
    public static Recipe data;
    public static DetailRecip data1;
    public static CowandRecip cowandeecip;
    public static List<DetailRecip> detailRecipList = new ArrayList<DetailRecip>();
    public static Inputfood inputfood;
    public static HashMap newmap = new HashMap<String,String>();


    public static play.mvc.Result showRecipe() {
//        if(session().get("name")==null){
//            flash("chkoop", "ท่านไม่มีสิทธิ์ใช้งานในส่วนที่เลือก");
//            return main1(oop.render());
//        }else {
//            String ustatus = session().get("position");
//            if (ustatus.equals("Admin")||ustatus.equals("Admin")) {
//                flash("chkoop", "ท่านไม่มีสิทธิ์ใช้งานในส่วนที่เลือก");
//                return main1(oop.render());
//            }
//        }

        RecipeList = Recipe.list();
        recipeForm = Form.form(Recipe.class);
        foodsAnimalList = Inputfood.showlist();

        return main1(show_Recipe.render(RecipeList,recipeForm, foodsAnimalList));
    }

//    public static play.mvc.Result add() {
//        foodsAnimalList = Inputfood.showlist();
//        recipeForm = Form.form(Recipe.class);
//        return main1(form_RecipeFoods.render(recipeForm, foodsAnimalList));
//    }
    public static String amountFood;
    public static play.mvc.Result input() {
        foodsAnimalList = Inputfood.showlist();
        DynamicForm newMapping = Form.form().bindFromRequest();
        int i=0;
        while (newMapping.field("id["+i+"]")!= null){
            String id = newMapping.field("id["+i+"]").value() ;
            String value = newMapping.field("chk["+i+"]").value();
            newmap.put(id,value);
            i++;
            if  (i == foodsAnimalList.size() ){
             break;
            }

        }

        String nameRe = newMapping.get("rid");
        String rdio = newMapping.get("rdio");
        String rdiomount = newMapping.get("rdiomount");
        String rdiomount1 = newMapping.get("rdiomount1");
        int  amount  =  Integer.parseInt(newMapping.get("amount")) ;
        Recipe recipe = new Recipe();
        recipe.setNameRecip(nameRe);

        recipe.setDateRecipe(new Date());
        recipe.setDateTimeRecipe(rdio);
        recipe.setMount(rdiomount);
        recipe.setMount1(rdiomount1);
        Recipe.add(recipe);
//
//        cowandeecip.setRecipCow();
//        CowandRecip.add(cowandeecip);
        int y;
        for (y = 0 ; y<foodsAnimalList.size();y++){
            foodsAnimalList = Inputfood.showlist();
            String id = foodsAnimalList.get(y).getId_ifoods();
            amountFood = (String) newmap.get(id);
            if (amountFood == "") {
                amountFood = "0";
            }
            inputfood = Inputfood.finder.byId(id);
            if (amountFood != null){
                foodsAnimalList=Inputfood.showlist();
int amountTotalStork = inputfood.getTotal();
int numint=1;
                if(amountTotalStork < numint){
flash("chk","สินค้าหมดสต๊อก กรุณาเพิ่มสินค้าในสต๊อกด้วย");
return showRecipe();
                }else{
                DetailRecip dataDetail = new DetailRecip();
                dataDetail.setAmount(Integer.parseInt(amountFood));
                inputfood = Inputfood.finder.byId(id);
                dataDetail.setInputfoods(inputfood);
                data = Recipe.finder.byId(nameRe);
                dataDetail.setRecipe(data);
                int total = inputfood.getTotal();
                int blanc  = total - Integer.parseInt(amountFood);
                inputfood.setTotal(blanc);
                inputfood.setId_ifoods(id);
                Inputfood.edit(inputfood);
                DetailRecip.add(dataDetail);
                }
            }
        }
        return showRecipe();
    }

    //ลบ
    public static play.mvc.Result delete(String id) {
        data = Recipe.finder.byId(id);
        if (data != null) {
            Recipe.delete(data);

        }
        RecipeList = Recipe.list();
        return showRecipe();
    }


    //เลือกอาหารมาจัดสูตร
    public static Inputfood food;
    public static Recipe recipes;
    public static RecipBasket dataRecip;
    public static List<Inputfood>  inputfoodsList  = new ArrayList<Inputfood>();
    public static List<RecipBasket>  RecipBasketList  = new ArrayList<RecipBasket>();
    public static List<Recipe>  ReciptList  = new ArrayList<Recipe>();
    public static Form<RecipBasket> rbForm = Form.form(RecipBasket.class);
    public static play.mvc.Result form() {
        rbForm = Form.form(RecipBasket.class);
        inputfoodsList=Inputfood.showlist();
        return ok(form.render(rbForm,inputfoodsList));
    }
    public static play.mvc.Result inputPost(){
        DynamicForm showForm = Form.form().bindFromRequest();
        String  name, date, time;


        name = showForm.get("name");
        date = showForm.get("date");
        time = showForm.get("time");

        dataRecip = new RecipBasket( name, date,time);
       /* Camerago.setId("id");
        Camerago.setNameproduct("nameproduct");
        Camerago.setTypeproduct("typeproduct");
        Camerago.setA("a");
        Camerago.setDetail("detail");
        Camerago.setPrice(price);
*/
       dataRecip.setName(name);
       dataRecip.setDate(date);
       dataRecip.setTime_recip(time);
        RecipBasketList.add(dataRecip);

        return ok(show.render(RecipBasketList));

    }

 public static play.mvc.Result check(){
 List<RecipBasket> basketList =new ArrayList<RecipBasket>();
  if (Cache.get("basketList") != null){
        basketList = (List<RecipBasket>) Cache.get("basketList");
    }
      return ok(check.render(basketList));
   }
    public static  play.mvc.Result saves(){
        List<RecipBasket> basketList = new ArrayList<RecipBasket>();


                Recipe ordersDetail = new Recipe();
String datafood;
String datafood1;
String datafood2;
datafood=dataRecip.getDate();
datafood1=dataRecip.getName();
datafood2=dataRecip.getTime_recip();
                ordersDetail.setNameRecip(datafood);
                ordersDetail.setDateTimeRecipe(datafood1);
                ordersDetail.setAmountSavefood(1);
                ordersDetail.setDateRecipe(new Date());
                ordersDetail.setMount(datafood2);
                ordersDetail.setMount1("2");

                Recipe.add(ordersDetail);
        RecipeList = Recipe.list();
Cache.remove("basketList");
        return showRecipe();
    }

}
