package models;

import com.avaje.ebean.Expr;
import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tbaddcow")
public class Cows extends Model {
    @Id
    private String cow_id;
    private String sex,color,status,picture;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    private Date date;
    private int price,weight,age;
    private String eat_recip;
    @ManyToOne
    private breeds breed;

    @OneToMany(mappedBy = "cow", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<InputCow> inputList = new ArrayList<InputCow>();
    private List<Cows> cowList = new ArrayList<Cows>();
    private List<SaveFoods> saveFoods = new ArrayList<SaveFoods>();


    @OneToMany(mappedBy = "SavefoodsCows", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<Detailsavefoods_cow> SavefoodsCowsList = new ArrayList<Detailsavefoods_cow>();

    @OneToMany(mappedBy = "addcows", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<Addcow_planvacs> addcowplanvac = new ArrayList<Addcow_planvacs>();
@OneToMany(mappedBy = "addcowpv", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<PlanVacs> addcowpv = new ArrayList<PlanVacs>();


    @OneToMany(mappedBy = "sellcow", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<SellCows> sellcowList = new ArrayList<SellCows>();




    public Cows() {

    }

    public Cows(String cow_id,breeds breed,String status,int price, String sex, String color, Date date, int age, String picture, int weight,String eat_recip) {
        this.breed = breed;
        this.status=status;
        this.cow_id = cow_id;
        this.sex = sex;
        this.color = color;
        this.date = date;
        this.age = age;
        this.picture = picture;
        this.weight = weight;
     
        this.price=price;
        this.eat_recip=eat_recip;
    }

    public String getCow_id() {
        return cow_id;
    }

    public void setCow_id(String cow_id) {

        this.cow_id=cow_id  ;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getDate() {

//        if (date==null)
//            return "ในเร็วๆนี้";
//        else
//
//        return date;
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }



    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public breeds getBreed() {
        return breed;
    }

    public void setBreed(breeds breed) {
        this.breed = breed;
    }

    public String getStatus() {
//        if (status==null)
//            return "ยังไม่รับโคขุน ⌛";
//    else
//        return "ได้รับโคขุนแล้ว ✓";
return status;

    }

    public void setStatus(String status) {
        this.status= status;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

    public String getEat_recip() {
        return eat_recip;
    }

    public void setEat_recip(String eat_recip) {
        this.eat_recip = eat_recip;
    }

    public void setInputList(List<InputCow> inputList) {
        this.inputList = inputList;
    }

    public void setCowList(List<Cows> cowList) {
        this.cowList = cowList;
    }

    public void setSaveFoods(List<SaveFoods> saveFoods) {
        this.saveFoods = saveFoods;
    }



    public void setSavefoodsCowsList(List<Detailsavefoods_cow> savefoodsCowsList) {
        SavefoodsCowsList = savefoodsCowsList;
    }

    public void setAddcowplanvac(List<Addcow_planvacs> addcowplanvac) {
        this.addcowplanvac = addcowplanvac;
    }

    public void setAddcowpv(List<PlanVacs> addcowpv) {
        this.addcowpv = addcowpv;
    }

    public void setSellcowList(List<SellCows> sellcowList) {
        this.sellcowList = sellcowList;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,Cows> finder=new Finder<String, Cows>(String.class,Cows.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<Cows> showlist(){
        return finder.all();
    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(Cows data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit( Cows data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(Cows data){
        data.delete();
    }

    public static List<Cows> cowlList (String id){
        return finder.where().eq("status_sale",id).findList();
    }
    public static List<Cows> brandList (String id){
        return finder.where().eq("breed.id",id).findList();
    }
}
