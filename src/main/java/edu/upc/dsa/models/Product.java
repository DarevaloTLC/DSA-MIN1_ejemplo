package edu.upc.dsa.models;

public class Product {
    private String ID;
    private String name;
    private Double price;
    public Integer sales;
    public String getID(){
        return this.ID;
    }
    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
    public Integer getSales(){
        return this.sales;
    }
    public void setSales(Integer i){
        this.sales = i;
    }
    public Product(){

    }
    public Product(String i, String n, Double p){
        this.ID = i;
        this.name = n;
        this.price = p;
        this.sales = 0;
    }
    public void incrementSales(Integer quantity){
        this.sales+=quantity;
    }

}
