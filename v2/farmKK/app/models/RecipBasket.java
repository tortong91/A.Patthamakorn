package models;

public class RecipBasket {
    private String  name;
    private String  date;
    private String  time_recip;


    public RecipBasket() {
    }


    public RecipBasket(String name, String date, String time_recip ) {
        this.name = name;
        this.date = date;
        this.time_recip = time_recip;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_recip() {
        return time_recip;
    }

    public void setTime_recip(String time_recip) {
        this.time_recip = time_recip;
    }


}
