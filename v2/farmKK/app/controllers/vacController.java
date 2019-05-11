package controllers;

import models.*;
import play.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;
import views.html.details.detailVac;

import views.html.plansVac.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static controllers.allController.main1;


public class vacController extends Controller {
    public static String picPath = Play.application().configuration().getString("path_Vac");


    //ข้อมูลวัคซีน
    public static Form<Vacs> myForms = Form.form(Vacs.class);
    public static Vacs data1;
    public static List<Vacs> vacList = new ArrayList<Vacs>();
//    public static Result numseach(){
//
//        vacList = Vacs.finder.where().orderBy().desc("id").findList();//title คือชื่อฟิลด์ที่ต้องการค้นหา
//        myForms = Form.form(Vacs.class);
//        return main1(showVac.render(vacList,myForms));
//    }
    public static Result showvac() {
        vacList = Vacs.showlist();
        myForms = Form.form(Vacs.class);
        return main1(showVac.render(vacList,myForms));
    }

//    public static Result add() {
//        myForms = Form.form(Vacs.class);
//        return main1(formVac.render(myForms));
//    }

    public static Result input() {
        Form<Vacs> myForm = myForms.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForm.hasErrors()) {
            flash("Error", "Please correct the from below.");
            return main1(formVac.render(myForm));
        } else {


            String typeid = "VC000001";
            int idwood = 0;
            int id_wood = 0;


            vacList = Vacs.showlist();


            for (int i = 0; i < vacList.size(); i++) {


                typeid = vacList.get(i).getId();


                typeid = typeid.substring(2, 8);


                idwood = Integer.parseInt(typeid);

                idwood = idwood + 100000;

                if (idwood > id_wood) {

                    id_wood = idwood;

                    typeid = Integer.toString(id_wood + 1);

                    typeid = "VC" + typeid;

                    typeid = typeid.substring(3, 8);

                    typeid = "Vc" + "0" + typeid;

                }


            }


            String a = myForm.get().getName();
            String b = myForm.get().getPr();
            String c = myForm.get().getPicture();

            data1 = new Vacs(typeid, a, b, c);



            if (picture != null) {
                contentType = picture.getContentType();
                File file = picture.getFile();
                fileName = picture.getFilename();
                if (contentType.startsWith("images")) {
                    return main1(showVac.render(vacList, myForms));
                }


                fileName = data1.getId() + fileName.substring(fileName.lastIndexOf("."));
                file.renameTo(new File(picPath, fileName));
                data1.setPicture(fileName);

                data1.add(data1);
                vacList = Vacs.showlist();


            }
            return showvac();
        }
    }



    public static Result edit(String id) {
        play.data.Form<Vacs> newformpro = myForms.bindFromRequest();
        data1 = Vacs.finder.byId(id);
        session("vacId",data1.getId());
        if (data1 == null) {
            return showvac();
        } else {
            myForms = play.data.Form.form(Vacs.class).fill(data1);
            return main1(editVac.render(myForms));
        }
    }

    public static Result update() {
        play.data.Form<Vacs> formupdate = myForms.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");

        if (formupdate.hasErrors()) {
            return main1(editVac.render(formupdate));
        } else {
            data1=formupdate.get();
            if (picture != null) {
                String fileName = picture.getFilename();
                String extension = fileName.substring(fileName.indexOf("."));
                String realName = data1.getId() + extension;
                File file = picture.getFile();
                File temp = new File("public/images/PicVac/" + realName);
                if (temp.exists()) {
                    temp.delete();
                }
                file.renameTo(new File("public/images/PicVac/" + realName));
                data1.setPicture(realName);
            }

            Vacs dataEdit = new Vacs(session("vacId"),formupdate.get().getPr(),formupdate.get().getName(),formupdate.get().getPicture());
            Vacs.edit(dataEdit);
            return showvac();
        }
    }

    //ลบ
    public static Result delete(String id) {
        data1 = Vacs.finder.byId(id);
        if (data1 != null) {
            File temp = new File("public/images/PicVac/" + data1.getPicture());
            temp.delete();

            data1.delete();
        }
        return showvac();
    }




    //แผนการฉีดวัคซีน

    //ข้อมูลวัคซีน
    public static Form<PlanVacs> myFormsplan = Form.form(PlanVacs.class);
    public static PlanVacs data2;
    public static Addcow_planvacs addcow_planvacs;
    public static Vacs vac;
    public static List<PlanVacs> PlanvacList = new ArrayList<PlanVacs>();
    public static List<Addcow_planvacs> addList = new ArrayList<Addcow_planvacs>();
    public static List<Vacs> vacList1 = new ArrayList<Vacs>();
    public static List<Cows> cowList1 = new ArrayList<Cows>();

    public static Result showPlanvac() {
        PlanvacList = PlanVacs.showlist();
        myFormsplan = Form.form(PlanVacs.class);
        vacList1 = Vacs.showlist();
        cowList1 = Cows.showlist();
        return main1(planVacs.render(PlanvacList,myFormsplan,vacList1,cowList1));
    }

    public static Result addPlanvac(String id) {
        session("planvacs",id.toString());
        myFormsplan = Form.form(PlanVacs.class);
        data2 = PlanVacs.finder.byId(id);
        cowList1 = Cows.showlist();
        return main1(addcowPVacs.render(myFormsplan,data2,cowList1));
    }

    public static Result inputPlan() {
        Form<PlanVacs> myForm1 = myFormsplan.bindFromRequest();

        if (myForm1.hasErrors()) {

            flash("error", "ข้อมูล โคขุน ที่ท่านเลือกซ้ำกัน");
            return main1(formplanVacs.render(myForm1, vacList1, cowList1));
        } else {


            data2 = myForm1.get();
            PlanVacs plan = PlanVacs.findvacs(data2.getVac().getId());

            if (plan != null) {
                flash("error", "ข้อมูล โคขุน ที่ท่านเลือกซ้ำกัน");
                return showPlanvac();
            } else {
                PlanvacList = PlanVacs.showlist();
                int numlist = PlanvacList.size();
                int id;
                String sId;
                id = numlist + 1;
                if (id < 10) {
                    sId = "SCH-00000" + id;
                } else if (id < 100) {
                    sId = "SCH-0000" + id;
                } else if (id < 1000) {
                    sId = "SCH-000" + id;
                } else if (id < 10000) {
                    sId = "SCH-00" + id;
                } else if (id < 100000) {
                    sId = "SCH-0" + id;
                } else {
                    sId = "SCH-" + id;
                }


                data2 = myForm1.get();
                data2.setId(sId);
                data2.setPvacdate(new Date());
                PlanVacs.add(data2);
                return showPlanvac();
            }
        }

        }


    public static Result editPlan(String id) {
        play.data.Form<PlanVacs> newformpro = myFormsplan.bindFromRequest();
        data2 = PlanVacs.finder.byId(id);
        session("vacplanId",data2.getId());
//
        if (data2 == null) {
            return showPlanvac();
        } else {
            myFormsplan = play.data.Form.form(PlanVacs.class).fill(data2);
            return numseach();
        }
    }

