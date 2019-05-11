package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Random;


public class CowandRecip extends Model {

    private String id;
    private int amount,amountreccip;
 @ManyToOne
    private SaveFoods saveFoods;



    public CowandRecip() {
        setId();
    }

    public CowandRecip(String id, int amount, int amountreccip, Cows cowRecip, Recipe recipCow, SaveFoods saveFoods) {
        setId();
        this.amount = amount;
        this.amountreccip = amountreccip;

        this.saveFoods = saveFoods;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        int i ;
        Random random = new Random();
        i = random.nextInt(1000000)+1;
        id = "cr-" + Integer.toString(i);
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountreccip() {
        return amountreccip;
    }

    public void setAmountreccip(int amountreccip) {
        this.amountreccip = amountreccip;
    }

    public SaveFoods getSaveFoods() {
        return saveFoods;
    }

    public void setSaveFoods(SaveFoods saveFoods) {
        this.saveFoods = saveFoods;
    }


    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Model.Finder<String,CowandRecip> finder =new Model.Finder<String, CowandRecip>(String.class,CowandRecip.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<CowandRecip> list(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(CowandRecip data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(CowandRecip data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(CowandRecip data){
        data.delete();
    }

}
