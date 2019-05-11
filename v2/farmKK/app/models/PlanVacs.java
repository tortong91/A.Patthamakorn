package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tbPlanVac")
public class PlanVacs extends Model {
    @Id
    private String id;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    private  Date pvacdate;
    private String pdate,pdate1;

@ManyToOne
private Vacs vac;
@ManyToOne
private Cows addcowpv;
    @OneToMany(mappedBy = "addcow", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<Addcow_planvacs> addcvac = new ArrayList<Addcow_planvacs>();



    public PlanVacs() {
    }

    public PlanVacs(String id, Date pvacdate, String pdate, String pdate1, Vacs vac, Cows addcowpv) {
        this.id = id;
        this.pvacdate = pvacdate;
        this.pdate = pdate;
        this.pdate1 = pdate1;
        this.vac = vac;
        this.addcowpv = addcowpv;
    }

    public Cows getAddcowpv() {
        return addcowpv;
    }

    public void setAddcowpv(Cows addcowpv) {
        this.addcowpv = addcowpv;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPvacdate() {
        return pvacdate;
    }

    public void setPvacdate(Date pvacdate) {
        this.pvacdate = pvacdate;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getPdate1() {
        return pdate1;
    }

    public void setPdate1(String pdate1) {
        this.pdate1 = pdate1;
    }



    public Vacs getVac() {
        return vac;
    }

    public void setVac(Vacs vac) {
        this.vac = vac;
    }

    public void setAddcvac(List<Addcow_planvacs> addcvac) {
        this.addcvac = addcvac;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,PlanVacs> finder=new Finder<String, PlanVacs>(String.class,PlanVacs.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<PlanVacs> showlist(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(PlanVacs data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(PlanVacs data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(PlanVacs data){
        data.delete();
    }

    public static PlanVacs findCow (String id ){
        return finder.where().eq("addcowpv.cow_id",id).findUnique();
    }
    public static PlanVacs findvacs (String id ){
        return finder.where().eq("vac.id",id).findUnique();
    }

}
