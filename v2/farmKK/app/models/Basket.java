package models;

/**
 * Created by Admin on 29/10/2561.
 */
public class Basket {
    private Inputfood data;
    private int amount;

    public Basket() {
    }

    public Basket(Inputfood data, int amount) {
        this.data = data;
        this.amount = amount;
    }

    public Inputfood getProduct() {
        return data;
    }

    public void setProduct(Inputfood data) {
        this.data = data;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}

