package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="tbsellcow")
public class SellCows extends Model {
@Id
    private String id;
    private String name , status,sale_price;
    @Formats.DateTime(pattern="dd/MM/yyyy")
    private Date date=new Date();
    @ManyToOne
    private Cows sellcow;
   @ManyToOne
    private cooperatives coo;

    public SellCows() {
    }

    public SellCows(String name, Date date, Cows sellcow,String status,String sale_price,cooperatives coo) {
        this.name = name;
        this.date = date;
        this.sellcow = sellcow;
        this.status = status;
        this.sale_price = sale_price;
        this.coo = coo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Cows getSellcow() {
        return sellcow;
    }

    public void setSellcow(Cows sellcow) {
        this.sellcow = sellcow;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public cooperatives getCoo() {
        return coo;
    }

    public void setCoo(cooperatives coo) {
        this.coo = coo;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,SellCows> finder=new Finder<String, SellCows>(String.class,SellCows.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<SellCows> showlist(){
        return finder.all();
    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(SellCows data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit( SellCows data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(SellCows data){
        data.delete();
    }

}
