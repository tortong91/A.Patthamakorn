package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="Detailsavefoods_cow")
public class Detailsavefoods_cow extends Model {
    private String id;
    private String name_cow;
@ManyToOne
private Cows SavefoodsCows;
    @ManyToOne
private Recipe Detailsavefoods;

    public Detailsavefoods_cow() {
    }

    public Detailsavefoods_cow(String id, String name_cow) {
        this.id = id;
        this.name_cow = name_cow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_cow() {
        return name_cow;
    }

    public void setName_cow(String name_cow) {
        this.name_cow = name_cow;
    }

    public Cows getSavefoodsCows() {
        return SavefoodsCows;
    }

    public void setSavefoodsCows(Cows savefoodsCows) {
        SavefoodsCows = savefoodsCows;
    }

    public Recipe getDetailsavefoods() {
        return Detailsavefoods;
    }

    public void setDetailsavefoods(Recipe detailsavefoods) {
        Detailsavefoods = detailsavefoods;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Model.Finder<String,Detailsavefoods_cow> finder=new Model.Finder<String, Detailsavefoods_cow>(String.class,Detailsavefoods_cow.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<Detailsavefoods_cow> list(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(Detailsavefoods_cow data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(Detailsavefoods_cow data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(Detailsavefoods_cow data){
        data.delete();
    }


}
