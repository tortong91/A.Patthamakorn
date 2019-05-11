package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Addcow_planvacs")
public class Addcow_planvacs extends Model {
    @Id
    private String id;
     @ManyToOne
    private Cows addcows;
 @ManyToOne
    private PlanVacs addcow;


    public Addcow_planvacs() {
    }

    public Addcow_planvacs(String id, Cows addcows,PlanVacs addcow) {
        this.id = id;
        this.addcows = addcows;
        this.addcow = addcow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cows getAddcows() {
        return addcows;
    }

    public PlanVacs getAddcow() {
        return addcow;
    }

    public void setAddcow(PlanVacs addcow) {
        this.addcow = addcow;
    }



    public void setAddcows(Cows addcows) {
        this.addcows = addcows;
    }
    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,Addcow_planvacs> finder=new Finder<String, Addcow_planvacs>(String.class,Addcow_planvacs.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<Addcow_planvacs> showlist(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(Addcow_planvacs data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(Addcow_planvacs data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(Addcow_planvacs data){
        data.delete();
    }

}
