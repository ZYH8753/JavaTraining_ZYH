package io.kimmking.dubbo.demo.api;

public class Order implements java.io.Serializable {
    private long dollarId;

    private long rmbId;

    private int dollarPrice;

    private int rmbPrice;

    public Order() {}

    public Order(long dollarId, long rmbId, int dollarPrice, int rmbPrice) {
        this.dollarId = dollarId;
        this.rmbId = rmbId;
        this.dollarPrice = dollarPrice;
        this.rmbPrice = rmbPrice;
    }

    public long getDollarId() {
        return dollarId;
    }

    public void setDollarId(long dollarId) {
        this.dollarId = dollarId;
    }

    public long getRmbId() {
        return rmbId;
    }

    public void setRmbId(long rmbId) {
        this.rmbId = rmbId;
    }

    public int getDollarPrice() {
        return dollarPrice;
    }

    public void setDollarPrice(int dollarPrice) {
        this.dollarPrice = dollarPrice;
    }

    public int getRmbPrice() {
        return rmbPrice;
    }

    public void setRmbPrice(int rmbPrice) {
        this.rmbPrice = rmbPrice;
    }
}