//    public static Result updatePlan() {
//        play.data.Form<PlanVacs> formupdate1 = myFormsplan.bindFromRequest();
//        if (formupdate1.hasErrors()) {
//            return numseach();
//        } else {
//          PlanVacs Edit = new PlanVacs(session("vacplanId"),formupdate1.get().getPvacdate(),formupdate1.get().getPdate(),formupdate1.get().getPdate1(),data2.getCowLst(),data2.getVac(),data2.getVacfinaly(),"0");
////          data2 = formupdate1.get();
//            PlanVacs.edit(Edit);
//            return showPlanvac();
//        }
//    }

    //ลบ
    public static Result deletePlan(String id) {
        data2 =PlanVacs.finder.byId(id);
        if (data2 != null) {
            data2.delete();
        }
        return showPlanvac();
    }
    public static Result numseach(){
        PlanvacList = PlanVacs.finder.where().orderBy().desc("pvacdate").findList();//title คือชื่อฟิลด์ที่ต้องการค้นหา
        myFormsplan = Form.form(PlanVacs.class);

        vacList1 = Vacs.showlist();
        cowList1 = Cows.showlist();
        return main1(planVacs.render(PlanvacList,myFormsplan,vacList1,cowList1));
    }



    public static HashMap newmap = new HashMap<String,String>();
    public static HashMap newmap1 = new HashMap<String,String>();

    public static Result inputcowPlan() {

        cowList1=Cows.showlist();
        DynamicForm newMapping = Form.form().bindFromRequest();
        int i = 0;
        while (newMapping.field("id[" + i + "]") != null) {
            String id = newMapping.field("id[" + i + "]").value();
            String value = newMapping.field("learnId[" + i + "]").value();
            newmap.put(id, value);
            newmap1.put(i,id);

            i++;
            if (i == cowList1.size()) {
                break;
            }
        }
        int y;
        PlanVacs dataC = PlanVacs.finder.byId(session("planvacs"));
        if (dataC==null){
            flash("Flashfood","ไม่พบข้อมูล");

        } else {


                for (y = 0; y < cowList1.size(); y++) {
                    String id = (String) newmap1.get(y);
                    String val = (String) newmap.get(id);

                    int idd;
                    String sId;
                    addList = Addcow_planvacs.showlist();
                    if (addList == null) {
                        sId = "SF-000001";
                    } else {
                        int numlist = addList.size();
                        idd = numlist + 1;
                        if (idd < 10) {
                            sId = "SF-00000" + idd;
                        } else if (idd < 100) {
                            sId = "SF-0000" + idd;
                        } else if (idd < 1000) {
                            sId = "SF-000" + idd;
                        } else if (idd < 10000) {
                            sId = "SF-00" + idd;
                        } else if (idd < 100000) {
                            sId = "SF-0" + idd;
                        } else {
                            sId = "SF-" + idd;
                        }
                    }

                    Cows datacow = Cows.finder.byId(id);
                    if (val != null) {
                        addcow_planvacs = new Addcow_planvacs(sId, datacow, dataC);
                        Addcow_planvacs.add(addcow_planvacs);
                    }


                }

            }
        return listPlanVacs();
        }





    public static Result detailplanvac(String id){
        vac =Vacs.finder.byId(id);
        vacList1=Vacs.showlist();
        addList=Addcow_planvacs.showlist();
        addList = Addcow_planvacs.finder.where().eq("addcow_id",id).findList();

        return ok(detail_planvac.render(vac,vacList1,addList));
    }
    public static Result listPlanVacs(){
        PlanvacList=PlanVacs.showlist();
        return main1(List_of_VacPlans.render(PlanvacList));
    }
//    vacList1 = Vacs.showlist();
//    cowList1 = Cows.showlist();
}
