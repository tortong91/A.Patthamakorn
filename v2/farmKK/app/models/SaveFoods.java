package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "saveFood")
public class SaveFoods extends Model {
    @Id

    private String id;
    private int amount;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    private Date datesavefood;


@ManyToOne
private Cows cowdata;
@ManyToOne
private Recipe recip;
@ManyToOne
private Inputfood ifoode;
    @OneToMany(mappedBy = "savefood", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<DetailRecip> cowListdetail = new ArrayList<DetailRecip>();



    public SaveFoods() {
setId();
    }

    public SaveFoods(String id, Date datesavefood, Cows cowdata, Recipe recip,Inputfood ifoode,int amount) {

setId();
        this.datesavefood = datesavefood;
        this.cowdata = cowdata;
        this.recip = recip;

        this.ifoode=ifoode;
        this.amount=amount;

    }

    public String getId() {
        return id;
    }

    public void setId() {

        int i ;
        Random random = new Random();
        i = random.nextInt(1000000)+1;
        id = "SF-" + Integer.toString(i);
    }




    public Date getDatesavefood() {
        return datesavefood;
    }

    public void setDatesavefood(Date datesavefood) {
        this.datesavefood = datesavefood;
    }

    public Cows getCowdata() {
        return cowdata;
    }

    public void setCowdata(Cows cowdata) {
        this.cowdata = cowdata;
    }

    public Recipe getRecip() {
        return recip;
    }

    public void setRecip(Recipe recip) {
        this.recip = recip;
    }




    public Inputfood getIfoode() {
        return ifoode;
    }

    public void setIfoode(Inputfood ifoode) {
        this.ifoode = ifoode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCowListdetail(List<DetailRecip> cowListdetail) {
        this.cowListdetail = cowListdetail;
    }



    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Model.Finder<String,SaveFoods> finder=new Model.Finder<String, SaveFoods>(String.class,SaveFoods.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<SaveFoods> list(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(SaveFoods data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(SaveFoods data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(SaveFoods data){
        data.delete();
    }

    public static SaveFoods findFood (String id )
    {
        return finder.where().eq("cowdata.cow_id",id).findUnique();
    }
    public static SaveFoods find (String id )
    {
        return finder.where().eq("recip.amountCow",id).findUnique();
    }

}
